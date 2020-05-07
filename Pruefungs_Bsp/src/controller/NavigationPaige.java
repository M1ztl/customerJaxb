package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name="navigationPaige")
@RequestScoped
public class NavigationPaige implements Serializable {

    @ManagedProperty(value="#{param.pageId}")
    private String pageId;

    public String showPage(){
        if(pageId == null){
            return "index";
        }
        switch (pageId){
            case "1":
                return "customers";

            case "2":
                return "details";
        }
        return "index";
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
