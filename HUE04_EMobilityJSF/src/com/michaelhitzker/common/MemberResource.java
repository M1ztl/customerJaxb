package com.michaelhitzker.common;

import com.michaelhitzker.data.MemberType;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface MemberResource {
    @Get("text/xml")
    MemberType represent();

    @Put("text/xml")
    MemberType update(MemberType memberType);

    @Delete
    MemberType remove();
}
