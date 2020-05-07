package com.michaelhitzker.common;

import com.michaelhitzker.data.CarType;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface CarResource {
    @Get("text/xml")
    CarType represent();

    @Put("text/xml")
    CarType update(CarType car);

    @Delete
    CarType remove();
}
