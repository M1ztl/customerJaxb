package com.michaelhitzker.server;

import com.michaelhitzker.data.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class CarTelemetryManager {
    private static CarTelemetryManager instance;
    private CarTelemetryType carTelemetry;

    private CarTelemetryManager() {
        this.readXML("data/data.xml");
    }

    public static CarTelemetryManager getInstance() {
        if (instance == null) {
            instance = new CarTelemetryManager();
        }
        return instance;
    }

    public CarsType getCars() {
        return carTelemetry.getCars();
    }

    public CarType getSingleCar(int carId) {
        List<CarType> cars = carTelemetry.getCars().getCar();
        return cars.stream().filter(car -> car.getIdCar() == carId).findAny().orElse(null);
    }

    public TelemtryDataListType getTelemetryData(int carId) {
        CarType car = getSingleCar(carId);
        if (car == null) {
            return null;
        }
        return car.getTelemtryDataList();
    }

    public TelemetryDataType getSingleTelemetry(int carId, int telemetryId) {
        TelemtryDataListType rawData = getTelemetryData(carId);
        if (rawData == null) {
            return null;
        }
        List<TelemetryDataType> data = rawData.getTelemetryData();
        return data.stream().filter(telemetry -> telemetry.getIdTelemetryData() == telemetryId).findAny().orElse(null);
    }

    public CarType updateCar(long carId, CarType car) {
        List<CarType> cars = getCars().getCar();
        int idInList = getIndexOfIdInList(carId, cars);
        if (idInList < 0) {
            return null;
        }
        cars.set(idInList, car);
        return car;
    }

    public CarType removeCar(int carId) {
        List<CarType> cars = getCars().getCar();
        int idInList = getIndexOfIdInList(carId, cars);
        if (idInList < 0) {
            return null;
        }
        return cars.remove(idInList);
    }

    public CarType addCar(CarType car) {
        CarType existing = getSingleCar(car.getIdCar());
        if (existing != null) {
            return existing;
        }
        getCars().getCar().add(car);
        return car;
    }

    public TelemetryDataType addTelemetryData(int carId, TelemetryDataType telemetryData) {
        TelemetryDataType existing = getSingleTelemetry(carId, telemetryData.getIdTelemetryData());
        if (existing != null) {
            return existing;
        }
        getTelemetryData(carId).getTelemetryData().add(telemetryData);
        return telemetryData;
    }

    private <T> int getIndexOfIdInList(long id, List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T obj = list.get(i);
            if (obj instanceof CarType && ((CarType) obj).getIdCar() == id) {
                return i;
            }
            if (obj instanceof TelemetryDataType && ((TelemetryDataType) obj).getIdTelemetryData() == id) {
                return i;
            }
        }
        return -1;
    }

    private void readXML(String path) {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(CarTelemetryType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            carTelemetry = (CarTelemetryType) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
