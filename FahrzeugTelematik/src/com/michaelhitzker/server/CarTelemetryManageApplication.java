package com.michaelhitzker.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class CarTelemetryManageApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attachDefault(RootServerResource.class);
        router.attach("/cars", CarsServerResource.class);
        router.attach("/car/{carId}", CarServerResource.class);
        router.attach("/car/{carId}/telemetry", TelemetryDataListServerResource.class);
        router.attach("/car/{carId}/telemetry/{carTelemetryId}", TelemetryDataServerResource.class);
        return router;
    }
}
