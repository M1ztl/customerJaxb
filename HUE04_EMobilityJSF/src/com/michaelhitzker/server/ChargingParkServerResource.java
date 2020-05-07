package com.michaelhitzker.server;

import com.michaelhitzker.common.ChargingParkResource;
import com.michaelhitzker.common.ChargingStationResource;
import com.michaelhitzker.data.ChargingparkType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class ChargingParkServerResource extends ServerResource implements ChargingParkResource {
    private EmobilityManager manager = EmobilityManager.getInstance();
    private long parkId;
    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        parkId = Long.parseLong(getAttribute("parkId"));
    }

    @Override
    public ChargingparkType represent() {
        return manager.getSingleChargingPark(parkId);
    }

    @Override
    public ChargingparkType update(ChargingparkType park) {
        return manager.updateChargingPark(parkId, park);
    }

    @Override
    public ChargingparkType remove() {
        return manager.removeChargingPark(parkId);
    }
}
