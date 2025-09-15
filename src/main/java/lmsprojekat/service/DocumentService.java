package lmsprojekat.service;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class DocumentService {

    private final TemplateEngine templateEngine;

    public DocumentService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generateDocument(String templateName, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);

        String html = templateEngine.process(templateName, context);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }
}
