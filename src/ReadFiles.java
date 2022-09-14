// Contains the file reading operations for the task
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReadFiles {

    /**
     * Checks if both .rtf files with the names exist
     * @param pathFirstNames Path to file with first names
     * @param pathLastNames Path to file with last names
     * @return boolean true if !!!both!!! files exist, else false
     */
    public static boolean getFileInfo(String pathFirstNames, String pathLastNames) {
        return (Files.exists(Path.of(pathFirstNames))) && (Files.exists(Path.of(pathLastNames)));
    }

    /**
     * Reads the specified file line by line and writes them line by line as String into the ArrayList
     * @param path File location
     * @return ArrayList<String> tempList
     * @throws IOException If file can't be found or is otherwise invalid
     */
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
