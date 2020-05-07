//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.05.06 um 08:37:08 PM CEST 
//


package data;

import javax.xml.bind.annotation.*;


/**
 * <p>Java-Klasse f�r EnergieDataExportType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="EnergieDataExportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerList" type="{}CustomerListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnergieDataExportType", propOrder = {
    "customerList"
})
@XmlRootElement(name ="EnergieDataExport")
public class EnergieDataExportType {

    @XmlElement(name = "CustomerList", required = true)
    protected CustomerListType customerList;

    /**
     * Ruft den Wert der customerList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CustomerListType }
     *     
     */
    public CustomerListType getCustomerList() {
        return customerList;
    }

    /**
     * Legt den Wert der customerList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerListType }
     *     
     */
    public void setCustomerList(CustomerListType value) {
        this.customerList = value;
    }

}
