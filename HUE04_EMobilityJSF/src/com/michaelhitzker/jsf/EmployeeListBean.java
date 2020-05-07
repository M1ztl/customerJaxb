package com.michaelhitzker.jsf;

import com.michaelhitzker.client.EmobilityClient;
import com.michaelhitzker.data.EmployeeType;
import com.michaelhitzker.data.EmployeesType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;

@ManagedBean(name = "employeeList")
@SessionScoped
public class EmployeeListBean implements Serializable {
    private EmobilityClient client;
    private EmployeesType employees;
    private EmployeeType selectedEmployee;
    private java.text.SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


    public EmployeeListBean() {
        client = new EmobilityClient("http://127.0.0.1:8182/api");
        employees = client.getEmployees();
    }

    public EmployeeType getSelectedEmployee() {
        return this.selectedEmployee;
    }

    public void setSelectedEmployee(EmployeeType selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public void showDialog(EmployeeType selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public String getDateFromMillis(long millis){
        return sdf.format(millis);
    }

    public EmployeesType getEmployees() {
        return employees;
    }

}
