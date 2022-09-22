/*
*   Author:     Michael Fessler
*   Date:       2022/09/14
*   Version:    0.4
*               Reads two specified .rtf files, loads their data into
*               ArrayLists, forms .csv writeable strings from them
*               and then writes the shoveled around data into a .csv file.
*/
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

        if(ReadFiles.getFileInfo(pathFirstNames, pathLastNames)) {      // if head to call .getFileInfo for a boolean
            ReadFiles.readTextFiles(pathFirstNames, firstNamesList);    // return if the files exist or not.
            ReadFiles.readTextFiles(pathLastNames, lastNamesList);      // And then read the files into 2 ArrayList.
            for (int i = 0; i < 8000; i++) {
                emailList.add(makeEmails());                                 // for to create 8000 random email addresses
                rdyToWriteList.add(convertToCSV(i));                         // from the data, and to convert them into
            }                                                                // .csv compatible strings saved separately
            if(WriteFiles.createFile(pathOutput)) {                          // into another ArrayList to prep it for the
                WriteFiles.writeDataToFile(pathOutput, rdyToWriteList);      // file writer.
            } else {
                WriteFiles.deleteFileContent(pathOutput);                    // if...else to determine if a new output
                WriteFiles.writeDataToFile(pathOutput, rdyToWriteList);      // file needs to be created or if the file
            }                                                                // already exists and then calls
        }                                                                    // .deleteFileContent() to wipe its content
    }                                                                        // before starting the writing process.

    /**
     * To randomly create the email addresses from the ArrayLists and save
     * their position index numbers into another ArrayList<Integer> as a register.
     * @return the concatenated strings to create the actual email addresses.
     */
    public static String makeEmails() {
        int firstNamesIndex = rng.nextInt(18238);           // Picks a random index number with bound being the
        fRandomIndexes.add(firstNamesIndex);                       // number of entries in the list and adds them to the
                                                                   // registry list.
        int lastNamesIndex = rng.nextInt(88798);           // Same for last names.
        lRandomIndexes.add(lastNamesIndex);

        String IDNumber;                                                    // Creates a random number to use as ID Number
        while (true) {                                                      // for the data sets.
            IDNumber = getIDNumber(rng.nextInt(1, 9999));
            if (IDSet.add(IDNumber)) {                                      // Adds them to a HashSet to check for duplicate
                IDNumbers.add(IDNumber);                                    // entries before storing them in the actual
                break;                                                      // ArrayList for use.
            }                                                               // This is repeated until a unique number is
        }                                                                   // found, because 8000 data sets would mean
                                                                            // a lot of duplicate ID numbers if the rng
        int randomDomainIndex = rng.nextInt(10);                    // range is 1 to 9999.

        return firstNamesList.get(firstNamesIndex) + "_" +                  // Selects a random domain from the string
                lastNamesList.get(lastNamesIndex) + IDNumber +              // array for domains, concatenates the
                domains[randomDomainIndex];                                 // different strings to form the email for
    }                                                                       // return.

    /**
     * Just to fix the formatting of the ID numbers, and have them always in 4 digits no matter if actual number is < 1000
     * @param IDNumber the integer being handed via the method call and to go through formatting.
     * @return the formatted ID number as a string.
     */
    public static String getIDNumber(int IDNumber) {
        if(IDNumber >= 0 && IDNumber <= 9)                  // if...else-if construct to add the appropriate amount of
            return "000" + IDNumber;                        // 0 (zero) characters to the randomly created ID numbers
        else if(IDNumber >= 10 && IDNumber <= 99)           // that are < 1000
            return "00" + IDNumber;
        else if(IDNumber >= 100 && IDNumber <= 999)
            return "0" + IDNumber;
        return IDNumber + "";
    }

    /**
     * To read and concatenate all the strings from the different ArrayLists and format them for writing into a .csv file
     * @param index integer to properly access the different lists, and the name lists with the help of their registry.
     * @return the finalised strings.
     */
    public static String convertToCSV(int index) {
        return firstNamesList.get(fRandomIndexes.get(index)) + "; " +
                lastNamesList.get(lRandomIndexes.get(index)) + "; " +
                IDNumbers.get(index) + "; " + emailList.get(index) + "\n";
    }
}