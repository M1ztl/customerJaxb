package com.michaelhitzker.common;

import com.michaelhitzker.data.ChargingparkType;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ChargingParkResource {
    @Get("text/xml")
    ChargingparkType represent();

    @Put("text/xml")
    ChargingparkType update(ChargingparkType park);

    @Delete
    ChargingparkType remove();
}
