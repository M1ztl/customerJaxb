package com.michaelhitzker.server;

import com.michaelhitzker.common.EmployeeResource;
import com.michaelhitzker.data.EmployeeType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class EmployeeServerResource extends ServerResource implements EmployeeResource {
    private EmobilityManager manager = EmobilityManager.getInstance();
    private long empId;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        empId = Long.parseLong(getAttribute("empId"));
    }

    @Override
    public EmployeeType represent() {
        return manager.getSingleEmployee(empId);
    }

    @Override
    public EmployeeType update(EmployeeType employeeType) {
        return manager.updateEmployee(empId, employeeType);
    }

    @Override
    public EmployeeType remove() {
        return manager.removeEmployee(empId);
    }
}
