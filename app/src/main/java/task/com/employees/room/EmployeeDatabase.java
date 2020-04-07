package task.com.employees.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import task.com.employees.model.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract ApplicationDao applicationDao();

    private static EmployeeDatabase employeeDatabase;

    public static EmployeeDatabase getEmployeeDatabase(Context c){

        if(employeeDatabase == null) {
            synchronized (EmployeeDatabase.class){
                if (employeeDatabase == null) {
                    employeeDatabase = Room.databaseBuilder(c,EmployeeDatabase.class,"EmployeeDatabase").allowMainThreadQueries() .fallbackToDestructiveMigration().build();
                }
            }
        }

        return employeeDatabase;
    }


}
