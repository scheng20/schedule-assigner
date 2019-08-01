package ui;

import exceptions.FileException;
import model.CustomFile;
import model.PeopleFile;
import model.ScheduleFile;
import tools.PostAssigner;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    // TODO: Remove testPaths in final version of program
    // For testing purposes
    static String sLoadPath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\BizTechScheduleInput.txt";

    static String savePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\ScheduleOutput.txt";

    static String pLoadPath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\BizTechPeopleInput.txt";

    static String pSavePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\PeopleOutput.txt";

    // Scanner for collecting user input
    static Scanner input = new Scanner(System.in);

    // Current UI of the program
    public static void main(String[] args) throws IOException, FileException {

        // Some variables
        String filePath;

        //Intro
        System.out.println("Welcome to schedule assigner! \n");

        // Ask for user input of data
        System.out.println("Please enter the location of the people file you want to load: ");
        PeopleFile p = new PeopleFile();

        // TODO: change the loadPath input once testing is done!
        //filePath = input.nextLine();
        filePath = pLoadPath;

        loadTo(filePath, p);

        System.out.println("\n");


        System.out.println("Please enter the location of the schedule file you want to load: ");
        ScheduleFile s = new ScheduleFile();

        // TODO: change the loadPath input once testing is done!
        //filePath = input.nextLine();
        filePath = sLoadPath;

        loadTo(filePath, s);

        System.out.println("Assign these people to the given schedule? (Y/N)");
        runAssignment(p, s);

    }

    // EFFECTS: Loads the given filePath's content into the given CustomFile;
    //          Catches and handles any exceptions the file loading process may throw
    public static void loadTo(String filePath, CustomFile fileType) {

        while (true) {
            try {
                fileType.readFile(filePath);
                fileType.printContents();
                break;

            } catch (Exception e) {
                fileType.handleException(e);
                filePath = input.nextLine();
            }

        }

    }

    // EFFECTS: Computes the assignment of the people to the schedule
    public static void runAssignment(PeopleFile p, ScheduleFile s) {

        PostAssigner machine = new PostAssigner(p,s);
        machine.assignPosts();
    }

}
