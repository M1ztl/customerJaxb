package com.michaelhitzker.client;

import com.michaelhitzker.common.*;
import com.michaelhitzker.data.*;
import org.restlet.resource.ClientResource;

import java.util.HashMap;
import java.util.Map;

public class EmobilityClient {
    private static final String EMPS_PATH = "/employees";
    private static final String EMP_PATH = "/employees/%1$d";
    private static final String MEMBERS_PATH = "/members";
    private static final String MEMBER_PATH = "/members/%1$d";
    private static final String PARKS_PATH = "/chargingParks";
    private static final String PARK_PATH = "/chargingParks/%1$d";
    private static final String STATIONS_PATH = "/chargingParks/%1$d/chargingStations";
    private static final String STATION_PATH = "/chargingParks/%1$d/chargingStations/%2$d";
    private static final String SC_PATH = "/charge/start?userId=%1$d&stationId=%3$d&parkId=%3$d";
    private static final String EC_PATH = "/charge/end?userId=%1$d&stationId=%3$d&parkId=%3$d";
    private static final String GC_PATH = "/charge?%1$s";


    private String url;
    private EmployeesResource empsResource;
    private EmployeeResource empResource;
    private MembersResource membersResource;
    private MemberResource memberResource;
    private ChargingParksResource parksResource;
    private ChargingParkResource parkResource;
    private ChargingStationsResource stationsResource;
    private ChargingStationResource stationResource;
    private StartChargingResource startChargingResource;
    private EndChargingResource endChargingResource;
    private GetChargesResource getChargesResource;

    public EmobilityClient(String baseURL) {
        this.url = baseURL;
        this.empsResource = ClientResource.create(url + EMPS_PATH, EmployeesResource.class);
        this.membersResource = ClientResource.create(url + MEMBERS_PATH, MembersResource.class);
        this.parksResource = ClientResource.create(url + PARKS_PATH, ChargingParksResource.class);
    }

    public EmployeesType getEmployees() {
        return empsResource.represent();
    }

    public MembersType getMembers() {
        return membersResource.represent();
    }

    public ChargingparksType getChargingParks() {
        return parksResource.represent();
    }

    public ChargingstationsType getChargingStations(long parkId) {
        stationsResource = ClientResource.create(url + String.format(STATIONS_PATH, parkId), ChargingStationsResource.class);
        return stationsResource.represent();
    }

    public EmployeeType addEmployee(EmployeeType employee) {
        return empsResource.addEmployee(employee);
    }

    public MemberType addMember(MemberType member) {
        return membersResource.addMember(member);
    }

    public ChargingparkType addChargingPark(ChargingparkType park) {
        return parksResource.addPark(park);
    }

    public ChargingstationType addChargingStation(long parkId, ChargingstationType station) {
        stationsResource = ClientResource.create(url + String.format(STATIONS_PATH, parkId), ChargingStationsResource.class);
        return stationsResource.addStation(station);
    }

    public EmployeeType getEmployee(long empId) {
        empResource = ClientResource.create(url + String.format(EMP_PATH, empId), EmployeeResource.class);
        return empResource.represent();
    }

    public MemberType getMember(long memberId) {
        memberResource = ClientResource.create(url + String.format(MEMBER_PATH, memberId), MemberResource.class);
        return memberResource.represent();
    }

    public ChargingparkType getChargingPark(long parkId) {
        parkResource = ClientResource.create(url + String.format(PARK_PATH, parkId), ChargingParkResource.class);
        return parkResource.represent();
    }

    public ChargingstationType getChargingStation(long parkId, long stationId) {
        stationResource = ClientResource.create(url + String.format(STATION_PATH, parkId, stationId), ChargingStationResource.class);
        return stationResource.represent();
    }

    public EmployeeType removeEmployee(long empId) {
        empResource = ClientResource.create(url + String.format(EMP_PATH, empId), EmployeeResource.class);
        return empResource.remove();
    }

    public MemberType removeMember(long memberId) {
        memberResource = ClientResource.create(url + String.format(MEMBER_PATH, memberId), MemberResource.class);
        return memberResource.remove();
    }

    public ChargingparkType removeChargingPark(long parkId) {
        parkResource = ClientResource.create(url + String.format(PARK_PATH, parkId), ChargingParkResource.class);
        return parkResource.remove();
    }

    public ChargingstationType removeChargingStation(long parkId, long stationId) {
        stationResource = ClientResource.create(url + String.format(STATION_PATH, parkId, stationId), ChargingStationResource.class);
        return stationResource.remove();
    }

    public EmployeeType updateEmployee(long empId, EmployeeType employee) {
        empResource = ClientResource.create(url + String.format(EMP_PATH, empId), EmployeeResource.class);
        return empResource.update(employee);
    }

    public MemberType updateMember(long memberId, MemberType member) {
        memberResource = ClientResource.create(url + String.format(MEMBER_PATH, memberId), MemberResource.class);
        return memberResource.update(member);
    }

    public ChargingparkType updateChargingPark(long parkId, ChargingparkType park) {
        parkResource = ClientResource.create(url + String.format(PARK_PATH, parkId), ChargingParkResource.class);
        return parkResource.update(park);
    }

    public ChargingstationType updateChargingStation(long parkId, long stationId, ChargingstationType station) {
        stationResource = ClientResource.create(url + String.format(STATION_PATH, parkId, stationId), ChargingStationResource.class);
        return stationResource.update(station);
    }

    public ChargeType startCharge(long userId, long parkId, long stationId){
        startChargingResource = ClientResource.create(url + String.format(SC_PATH, userId, parkId, stationId), StartChargingResource.class);
        return startChargingResource.start();
    }

    public ChargeType endCharge(long userId, long parkId, long stationId){
        endChargingResource = ClientResource.create(url + String.format(EC_PATH, userId, parkId, stationId), EndChargingResource.class);
        return endChargingResource.end();
    }

    public ChargesType getCharges(HashMap<String, String> params){
        String queryString = getQueryStringFromParams(params);
        getChargesResource = ClientResource.create(url + String.format(GC_PATH, queryString), GetChargesResource.class);
        return getChargesResource.getCharges();
    }

    private String getQueryStringFromParams(HashMap<String,String>params){
        String queryString = "";
        for(Map.Entry<String,String> entry: params.entrySet()){
            queryString += entry.getKey() + "="+entry.getValue() + "&";
        }
        queryString = queryString.substring(0, queryString.length()-1);
        return queryString;
    }
}
