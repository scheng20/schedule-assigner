package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.PeopleFile;
import model.Person;
import model.ScheduleFile;

import java.util.Map;

public class MainController {

    // For computing and storing the data
    public PeopleFile peopleFile;
    public ScheduleFile scheduleFile;

    Boolean readyToAssign;

    // For the UI
    public Label upperHelpLabel;
    public Label lowerHelpLabel;

    public Button openFiles;
    public Button assignSchedule;

    public TextField peopleInputField;
    public TextField scheduleInputField;

    public TableView<Person> peopleTable;
    public TableColumn<Person, String> nameColumn;
    public TableColumn<Person, String> groupColumn;

    public TableView<Map.Entry<String,String>> scheduleTable;
    public TableColumn<Map.Entry<String, String>, String> dateColumn;
    public TableColumn<Map.Entry<String, String>, String> scheduledGroupsColumn;

    public MainController() {

        // Create the files
        peopleFile = new PeopleFile();
        scheduleFile = new ScheduleFile();
        readyToAssign = false;

        // Create the People Table
        peopleTable = new TableView<Person>();
        nameColumn = new TableColumn<>("Name");
        groupColumn = new TableColumn<>("Groups");
        peopleTable.getColumns().addAll(nameColumn, groupColumn);

        // Create the Schedule Table
        scheduleTable = new TableView<Map.Entry<String,String>>();
        dateColumn = new TableColumn<Map.Entry<String, String>, String>("Date");
        scheduledGroupsColumn = new TableColumn<Map.Entry<String, String>, String>("Scheduled Groups:");
        scheduledGroupsColumn.getColumns().addAll(dateColumn, scheduledGroupsColumn);

    }

    public void openFiles() {

        if (!peopleInputField.getText().isEmpty() && !scheduleInputField.getText().isEmpty()) {

            try {
                // Clear out the old data
                peopleFile.clearPeople();
                scheduleFile.clearSchedule();

                // Read the People File
                peopleFile.readFile(peopleInputField.getText());

                // Read the Schedule File
                scheduleFile.readFile(scheduleInputField.getText());

                // Add in the new data
                populatePeopleTable();
                populateScheduleTable();

                reportSuccess();

            } catch (Exception e) {

                // Announce Errors
                upperHelpLabel.setTextFill(Color.web("#de2707"));
                upperHelpLabel.setText(peopleFile.handleException(e));
            }

        } else {

            // Complain about empty file fields
            upperHelpLabel.setTextFill(Color.web("#de2707"));
            upperHelpLabel.setText("File paths cannot be empty!");
        }

    }

    public void reportSuccess() {

        // Announce Success!
        upperHelpLabel.setTextFill(Color.web("#529900"));
        upperHelpLabel.setText("Data loaded successfully!");
        readyToAssign = true;
    }

    public void populatePeopleTable() {

        // Clear out the old table data
        peopleTable.getItems().clear();

        //Associate data with columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("isInGroupsString"));

        // Add the new data to the people table
        peopleTable.setItems(getPeopleForTable());

    }

    public void populateScheduleTable() {

        // Clear out the old table data
        scheduleTable.getItems().clear();

        // Retrieve HashMap in form of Strings
        Map<String, String> map = scheduleFile.getScheduleMapString(scheduleFile.getSchedule());

        //Associate data with columns
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>,
                ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {

                // Uses the key
                return new SimpleStringProperty(p.getValue().getKey());
            }
        });

        scheduledGroupsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>,
                String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {

                // Uses the value
                return new SimpleStringProperty(p.getValue().getValue());
            }
        });

        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(map.entrySet());
        scheduleTable.setItems(items);

    }

    public ObservableList<Person> getPeopleForTable() {

        ObservableList<Person> people = FXCollections.observableArrayList();

        people.addAll(peopleFile.getPeople());

        return people;
    }

    public void runAssignmentAndDisplayOutput() {

        if (readyToAssign) {


            try {

                // Creates a new scene
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AssignedScheduleInterface.fxml"));
                Parent root = (Parent) fxmlLoader.load();

                // Get controller of newly loaded scene
                AssignedScheduleController scheduleController = fxmlLoader.getController();

                // Pass the data from this controller to scheduleController
                scheduleController.setFiles(peopleFile, scheduleFile);
                scheduleController.computeAssignment();

                // Show scene in new window
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Assigned Schedule Output");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            lowerHelpLabel.setTextFill(Color.web("#de2707"));
            lowerHelpLabel.setText("Missing data required to compute assignments!");
        }

    }

}
