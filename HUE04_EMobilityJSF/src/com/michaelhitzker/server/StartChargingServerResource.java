package com.michaelhitzker.server;

import com.michaelhitzker.common.StartChargingResource;
import com.michaelhitzker.data.ChargeType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class StartChargingServerResource extends ServerResource implements StartChargingResource {

    private long userId;
    private long stationId;
    private long parkId;
    private EmobilityManager manager = EmobilityManager.getInstance();

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();

        try {
            userId = Long.parseLong(getQueryValue("userId"));
            stationId = Long.parseLong(getQueryValue("stationId"));
            parkId = Long.parseLong(getQueryValue("parkId"));
        } catch (Exception ex) {
            userId = -1;
            parkId = -1;
            stationId = -1;
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ChargeType start() {
        if (userId < 0) {
            System.out.println("error");
            return null;
        }
        return manager.startCharge(userId, parkId, stationId);
    }
}
