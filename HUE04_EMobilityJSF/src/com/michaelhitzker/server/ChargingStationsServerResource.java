package com.michaelhitzker.server;

import com.michaelhitzker.common.ChargingStationsResource;
import com.michaelhitzker.data.ChargingstationType;
import com.michaelhitzker.data.ChargingstationsType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class ChargingStationsServerResource extends ServerResource implements ChargingStationsResource {
    private EmobilityManager manager = EmobilityManager.getInstance();
    private long parkId;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        parkId = Long.parseLong(getAttribute("parkId"));
    }

    @Override
    public ChargingstationsType represent() {
        return manager.getChargingStations(parkId);
    }

    @Override
    public ChargingstationType addStation(ChargingstationType station) {
        return manager.addChargingStation(parkId, station);
    }
}
