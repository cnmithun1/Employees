package task.com.employees.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import task.com.employees.model.Employee;

public interface ApiClient {

    @GET("/api/v1/employees")
    Call<Employee.EmployeeResponse> getEmployees();
}
