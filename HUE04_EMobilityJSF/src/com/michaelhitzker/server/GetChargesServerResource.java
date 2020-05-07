package com.michaelhitzker.server;

import com.michaelhitzker.common.GetChargesResource;
import com.michaelhitzker.data.ChargeType;
import com.michaelhitzker.data.ChargesType;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.List;

public class GetChargesServerResource extends ServerResource implements GetChargesResource {
    private long userId;
    private long stationId;
    private long parkId;
    private long startDate;
    private long endDate;
    private boolean active;
    private EmobilityManager manager = EmobilityManager.getInstance();

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        userId = getLongFromMap("userId");
        stationId = getLongFromMap("stationId");
        parkId = getLongFromMap("parkId");
        startDate = getLongFromMap("startDate");
        endDate = getLongFromMap("endDate");
        active = getBooleanFromMap("active");

    }

    private boolean getBooleanFromMap(String name){
        try {
            return Boolean.parseBoolean(getQueryValue(name));
        } catch (Exception ex) {
            return false;
        }
    }

    private long getLongFromMap(String name) {
        try {
            return Long.parseLong(getQueryValue(name));
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public ChargesType getCharges() {
        return manager.getCharges(active, startDate, endDate, userId, parkId, stationId);
    }
}
