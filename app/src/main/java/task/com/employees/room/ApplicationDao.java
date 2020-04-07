package task.com.employees.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import task.com.employees.model.Employee;

@Dao
public interface ApplicationDao {



    //insert employee data to employeeData table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(List<Employee> employee);

    //read employee data from employeeData table
    @Query("SELECT * from EmployeeData")
    LiveData<List<Employee>> getAllEmployees();

    //delete all data from employeeData table
    @Query("Delete from EmployeeData")
    void deleteAllEmployeeList();

    @Delete
    void deleteAllList(List<Employee> employees);
}
