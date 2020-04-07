package task.com.employees.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import task.com.employees.model.Employee;

public class EmployeeViewModel extends AndroidViewModel {


    private ApplicationDao applicationDao;
    private EmployeeDatabase employeeDatabase;
    private LiveData<List<Employee>> allEmployees;


    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeDatabase = EmployeeDatabase.getEmployeeDatabase(application);
        applicationDao = employeeDatabase.applicationDao();
        allEmployees = applicationDao.getAllEmployees();

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        allEmployees = applicationDao.getAllEmployees();

    }

    public void insertEmployee(List<Employee> employee) {
        new InsertAsyncTask(applicationDao).execute(employee);
    }

    public void deleteAllEmployees(List<Employee> employees) {
        new DeleteAllAsyncTask(applicationDao).execute((ArrayList<Employee>) employees);
    }


    public LiveData<List<Employee>> getAllEmployees() {
        return allEmployees;
    }

    private static class InsertAsyncTask extends AsyncTask<List<Employee>,Void,Void> {


        private  ApplicationDao applicationDao;

        public InsertAsyncTask(ApplicationDao applicationDao) {
            this.applicationDao = applicationDao;
        }

        @Override
        protected Void doInBackground(List<Employee>... lists) {
            applicationDao.insertData(lists[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<ArrayList<Employee>,Void,Void> {


        private  ApplicationDao applicationDao;

        public DeleteAllAsyncTask(ApplicationDao applicationDao) {
            this.applicationDao = applicationDao;
        }


        @Override
        protected Void doInBackground(ArrayList<Employee>... arrayLists) {
            applicationDao.deleteAllList(arrayLists[0]);
            return null;
        }
    }
}
