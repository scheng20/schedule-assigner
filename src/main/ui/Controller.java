package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Group;
import model.PeopleFile;
import model.Person;
import model.ScheduleFile;

import java.util.ArrayList;

public class Controller {

    // For computing and storing the data
    public PeopleFile peopleFile;
    public ScheduleFile scheduleFile;

    // For the UI
    public Label helpLabel;

    public Button openFiles;

    public TextField peopleInputField;
    public TextField scheduleInputField;

    public TableView<Person> peopleTable;

    public Controller() {
        peopleFile = new PeopleFile();
        scheduleFile = new ScheduleFile();

        peopleTable = new TableView<>();
    }

    public void openFiles() {

        // TODO: Add opening function for schedule file as well
        if (!peopleInputField.getText().isEmpty()) {

            try {

                peopleFile.readFile(peopleInputField.getText());
                peopleFile.getContents();
                populatePeopleTable();

            } catch (Exception e) {
                peopleFile.handleException(e);
            }

        } else {
            helpLabel.setText("File paths cannot be empty!");
        }

    }

    public void populatePeopleTable() {

        // Step 1: Create Columns
        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);

        TableColumn<Person, ArrayList<Group>> groupColumn = new TableColumn<>("Groups");
        groupColumn.setMinWidth(200);

        peopleTable.getColumns().addAll(nameColumn, groupColumn);

        // Step 2: Associate data with columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        //groupColumn.setCellValueFactory(new PropertyValueFactory<Person, ArrayList<Group>>("isInGroups"));

        peopleTable.setItems(getPeopleForTable());

    }

    public ObservableList<Person> getPeopleForTable() {

        ObservableList<Person> people = FXCollections.observableArrayList();

        for (Person p: peopleFile.getPeople()) {
            people.add(p);
        }

        //people.addAll(peopleFile.getPeople());

        return people;

    }

}
