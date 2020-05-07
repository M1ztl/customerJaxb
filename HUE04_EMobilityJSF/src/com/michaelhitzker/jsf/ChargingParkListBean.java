package com.michaelhitzker.jsf;

import com.michaelhitzker.client.EmobilityClient;
import com.michaelhitzker.data.ChargingparkType;
import com.michaelhitzker.data.ChargingparksType;
import com.michaelhitzker.data.EmployeeType;
import com.michaelhitzker.data.EmployeesType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;

@ManagedBean(name = "chargingParkList")
@SessionScoped
public class ChargingParkListBean implements Serializable {
    private EmobilityClient client;
    private ChargingparksType chargingParks;
    private ChargingparkType selectedChargingPark;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


    public ChargingParkListBean() {
        client = new EmobilityClient("http://127.0.0.1:8182/api");
        chargingParks = client.getChargingParks();
    }

    public ChargingparkType getSelectedChargingPark() {
        return this.selectedChargingPark;
    }

    public void setSelectedChargingPark(ChargingparkType selectedChargingPark) {
        this.selectedChargingPark = selectedChargingPark;
    }

    public void showDialog(ChargingparkType selectedEmployee) {
        this.selectedChargingPark = selectedEmployee;
    }

    public String getDateFromMillis(long millis){
        return sdf.format(millis);
    }

    public ChargingparksType getEmployees() {
        return chargingParks;
    }

}
