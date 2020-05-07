package com.michaelhitzker.common;

import com.michaelhitzker.data.ChargingparkType;
import com.michaelhitzker.data.ChargingparksType;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface ChargingParksResource {
    @Get("text/xml")
    ChargingparksType represent();

    @Post("text/xml")
    ChargingparkType addPark(ChargingparkType park);
}
