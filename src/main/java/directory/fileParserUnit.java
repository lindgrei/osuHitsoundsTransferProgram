package directory;

import java.io.File;
import java.io.FileNotFoundException;
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

}
