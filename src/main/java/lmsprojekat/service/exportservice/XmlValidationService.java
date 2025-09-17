package lmsprojekat.service.exportservice;

import java.io.StringReader;
import java.net.URL;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class XmlValidationService {

    private final Schema schema;

    public XmlValidationService() {
        try {
            SchemaFactory f = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL schemaUrl = new ClassPathResource("templates\\evaluation.xsd").getURL();
            this.schema = f.newSchema(schemaUrl);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load evaluation.xsd", e);
        }
    }

    public void validate(String xml) {
        try {
            Validator v = schema.newValidator();
            v.validate(new StreamSource(new StringReader(xml)));
        } catch (SAXException e) {
            throw new IllegalArgumentException("XML validation error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalArgumentException("XML validation failed", e);
        }
    }
}
