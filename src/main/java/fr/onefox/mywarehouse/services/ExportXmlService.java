package fr.onefox.mywarehouse.services;

import fr.onefox.mywarehouse.view.export.CargoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class ExportXmlService {

    private static final String SEPARATOR = "_";
    private static final String XML = ".xml";
    private static final String UNABLE_TO_GENERATE_THE_XML_FILE = "Unable to generate the XML file";

    /**
     * Export XML
     *
     * @param prefixFile
     * @param classToExport
     * @return
     */
    public File export(String prefixFile, Object classToExport) throws Exception {
        log.info("Export XML");

        File file;
        try {
            file = File.createTempFile(prefixFile + SEPARATOR, XML);
            JAXBContext jaxbContext = JAXBContext.newInstance(CargoMessage.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(classToExport, file);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            log.error(UNABLE_TO_GENERATE_THE_XML_FILE);
            throw new Exception(UNABLE_TO_GENERATE_THE_XML_FILE);
        }
        return file;
    }

}

