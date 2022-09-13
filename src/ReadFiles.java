import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReadFiles {

    public static boolean getFileInfo(String pathFirstNames, String pathLastNames) {
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
}
