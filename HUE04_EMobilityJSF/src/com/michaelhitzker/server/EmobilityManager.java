package com.michaelhitzker.server;

import com.michaelhitzker.data.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class EmobilityManager {

    private static EmobilityManager instance;
    private static ChargeManagerType manager;
    private EmobilityType emobility;

    public EmobilityManager() {

        this.readXML("data/emobility.xml");
        manager= emobility.getChargeManager();
    }

    public static EmobilityManager getInstance() {
        if (instance == null) {
            instance = new EmobilityManager();
        }
        return instance;
    }

    public EmployeesType getEmployees() {
        return emobility.getOperator().getEmployees();
    }

    public MembersType getMembers() {
        return emobility.getOperator().getMembers();
    }

    public ChargingparksType getChargingParks() {
        return emobility.getOperator().getChargingparks();
    }

    public ChargingstationsType getChargingStations(long parkId) {
        List<ChargingparkType> parks = emobility.getOperator().getChargingparks().getChargingpark();
        return parks.stream().filter(park -> park.getId() == parkId).collect(Collectors.toList()).get(0).getChargingstations();
    }

    public EmployeeType getSingleEmployee(long empId) {
        List<EmployeeType> employees = emobility.getOperator().getEmployees().getEmployee();
        return employees.stream().filter(employee -> employee.getId() == empId).findAny().orElse(null);
    }

    public MemberType getSingleMember(long memberId) {
        List<MemberType> members = emobility.getOperator().getMembers().getMember();
        return members.stream().filter(member -> member.getId() == memberId).findAny().orElse(null);
    }

    public ChargingparkType getSingleChargingPark(long parkId) {
        List<ChargingparkType> parks = emobility.getOperator().getChargingparks().getChargingpark();
        return parks.stream().filter(park -> park.getId() == parkId).findAny().orElse(null);
    }

    public ChargingstationType getSingleChargingStation(long parkId, long stationId) {
        ChargingparkType chargingPark = getSingleChargingPark(parkId);

        List<ChargingstationType> stations = chargingPark.getChargingstations().getChargingstation();
        return stations.stream().filter(station -> station.getId() == stationId).findAny().orElse(null);
    }

    public EmployeeType updateEmployee(long empId, EmployeeType employee) {
        List<EmployeeType> employees = getEmployees().getEmployee();
        int idInList = getIndexOfIdInList(empId, employees);
        if (idInList < 0) {
            return null;
        }
        employees.set(idInList, employee);
        return employee;
    }

    public MemberType updateMember(long memberId, MemberType member) {
        List<MemberType> members = getMembers().getMember();
        int idInList = getIndexOfIdInList(memberId, members);
        if (idInList < 0) {
            return null;
        }
        members.set(idInList, member);
        return member;
    }

    public ChargingparkType updateChargingPark(long parkId, ChargingparkType park) {
        List<ChargingparkType> parks = getChargingParks().getChargingpark();
        int idInList = getIndexOfIdInList(parkId, parks);
        if (idInList < 0) {
            return null;
        }
        parks.set(idInList, park);
        return park;
    }

    public ChargingstationType updateChargingStation(long parkId, long stationId, ChargingstationType station) {
        List<ChargingstationType> stations = getChargingStations(parkId).getChargingstation();
        int idInList = getIndexOfIdInList(stationId, stations);
        if (idInList < 0) {
            return null;
        }
        stations.set(idInList, station);
        return station;
    }

    public EmployeeType removeEmployee(long empId) {
        List<EmployeeType> employees = getEmployees().getEmployee();
        int idInList = getIndexOfIdInList(empId, employees);
        if (idInList < 0) {
            return null;
        }
        EmployeeType employee = employees.get(idInList);
        employee.setQuitted(true);
        return employees.set(idInList, employee);
    }

    public MemberType removeMember(long memberId) {
        List<MemberType> members = getMembers().getMember();
        int idInList = getIndexOfIdInList(memberId, members);
        if (idInList < 0) {
            return null;
        }
        MemberType member = members.get(idInList);
        member.setActive(false);
        return members.set(idInList, member);
    }

    public ChargingparkType removeChargingPark(long parkId) {
        List<ChargingparkType> parks = getChargingParks().getChargingpark();
        int idInList = getIndexOfIdInList(parkId, parks);
        if (idInList < 0) {
            return null;
        }
        return parks.remove(idInList);
    }

    public ChargingstationType removeChargingStation(long parkId, long stationId) {
        List<ChargingstationType> stations = getChargingStations(parkId).getChargingstation();
        int idInList = getIndexOfIdInList(stationId, stations);
        if (idInList < 0) {
            return null;
        }
        return stations.remove(idInList);
    }

    public EmployeeType addEmployee(EmployeeType employee) {
        EmployeeType existing = getSingleEmployee(employee.getId());
        if (existing != null) {
            return existing;
        }
        getEmployees().getEmployee().add(employee);
        return employee;
    }

    public MemberType addMember(MemberType member) {
        MemberType existing = getSingleMember(member.getId());
        if (existing != null) {
            return existing;
        }
        getMembers().getMember().add(member);
        return member;
    }

    public ChargingparkType addChargingpark(ChargingparkType park) {
        ChargingparkType existing = getSingleChargingPark(park.getId());
        if (existing != null) {
            return existing;
        }
        getChargingParks().getChargingpark().add(park);
        return park;
    }

    public ChargingstationType addChargingStation(long parkId, ChargingstationType station) {
        ChargingstationType existing = getSingleChargingStation(parkId, station.getId());
        if (existing != null) {
            return existing;
        }
        getChargingStations(parkId).getChargingstation().add(station);
        return station;
    }

    private <T> int getIndexOfIdInList(long id, List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T obj = list.get(i);
            if (obj instanceof EmployeeType && ((EmployeeType) obj).getId() == id) {
                return i;
            }
            if (obj instanceof MemberType && ((MemberType) obj).getId() == id) {
                return i;
            }
            if (obj instanceof ChargingparkType && ((ChargingparkType) obj).getId() == id) {
                return i;
            }
            if (obj instanceof ChargingstationType && ((ChargingstationType) obj).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private boolean isUserValid(long userId){
        EmployeeType employee = getSingleEmployee(userId);
        MemberType member = getSingleMember(userId);

        if(employee == null && member == null){
            return false;
        }
        if(employee != null && employee.isQuitted()){
            return false;
        }
        if(member != null && !member.isActive()){
            return false;
        }
        return true;
    }

    public ChargeType startCharge(long userId, long parkId, long stationId) {
        ChargingstationType station = getSingleChargingStation(parkId, stationId);
        List<ChargeType> activeCharges = manager.getActiveCharges().getCharge();
        boolean inUse = activeCharges.stream()
                .anyMatch(activeCharge ->
                            activeCharge.getParkId() == parkId
                            && activeCharge.getStationId() == stationId);
        if(inUse || !isUserValid(userId) || station == null){
            return null;
        }

        ChargeType chargeType = new ChargeType();
        chargeType.setStartDate(System.currentTimeMillis());
        chargeType.setUserId(userId);
        chargeType.setParkId(parkId);
        chargeType.setStationId(stationId);
        chargeType.setPricePerKwh(station.getPricePerKwh());
        manager.getActiveCharges().getCharge().add(chargeType);
        return chargeType;
    }

    public ChargeType endCharge(long userId, long parkId, long stationId) {
        ChargingstationType station = getSingleChargingStation(parkId, stationId);
        List<ChargeType> activeCharges = manager.getActiveCharges().getCharge();
        int idOfChargeInList = getIndexOfChargeInList(userId, parkId, stationId, activeCharges);

        if(idOfChargeInList<0 || !isUserValid(userId) || station == null){
            return null;
        }

        ChargeType chargeType = activeCharges.get(idOfChargeInList);

        long endDate = System.currentTimeMillis();
        double duration = endDate - chargeType.getStartDate();
        duration = duration/1000/60/60;
        double kwhCharged = duration * station.getMaxPowerOut();

        chargeType.setEndDate(endDate);
        chargeType.setDuration(duration);
        chargeType.setKwhCharged(kwhCharged);
        chargeType.setTotal(chargeType.getPricePerKwh() * kwhCharged);
        manager.getActiveCharges().getCharge().remove(idOfChargeInList);
        manager.getCharges().getCharge().add(chargeType);
        return chargeType;
    }

    public ChargesType getCharges(boolean active, long startDate, long endDate, long userId, long parkId, long stationId){
        ObjectFactory of = new ObjectFactory();
        ChargesType charges  = of.createChargesType();
        List<ChargeType> filteredCharges = new LinkedList<>();
        if(active){
            filteredCharges =  filterCharges(manager.getActiveCharges().getCharge(), startDate, endDate, userId, parkId, stationId);
        }else{
            filteredCharges =  filterCharges(manager.getCharges().getCharge(), startDate, endDate, userId, parkId, stationId);
        }

        charges.getCharge().addAll(filteredCharges);
        return charges;
    }

    public List<ChargeType> filterCharges(List<ChargeType> charges, long startDate, long endDate, long userId, long parkId, long stationId){
        List<ChargeType> filteredCharges = charges.stream().filter(charge ->
                (startDate<0 || charge.getStartDate()>startDate) &&
                (endDate<0 || charge.getEndDate()<endDate)&&
                (userId<0 || charge.getUserId() == userId)&&
                (parkId<0 || charge.getParkId() == parkId) &&
                (stationId <0 || charge.getStationId() == stationId)
        ).collect(Collectors.toList());
        return filteredCharges;
    }

    private int getIndexOfChargeInList(long userId, long parkId, long stationId, List<ChargeType> charges){
        for(int i = 0; i<charges.size(); i++){
            ChargeType charge = charges.get(i);
            if(charge.getUserId() == userId && charge.getParkId() == parkId && charge.getStationId() == stationId){
                return i;
            }
        }
        return -1;
    }

    private void readXML(String path) {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(EmobilityType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            emobility = (EmobilityType) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
