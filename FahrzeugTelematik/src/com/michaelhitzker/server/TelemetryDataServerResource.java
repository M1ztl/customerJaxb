package com.michaelhitzker.server;

import com.michaelhitzker.common.TelemetryDataResource;
import com.michaelhitzker.data.TelemetryDataType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class TelemetryDataServerResource extends ServerResource implements TelemetryDataResource {
    private CarTelemetryManager manager = CarTelemetryManager.getInstance();
    private int carId;
    private int telemetryId;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        carId = Integer.parseInt(getAttribute("carId"));
        telemetryId = Integer.parseInt(getAttribute("carTelemetryId"));

    }

    @Override
    public TelemetryDataType represent() {
        return manager.getSingleTelemetry(carId, telemetryId);
    }
}
