import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        String pathFirstNames = "src\\firstnames.rtf";

        ArrayList<String> firstNamesList = new ArrayList<>();

        ArrayList<String> lastNamesList = new ArrayList<>();

        if(ReadFiles.getFileInfo()) {
            ReadFiles.saveToContainer(pathFirstNames, firstNamesList);
        }

        for(int i = 0; i < 10; i++) {
            System.out.println(firstNamesList.get(0));
        }
    }
}