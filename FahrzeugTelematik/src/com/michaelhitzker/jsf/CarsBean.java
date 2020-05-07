package com.michaelhitzker.jsf;

import com.michaelhitzker.client.CarTelemetryClient;
import com.michaelhitzker.data.*;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import static org.restlet.engine.util.StringUtils.isNullOrEmpty;

@ManagedBean(name = "carsBean")
@SessionScoped
public class CarsBean implements Serializable {
    private CarTelemetryClient client;
    private CarsType cars;
    private CarType selectedCar;
    private int selectedId;

    public CarsBean() {
        client = new CarTelemetryClient("http://127.0.0.1:8182/api");
        this.loadCars();
    }

    public CarType getSelectedCar() {
        return this.selectedCar;
    }

    public void setSelectedCar(CarType selectedCar) {
        this.selectedCar = selectedCar;
    }

    public void showDialog(CarType car) {
        this.selectedCar = car;
        this.selectedId = cars.getCar().indexOf(car);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("editDialog");
        context.execute("PF(\"dlg\").show();");
    }

    public void removeCar(CarType car) {
        cars.getCar().remove(car);
        client.removeCar(car.getIdCar());
    }

    public CarsType getCars() {
        return cars;
    }

    private int findNewId() {
        int id = Integer.MIN_VALUE;
        for (CarType car : cars.getCar()) {
            id = Math.max(car.getIdCar(), id);
        }
        return id + 1;
    }

    public void startAdd() {
        ObjectFactory factory = new ObjectFactory();
        this.selectedCar = factory.createCarType();
        this.selectedCar.setTelemtryDataList(factory.createTelemtryDataListType());
        int id = findNewId();
        this.selectedCar.setIdCar(id);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("addDialog");
        context.execute("PF(\"addDlg\").show();");
    }

    public void handleAddClose() {
        if (!isEverythingValid()) {
            return;
        }
        //selectedBike.setId(this.findNewId());
        this.cars.getCar().add(selectedCar);
        client.addCar(selectedCar);
        this.loadCars();
    }

    public void handleClose(CloseEvent event) {
        if (!isEverythingValid()) {
            return;
        }

        this.cars.getCar().set(selectedId, selectedCar);
        this.client.updateCar(selectedCar.getIdCar(), selectedCar);
        this.loadCars();
    }

    private void loadCars() {
        cars = client.getCars();
        RequestContext.getCurrentInstance().update("carsTable");
    }

    private boolean isInvalid(CarType car) {
        return isNullOrEmpty(car.getName())
                || isNullOrEmpty(car.getType());
    }

    private void showMsg(String msgTxt) {
        FacesMessage msg = new FacesMessage("Error", msgTxt);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    private boolean isEverythingValid() {
        if (isInvalid(selectedCar)) {
            showMsg("Every field is required!");
            return false;
        }
        return true;
    }

}
