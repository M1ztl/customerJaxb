package com.michaelhitzker.jsf;

import com.michaelhitzker.client.EmobilityClient;
import com.michaelhitzker.data.MemberType;
import com.michaelhitzker.data.MembersType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;

@ManagedBean(name = "memberList")
@SessionScoped
public class MemberListBean implements Serializable {
    private EmobilityClient client;
    private MembersType members;
    private MemberType selectedMember;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


    public MemberListBean() {
        client = new EmobilityClient("http://127.0.0.1:8182/api");
        members = client.getMembers();
    }

    public MemberType getSelectedMember() {
        return this.selectedMember;
    }

    public void setSelectedMember(MemberType selectedMember) {
        this.selectedMember = selectedMember;
    }

    public void showDialog(MemberType selectedMember) {
        this.selectedMember = selectedMember;
    }

    public String getDateFromMillis(long millis){
        return sdf.format(millis);
    }

    public MembersType getMembers() {
        return members;
    }

}
