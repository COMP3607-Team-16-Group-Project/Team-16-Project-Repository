//--------------------------
//SubmissionProcessor Class:
//--------------------------




//-------------------
//Maven Package Name:
//-------------------
package com.example;
//-------------------




//----------------------
//Import Statements:
//----------------------
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;
//----------------------




//--------------------------------------------------------------------------------------------------------------------------
//Class Body:
//--------------------------------------------------------------------------------------------------------------------------
public class SubmissionProcessor {
    //----------------------------------------------------------------------------------------------------------------------
    //Attributes:
    //----------------------------------------------------------------------------------------------------------------------
    private final List<File> javaFiles = new ArrayList<>();
    //----------------------------------------------------------------------------------------------------------------------




    //----------------------------------------------------------------------------------------------------------------------
    //Methods:
    //----------------------------------------------------------------------------------------------------------------------
    public void processSubmission(File assignmentFile) throws IOException {
        Path tempDir = Files.createTempDirectory("submission_");
        unzip(assignmentFile, tempDir.toFile());
        collectJavaFiles(tempDir.toFile());
    }

    private void unzip(File assignmentFile, File targetDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(assignmentFile))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File newFile = new File(targetDir, entry.getName());
                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    new File(newFile.getParent()).mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }

    private void collectJavaFiles(File dir) {
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    collectJavaFiles(file);
                } else if (file.getName().endsWith(".java")) {
                    javaFiles.add(file);
                }
            }
        }
    }

    public List<File> getJavaFiles() {
        return javaFiles;
    }
    //----------------------------------------------------------------------------------------------------------------------
}
//--------------------------------------------------------------------------------------------------------------------------
