package directory;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static directory.fileParserUnit.readFile;

public class directoryHandlingUnit {
    public static String getSkinName(String dir){
        String[] arr = dir.split("/");
        return arr[arr.length - 1];
    }

    public static List<File> checkValidFiles(String dir, String choice){
        File[] files = new File(dir).listFiles();
        List<File> validFiles= new ArrayList<>();




        if(choice.equals("hitsounds") || choice.equals("h")){
            List<String> hitsounds = readFile(new File("src/main/java/directory/hitsounds.txt"));
            for(File file : Objects.requireNonNull(files)){

                if (FilenameUtils.getExtension(String.valueOf(file)).equals("mp3") || FilenameUtils.getExtension(String.valueOf(file)).equals("ogg") || FilenameUtils.getExtension(String.valueOf(file)).equals("wav")){
                    if (hitsounds.contains(getSkinName(file.getName())) || file.getName().contains("soft")|| file.getName().contains("normal") || file.getName().contains("hit") || file.getName().contains("combobreak")){validFiles.add(file);}
                }

            }
        } else if (choice.equals("interface") || choice.equals("i")) {
            List<String> interfacee = readFile(new File("src/main/java/directory/interface.txt"));
            for(File file : Objects.requireNonNull(files)){
                if (FilenameUtils.getExtension(String.valueOf(file)).equals("mp3") || FilenameUtils.getExtension(String.valueOf(file)).equals("ogg") || FilenameUtils.getExtension(String.valueOf(file)).equals("wav")){
                    if (interfacee.contains(getSkinName(file.getName()))){validFiles.add(file);}
                }
            }

        }


        return validFiles;
    }

    public static void truncate(String dir) throws IOException{
        String str_target = dir+"/old/";
        File directory = new File(dir);
        File[] filesList = directory.listFiles();

        Files.createDirectories(Path.of(str_target));

        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to transfer?  hitsounds (h) or interface (i) sound files? or end (e) ");
        String choice = sc.nextLine().toLowerCase().strip();


        for(File file: Objects.requireNonNull(filesList))
        {
            System.out.println(file.getPath());
        }

       while (!choice.equals("e")){
           Path result = null;
           try
           {
               for(File file:filesList)
               {

                   if (checkValidFiles(dir, choice).contains(file)) {
                       result = Files.move(Paths.get(file.getPath()), Paths.get(str_target+ file.getName()));
                       System.out.println("Moved " + file.getName() + " to " + str_target);
                   }


               }
           }
           catch (IOException e)
           {
               System.out.println("Exception while moving file: " + e.getMessage());
           }
           if(result != null)
           {
               System.out.println("File moved successfully.");
           }
           else
           {
               System.out.println("File movement failed.");
           }
           System.out.println("What do you want to transfer too?  hitsounds (h) or interface (i) sound files? or end (e) ");
           choice = sc.nextLine().toLowerCase().strip();
       }
    }


    public static void main(String[] args) throws IOException {
        truncate("/Users/ayman/Downloads/morg+green+skin");

    }
}