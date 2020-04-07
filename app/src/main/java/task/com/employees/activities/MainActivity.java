package task.com.employees.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import task.com.employees.adapter.EmployeesAdapter;
import task.com.employees.model.Employee;
import task.com.employees.R;
import task.com.employees.retrofit.RetrofitBuilder;
import task.com.employees.room.EmployeeViewModel;

public class MainActivity extends AppCompatActivity {

    private Context ctx;
    private RetrofitBuilder builder = new RetrofitBuilder();
    ProgressDialog progressDialog=null;
    public static List<Employee> employees = new ArrayList();
    private RecyclerView employeesRecyclerView;
    private EmployeesAdapter employeesAdapter;
    private EmployeeViewModel employeeViewModel;
    private Boolean offlineFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(ctx);

        initSetUp();

        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        if(employeeViewModel != null) {
            employeeViewModel.deleteAllEmployees(employees);
        }
    }

    private void initSetUp(){

        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        employeesRecyclerView = (RecyclerView) findViewById(R.id.employeesRecyclerView);

        if(!isNetworkConnected()){
            offlineFlag = true;
            employeeViewModel.insertEmployee(employees);
            loadEmployeeData();
        } else {

            loadData();
        }
    }

    private void loadData(){
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        retrofit2.Call<Employee.EmployeeResponse> call = builder.apiClient.getEmployees();

        call.enqueue(new Callback<Employee.EmployeeResponse>() {
            @Override
            public void onResponse(Call<Employee.EmployeeResponse> call, Response<Employee.EmployeeResponse> response) {
                String errorMessage = response.body().getStatus();
                if (!errorMessage.equals("success")) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                } else {
                    employees = new ArrayList<>();
                    if(response.body().getData()!=null){
                        for(int i=0; i<response.body().getData().size(); i++){
                            employees.add(response.body().getData().get(i));
                        }
                    }
                    setUpEmployeeAdapter();
                }
            }

            @Override
            public void onFailure(Call<Employee.EmployeeResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void setUpEmployeeAdapter(){

        progressDialog.dismiss();

        employeesAdapter = new EmployeesAdapter(employees, ctx);
        employeesRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        employeesRecyclerView.setAdapter(employeesAdapter);

    }

    private void loadEmployeeData(){

        if(employeeViewModel != null) {
            employeeViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {

                @Override
                public void onChanged(List<Employee> employee) {
                    if(offlineFlag == true) {
                        employeeViewModel.deleteAllEmployees(employee);
                        if(employees.size() != 0) {
                            employee = employees;
                        }
                        employeeViewModel.insertEmployee(employee);
                        offlineFlag = false;
                        if (employee != null) {
                            employeesAdapter =
                                    new EmployeesAdapter(employee, ctx);
                            employeesRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                            employeesRecyclerView.setAdapter(employeesAdapter);
                            if(employees.size() == 0) {
                                for(int i=0 ;i<employee.size();i++) {
                                            employees.add(employee.get(i));
                                }
                                employees = employee;
                            }
                        } else {
                            Toast.makeText(ctx, "Employees null", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
            Toast.makeText(ctx, "Table is empty", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
