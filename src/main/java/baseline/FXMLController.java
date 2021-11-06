package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import static java.lang.Integer.parseInt;


//Changes to make!
//  Make only one list
//  List must have the capacity to store at least 100 item
//  Item Description must be between 1 and 256 chars in length
//  Due Date shall be optional (if user adds item without a due date, put it at the head of the list)
//  If so, check to see if the due date follows the format of YYYY-MM-DD
//
//  Functionality
//      Add item to list
//      Remove item from list
//      Clear all items from list
//      Edit the description or due date of an item
//      A user shall be able to mark an item in the list as either complete or incomplete
//      Display all existing items, only current items, or only completed items

//      Functionality to add:
//      A clear all button: Opens a pop up warning user they are about to delete all items, Both complete and incomplete, from the list
//      Add a JavaFX file and FXML Controller for a new scene - user confirmation window


public class FXMLController implements Initializable {
    private final ObservableList<String> toDoNames = FXCollections.observableArrayList();
    //  One list storing class "ToDoList" stores all data within a list
    private final ToDoList todos = new ToDoList();
    private int selectedIndex = 0;
    private final String incomp = "incomplete";


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addItemButton;

    @FXML
    private Button changeViewAllButton;

    @FXML
    private Button clearListButton;

    @FXML
    private Button changeViewCompletedButton;

    @FXML
    private Button changeViewCurrentButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button saveButton;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    private CheckBox saveCompletedCheckBox;

    @FXML
    private CheckBox saveToDoCheckBox;

    @FXML
    private Text currentDate;

    @FXML
    private DialogPane descriptionPane;

    @FXML
    private Button editItemButton;

    @FXML
    private TextField fileNameField;

    @FXML
    private DatePicker itemDate;

    @FXML
    private TextField itemDescField;

    @FXML
    private TextField itemNameField;

    @FXML
    private ListView<String> toDoList;




    @FXML void checkBoxClicked(ActionEvent event) {
        //  if the checkbox is clicked,
        //  todos is then searched for the item, which is returned as a ToDoClass object
        ToDoClass item = todos.getList().get(selectedIndex);
        todos.markItemComplete(todos.getList(), item);
        resetListView();
    }

    @FXML void remove(ActionEvent event) {
        //      get selected item
        //  todos is then searched for the item, which is returned as a ToDoClass object
        if (selectedIndex >= 0) {
            int index = selectedIndex;
            todos.removeItem(todos.getList(), todos.getList().get(index));
            //  Remove selected item from todos
            toDoList.getItems().remove(toDoList.getSelectionModel().getSelectedItem());
        }
    }


    @FXML void add(ActionEvent event) {
        //  Create Strings to hole text field data
        toDoList.getItems().clear();
        String name = itemNameField.getText();
        String desc = itemDescField.getText();
        itemNameField.clear();
        itemDescField.clear();
        ToDoClass item = new ToDoClass(name,itemDate.getValue(),desc,false);
        if (!desc.isEmpty() && !name.isEmpty()) {
            desc = (String.format("%1.256s", desc));
            //  add item to todos list
            todos.addItem(todos.getList(), item);
            resetListView();
        }
    }


    @FXML void clear(ActionEvent event) {
        //Call a method within ToDoList class to clear its list
        todos.clearList();
        resetListView();
    }



    @FXML void edit(ActionEvent event) {
        //  get name, description from text fields
        //  and date from datePicker
        String name = itemNameField.getText();
        String desc = itemDescField.getText();
        String completed = "incomplete";
        LocalDate date = itemDate.getValue();
        itemNameField.clear();
        itemDescField.clear();
        //  search both lists for the selected item,
        ToDoClass item = todos.searchList(name);
        todos.editItem(todos.getList(), new ToDoClass(name,date,desc,item.getCompleted()), todos.getList().indexOf(item));
        resetListView();
    }


    @FXML void saveList(ActionEvent event) {
        //  get fileName from filename text field
        String fileName = fileNameField.getText();
        System.out.println(fileName);
        //  Make sure user did not specify a text file name
        if (!fileName.contains(".")) {
            //  Make a new WriteToDoList object
            fileName = "docs//" + fileName + ".txt";
            WriteToDoList writer = new WriteToDoList();
            writer.writeToDoList(fileName,todos);
            resetListView();
        }
    }


    @FXML void loadList(ActionEvent event) throws IOException {
        //  get fileName from filename text field
        String fileName = fileNameField.getText();
        System.out.println(fileName);
        //  Make sure user did not specify a text file name
        if (!fileName.contains(".")) {
            System.out.println(fileName);
            //  Make a new ReadToDoList object
            fileName = "docs//" + fileName + ".txt";
            ReadToDoList reader = new ReadToDoList();
            reader.readToDoList(fileName,todos);
            resetListView();
        }
    }


    @FXML void setCurrentDate() {
        //      get current date
        LocalDate today = LocalDate.now();
        currentDate.setText(today.toString());
        //      currentDate.setText(formatted today's date)
    }


    @FXML
    void ViewCurrent(ActionEvent event) {
        //Changes view so that user is looking at todos list
        System.out.println("Changed to current todos");
        // clear toDoList and set its items to be the todos list
        // set view to be todos(0)
    }

    @FXML
    void viewCompleted(ActionEvent event) {
        //Changes view so the viewer is looking at completed list
        System.out.println("Changed to completed todos");
        //  clear toDoList and set its items to be the completed list within list of lists
        //  set view to be todos(1)
    }

    @FXML
    void viewAll(ActionEvent event) {
        //Changes view so the viewer is looking at completed list
        System.out.println("Changed to completed todos");
        //  clear toDoList and set its items to be the completed list within list of lists
        //  set view to be todos.get(0) + todos.get(1)  (current and completed todos)
    }


    public void resetListView() {
        toDoList.getItems().clear();
        toDoNames.removeAll();
        String complete;
        for (ToDoClass item : todos.getList()) {
            System.out.println(item.getToDoDate());
            if (item.getCompleted())
                complete = "complete";
            else
                complete = "incomplete";

            toDoNames.add(String.format("%40s", item.getToDoName()) + String.format("%200s", ":") + item.getToDoDate() + String.format("%50s", ":" + complete));

            toDoList.setItems(toDoNames);
            System.out.println("Long haul");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  Set a default value for the DatePicker
        resetListView();
        setCurrentDate();
        itemDate.setValue(LocalDate.of(2021,11,1));
        //  Check if each item is completed or not

        toDoList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    //  Listener used to find item that user picks in list for removal or marking to complete
                    selectedIndex = toDoList.getSelectionModel().getSelectedIndex();
                    descriptionPane.setContentText(todos.getList().get(selectedIndex).getToDoDesc());
                    completedCheckBox.setSelected(todos.getList().get(selectedIndex).getCompleted());
                }
        );
    }
}