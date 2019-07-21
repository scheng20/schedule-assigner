package ui;

import exceptions.FileException;
import model.PeopleFile;
import model.ScheduleFile;
import tools.PostAssigner;

import java.io.FileNotFoundException;
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

    public static void main(String[] args) throws IOException {

        //Intro
        System.out.println("Welcome to schedule assigner! \n");

        // Ask for user input of data
        System.out.println("Please enter the location of the people file you want to load: ");
        PeopleFile p = loadPeopleFile();
        System.out.println("\n");

        System.out.println("Please enter the location of the schedule file you want to load: ");
        ScheduleFile s = loadScheduleFile();
        System.out.println("\n");

        System.out.println("Assign these people to the given schedule? (Y/N)");

    }

    public static PeopleFile loadPeopleFile() {

        String filePath = input.nextLine();

        // Instantiate a people file
        PeopleFile peeps = new PeopleFile();

        while (true) {
            try {
                peeps.readFile(filePath);
                peeps.printContents();
                break;

            } catch (FileNotFoundException e) {
                peeps.handleException(e);
                filePath = input.nextLine();

            } catch (FileException e) {
                peeps.handleException(e);
                filePath = input.nextLine();
            } finally {
                System.out.println("Working on loading the file's contents...");
            }

            // ADD TESTS FOR EXCEPTIONS!
        }

        return peeps;
    }

    public static ScheduleFile loadScheduleFile() {

        String filePath = input.nextLine();

        // Instantiate a people file
        ScheduleFile schedule = new ScheduleFile();

        while (true) {
            try {
                schedule.readFile(filePath);
                schedule.printContents();
                break;

            } catch (FileNotFoundException e) {
                schedule.handleException(e);
                filePath = input.nextLine();

            } catch (FileException e) {
                schedule.handleException(e);
                filePath = input.nextLine();
            } finally {
                System.out.println("Working on loading the file's contents...");
            }
        }

        return schedule;

    }

    public void runAssignment(PeopleFile p, ScheduleFile s) {

        PostAssigner machine = new PostAssigner(p,s);
    }

}
