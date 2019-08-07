package ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.PeopleFile;
import model.ScheduleFile;
import tools.PostAssigner;

import java.util.HashMap;
import java.util.Map;

public class AssignedScheduleController {

    // For storing the data
    private PeopleFile peopleFile;
    private ScheduleFile scheduleFile;

    Map<String, String> scheduleStringMap;

    // For the UI
    public TableView<Map.Entry<String,String>> scheduleOutputTable;
    public TableColumn<Map.Entry<String, String>, String> dateColumn;
    public TableColumn<Map.Entry<String, String>, String> scheduledGroupWithPersonColumn;

    public AssignedScheduleController() {

        scheduleStringMap = new HashMap<>();

        // Create the Schedule Table
        scheduleOutputTable = new TableView<Map.Entry<String,String>>();
        dateColumn = new TableColumn<Map.Entry<String, String>, String>("Date");
        scheduledGroupWithPersonColumn = new TableColumn<Map.Entry<String, String>,
                String>("Scheduled Group (Person Responsible):");
        scheduledGroupWithPersonColumn.getColumns().addAll(dateColumn, scheduledGroupWithPersonColumn);

    }

    public void setFiles(PeopleFile peopleFile, ScheduleFile scheduleFile) {

        this.peopleFile = peopleFile;
        this.scheduleFile = scheduleFile;

    }

    public void computeAssignment() {

        PostAssigner postAssigner = new PostAssigner(peopleFile,scheduleFile);
        postAssigner.assignPosts();

        scheduleStringMap = postAssigner.getScheduleForTable();

        populateScheduleOutputTable();

    }

    public void populateScheduleOutputTable() {

        // Clear out the old table data
        scheduleOutputTable.getItems().clear();

        // Retrieve HashMap in form of Strings
        Map<String, String> map = scheduleStringMap;

        //Associate data with columns
        dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>,
                ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {

                // Uses the key
                return new SimpleStringProperty(p.getValue().getKey());
            }
        });

        scheduledGroupWithPersonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>,
                String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {

                // Uses the value
                return new SimpleStringProperty(p.getValue().getValue());
            }
        });

        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(map.entrySet());
        scheduleOutputTable.setItems(items);

    }

}
