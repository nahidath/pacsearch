package com.parsing.dbms.service.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parsing.dbms.service.business.Document;

import java.io.*;
import java.util.Scanner;

/**
 * Created bu PacLab
 * User: sami
 * Date: 2/9/2021
 * Time: 3:02 PM
 * Package: dbms.service.util
 * <p>
 * Class name: TransformCsvToJson
 * Class description: A TransformCsvToJson is a tool to transform Csv to Json.
 * Class members:
 * Class attributes: none
 * Class methods:
 * readCsvUsingBufferedReader :
 * use:
 * best use to get strings
 * note:
 * faster than readCsvUsingScanner
 * as it only reads one character per stream (1 byte per call)
 * readCsvUsingScanner :
 * use:
 * best use to get specific data types or regular expressions from csv file
 * note:
 * slower than using readCsvUsingBufferedReader
 */
public class TransformCsvToJson implements Closeable {
    // TODO: first line describes the terms
    // TODO: if empty line then skip
    // TODO: if empty value then empty String
    // TODO: a document is a line of csv file

    /**
     * main : main entry of TransformCsvToJson class to test member methods of the class.
     *
     * @param args : an array of String meant to hold passed command line arguments.
     */
    public static void main(String[] args) {
        // debug info
        System.out.println("dbms.service.util.TransformCsvToJson : main call");
        // Csv file's path to test
        String testPath = "samples/test.csv";
        // readCsvUsingScanner test
        System.out.println("Calling readCsvUsingScanner :");
        if (readCsvUsingScanner(testPath) == -1) {
            System.out.println("Problem occurred during reading");
        } else {
            System.out.println("No problems were occurred during reading");
        } // if-else block end
        // readCsvUsingBufferedReader test
        System.out.println("Calling readCsvUsingBufferedReader :");
        if (readCsvUsingBufferedReader(testPath) == null) {
            System.out.println("Problem occurred during reading");
        } else {
            System.out.println("No problems were occurred during reading");
        } // if-else block end
    } // main end

    /**
     * readCsvUsingScanner :
     * static method to read csv file using Scanner.
     * returns number of lines redden from file, -1 if error.
     *
     * @param filePath : String that represents file path of the csv file.
     */
    private static long readCsvUsingScanner(String filePath) {
        // debug info
        System.out.println("dbms.service.util.TransformCsvToJson : readCsvUsingScanner call");

        // file
        File file = new File(filePath);

        // number of lines redden
        long reddenLinesNumber = 0;

        try {
            // use Scanner InputStream to open file
            Scanner scanner = new Scanner(file);

            // read file using Scanner InputStream
            while (scanner.hasNext()) {
                // TODO : option to neglect header if needed
                System.out.println("User data : " + scanner.next());
                ++reddenLinesNumber;
            } // while block end

            // close file using Scanner InputStream
            scanner.close();
        } catch (FileNotFoundException exception) {
            // debug info
            exception.printStackTrace();

            System.out.println("/!\\ Error while reading file /!\\");
        } finally {
            // custom message to inform user about the error
            String outputMessage = "";

            // file does not exist
            if (!file.exists()) {
                outputMessage += "\tThe file does not exist.";
                outputMessage += "\n\tPlease verify that the path of the file and that it exists :";
                File currentDirectory = new File(".");
                outputMessage += "\n\tCurrent directory : " + currentDirectory.getAbsolutePath();
                outputMessage += "\n\tThe given path is '" + filePath + "'";
            } // if-end block end

            // file is a directory
            if (file.isDirectory()) {
                outputMessage += "\tThe file is a directory.";
            } // if-end block end

            // file cannot be read
            if (!file.canRead()) {
                outputMessage += "\tThe file cannot be read.";
                outputMessage += "\tPlease verify that the read rights and that the file is not open by another program.";
            } // if-end block end

            // generic redden successfully or not file message and reddenLinesNumber update
            if (outputMessage.length() == 0) { // file was read successfully
                outputMessage += "File was read successfully";
                outputMessage += "\n" + reddenLinesNumber + " lines were redden";
            } else { // file was not read successfully
                reddenLinesNumber = -1;
            } // if-else block end

            // output message to user
            System.out.println(outputMessage);
        } // try-catch-finally block end

        return reddenLinesNumber;
    } // readCsvUsingScanner end

    /**
     * readCsvUsingBufferedReader :
     * static method to read csv file using BufferedReader.
     * returns number of lines redden from file, -1 if error.
     *
     * @param filePath : A String that represents file path of the csv file.
     */
    public static Document readCsvUsingBufferedReader(String filePath) {
        // TODO: do the same logic for file verification as ScannerRead
        // TODO: do the long return logic as ScannerRead
        Document document = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String reddenLine = reader.readLine();
            String delimiter = ","; // "," as csv file

            while (reddenLine != null) {

                System.out.println("Imported data : " + reddenLine);

                String[] importedData = reddenLine.split(delimiter);

                System.out.println("Document creation -> Waiting...");
                document = new Document(importedData);
                System.out.println("Document created -> OK");
                // V0 :

                // if we make something
                // something_type something = new Document(
                // importedData[0],
                // importedData[1],
                // ...
                // );

                // update reddenLine

                // if we can use maven dependency Gson + only one (remove the lines for all of them - below)
                System.out.println("Document (first print) : " + new Gson().toJson(document));
                reddenLine = reader.readLine();
            }
            // if we can use maven dependency Gson + all of them (remove the lines for one of them - top)
            System.out.println("Document (second print) : " + new GsonBuilder().setPrettyPrinting().create().toJson(document));
        } catch (IOException ioException) {
            // debug info
            ioException.printStackTrace();
            // placeholder
            System.out.println("Exception !");
        } finally {
            // placeholder
            System.out.println("it's not the end of the world :p");
        } // try-catch-finally block end
        /*
            catch (FileNotFoundException fileNotFoundException) { // FileReader constructor call
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) { // readLine constructor call
                ioException.printStackTrace();
            }
        */

        // placeholder return statement
        return document;
    } // readCsvUsingBufferedReader end

    @Override
    public void close() throws IOException {
        // TODO: implement logic and modify the rest
    } // close end
} // TransformCsvToJson end
