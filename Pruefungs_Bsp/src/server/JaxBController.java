package server;

import data.EnergieDataExportType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxBController {
    private static JaxBController instance;
    private EnergieDataExportType energies;

    public JaxBController() {

        this.readXML("C://Users//Lukas//Documents//HTL//5BHIF//Java//JavaBeispiele//Pruefungs_Bsp//energie.xml");
    }

    public static JaxBController getInstance() {
        if (instance == null) {
            instance = new JaxBController();
        }
        return instance;
    }

    private void readXML(String path) {

        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(EnergieDataExportType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            energies = (EnergieDataExportType) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public EnergieDataExportType getEnergies() {
        return energies;
    }
}
