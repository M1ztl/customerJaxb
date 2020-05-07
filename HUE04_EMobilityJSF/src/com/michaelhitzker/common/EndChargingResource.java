package com.michaelhitzker.common;

import com.michaelhitzker.data.ChargeType;
import org.restlet.resource.Post;

public interface EndChargingResource {
    @Post("text/xml")
    ChargeType end();
}
