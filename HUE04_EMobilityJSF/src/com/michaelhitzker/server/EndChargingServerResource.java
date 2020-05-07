package com.michaelhitzker.server;

import com.michaelhitzker.common.EndChargingResource;
import com.michaelhitzker.data.ChargeType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class EndChargingServerResource extends ServerResource implements EndChargingResource {
    private long userId;
    private long stationId;
    private long parkId;
    private EmobilityManager manager = EmobilityManager.getInstance();

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();

        try{
            userId = Long.parseLong(getQueryValue("userId"));
            stationId = Long.parseLong(getQueryValue("stationId"));
            parkId = Long.parseLong(getQueryValue("parkId"));
        }catch(Exception ex){
            userId = -1;
            parkId = -1;
            stationId = -1;
        }
    }

    @Override
    public ChargeType end() {
        if(userId < 0){
            return null;
        }
        return manager.endCharge(userId, parkId, stationId);
    }
}
