package com.michaelhitzker.common;

import com.michaelhitzker.data.EmployeeType;
import com.michaelhitzker.data.MemberType;
import com.michaelhitzker.data.MembersType;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface MembersResource {
    @Get("text/xml")
    MembersType represent();

    @Post("text/xml")
    MemberType addMember(MemberType member);
}
