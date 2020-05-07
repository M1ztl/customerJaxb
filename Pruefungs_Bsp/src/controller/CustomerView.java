package controller;

import data.CustomerListType;
import data.CustomerType;
import data.ObjectFactory;
import org.omg.CORBA.Request;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import server.JaxBController;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.math.BigInteger;


@ManagedBean(name="customerView")
@ViewScoped
public class CustomerView {

    JaxBController jaxb = new JaxBController();

    private CustomerListType customers;
    private int selectedId;
    private CustomerType selectedCustomer;

    public CustomerView() {
        this.init();
    }

    public void init(){
        customers = jaxb.getEnergies().getCustomerList();
    }

    public CustomerListType getCustomers() {
        return customers;

    }

    public void removeCustomer(CustomerType cus){
            customers.getCustomer().remove(cus);
    }
    public void editCustomer(CustomerType cus){
        this.selectedCustomer = cus;
        this.selectedId = customers.getCustomer().indexOf(cus);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("editDialog");
        context.execute("PF(\"dlg\").show();");
    }

    public void add(){
        this.selectedId=customers.getCustomer().size();
        ObjectFactory factory = new ObjectFactory();
        this.selectedCustomer = factory.createCustomerType();
        this.selectedCustomer.setDataEntries(factory.createDataEntriesType());
        int id = findNewId();
        this.selectedCustomer.setId(id);
        customers.getCustomer().add(this.selectedCustomer);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("addDialog");
        context.execute("PF(\"addDlg\").show();");
    }

    public int findNewId(){
       int id = Integer.MIN_VALUE;
        for(CustomerType cus: customers.getCustomer()){
            id = Math.max(cus.getId(),id);
        }

       return id+1;
    }

    public void handleClose(CloseEvent event){
        if(!isEverythingValid()){
            return;
        }
        customers.getCustomer().set(selectedId,selectedCustomer);
        RequestContext context = RequestContext.getCurrentInstance();
        customers = getCustomers();
        context.update("customerTable");
    }


    public boolean isEverythingValid(){
        if(isInvalid(selectedCustomer)){
            showMsg("Every Field is required");
            return false;
        }
        return true;
    }

    public boolean isInvalid(CustomerType cus){
        return isNullOrEmpty(cus.getFirstName())||isNullOrEmpty(cus.getLastName());
    }

    public boolean isNullOrEmpty(String str){
        return str == null || str.equals("");
    }

    public void showMsg(String str){
        FacesMessage msg = new FacesMessage("Error",str);
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }


    public void setCustomers(CustomerListType customers) {
        this.customers = customers;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public CustomerType getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(CustomerType selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
}
