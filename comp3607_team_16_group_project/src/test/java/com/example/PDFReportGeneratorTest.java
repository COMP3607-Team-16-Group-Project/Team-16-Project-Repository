//-------------------------------
//PDFReportGeneratorTest Class:
//-------------------------------



//-------------------
//Maven Package Name:
//-------------------
package com.example;
//-------------------




//-----------------------------------------------
//Import Statements:
//-----------------------------------------------
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
//-----------------------------------------------




//--------------------------------------------------------------------------------------------------------------------------
//Class Body:
//--------------------------------------------------------------------------------------------------------------------------
class PDFReportGeneratorTest {
    //----------------------------------------------------------------------------------------------------------------------
    //Declare Copies of PDFReportGenerator & File Class:
    //----------------------------------------------------------------------------------------------------------------------
    private PDFReportGenerator generator;
    private File outputDir;
    //----------------------------------------------------------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------------------------------
    //Initialize Copies of PDFReportGenerator & File Class:
    //----------------------------------------------------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        generator = new PDFReportGenerator();
        outputDir = new File("src/test/output");
        if (!outputDir.exists()) {
            assertTrue(outputDir.mkdirs());
        }
    }
    //----------------------------------------------------------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------------------------------
    //Unit Tests:
    //----------------------------------------------------------------------------------------------------------------------
    @Test
    void testGenerateReport() throws Exception {
        // Prepare detailed results
        Map<File, Map<String, Integer>> results = new HashMap<>();
        results.put(
                new File("src/test/resources/ValidClass.java"),
                Map.of(
                        "NamingConvention", 10,
                        "MethodBehavior", 8,
                        "ClassRelationship", 10
                )
        );
        results.put(
                new File("src/test/resources/InvalidClass.java"),
                Map.of(
                        "NamingConvention", 6,
                        "MethodBehavior", 5,
                        "ClassRelationship", 7
                )
        );

        String studentID = "123456";
        generator.generateReport(results, studentID, outputDir);

        // Verify the report is generated
        File pdfFile = new File(outputDir, studentID + "_Report.pdf");
        assertTrue(pdfFile.exists(), "The PDF report should be generated.");

        // Validate basic properties of the PDF
        try (PDDocument document = PDDocument.load(pdfFile)) {
            assertEquals(1, document.getNumberOfPages(), "The PDF should have one page.");
        }
    }
    //----------------------------------------------------------------------------------------------------------------------
}
//--------------------------------------------------------------------------------------------------------------------------