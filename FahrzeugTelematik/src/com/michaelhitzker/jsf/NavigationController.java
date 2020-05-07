package com.michaelhitzker.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
    //this managed property will read value from request parameter pageId
    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;

    //condional navigation based on pageId
    //if pageId is 1 show page1.xhtml,
    //if pageId is 2 show page2.xhtml
    //else show home.xhtml

    public String showPage() {
        if(pageId == null){
            return "index";
        }
        switch (pageId) {
            case "1":
                return "carsBean";
            case "2":
                return "carBean";
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