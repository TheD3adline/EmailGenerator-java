/*
* Author:   Michael Fessler
* Date:     14.9.2022
* Version:  0.3
* Reads two specified .rtf files, loads the data into several Lists and Arrays, then writes into a new .csv file
*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    static final String pathFirstNames = "input\\firstnames.rtf";
    static final String pathLastNames = "input\\lastnames.rtf";
    static final String pathOutput = "output\\EmailFile.csv";
    static ArrayList<String> firstNamesList = new ArrayList<>();
    static ArrayList<String> lastNamesList = new ArrayList<>();
    static ArrayList<String> emailList = new ArrayList<>();
    static Random rng = new Random();
    static ArrayList<Integer> fRandomIndexes = new ArrayList<>();
    static ArrayList<Integer> lRandomIndexes = new ArrayList<>();
    static ArrayList<String> IDNumbers = new ArrayList<>();
    static String[] domains = {"@gmx.at", "@yahoo.com", "@icloud.com", "@gmx.net", "@gmail.com", "@qualifizierung.at",
                               "@bbrz.at", "@gov.at", "@me.com", "@ams.at", "@private-relay.com", "@discord.com",};

    public static void main(String[] args) throws IOException {

        if(ReadFiles.getFileInfo(pathFirstNames, pathLastNames)) {
            ReadFiles.readTextFiles(pathFirstNames, firstNamesList);
            ReadFiles.readTextFiles(pathLastNames, lastNamesList);
            for(int i = 0; i < 8000; i++) {
                emailList.add(makeEmails());
            }
            if(WriteFiles.createFile(pathOutput)) {
                for(int i = 0; i < 8000; i++) {
                    WriteFiles.writeDataToFile(pathOutput, convertToCSV(i));
                }
            }
        }
        for(int i = 0; i < 10; i++) {
            System.out.println(firstNamesList.get(fRandomIndexes.get(i)) + ", " + lastNamesList.get(lRandomIndexes.get(i)) + ", " +
                    IDNumbers.get(i) + ", " + emailList.get(i));
        }

    }

    public static String makeEmails() {
        int firstNamesIndex = rng.nextInt(18238);
        fRandomIndexes.add(firstNamesIndex);

        int lastNamesIndex = rng.nextInt(88798);
        lRandomIndexes.add(lastNamesIndex);

        String IDNumber = getIDNumber(rng.nextInt(9999));
        IDNumbers.add(IDNumber);
        int randomDomainIndex = rng.nextInt(10);

        return firstNamesList.get(firstNamesIndex) + '_' +
                lastNamesList.get(lastNamesIndex) + IDNumber + domains[randomDomainIndex];
    }

    public static String getIDNumber(int IDNumber) {
        if(IDNumber >= 0 && IDNumber <= 9)
            return "000" + IDNumber;
        else if(IDNumber >= 10 && IDNumber <= 99)
            return "00" + IDNumber;
        else if(IDNumber >= 100 && IDNumber <= 999)
            return "0" + IDNumber;
        return IDNumber + "";
    }

    public static String convertToCSV(int index) {
        return firstNamesList.get(fRandomIndexes.get(index)) + ", " +
                lastNamesList.get(lRandomIndexes.get(index)) + ", " +
                IDNumbers.get(index) + ", " + emailList.get(index) + "\n";
    }
}