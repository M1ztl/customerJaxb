package com.michaelhitzker.common;

import com.michaelhitzker.data.EmployeeType;
import com.michaelhitzker.data.EmployeesType;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface EmployeesResource {
        @Get("text/xml")
        EmployeesType represent();

        @Post("text/xml")
        EmployeeType addEmployee(EmployeeType employee);
}
