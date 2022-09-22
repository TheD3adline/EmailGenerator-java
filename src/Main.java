/*
*   Author:     Michael Fessler
*   Date:       2022/09/14
*   Version:    0.4
*               Reads two specified .rtf files, loads their data into
*               ArrayLists, forms .csv writeable strings from them
*               and then writes the shoveled around data into a .csv file.
*/
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Main {
    static final String pathFirstNames = "input\\firstnames.rtf";
    static final String pathLastNames = "input\\lastnames.rtf";
    static final String pathOutput = "output\\EmailFile.csv";
    static ArrayList<String> firstNamesList = new ArrayList<>();
    static ArrayList<String> lastNamesList = new ArrayList<>();
    static ArrayList<String> emailList = new ArrayList<>();
    static ArrayList<String> rdyToWriteList = new ArrayList<>();
    static Random rng = new Random();
    static ArrayList<Integer> fRandomIndexes = new ArrayList<>();
    static ArrayList<Integer> lRandomIndexes = new ArrayList<>();
    static ArrayList<String> IDNumbers = new ArrayList<>();
    static HashSet<String> IDSet = new HashSet<>();
    static String[] domains = {"@gmx.at", "@yahoo.com", "@icloud.com",
                                "@gmx.net", "@gmail.com", "@qualifizierung.at",
                                "@bbrz.at", "@gov.at", "@me.com", "@ams.at",
                                "@private-relay.com", "@discord.com"};

    public static void main(String[] args) throws IOException {

        if(ReadFiles.getFileInfo(pathFirstNames, pathLastNames)) {
            ReadFiles.readTextFiles(pathFirstNames, firstNamesList);
            ReadFiles.readTextFiles(pathLastNames, lastNamesList);
            for (int i = 0; i < 8000; i++) {
                emailList.add(makeEmails());
                rdyToWriteList.add(convertToCSV(i));
            }
            if(WriteFiles.createFile(pathOutput)) {
                WriteFiles.writeDataToFile(pathOutput, rdyToWriteList);
            } else {
                WriteFiles.deleteFileContent(pathOutput);
                WriteFiles.writeDataToFile(pathOutput, rdyToWriteList);
            }
        }
    }

    public static String makeEmails() {
        int firstNamesIndex = rng.nextInt(18238);
        fRandomIndexes.add(firstNamesIndex);

        int lastNamesIndex = rng.nextInt(88798);
        lRandomIndexes.add(lastNamesIndex);

        String IDNumber;
        while (true) {
            IDNumber = getIDNumber(rng.nextInt(1, 9999));
            if (IDSet.add(IDNumber)) {
                IDNumbers.add(IDNumber);
                break;
            }
        }

        int randomDomainIndex = rng.nextInt(10);

        return firstNamesList.get(firstNamesIndex) + "_" +
                lastNamesList.get(lastNamesIndex) + IDNumber +
                domains[randomDomainIndex];
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
        return firstNamesList.get(fRandomIndexes.get(index)) + "; " +
                lastNamesList.get(lRandomIndexes.get(index)) + "; " +
                IDNumbers.get(index) + "; " + emailList.get(index) + "\n";
    }
}