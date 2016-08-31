import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        String fileName;
        String fileType = "";
        String filePath = "";
        double userInput1 = 0;
        double userInput2 = 0;
        int ans = 0;
        char operation = 0;
        boolean anotherOperation = true;
        boolean continueUse = true;
        boolean firstInput = true;
        boolean secondInput = true;
        boolean operationInput = true;
        boolean useAnswer = false;
        boolean displayArray = true;
        boolean selectFromArray = false;
        Scanner scan = new Scanner(System.in);

        List<Double> storedResults = new ArrayList<Double>();

        System.out.println("\n*****Welcome to Calculator*****");

        while (anotherOperation) {
            System.out.println("Use saved data from file? Y/N");

            char q = scan.next().charAt(0);
            if (q == 'y' || q == 'Y') {

                int useSavedData = 0;
                while (useSavedData == 0) {
                    try {

                        File folder = new File("C:\\Users\\karas_000\\Desktop\\ReadWriteJava\\CalculatorStorage\\");
                        File[] listOfFiles = folder.listFiles();
                        for (int i = 0; i < listOfFiles.length; i++) {
                            if (listOfFiles[i].isFile()) {
                                System.out.println("File " + listOfFiles[i].getName());
                            } else if (listOfFiles[i].isDirectory()) {
                                System.out.println("Directory " + listOfFiles[i].getName());
                            }
                        }

                        System.out.println("Enter in file name without .txt extension: ");
                        fileName = scan.next();
                        filePath = "C:\\Users\\karas_000\\Desktop\\ReadWriteJava\\CalculatorStorage\\" + fileName + ".txt";
                        ReadWriteFiles readWriteFiles = new ReadWriteFiles();
                        readWriteFiles.readTextFile(filePath);
                        storedResults = ReadWriteFiles.getArrayOfFile();
                        useSavedData++;
                        useAnswer = true;
                    } catch (NoSuchFileException nsfe) {
                        System.out.println("Not a file that exists");
                    }
                }
                anotherOperation = false;

            } else if (q == 'n' || q == 'N') {

                anotherOperation = false;

            } else {
                System.out.println("Error: Please enter y or n");
                anotherOperation = true;
            }
            scan.nextLine();
        }
        anotherOperation = true;

        while (continueUse) {


            //System.out.println("\n*****Welcome to Calculator*****");
            while (firstInput) {

                if (useAnswer) {

                    ans++;
                    while (anotherOperation) {

                        int arraySelection = 0;

                        while (displayArray) {

                            System.out.println("Array of Data in Memory: " + storedResults + "\nUse Previous Answer From Memory? Y/N");

                            char q = scan.next().charAt(0);
                            if (q == 'y' || q == 'Y') {
                                displayArray = false;
                                boolean loop = true;
                                selectFromArray = true;
                                while (loop) {
                                    try {
                                        System.out.println("What array entry? (first entry corresponds to 1) ");
                                        arraySelection = scan.nextInt();
                                        if (arraySelection <= ans) {
                                            loop = false;
                                        }
                                    } catch (InputMismatchException ime) {
                                        System.out.println("Error: Input an int");
                                        scan.nextLine();
                                    }
                                    if (arraySelection > ans) {
                                        System.out.println("Error: Input outside array index");
                                    }
                                }
                            } else if (q == 'n' || q == 'N') {
                                displayArray = false;
                                selectFromArray = false;
                                anotherOperation = false;

                            } else {
                                System.out.println("Error: Please enter y or n");
                                displayArray = true;
                            }
                            scan.nextLine();
                        }
                        displayArray = true;

                        if (selectFromArray == true) {
                            System.out.println("Use Previous Answer: " + storedResults.get(arraySelection - 1) + " Y/N");

                            char q = scan.next().charAt(0);
                            if (q == 'y' || q == 'Y') {
                                userInput1 = storedResults.get(ans - 1);
                                anotherOperation = false;
                                firstInput = false;
                                System.out.println("First Input = " + userInput1);
                            } else if (q == 'n' || q == 'N') {
                                //anotherOperation = false;


                            } else {
                                System.out.println("Error: Please enter y or n");
                                anotherOperation = true;
                            }
                            scan.nextLine();
                        }
                    }
                    anotherOperation = true;
                }
                useAnswer = true;
                if (firstInput == true) {
                    try {
                        System.out.println("Enter in first number: ");
                        userInput1 = scan.nextDouble();
                        firstInput = false;
                        scan.nextLine();
                    } catch (InputMismatchException ime) {
                        System.out.println("Error: Enter a double value");
                        scan.nextLine();
                    }
                }
            }
            firstInput = true;

            while (operationInput) {
                System.out.println("Enter operation: + - * / ");
                operation = scan.next().charAt(0);
                switch (operation) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        operationInput = false;
                        scan.nextLine();
                        break;
                    default:
                        System.out.println("Error: Enter one of the accepted operations");
                        scan.nextLine();
                }
            }
            operationInput = true;

            while (secondInput) {
                try {
                    System.out.println("Enter in second number:");
                    userInput2 = scan.nextDouble();
                    secondInput = false;
                    scan.nextLine();
                } catch (InputMismatchException ime) {
                    System.out.println("Error: Enter a double value");
                    scan.nextLine();
                }
            }
            secondInput = true;

            switch (operation) {
                case '+':
                    AddOperation addOperation = new AddOperation(userInput1, userInput2, operation);
                    storedResults.add(addOperation.getResultOfOperation());
                    break;
                case '-':
                    SubtractOperation subtractOperation = new SubtractOperation(userInput1, userInput2, operation);
                    storedResults.add(subtractOperation.getResultOfOperation());
                    break;
                case '*':
                    MultiplyOperation multiplyOperation = new MultiplyOperation(userInput1, userInput2, operation);
                    storedResults.add(multiplyOperation.getResultOfOperation());
                    break;
                case '/':
                    DivideOperation divideOperation = new DivideOperation(userInput1, userInput2, operation);
                    storedResults.add(divideOperation.getResultOfOperation());
                    break;

            }
            System.out.println("Result saved in ArrayList: " + storedResults);


            while (anotherOperation) {
                System.out.println("Do another calculation? Y/N");

                char q = scan.next().charAt(0);
                if (q == 'y' || q == 'Y') {
                    anotherOperation = false;
                } else if (q == 'n' || q == 'N') {
                    anotherOperation = false;
                    continueUse = false;


                } else {
                    System.out.println("Error: Please enter y or n");
                    anotherOperation = true;
                }
                scan.nextLine();
            }
            anotherOperation = true;


        }

        while (anotherOperation) {
            System.out.println("Save Results? Y/N");

            char q = scan.next().charAt(0);
            if (q == 'y' || q == 'Y') {

                ReadWriteFiles readWriteFiles = new ReadWriteFiles();
                try {
                    fileType = ".txt";
                    //String fileName = "FileName";
                    System.out.println("Save Answer in File Path: C:\\Users\\karas_000\\Desktop\\ReadWriteJava\\CalculatorStorage\\ \nEnter in desired file name: ");
                    fileName = scan.next();
                    filePath = "C:\\Users\\karas_000\\Desktop\\ReadWriteJava\\CalculatorStorage\\" + fileName + fileType;
                    System.out.println("The file is saved here: " + filePath);
                    //readWriteFiles.writeTextFile(ReadWriteFiles.writaAFile, storedResults);
                    readWriteFiles.writeTextFile(filePath, storedResults);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                anotherOperation = false;

            } else if (q == 'n' || q == 'N') {
                anotherOperation = false;

            } else {
                System.out.println("Error: Please enter y or n");
                anotherOperation = true;
            }
            scan.nextLine();
        }
        anotherOperation = true;

        if (fileType == ".txt") {
            while (anotherOperation) {
                System.out.println("Open Saved File? Y/N");

                char q = scan.next().charAt(0);
                if (q == 'y' || q == 'Y') {

                    ReadWriteFiles readWriteFiles = new ReadWriteFiles();
                    File file = new File(filePath);
                    readWriteFiles.open(file);
                    anotherOperation = false;

                } else if (q == 'n' || q == 'N') {
                    anotherOperation = false;

                } else {
                    System.out.println("Error: Please enter y or n");
                    anotherOperation = true;
                }
                //scan.nextLine();
            }
        }
        anotherOperation = true;





    }


}
