package com.michaelhitzker.common;

import com.michaelhitzker.data.CarType;
import com.michaelhitzker.data.CarsType;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface CarsResource {
    @Get("text/xml")
    CarsType represent();

    @Post("text/xml")
    CarType addCar(CarType car);

}
