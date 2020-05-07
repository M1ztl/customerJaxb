package com.michaelhitzker.server;

import com.michaelhitzker.common.ChargingStationResource;
import com.michaelhitzker.data.ChargingstationType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class ChargingStationServerResource extends ServerResource implements ChargingStationResource {
    private EmobilityManager manager = EmobilityManager.getInstance();
    private long parkId;
    private long stationId;
    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        parkId = Long.parseLong(getAttribute("parkId"));
        stationId = Long.parseLong(getAttribute("stationId"));
    }

    @Override
    public ChargingstationType represent() {
        return manager.getSingleChargingStation(parkId, stationId);
    }

    @Override
    public ChargingstationType update(ChargingstationType station) {
        return manager.updateChargingStation(parkId, stationId, station);
    }

    @Override
    public ChargingstationType remove() {
        return manager.removeChargingStation(parkId, stationId);
    }
}
