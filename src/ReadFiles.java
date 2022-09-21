// File reading operations for the task
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public static void readTextFiles(String path, ArrayList<String> ListToAdd)
    {
        try {
            File tempFile = new File(path);
            Scanner reader = new Scanner(tempFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                data = data.replace("\\", "");
                data = data.replace("\n", "");
                ListToAdd.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
            e.printStackTrace();
        }
    }
}
