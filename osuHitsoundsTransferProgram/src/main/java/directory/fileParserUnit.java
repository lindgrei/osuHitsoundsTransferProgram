package directory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fileParserUnit {
    public static List<String> readFile(File file) {
        List<String> hitsounds = new ArrayList<>();

        try {
            File myObj = new File(file.getPath());
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    hitsounds.add(myReader.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        return hitsounds;
    }

    public static String readOneLine(File file) {
        StringBuilder temp = new StringBuilder();
        try {
            File myObj = new File(file.getPath());
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                temp.append(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("an error occured");
        }

        return temp.toString();
    }

    public static void WriteToFile(String path, String message) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }


    public static String CreateTempFiles(String name) throws IOException{
        ArrayList<String> paths = new ArrayList<>();
        File f = new File( System.getProperty("user.home") + String.format("/Documents/OHSTP/%s.txt", name ));

        System.out.println(f.getAbsolutePath());

        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
            paths.add(f.getAbsolutePath());
            System.out.println("creating dirs"+ f.getName());
        }else {System.out.println("Already Exists" + f.getName());paths.add(f.getAbsolutePath());}
        if (!f.exists()) {
            f.createNewFile();
            System.out.println("Creating file"+ f.getName());
            paths.add(f.getAbsolutePath());
        }else {System.out.println("Already Exists" + f.getName());}


        return paths.get(0);
    }



}
