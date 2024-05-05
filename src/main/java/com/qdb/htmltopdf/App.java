package com.qdb.htmltopdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

public class App {

    private static final String HTML_INPUT = "src/main/resources/template-rmt-table.html";
    private static final String PDF_OUTPUT = "src/main/resources/html2pdf.pdf";

    public static void main(String[] args) {
        try {
            App htmlToPdf = new App();
            byte[] response = new ImageHttpClient().execute("https://c1.wallpaperflare.com/preview/345/307/597/architecture-industry-factory-building-building.jpg");
            String imageBase64Bytes = Base64.encodeBase64String(response);
            htmlToPdf.generateHtmlToPdf(imageBase64Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateHtmlToPdf(String imageContent) throws IOException {
        File inputHTML = new File(HTML_INPUT);
        Document doc = createWellFormedHtml(inputHTML);
        xhtmlToPdf(doc, PDF_OUTPUT);
    }

    private Document createWellFormedHtml(File inputHTML) throws IOException {
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings()
                .syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }

    private void xhtmlToPdf(Document doc, String outputPdf) throws IOException {
        try (OutputStream os = new FileOutputStream(outputPdf)) {
            String baseUri = FileSystems.getDefault()
                    .getPath("src/main/resources/")
                    .toUri()
                    .toString();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(outputPdf);
            builder.toStream(os);
            builder.withW3cDocument(new W3CDom().fromJsoup(doc), baseUri);
            builder.run();
        }
    }
}
