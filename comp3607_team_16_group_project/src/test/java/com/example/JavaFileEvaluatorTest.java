//-------------------------------
//JavaFileEvaluatorTest Class:
//-------------------------------



//-------------------
//Maven Package Name:
//-------------------
package com.example;
//-------------------




//-----------------------------------------------
//Import Statements:
//-----------------------------------------------
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
//-----------------------------------------------




//--------------------------------------------------------------------------------------------------------------------------
//Class Body:
//--------------------------------------------------------------------------------------------------------------------------
class JavaFileEvaluatorTest {
    //----------------------------------------------------------------------------------------------------------------------
    //Declare Copy of JavaFileEvaluator Class:
    //----------------------------------------------------------------------------------------------------------------------
    private JavaFileEvaluator evaluator;
    //----------------------------------------------------------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------------------------------
    //Create mock Java files for testing:
    //----------------------------------------------------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        List<File> javaFiles = List.of(
                new File("src/test/resources/ValidClass.java"),
                new File("src/test/resources/InvalidClass.java")
        );
        evaluator = new JavaFileEvaluator(javaFiles);
    }
    //----------------------------------------------------------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------------------------------
    //Unit Tests:
    //----------------------------------------------------------------------------------------------------------------------
    @Test
    void testEvaluateFiles() {
        evaluator.evaluate();
        Map<File, Map<String, Integer>> results = evaluator.getDetailedResults(); // Corrected type

        assertEquals(2, results.size(), "There should be two files evaluated.");
        assertTrue(results.containsKey(new File("src/test/resources/ValidClass.java")));
        assertTrue(results.containsKey(new File("src/test/resources/InvalidClass.java")));

        // Validate nested map structure
        for (Map.Entry<File, Map<String, Integer>> entry : results.entrySet()) {
            Map<String, Integer> strategyScores = entry.getValue();
            assertNotNull(strategyScores, "Strategy scores should not be null.");
            assertFalse(strategyScores.isEmpty(), "Each file should have scores for strategies.");
            for (Map.Entry<String, Integer> strategyEntry : strategyScores.entrySet()) {
                assertTrue(strategyEntry.getValue() >= 0, "Scores should be non-negative.");
            }
        }
    }
    //----------------------------------------------------------------------------------------------------------------------
}
//--------------------------------------------------------------------------------------------------------------------------
