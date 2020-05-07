package com.michaelhitzker.common;

import com.michaelhitzker.data.ChargingstationType;
import com.michaelhitzker.data.EmployeeType;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface EmployeeResource {
    @Get("text/xml")
    EmployeeType represent();

    @Put("text/xml")
    EmployeeType update(EmployeeType employeeType);

    @Delete
    EmployeeType remove();
}
