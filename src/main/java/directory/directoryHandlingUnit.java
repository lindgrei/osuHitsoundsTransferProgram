package directory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static directory.fileParserUnit.readFile;

public class directoryHandlingUnit {
    public static String getSkinName(String dir) {
        String[] arr = dir.split("/");
        return arr[arr.length - 1];
    }

    public static List<File> checkValidFiles(String dir, String choice) {
        File[] files = new File(dir).listFiles();
        List<File> validFiles = new ArrayList<>();


        switch (choice) {
            case "hitsounds":
            case "h":
                List<String> hitsounds = readFile(new File("src/main/java/directory/hitsounds.txt"));
                for (File file : Objects.requireNonNull(files)) {

                    if (FilenameUtils.getExtension(String.valueOf(file)).equals("mp3") || FilenameUtils.getExtension(String.valueOf(file)).equals("ogg") || FilenameUtils.getExtension(String.valueOf(file)).equals("wav")) {
                        if (hitsounds.contains(getSkinName(file.getName())) || file.getName().contains("soft") || file.getName().contains("normal") || file.getName().contains("hit") || file.getName().contains("combobreak")) {
                            validFiles.add(file);
                        }
                    }

                }
                break;
            case "interface":
            case "i":
                List<String> interfacee = readFile(new File("src/main/java/directory/interface.txt"));
                for (File file : Objects.requireNonNull(files)) {
                    if (FilenameUtils.getExtension(String.valueOf(file)).equals("mp3") || FilenameUtils.getExtension(String.valueOf(file)).equals("ogg") || FilenameUtils.getExtension(String.valueOf(file)).equals("wav")) {
                        if (interfacee.contains(getSkinName(file.getName()))) {
                            validFiles.add(file);
                        }
                    }
                }

                break;
            case "a":
            case "all":
                for (File file : Objects.requireNonNull(files)) {
                    if (FilenameUtils.getExtension(String.valueOf(file)).equals("mp3") || FilenameUtils.getExtension(String.valueOf(file)).equals("ogg") || FilenameUtils.getExtension(String.valueOf(file)).equals("wav")) {
                        validFiles.add(file);
                    }
                }
                break;
        }


        return validFiles;
    }

    //TODO: fix truncating the files
    
    public static void truncate(String dir, String choice) throws IOException {
        String str_target = dir + "/old/";
        File directory = new File(dir);
        File[] filesList = directory.listFiles();

        Files.createDirectories(Path.of(str_target));

//        Scanner sc = new Scanner(System.in);
//        System.out.println("What do you want to transfer?  hitsounds (h) or interface (i) sound files? or end (e) ");
//        String choice = sc.nextLine().toLowerCase().strip();

        //todo replace scanner by radio 
        
         List<File> audioFiles = new ArrayList<>();
            
        for (File file : Objects.requireNonNull(filesList)) {
            System.out.println(file.getPath());
            if(FilenameUtils.getExtension(String.valueOf(file)).equals("mp3") || FilenameUtils.getExtension(String.valueOf(file)).equals("ogg") || FilenameUtils.getExtension(String.valueOf(file)).equals("wav")){
                audioFiles.add(file);
            }
        }

         Path result = null;
            try {
                for (File file : filesList) {
                    

                    if (checkValidFiles(dir, choice).contains(file)) {
                        result = Files.move(Paths.get(file.getPath()), Paths.get(str_target + file.getName()));
                        System.out.println("Moved " + file.getName() + " to " + str_target);
                    }
                    else if(audioFiles.isEmpty()){System.out.println("Dir has no audio files"); result = Path.of(dir);}
                }
            } catch (IOException e) {
                System.out.println("Exception while moving file: " + e.getMessage());
            }
            if (result != null) {
                System.out.println("File moved successfully.");
            } else {
                System.out.println("File movement failed.");
            }
            
    }
    
    
    private static List<File> FindCommonFiles(String srcDir, String trgtDir, String choice) {
        List<File> srcList = checkValidFiles(srcDir, choice);
        List<File> trgtList = checkValidFiles(trgtDir, choice);
        List<File> commList = new ArrayList<>();
        for (File fileA : srcList) {
    if (fileA.isFile()) 
    {
        for (File fileB : trgtList) {
             if(fileB.isFile()) 
             {
                if(fileA.getName().replaceAll("\\d","").contains(fileB.getName().replaceAll("\\d","")) || fileB.getName().replaceAll("\\d","").contains(fileA.getName().replaceAll("\\d","")) || srcList.isEmpty() || trgtDir.isEmpty()){commList.add(fileA);}
             }
         } 
    }
}
        return commList;
    }
    
    public static void MoveFiles(String srcDir , String trgtDir, String choice) throws IOException{

        File directory = new File(trgtDir);
        File[] filesList = directory.listFiles();
        
        List<File> audioFiles = new ArrayList<>();
            
        for (File file : Objects.requireNonNull(filesList)) {
            if(FilenameUtils.getExtension(String.valueOf(file)).equals("mp3") || FilenameUtils.getExtension(String.valueOf(file)).equals("ogg") || FilenameUtils.getExtension(String.valueOf(file)).equals("wav")){
                audioFiles.add(file);
            }
        }
        
        List<File> commonFiles;
        
        if (!audioFiles.isEmpty() && !(audioFiles.size() < 10)) {
            commonFiles = FindCommonFiles(srcDir, trgtDir, choice);
        }else{
            commonFiles = checkValidFiles(srcDir, choice);
        } 
        

        for(File file: commonFiles){

            FileUtils.copyFileToDirectory(new File(file.getAbsolutePath()), new File(trgtDir + "/"));
            System.out.println("Moved File from: "+file.getAbsolutePath()+" to " + trgtDir +"/"+ file.getName());
        }
    }

    

       


}
