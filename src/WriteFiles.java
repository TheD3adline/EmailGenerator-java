// Contains the file writing operations for the task
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class WriteFiles {

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
            return false;
        }
    }
    public static void writeDataToFile(String path, String data) {
        try {
            FileWriter csvWriter = new FileWriter(path);
            csvWriter.append(data);
            csvWriter.flush();
            csvWriter.close();
        } catch(IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
