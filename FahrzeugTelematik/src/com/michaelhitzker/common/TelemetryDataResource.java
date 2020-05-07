package com.michaelhitzker.common;

import com.michaelhitzker.data.CarType;
import com.michaelhitzker.data.TelemetryDataType;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface TelemetryDataResource {
    @Get("text/xml")
    TelemetryDataType represent();
}
