package com.michaelhitzker.common;

import com.michaelhitzker.data.ChargingstationType;
import com.michaelhitzker.data.ChargingstationsType;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface ChargingStationsResource {
    @Get("text/xml")
    ChargingstationsType represent();

    @Post("text/xml")
    ChargingstationType addStation(ChargingstationType station);
}
