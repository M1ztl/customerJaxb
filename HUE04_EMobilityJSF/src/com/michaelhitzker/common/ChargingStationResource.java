package com.michaelhitzker.common;

import com.michaelhitzker.data.ChargingparkType;
import com.michaelhitzker.data.ChargingstationType;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface ChargingStationResource {
    @Get("text/xml")
    ChargingstationType represent();

    @Put("text/xml")
    ChargingstationType update(ChargingstationType station);

    @Delete
    ChargingstationType remove();
}
