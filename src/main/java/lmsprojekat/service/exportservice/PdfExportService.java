package lmsprojekat.service.exportservice;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import lmsprojekat.export.xml.EvaluationXml;
import lmsprojekat.export.xml.StudentXml;
import lmsprojekat.export.xml.TeacherXml;

@Service
public class PdfExportService {

  private final SpringTemplateEngine thymeleaf;

  public PdfExportService(SpringTemplateEngine thymeleaf) {
    this.thymeleaf = thymeleaf;
  }

  public byte[] teachersToPdf(List<TeacherXml> teachers) throws Exception {
    Context ctx = new Context();
    ctx.setVariable("teachers", teachers);
    String html = thymeleaf.process("teachers-pdf", ctx);
    return render(html);
  }

  public byte[] studentsToPdf(List<StudentXml> students) throws Exception {
    Context ctx = new Context();
    ctx.setVariable("students", students);
    String html = thymeleaf.process("students-pdf", ctx); 
    return render(html);
  }
  public byte[] evaluationToPdf(EvaluationXml evaluation) throws Exception {
	    org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();
	    ctx.setVariable("eval", evaluation);
	    String html = thymeleaf.process("evaluation-pdf", ctx);
	    return render(html);
	  }

  private byte[] render(String html) throws Exception {
	  Document doc = Jsoup.parse(html);
	  doc.outputSettings()
	     .syntax(Document.OutputSettings.Syntax.xml)
	     .escapeMode(org.jsoup.nodes.Entities.EscapeMode.xhtml)
	     .charset("UTF-8");
	  String xhtml = doc.outerHtml();

	  try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	    PdfRendererBuilder b = new PdfRendererBuilder();
	    b.useFastMode();
	    b.withHtmlContent(xhtml, null);
	    b.toStream(baos);
	    b.run();
	    return baos.toByteArray();
	  }
	}
}
