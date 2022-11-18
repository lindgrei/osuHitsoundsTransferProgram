package directory;

import gui.OHSTP;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
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
        CheckIfFileExists(path);
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void CheckIfFileExists(String path) throws IOException {
        File f = new File(path);
        f.mkdir();

    }

    public static void write(String path) {

        InputStream stream = fileParserUnit.class.getResourceAsStream(path);
        assert stream != null;
        System.out.println(stream.toString());

    }

    public static void JsonWriter(String path, String key, String value) throws IOException {
        CheckIfFileExists(path);
        JSONObject j = new JSONObject();
        j.put(key, value);

        try (FileWriter file = new FileWriter(path)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(j.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String JsonReader(String path, String key) {
        JSONParser jsonParser = new JSONParser();

        try {
            Object o = jsonParser.parse(new FileReader(path));

            JSONObject j = (JSONObject) o;

            return (String) j.get(key);

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
