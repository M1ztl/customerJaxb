package com.michaelhitzker.common;

import com.michaelhitzker.data.TelemetryDataType;
import com.michaelhitzker.data.TelemtryDataListType;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface TelemetryDataListResource {
    @Get("text/xml")
    TelemtryDataListType represent();

    @Post("text/xml")
    TelemetryDataType addTelemetryData(TelemetryDataType telemetryDataType);
}
