package ui;

import exceptions.FileException;
import model.CustomFile;
import model.PeopleFile;
import model.ScheduleFile;
import tools.PostAssigner;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    // For testing purposes
    static String sLoadPath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\ScheduleInput.txt";
    static String savePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\ScheduleOutput.txt";

    static String pLoadPath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\PeopleInput.txt";

    static String pSavePath = "D:\\Programming Projects\\Intellij IDEA Workspace\\UBC CPSC 210\\Personal Project\\"
            + "project_scheng20\\storage\\PeopleOutput.txt";

    // Scanner for collecting user input
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, FileException {

        // Some variables
        String filePath;

        //Intro
        System.out.println("Welcome to schedule assigner! \n");

        // Ask for user input of data
        System.out.println("Please enter the location of the people file you want to load: ");
        PeopleFile p = new PeopleFile();
        filePath = input.nextLine();
        loadTo(filePath, p);

        System.out.println("\n");


        System.out.println("Please enter the location of the schedule file you want to load: ");
        ScheduleFile s = new ScheduleFile();
        filePath = input.nextLine();
        loadTo(filePath, s);

        System.out.println("Assign these people to the given schedule? (Y/N)");
        runAssignment(p, s);

    }

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

    public static void runAssignment(PeopleFile p, ScheduleFile s) {

        PostAssigner machine = new PostAssigner(p,s);
    }

}
