import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<String> firstNamesList = new ArrayList<>();
        ArrayList<String> lastNamesList = new ArrayList<>();

        if(ReadFiles.getFileInfo()) {
            try {
                firstNamesList = ReadFiles.saveToContainer(ReadFiles.getPathFirstNames());
                lastNamesList = ReadFiles.saveToContainer(ReadFiles.getPathLastNames());
            } catch(IOException e) {
                System.out.println("IO Exception occurred");
                e.printStackTrace();
            }
        }

        for(int i = 0; i < 10; i++) {
            System.out.println(firstNamesList.get(i));
        }
        System.out.println();
        System.out.println();
        for(int i = 0; i < 10; i++) {
            System.out.println(lastNamesList.get(i));
        }
    }
}