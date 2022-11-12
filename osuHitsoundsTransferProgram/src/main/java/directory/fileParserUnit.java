package directory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class fileParserUnit {
    public static List<String> readFile(File file){
        List<String> hitsounds = new ArrayList<>();

        try {
            File myObj = new File(file.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                hitsounds.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return hitsounds;
    }

    public static String readOneLine(File file){
           String temp = "";
        try{
        File myObj = new File(file.getPath());
        Scanner reader = new Scanner(myObj);
            while(reader.hasNextLine()){
            temp += reader.nextLine();
            }
             }
        catch(FileNotFoundException e){
            System.out.println("an error occured");}

        return temp;
    }


    public static void WriteToFile(File file, String message) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
