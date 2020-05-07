package controller;

import data.CustomerListType;
import data.DataEntriesType;
import server.JaxBController;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name="detailView")
@RequestScoped
public class DetailView implements Serializable {

    JaxBController jaxb = new JaxBController();

    private CustomerListType customers;
    private DataEntriesType entries;

    @ManagedProperty(value = "#{param.customerid}")
    private int customerid;

    public DetailView() {
        this.init();
    }

    @PostConstruct
    public void init(){
        customers = jaxb.getEnergies().getCustomerList();
        entries = customers.getCustomer().get(customerid).getDataEntries();
        System.out.println("Hallo: "+customerid);
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public CustomerListType getCustomers() {
        return customers;
    }

    public void setCustomers(CustomerListType customers) {
        this.customers = customers;
    }

    public DataEntriesType getEntries() {
        return entries;
    }

    public void setEntries(DataEntriesType entries) {
        this.entries = entries;
    }
}
