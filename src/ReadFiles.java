// File reading operations for the task
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFiles {

    /**
     * Checks if both .rtf files with the names for the task exist.
     * @param pathFirstNames Path to file with first names.
     * @param pathLastNames Path to file with last names.
     * @return boolean true if !!!both!!! files exist, else false.
     */
    public static boolean getFileInfo(String pathFirstNames, String pathLastNames) {
        return (Files.exists(Path.of(pathFirstNames))) && (Files.exists(Path.of(pathLastNames)));
    }

    /**
     * Reads the data of the file specified via the String
     * @param path and writes their data line for line into
     * @param listToAdd ArrayList.
     */
    public static void readTextFiles(String path, ArrayList<String> listToAdd)
    {
        try {
            File tempFile = new File(path);
            Scanner reader = new Scanner(tempFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                data = data.replace("\\", "");
                data = data.replace("\n", "");
                listToAdd.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
            e.printStackTrace();
        }
    }
}
