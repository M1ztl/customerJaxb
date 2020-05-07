package com.michaelhitzker.server;

import com.michaelhitzker.common.CarResource;
import com.michaelhitzker.common.TelemetryDataResource;
import com.michaelhitzker.data.CarType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class CarServerResource extends ServerResource implements CarResource {
    private CarTelemetryManager manager = CarTelemetryManager.getInstance();
    private int carId;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        carId = Integer.parseInt(getAttribute("carId"));
    }

    @Override
    public CarType represent() {
        return manager.getSingleCar(carId);
    }

    @Override
    public CarType update(CarType car) {
        return manager.updateCar(carId, car);
    }

    @Override
    public CarType remove() {
        return manager.removeCar(carId);
    }
}
