package com.michaelhitzker.server;

import com.michaelhitzker.common.MembersResource;
import com.michaelhitzker.data.MemberType;
import com.michaelhitzker.data.MembersType;
import org.restlet.resource.ServerResource;

public class MembersServerResource extends ServerResource implements MembersResource {
    private EmobilityManager manager = EmobilityManager.getInstance();

    @Override
    public MembersType represent() {
        return manager.getMembers();
    }

    @Override
    public MemberType addMember(MemberType member) {
        return manager.addMember(member);
    }
}
