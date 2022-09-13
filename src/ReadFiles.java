import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReadFiles {

    private static final String pathFirstNames = "input\\firstnames.rtf";
    private static final String pathLastNames = "input\\lastnames.rtf";

    public static boolean getFileInfo() {
        if(Files.exists(Path.of(pathFirstNames))) {
            if(Files.exists(Path.of(pathLastNames))) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> saveToContainer(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        ArrayList<String> tempList = new ArrayList<>();
        String curLine;
        int cnt = 1;
        while(((curLine = reader.readLine()) != null) && (cnt <= 8000)) {
            tempList.add(curLine);
            cnt++;
        }
        return tempList;
    }
    public static String getPathFirstNames() {
        return pathFirstNames;
    }
    public static String getPathLastNames() {
        return pathLastNames;
    }
}
