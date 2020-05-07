package com.michaelhitzker.server;

import com.michaelhitzker.common.ChargingParksResource;
import com.michaelhitzker.common.ChargingStationsResource;
import com.michaelhitzker.data.ChargingparkType;
import com.michaelhitzker.data.ChargingparksType;
import org.restlet.resource.ServerResource;

public class ChargingParksServerResource extends ServerResource implements ChargingParksResource {
    private EmobilityManager manager = EmobilityManager.getInstance();

    @Override
    public ChargingparksType represent() {
        return manager.getChargingParks();
    }

    @Override
    public ChargingparkType addPark(ChargingparkType park) {
        return manager.addChargingpark(park);
    }
}
