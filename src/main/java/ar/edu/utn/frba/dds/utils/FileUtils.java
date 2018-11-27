package ar.edu.utn.frba.dds.utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    final static String tempFile = "temps";

    public static Boolean isJsonFile(File f){
        return getExtensionFile(f).equals(".json");
    }
    public static Boolean isTxtFile(File f){
        return getExtensionFile(f).equals(".txt");
    }

    public static String getExtensionFile(File f){
        String name = f.getName();
        return f.getName().substring(getPositionExtension(name),f.getName().length());
    }

    private static int getPositionExtension(String name){
        return Integer.max(name.indexOf("."),0);
    }

    public static String filePath(Class classObj,String name) throws URISyntaxException {
        return fileWithName(classObj,name).getAbsolutePath().toString();
    }

    public static File fileWithName(Class classObj,String name) throws URISyntaxException {
        return new File(classObj.getResource(name).toURI());
    }

    public static Path pathTemp(String fileName) throws IOException {
        File file = new File(tempFile);
        if(!file.exists()){
            file.mkdir();
        }
        return Paths.get(tempFile + "/" + fileName);
    }

    public static File getFileWithPath(Part part) throws IOException {
        InputStream input = part.getInputStream();
        String fileName = part.getSubmittedFileName();
        Path out = pathTemp(fileName);
        Files.copy(input,out,StandardCopyOption.REPLACE_EXISTING);
        return out.toFile();
    }
}
