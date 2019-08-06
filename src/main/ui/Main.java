package ui;

import exceptions.FileException;
import model.CustomFile;
import model.Group;
import model.PeopleFile;
import model.ScheduleFile;
import tools.PostAssigner;
import tools.WebReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // For testing purposes
    static String sLoadPath = ".\\src\\test\\storage\\BizTechScheduleInput.txt";

    static String pLoadPath = ".\\src\\test\\storage\\BizTechPeopleInput.txt";

    // Scanner for collecting user input
    static Scanner input = new Scanner(System.in);

    // FOR WEEK 10 DELIVERABLE
    static WebReader wb = new WebReader();

    // Current UI of the program
    public static void main(String[] args) throws IOException, FileException {

        // Stores the filePath of the files being loaded
        String filePath;

        //Intro
        System.out.println("================ INTRO =======================");
        wb.readWeb();
        System.out.println("Welcome to schedule assigner!");

        // Ask for user input of people data
        System.out.println("\n================ PEOPLE INPUT ================");
        System.out.println("Please enter the location of the people file you want to load: ");
        PeopleFile p = new PeopleFile();

        // NOTE: current file path is fixed!!!
        //filePath = input.nextLine();
        filePath = pLoadPath;
        loadTo(filePath, p);

        // Ask for user input of the schedule data
        System.out.println("\n================ SCHEDULE INPUT ================");
        System.out.println("Please enter the location of the schedule file you want to load: ");
        ScheduleFile s = new ScheduleFile();

        // NOTE: current file path is fixed!!!
        //filePath = input.nextLine();
        filePath = sLoadPath;
        loadTo(filePath, s);

        // Run the assignments
        runAssignment(p, s);

        // Print the statistics
        printStatistics(p, s);

    }

    // EFFECTS: Loads the given filePath's content into the given CustomFile;
    //          Catches and handles any exceptions the file loading process may throw
    public static void loadTo(String filePath, CustomFile fileType) {

        while (true) {
            try {
                fileType.readFile(filePath);
                fileType.getContents();
                break;

            } catch (Exception e) {
                System.out.println(fileType.handleException(e));
                filePath = input.nextLine();
            }

        }

    }

    // EFFECTS: Computes the assignment of the people to the schedule
    public static void runAssignment(PeopleFile p, ScheduleFile s) {

        System.out.println("\n================ ASSIGNED SCHEDULE ================");
        System.out.println("Computing Assignments...");

        PostAssigner postAssigner = new PostAssigner(p,s);
        postAssigner.assignPosts();

        printAssignedSchedule(postAssigner.getSchedule());

    }

    // EFFECTS: Prints out the given schedule and all of its assignments
    public static void printAssignedSchedule(Map<String, ArrayList<Group>> schedule) {

        System.out.println("Below is the assigned schedule:");
        System.out.println("-----------");

        for (Map.Entry<String, ArrayList<Group>> entry: schedule.entrySet()) {
            System.out.println();
            System.out.println("Date: " + entry.getKey());

            ArrayList<Group> currentDayGroup = entry.getValue();

            for (Group g: currentDayGroup) {
                System.out.println("Group Name: " + g.getName());
                System.out.println("Person Responsible: " + g.getPersonResponsible().getName());
                System.out.println();
            }

            System.out.println("-----------");
        }
    }

    public static void printStatistics(PeopleFile p, ScheduleFile s) {

        System.out.println("\n================ STATISTICS ================");
        p.printStats();

    }

}
