package task.com.employees.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.List;

import task.com.employees.retrofit.ApiResponse;

@Entity(tableName = "EmployeeData", indices = @Index(value = {"employee_name"}, unique = true))
public class Employee {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String  employee_name;

    private String employee_salary, employee_age, profile_image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(String employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(String employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public class EmployeeResponse extends ApiResponse {

        private List<Employee> data;

        public List<Employee> getData() {
            return data;
        }
    }
}
