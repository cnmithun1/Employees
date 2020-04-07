package task.com.employees.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import task.com.employees.R;
import task.com.employees.activities.EmployeeDetailActivity;
import task.com.employees.model.Employee;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.MyViewHolder> {

    private List<Employee> dataSet;
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView employeeId, employeeName;
        RelativeLayout listLayout;

        public MyViewHolder(View view) {
            super(view);

            employeeId = (TextView) view.findViewById(R.id.employeeId);
            employeeName = (TextView) view.findViewById(R.id.employeeName);
            listLayout = (RelativeLayout) view.findViewById(R.id.listLayout);

        }
    }

    public EmployeesAdapter(List<Employee> data, Context context){
        this.dataSet = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public EmployeesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list,parent,false);
        return new EmployeesAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Employee e = dataSet.get(position);

        holder.employeeId.setText(e.getId());

        holder.employeeName.setText(e.getEmployee_name());

        holder.listLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, EmployeeDetailActivity.class);
                i.putExtra("employeeId", e.getId());
                i.putExtra("employeeName", e.getEmployee_name());
                i.putExtra("employeeAge", e.getEmployee_age());
                i.putExtra("employeeSalary", e.getEmployee_salary());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
