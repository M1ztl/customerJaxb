package com.michaelhitzker.client;

import com.michaelhitzker.common.CarResource;
import com.michaelhitzker.common.CarsResource;
import com.michaelhitzker.common.TelemetryDataListResource;
import com.michaelhitzker.common.TelemetryDataResource;
import com.michaelhitzker.data.CarType;
import com.michaelhitzker.data.CarsType;
import com.michaelhitzker.data.TelemetryDataType;
import com.michaelhitzker.data.TelemtryDataListType;
import org.restlet.resource.ClientResource;

public class CarTelemetryClient {
    private static final String CARS_PATH = "/cars";
    private static final String CAR_PATH = "/car/%1$d";
    private static final String TELEMETRY_LIST_PATH = "/car/%1$d/telemetry";
    private static final String TELEMETRY_PATH = "/car/%1$d/telemetry/%2$d";


    private CarsResource carsResource;
    private CarResource carResource;
    private TelemetryDataListResource telemetryDataListResource;
    private TelemetryDataResource telemetryDataResource;

    private String url;

    public CarTelemetryClient(String baseURL) {
        this.url = baseURL;
        this.carsResource = ClientResource.create(url + CARS_PATH, CarsResource.class);
    }

    public CarsType getCars() {
        return carsResource.represent();
    }

    public CarType getCar(int carId) {
        carResource = ClientResource.create(url + String.format(CAR_PATH, carId), CarResource.class);
        return carResource.represent();
    }

    public TelemtryDataListType getTelemetryDataList(int carId) {
        telemetryDataListResource = ClientResource.create(url + String.format(TELEMETRY_LIST_PATH, carId), TelemetryDataListResource.class);
        return telemetryDataListResource.represent();
    }

    public TelemetryDataType getTelemetryData(int carId, int telemetryId) {
        telemetryDataResource = ClientResource.create(url + String.format(TELEMETRY_PATH, carId, telemetryId), TelemetryDataResource.class);
        return telemetryDataResource.represent();
    }

    public CarType addCar(CarType carType) {
        return carsResource.addCar(carType);
    }

    public TelemetryDataType addTelemetryData(int carId, TelemetryDataType telemetryDataType) {
        telemetryDataListResource = ClientResource.create(url + String.format(TELEMETRY_LIST_PATH, carId), TelemetryDataListResource.class);
        return telemetryDataListResource.addTelemetryData(telemetryDataType);
    }

    public CarType updateCar(int carId, CarType carType) {
        carResource = ClientResource.create(url + String.format(CAR_PATH, carId), CarResource.class);
        return carResource.update(carType);
    }

    public CarType removeCar(int carId) {
        carResource = ClientResource.create(url + String.format(CAR_PATH, carId), CarResource.class);
        return carResource.remove();
    }

}
