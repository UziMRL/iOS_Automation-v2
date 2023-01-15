
import java.io.FileOutputStream;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TestRunner {
    public static void main(String[] args) {
        // Run the tests
        Result result = JUnitCore.runClasses(
                Activation.class
//                Permissions.class,
//                homePageMute.class,
//                History.class,
//                InformationUI.class,
//                InformationFunctionality.class
        );

        // Create the report document
        Document document = new Document();
        try {
            // Create the PDF writer
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/qa/Desktop/Reports/untitled folder/report.pdf"));

            // Open the document
            document.open();

            // Add a title to the report
            document.add(new Paragraph("Test Report"));

            // Create a table to display the test results
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add the table headers
            PdfPCell cell1 = new PdfPCell(new Phrase("Test Class"));
            cell1.setBorderColor(BaseColor.BLUE);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Phrase("Result"));
            cell2.setBorderColor(BaseColor.BLUE);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell1);
            table.addCell(cell2);

            // Add a row for each test
            List<Failure> failures = result.getFailures();
            for (Failure failure : failures) {
                String className = failure.getTestHeader();
                String resultStr = failure.getMessage();

                table.addCell(className);
                table.addCell(resultStr);
            }

            // Add the table to the document
            document.add(table);

            // Add a summary to the report
            document.add(new Paragraph("Total Tests: " + result.getRunCount()));
            document.add(new Paragraph("Passed: " + (result.getRunCount() - result.getFailureCount())));
            document.add(new Paragraph("Failed: " + result.getFailureCount()));

            // Close the document
            document.close();
            // Close the PDF writer
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}