package task.com.employees.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import task.com.employees.R;

public class EmployeeDetailActivity extends AppCompatActivity {

    String eId, eName, eAge, eSalary;
    TextView employeeId, employeeName, employeeAge, employeeSalary, countDowntimer;
    public int counter = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_detail);

        eId = getIntent().getStringExtra("employeeId");
        eName = getIntent().getStringExtra("employeeName");
        eAge = getIntent().getStringExtra("employeeAge");
        eSalary = getIntent().getStringExtra("employeeSalary");

        initSetUp();
    }

    private void initSetUp(){

        employeeId = (TextView) findViewById(R.id.employeeId);
        employeeName = (TextView) findViewById(R.id.employeeName);
        employeeAge = (TextView) findViewById(R.id.employeeAge);
        employeeSalary = (TextView) findViewById(R.id.employeeSalary);

        countDowntimer = (TextView) findViewById(R.id.countDowntimer);

        employeeId.setText(eId);
        employeeName.setText(eName);
        employeeAge.setText(eAge);
        employeeSalary.setText(eSalary);

        new CountDownTimer(5000, 1000){
            @Override
            public void onTick(long l) {
                countDowntimer.setText(String.valueOf(counter));
                counter--;
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();

    }
}
