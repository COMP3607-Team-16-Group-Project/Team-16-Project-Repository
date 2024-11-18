//--------------------------------
//ClassRelationship Class:
//--------------------------------




//-------------------
//Maven Package Name:
//-------------------
package com.example;
//-------------------




//-------------------------
//Import Statements:
//-------------------------
import java.io.File;
import java.nio.file.Files;
import java.util.List;
//-------------------------




//--------------------------------------------------------------------------------------------------------------------------
//Class Body:
//--------------------------------------------------------------------------------------------------------------------------
public class ClassRelationship implements Evaluation{
    //----------------------------------------------------------------------------------------------------------------------
    //Attributes:
    //----------------------------------------------------------------------------------------------------------------------
    private String feedback;
    //----------------------------------------------------------------------------------------------------------------------


    

    //----------------------------------------------------------------------------------------------------------------------
    //Methods:
    //----------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //Evaluate Method:
    //---------------------------------------------------------------------------------------------
    @Override
    public int evaluate(File javaFile){
        try{
            List<String> lines = Files.readAllLines(javaFile.toPath());
            boolean hasInheritance = false;
            boolean implementsInterface = false;

            for(String line : lines){
                // Check for inheritance (e.g., extends)
                if(line.contains("extends ")){
                    hasInheritance = true;
                }

                // Check for interface implementation (e.g., implements)
                if(line.contains("implements ")){
                    implementsInterface = true;
                }
            }

            if(!hasInheritance && !implementsInterface){
                feedback = "Class does not extend a parent class or implement an interface.";
                return 5; // Deduct points for missing relationships
            }

            if(hasInheritance && implementsInterface){
                feedback = "Class correctly extends a parent class and implements an interface.";
                return 10; // Full points
            }

            if(hasInheritance){
                feedback = "Class extends a parent class but does not implement an interface.";
                return 8; // Partial points
            }

            feedback = "Class implements an interface but does not extend a parent class.";
            return 7; // Partial points
        }catch(Exception e){
            feedback = "Error reading file: " + e.getMessage();
            return 0; // No points if an error occurs
        }
    }
    //---------------------------------------------------------------------------------------------




    //---------------------------------------------------------------------------------------------
    //Getter Methods:
    //---------------------------------------------------------------------------------------------
    @Override
    public String getFeedback(){
        return feedback;
    }
    //---------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
}
//--------------------------------------------------------------------------------------------------------------------------
