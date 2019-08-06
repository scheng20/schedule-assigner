package ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.PeopleFile;
import model.ScheduleFile;

public class Controller {

    // For computing and storing the data
    public PeopleFile peopleFile;
    public ScheduleFile scheduleFile;

    // For the UI
    public Label helpLabel;

    public Button openFiles;

    public TextField peopleInputField;
    public TextField scheduleInputField;

    public TableView peopleTable;

    public Controller() {
        peopleFile = new PeopleFile();
        scheduleFile = new ScheduleFile();
    }

    public void openFiles() {

        // TODO: Add opening function for schedule file as well
        if (!peopleInputField.getText().isEmpty()) {

            try {

                peopleFile.readFile(peopleInputField.getText());
                peopleFile.printContents();

            } catch (Exception e) {
                peopleFile.handleException(e);
            }

        } else {
            helpLabel.setText("File paths cannot be empty!");
        }

    }

}
