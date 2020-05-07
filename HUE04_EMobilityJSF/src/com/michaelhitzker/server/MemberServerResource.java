package com.michaelhitzker.server;

import com.michaelhitzker.common.MemberResource;
import com.michaelhitzker.data.MemberType;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class MemberServerResource extends ServerResource implements MemberResource {
    private EmobilityManager manager = EmobilityManager.getInstance();
    private long memberId;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        memberId = Long.parseLong(getAttribute("memberId"));
    }

    @Override
    public MemberType represent() {
        return manager.getSingleMember(memberId);
    }

    @Override
    public MemberType update(MemberType memberType) {
        return manager.updateMember(memberId, memberType);
    }

    @Override
    public MemberType remove() {
        return manager.removeMember(memberId);
    }
}
