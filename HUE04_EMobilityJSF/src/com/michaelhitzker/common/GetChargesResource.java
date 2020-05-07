package com.michaelhitzker.common;

import com.michaelhitzker.data.ChargeType;
import com.michaelhitzker.data.ChargesType;
import org.restlet.resource.Get;

import java.util.List;

public interface GetChargesResource {
    @Get("text/xml")
    public ChargesType getCharges();
}
