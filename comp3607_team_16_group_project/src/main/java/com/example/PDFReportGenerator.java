//--------------------------------
//PDFReportGenerator Class:
//--------------------------------




//-------------------
//Maven Package Name:
//-------------------
package com.example;
//-------------------




//------------------------------------------------
//Import Statements:
//------------------------------------------------
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.IOException;
import java.util.Map;
//------------------------------------------------




//-------------------------------------------------------------------------------------------------------------------------------
//Class Body:
//-------------------------------------------------------------------------------------------------------------------------------
public class PDFReportGenerator {

    //---------------------------------------------------------------------------------------------------------------------------
    //Method to generate and format the report:
    //---------------------------------------------------------------------------------------------------------------------------
    public void generateReport(Map<File, Map<String, Integer>> results, String studentID, File outputDir) throws IOException {
        //-----------------------------------------------------------------------------------------------------------------------
        //Initialize File variable:
        //-----------------------------------------------------------------------------------------------------------------------
        File pdfFile = new File(outputDir, studentID + "_Report.pdf");
        //-----------------------------------------------------------------------------------------------------------------------




        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 700);

                contentStream.showText("Evaluation Report for Student: " + studentID);
                contentStream.newLine();
                contentStream.newLine();

                for (Map.Entry<File, Map<String, Integer>> entry : results.entrySet()) {
                    contentStream.showText("File: " + entry.getKey().getName());
                    contentStream.newLine();

                    int totalScore = 0;
                    for (Map.Entry<String, Integer> strategyScore : entry.getValue().entrySet()) {
                        contentStream.showText("  - " + strategyScore.getKey() + ": " + strategyScore.getValue() + " points");
                        contentStream.newLine();
                        totalScore += strategyScore.getValue();
                    }
                    contentStream.showText("  Total Score: " + totalScore + " points");
                    contentStream.newLine();
                    contentStream.newLine();
                }

                contentStream.endText();
            }
            document.save(pdfFile);
        }
    }
}
//-------------------------------------------------------------------------------------------------------------------------------