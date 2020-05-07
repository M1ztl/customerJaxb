package com.michaelhitzker.server;

import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class Main {

    public static void main(String[] args) {
        Component component = new Component();
        component.getDefaultHost().attach("/api", new EmobilityManageApplication());

        Server emobilityServiceServer = new Server(Protocol.HTTP, 8182, component);
        try {
            emobilityServiceServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
