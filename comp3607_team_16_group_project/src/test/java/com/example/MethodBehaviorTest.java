//-------------------------------
//MethodBehaviorTest Class:
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
import static org.junit.jupiter.api.Assertions.*;
//-----------------------------------------------




//--------------------------------------------------------------------------------------------------------------------------
//Class Body:
//--------------------------------------------------------------------------------------------------------------------------
class MethodBehaviorTest {
    //----------------------------------------------------------------------------------------------------------------------
    //Declare Copy of MethodBehavior Class:
    //----------------------------------------------------------------------------------------------------------------------
    private MethodBehavior strategy;
    //----------------------------------------------------------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------------------------------
    //Initialize Copy of MethodBehavior Class:
    //----------------------------------------------------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        strategy = new MethodBehavior();
    }
    //----------------------------------------------------------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------------------------------
    //Unit Tests:
    //----------------------------------------------------------------------------------------------------------------------
    @Test
    void testEvaluateWithValidMethod() {
        File validFile = new File("ValidClass.java"); // Pretend this file has valid methods.
        int score = strategy.evaluate(validFile);
        assertEquals(10, score); // Assuming full score is 10 for correct methods.
    }

    @Test
    void testEvaluateWithInvalidMethod() {
        File invalidFile = new File("InvalidClass.java"); // Pretend this file has method errors.
        int score = strategy.evaluate(invalidFile);
        assertTrue(score < 10); // Score should be reduced for invalid methods.
    }

    @Test
    void testFeedbackGenerated() {
        File invalidFile = new File("InvalidClass.java");
        strategy.evaluate(invalidFile);
        String feedback = strategy.getFeedback();
        assertNotNull(feedback);
        assertFalse(feedback.isEmpty());
    }
    //----------------------------------------------------------------------------------------------------------------------
}
//--------------------------------------------------------------------------------------------------------------------------
