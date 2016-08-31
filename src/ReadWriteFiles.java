import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;


public class ReadWriteFiles {

    //final static String writaAFile = "C:\\Users\\karas_000\\Desktop\\ReadWriteJava\\calculatorMemory.txt";
    //final static String OUTPUT_FILE_NAME = "C:\\Temp\\output.txt";
    final static Charset ENCODING = StandardCharsets.UTF_8;
    private static int numberOfLines = 1;
    private static ArrayList<Double> arrayOfFile;



    void readTextFile(String aFileName) throws IOException {
        Path path = Paths.get(aFileName);
        ArrayList<Double> readLines = new ArrayList();
        try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)){
            String line = null;
            while ((line = reader.readLine()) != null){ //
                //log(line);
                double doubleLine = Double.parseDouble(line);
                readLines.add(doubleLine);
                numberOfLines++;
            }
            arrayOfFile = readLines;
        }
    }
    public static int getNumberOfLines(){
        return numberOfLines - 1;
    }
    public static ArrayList getArrayOfFile(){
        return arrayOfFile;
    }

    void displayArrayOfFile(){
        System.out.println(arrayOfFile);

    }

    void writeTextFile(String writaAFile, List<Double> aLines) throws IOException {
        Path path = Paths.get(writaAFile);
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
            for(Double line : aLines){
                writer.write(Double.toString(line));
                writer.newLine();
            }
        }
    }

    public static void open(File document) throws IOException {
        Desktop dt = Desktop.getDesktop();
        dt.open(document);
    }


    private static void log(Object aMsg){
        System.out.println(String.valueOf(aMsg));
    }

}
