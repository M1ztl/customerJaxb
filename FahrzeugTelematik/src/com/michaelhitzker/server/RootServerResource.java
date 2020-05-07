package com.michaelhitzker.server;

import com.michaelhitzker.common.RootResource;
import org.restlet.resource.ServerResource;

public class RootServerResource extends ServerResource implements RootResource {
    @Override
    public String represent() {
        return "Car telemetry";
    }
}
