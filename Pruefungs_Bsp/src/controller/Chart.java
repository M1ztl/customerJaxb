package controller;

import data.CustomerListType;
import data.DataEntriesType;
import javafx.scene.chart.PieChart;
import org.primefaces.model.chart.PieChartModel;
import server.JaxBController;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name="chartView")
@RequestScoped
public class Chart implements Serializable {

    JaxBController jaxb = new JaxBController();

    private CustomerListType customers;
    private DataEntriesType entries;
    private PieChartModel pie;

    @ManagedProperty(value = "#{param.customerid}")
    private int customerid;

    public Chart() {
        this.init();
    }

    @PostConstruct
    public void init(){
        customers = jaxb.getEnergies().getCustomerList();
        entries = customers.getCustomer().get(customerid).getDataEntries();
        createPie();
    }

    private void createPie(){
        pie = new PieChartModel();
        for(int i = 0;i<entries.getContent().size();i++){
            pie.set(entries.getContent().get(i).getPower().toString(),entries.getContent().get(i).getSellingUser());

        }
        pie.setTitle("DataEntries");
        pie.setLegendPosition("w");
        pie.setShadow(false);
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

    public PieChartModel getPie() {
        return pie;
    }

    public void setPie(PieChartModel pie) {
        this.pie = pie;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
}
