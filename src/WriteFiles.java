// File writing operations for the task
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFiles {

    /**
     * Creates the output file at the specified
     * @param path , and
     * @return boolean true if file created or
     * false when it already exists.
     */
    public static boolean createFile(String path) {
        try {
            File emailList = new File(path);
            if(emailList.createNewFile()) {
                System.out.println("Output file created: " + emailList.getName());
                return true;
            } else {
                System.out.println("Output file already exists");
                return false;
            }
        } catch(IOException e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * To write the finalised String list into the file specified via the
     * @param path String.
     * @param data The ArrayList for the file writing process.
     */
    public static void writeDataToFile(String path, ArrayList<String> data) {
        try {
            System.out.println("Writing data to file");
            FileWriter csvWriter = new FileWriter(path);
            csvWriter.write("Vorname; Nachname; ID; e-Mail \n");
            for(String str: data) {
                csvWriter.write(str);
            }
            csvWriter.flush();
            csvWriter.close();
        } catch(IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    /**
     * To delete all content of a file specified via
     * @param path String.
     */
    public static void deleteFileContent(String path) {
        try {
            System.out.println("Deleting previous file content");
            new FileWriter(path, false).close();
        } catch(IOException e) {
            System.out.println("Exception occurred");
        }
    }
}
