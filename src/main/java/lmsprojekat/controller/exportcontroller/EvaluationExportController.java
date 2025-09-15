package lmsprojekat.controller.exportcontroller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.export.xml.EvaluationXml;
import lmsprojekat.service.exportservice.EvaluationExportService;
import lmsprojekat.service.exportservice.PdfExportService;

@RestController
@RequestMapping("/api/export/")
public class EvaluationExportController {

    private final EvaluationExportService evalService;
    private final PdfExportService pdfService;

    public EvaluationExportController(EvaluationExportService evalService, PdfExportService pdfService) {
        this.evalService = evalService;
        this.pdfService = pdfService;
    }

    @GetMapping(value = "/evaluations/{id}.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> exportEvaluationXml(@PathVariable Long id) throws Exception {
        EvaluationXml eval = evalService.buildEvaluation(id);
        String xml = evalService.toXml(eval);
        return ResponseEntity.ok(xml);
    }

    @GetMapping(value = "/evaluations/{id}.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportEvaluationPdf(@PathVariable Long id) throws Exception {
        EvaluationXml eval = evalService.buildEvaluation(id);
        byte[] pdfBytes = pdfService.evaluationToPdf(eval);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=evaluation-" + id + ".pdf")
            .body(pdfBytes);
    }

}
