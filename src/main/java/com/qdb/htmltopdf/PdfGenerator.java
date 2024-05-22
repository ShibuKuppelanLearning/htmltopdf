package com.qdb.htmltopdf;

import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.commons.codec.binary.Base64;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class PdfGenerator {

    private static final String HTML_INPUT = "src/main/resources/template-rmt-table-1.html";
    private static final String PDF_OUTPUT = "src/main/resources/html2pdf.pdf";
    private static ImageHttpClient imageHttpClient = new ImageHttpClient();

    public static void main(String[] args) throws IOException {
        arabicContent();
        /*TemplateEngine engine = new TemplateEngine();
        Context context = new Context();
        context.setVariable("generalInfoLbl", "General Information");

        context.setVariable("companyName", "QATAR STEEL INDUSTRIES FACTORY(QSIF)");
        context.setVariable("companyNameLbl", "Company name");

        context.setVariable("dovLbl", "Date of site visit");
        context.setVariable("dov", "23/04/2024");

        context.setVariable("tobLbl", "Type of business");
        context.setVariable("tob", "Existing");

        context.setVariable("rfsvLbl", "Reason for site visit");
        context.setVariable("rfsv", "Reason for site visit");

        context.setVariable("svdbLbl", "Site visit done by");
        context.setVariable("svdb", "Test RM Tablet Store");

        context.setVariable("companyInfoLbl", "Company Information");

        context.setVariable("offcialPresentLbl", "Name & Title of official's present");
        context.setVariable("offcialPresent", "ICC Testing");

        context.setVariable("addImageVideoLbl", "Add image / video");

        context.setVariable("photoVideoLbl", "Photo / Video");
        context.setVariable("addFilenameLbl", "Add filename");
        context.setVariable("addDescLbl", "Add description");

        context.setVariable("image1Name", "Mountain");
        context.setVariable("image1Time", "Time: 27/03/2024 9:35 AM");
        context.setVariable("image1Location", "25.281425102683826,51.53580400495148");
        context.setVariable("image1Description", "A blurry image of a wireless computer mouse on a wooden surface  A blurry image of a wireless computer mouse on a wooden surface  A blurry image of a wireless computer mouse on a wooden surface");

        context.setVariable("image2Name", "Mountain");
        context.setVariable("image2Time", "Time: 27/03/2024 9:35 AM");
        context.setVariable("image2Location", "25.281425102683826,51.53580400495148");
        context.setVariable("image2Description", "A blurry image of a wireless computer mouse on a wooden surface  A blurry image of a wireless computer mouse on a wooden surface  A blurry image of a wireless computer mouse on a wooden surface");


        context.setVariable("image1", "data:image/jpeg;base64," + Base64.encodeBase64String(imageHttpClient.execute("https://c1.wallpaperflare.com/preview/345/307/597/architecture-industry-factory-building-building.jpg")));
        context.setVariable("gmap1", "data:image/jpeg;base64," + Base64.encodeBase64String(imageHttpClient.execute("https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&key=AIzaSyB7qgctfixewBD-yYZXDQS25RTID8-OQnY")));

        context.setVariable("image2", "data:image/jpeg;base64," + Base64.encodeBase64String(imageHttpClient.execute("https://c0.wallpaperflare.com/preview/80/376/977/air-pollution-chimney-clouds-current.jpg")));
        context.setVariable("gmap2", "data:image/jpeg;base64," + Base64.encodeBase64String(imageHttpClient.execute("https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&key=AIzaSyB7qgctfixewBD-yYZXDQS25RTID8-OQnY")));

        context.setVariable("businessOperationalLbl", "Is the state of business operational?");
        context.setVariable("ownerInvolved", "Is the owner involved?");
        context.setVariable("accBehaviourSatifactory", "Is the Account behaviour satisfactory?");
        context.setVariable("wellMaintained", "Is the company well maintained?");
        context.setVariable("locatedInside", "Is the office located inside?");
        context.setVariable("comNameDisplayedOnUnit", "Is the company name displayed on the unit?");
        context.setVariable("inventoryAvailable", "Is the inventory available?");
        context.setVariable("orderBookPresent", "Is the Order book present?");


        String html = engine.process(Files.readString(Path.of(HTML_INPUT)), context);
        System.out.println(html);
        try (OutputStream os = new FileOutputStream(PDF_OUTPUT)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFont(new File("src/main/resources/ArslanWessamA.ttf"), "(A) Arslan Wessam A (A) Arslan Wessam A", 400, BaseRendererBuilder.FontStyle.NORMAL, true);
            builder.withHtmlContent(html, "/");
            builder.toStream(os);
            builder.run();
        }*/
    }

    public static void arabicContent(){
        try {
            // HTML content with Arabic text
            String htmlContent = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<meta charset=\"UTF-8\"/>" +
                    "<style>" +
                    "@font-face {" +
                    "font-family: 'Amiri';" +
                    "src: url('src/main/resources/Amiri.ttf') format('truetype');" +
                    "}" +
                    "body {" +
                    "font-family: 'Amiri', serif;" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<p>مرحبا بالعالم</p>" + // "Hello World" in Arabic
                    "</body>" +
                    "</html>";

            // Output PDF file path
            String outputPath = "output.pdf";

            // Convert HTML to PDF
            try (OutputStream os = new FileOutputStream(outputPath)) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useFastMode();
                builder.withHtmlContent(htmlContent, new File(".").toURI().toString());
                builder.toStream(os);
                builder.run();
            }

            System.out.println("PDF generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}