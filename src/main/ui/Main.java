package ui;

import exceptions.FileException;
import model.CustomFile;
import model.Group;
import model.PeopleFile;
import model.ScheduleFile;
import tools.PostAssigner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // TODO: Remove testPaths in final version of program
    // For testing purposes
    static String sLoadPath = ".\\storage\\BizTechScheduleInput.txt";

    static String pLoadPath = ".\\storage\\BizTechPeopleInput.txt";

    // Scanner for collecting user input
    static Scanner input = new Scanner(System.in);

    // Current UI of the program
    public static void main(String[] args) throws IOException, FileException {

        // Stores the filePath of the files being loaded
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

        // TODO: actually run the assignment depending on what the user inputs
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

        PostAssigner postAssigner = new PostAssigner(p,s);
        postAssigner.assignPosts();

        printAssignedSchedule(postAssigner.getSchedule());

    }

    // EFFECTS: Prints out the given schedule and all of its assignments
    public static void printAssignedSchedule(Map<String, ArrayList<Group>> schedule) {

        for (Map.Entry<String, ArrayList<Group>> entry: schedule.entrySet()) {
            System.out.println();
            System.out.println("Date: " + entry.getKey());
            System.out.println("Groups: ");

            ArrayList<Group> currentDayGroup = entry.getValue();

            for (Group g: currentDayGroup) {
                System.out.println("Group Name: " + g.getName());
                System.out.println("Person Responsible: " + g.getPersonResponsible().getName());
                System.out.println();
            }
        }
    }

}
