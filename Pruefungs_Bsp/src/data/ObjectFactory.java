//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.05.06 um 08:37:08 PM CEST 
//


package data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the data package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EnergieDataExport_QNAME = new QName("", "EnergieDataExport");
    private final static QName _DataEntriesTypePowerRecord_QNAME = new QName("", "PowerRecord");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: data
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnergieDataExportType }
     * 
     */
    public EnergieDataExportType createEnergieDataExportType() {
        return new EnergieDataExportType();
    }

    /**
     * Create an instance of {@link PowerRecordType }
     * 
     */
    public PowerRecordType createPowerRecordType() {
        return new PowerRecordType();
    }

    /**
     * Create an instance of {@link CustomerListType }
     * 
     */
    public CustomerListType createCustomerListType() {
        return new CustomerListType();
    }

    /**
     * Create an instance of {@link CustomerType }
     * 
     */
    public CustomerType createCustomerType() {
        return new CustomerType();
    }

    /**
     * Create an instance of {@link DataEntriesType }
     * 
     */
    public DataEntriesType createDataEntriesType() {
        return new DataEntriesType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnergieDataExportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "EnergieDataExport")
    public JAXBElement<EnergieDataExportType> createEnergieDataExport(EnergieDataExportType value) {
        return new JAXBElement<EnergieDataExportType>(_EnergieDataExport_QNAME, EnergieDataExportType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PowerRecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PowerRecord", scope = DataEntriesType.class)
    public JAXBElement<PowerRecordType> createDataEntriesTypePowerRecord(PowerRecordType value) {
        return new JAXBElement<PowerRecordType>(_DataEntriesTypePowerRecord_QNAME, PowerRecordType.class, DataEntriesType.class, value);
    }

}
