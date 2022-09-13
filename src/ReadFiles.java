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

    public static void saveToContainer(String path, ArrayList<String> temp) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String curLine;
        int cnt = 1;
        while(((curLine = reader.readLine()) != null) && (cnt <= 8000)) {
            temp.add(curLine);
            cnt++;
        }
    }
}
