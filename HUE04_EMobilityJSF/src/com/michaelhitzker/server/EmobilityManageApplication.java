package com.michaelhitzker.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class EmobilityManageApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attachDefault(RootServerResource.class);
        router.attach("/members", MembersServerResource.class);
        router.attach("/employees", EmployeesServerResource.class);
        router.attach("/chargingParks", ChargingParksServerResource.class);
        router.attach("/member/{memberId}", MemberServerResource.class);
        router.attach("/employees/{empId}", EmployeeServerResource.class);
        router.attach("/chargingParks/{parkId}", ChargingStationServerResource.class);
        router.attach("/chargingParks/{parkId}/chargingStations", ChargingStationsServerResource.class);
        router.attach("/chargingParks/{parkId}/chargingStations/{stationId}", ChargingStationServerResource.class);
        router.attach("/charge", GetChargesServerResource.class);
        router.attach("/charge/start", StartChargingServerResource.class);
        router.attach("/charge/end", EndChargingServerResource.class);
        return router;
    }

}
