package com.michaelhitzker.common;

import org.restlet.resource.Get;

public interface RootResource {
    @Get("text")
    String represent();
}
