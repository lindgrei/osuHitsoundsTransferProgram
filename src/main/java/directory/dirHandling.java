package directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

import FilesHandler.fileHandling;

public class dirHandling {
    public static String getSkinName(String dir){
        String[] arr = dir.split("/");
        return arr[arr.length - 1];
    }

    public static List<File> checkValidFiles(String dir){
        File[] files = new File(dir).listFiles();
        List<File> validFiles= new ArrayList<>();
        for(File file : Objects.requireNonNull(files)){
            if (file.isFile() && Arrays.asList(fileHandling.HITSOUNDS).contains(getSkinName(file.getName()))){validFiles.add(file);
                System.out.println(file.getName());}


        }


        return validFiles;
    }



    public static void truncate(String dir) throws IOException{
        String str_target = dir+"/old/";
        File directory = new File(dir);
        File[] filesList = directory.listFiles();

        Files.createDirectories(Path.of(str_target));

        for(File file: Objects.requireNonNull(filesList))
        {
            System.out.println(file.getPath());
        }

        Path result = null;
        try
        {
            for(File file:filesList)
            {

                if (!checkValidFiles(dir).contains(file)) {
                    continue;
                }
                result = Files.move(Paths.get(file.getPath()), Paths.get(str_target+ file.getName()));

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
    }


    public static void main(String[] args) throws IOException {
        truncate("/Users/ayman/Downloads/morg+green+skin");

    }
}
