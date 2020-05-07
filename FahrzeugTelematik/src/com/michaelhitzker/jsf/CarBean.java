package com.michaelhitzker.jsf;

import com.michaelhitzker.client.CarTelemetryClient;
import com.michaelhitzker.data.CarType;
import com.michaelhitzker.data.ObjectFactory;
import com.michaelhitzker.data.TelemetryDataType;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import static org.restlet.engine.util.StringUtils.isNullOrEmpty;

@ManagedBean(name = "carBean")
@RequestScoped
public class CarBean {
    private CarTelemetryClient client;
    private CarType car;
    private int numericCarId;

    private TelemetryDataType selectedTelemetryData;

    @ManagedProperty(value = "#{param.carId}")
    private String carId;

    public CarBean() {
        client = new CarTelemetryClient("http://127.0.0.1:8182/api");
    }

    @PostConstruct
    public void init() {

        try {
            numericCarId = Integer.parseInt(carId);
        } catch (Exception e) {
            numericCarId = -1;
        }
        if (numericCarId != -1) {
            this.loadCar();
        }
    }

    private int findNewId() {
        int id = Integer.MIN_VALUE;
        for (TelemetryDataType telemetry : car.getTelemtryDataList().getTelemetryData()) {
            id = Math.max(telemetry.getIdTelemetryData(), id);
        }
        return id + 1;
    }

    public void startAdd() {
        System.out.println("test342######");
        ObjectFactory factory = new ObjectFactory();
        this.selectedTelemetryData = factory.createTelemetryDataType();
        int id = findNewId();
        this.selectedTelemetryData.setIdTelemetryData(id);
        RequestContext context = RequestContext.getCurrentInstance();

        System.out.println("test34244322999######");

        System.out.println(this.selectedTelemetryData.getIdTelemetryData());
        context.update("addDialog2");
        context.execute("PF(\"addDlg2\").show();");
    }

    public void handleAddClose() {
        if (!isEverythingValid()) {
            return;
        }
        //selectedBike.setId(this.findNewId());
        this.car.getTelemtryDataList().getTelemetryData().add(selectedTelemetryData);
        client.addTelemetryData(car.getIdCar(), selectedTelemetryData);
        this.loadCar();
    }

    private void loadCar() {
        car = client.getCar(numericCarId);
        RequestContext.getCurrentInstance().update("telemetryTable");
    }

    private boolean isEverythingValid() {
        if (isInvalid(selectedTelemetryData)) {
            showMsg("Every field but 'Notes' and 'Date of Last Service' is required!");
            return false;
        }
        return true;
    }

    private void showMsg(String msgTxt) {
        FacesMessage msg = new FacesMessage("Error", msgTxt);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private boolean isInvalid(TelemetryDataType telemetry) {
        return isNullOrEmpty(telemetry.getSpeed());
    }

    private boolean isInvalidDouble(String value){
        try{
            Double.parseDouble(value);
            return false;
        }catch(Exception ex){
            return true;
        }
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public CarType getCar() {
        return car;
    }

    public void setCar(CarType car) {
        this.car = car;
    }

    public TelemetryDataType getSelectedTelemetryData() {
        return selectedTelemetryData;
    }

    public void setSelectedTelemetryData(TelemetryDataType selectedTelemetryData) {
        this.selectedTelemetryData = selectedTelemetryData;
    }
}
