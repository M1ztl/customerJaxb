package com.michaelhitzker.server;

import com.michaelhitzker.common.EmployeesResource;
import com.michaelhitzker.data.EmployeeType;
import com.michaelhitzker.data.EmployeesType;
import org.restlet.resource.ServerResource;

public class EmployeesServerResource extends ServerResource implements EmployeesResource {
    private EmobilityManager manager = EmobilityManager.getInstance();

    @Override
    public EmployeesType represent() {
        return manager.getEmployees();
    }

    @Override
    public EmployeeType addEmployee(EmployeeType employee) {
        return manager.addEmployee(employee);
    }
}
