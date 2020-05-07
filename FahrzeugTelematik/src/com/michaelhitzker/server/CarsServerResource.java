package com.michaelhitzker.server;

import com.michaelhitzker.common.CarsResource;
import com.michaelhitzker.common.TelemetryDataResource;
import com.michaelhitzker.data.CarType;
import com.michaelhitzker.data.CarsType;
import org.restlet.resource.ServerResource;

public class CarsServerResource extends ServerResource implements CarsResource {
    private CarTelemetryManager manager = CarTelemetryManager.getInstance();

    @Override
    public CarsType represent() {
        return manager.getCars();
    }

    @Override
    public CarType addCar(CarType car) {
        return manager.addCar(car);
    }
}
