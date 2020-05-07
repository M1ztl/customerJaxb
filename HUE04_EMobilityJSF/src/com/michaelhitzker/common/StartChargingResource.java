package com.michaelhitzker.common;

import com.michaelhitzker.data.ChargeType;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface StartChargingResource {
    @Post("text/xml")
    ChargeType start();
}
