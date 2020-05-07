package com.michaelhitzker.server;

import com.michaelhitzker.common.TelemetryDataListResource;
import com.michaelhitzker.common.TelemetryDataResource;
import com.michaelhitzker.data.CarsType;
import com.michaelhitzker.data.TelemetryDataType;
import com.michaelhitzker.data.TelemtryDataListType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class TelemetryDataListServerResource extends ServerResource implements TelemetryDataListResource {
    private CarTelemetryManager manager = CarTelemetryManager.getInstance();
    private int carId;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        carId = Integer.parseInt(getAttribute("carId"));
    }

    @Override
    public TelemtryDataListType represent() {
        return manager.getTelemetryData(carId);
    }

    @Override
    public TelemetryDataType addTelemetryData(TelemetryDataType telemetryDataType) {
        return manager.addTelemetryData(carId, telemetryDataType);
    }
}
