package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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


public class FXMLController {
    //  four lists declared, one to hold current todos, one to hold completed todos, one to hold queued completed todos
    private final List<ToDoClass> todos = new ArrayList<>();
    private final SortToDoByDate sorter = new SortToDoByDate();

    //  ObservableList tempCompleted
    //  Use the fourth list to track which list to look at. By default set to todos at start
    //  ObservableList view = todos.get(0);
    //  Use another variable to represent selectedItem
    //  ToDoClass selected = new ToDoClass();

    public void initLists() {
        for (int i = 1; i <= 100; i++) {
            //  Proper formatting will be required when implementing ToDoList objects
            //  For now, List is of type String and each element is as follows for
            //  presentation purposes:
            todos.add(new ToDoClass("Todo " + i, "Date " + i,"Todo desc "+ i,"incomplete"));
        }
        for (int i = 1; i <= 100; i++) {
            todos.add(new ToDoClass("Complete Todo " + i, "Date " + i,"Todo desc "+ i,"completed"));
        }
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addItemButton;

    @FXML
    private Button applyChangesButton;

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
    private TextField itemDateField;

    @FXML
    private TextField itemDescField;

    @FXML
    private TextField itemNameField;

    @FXML
    private ListView<String> toDoList;
    // Will be of type ToDoClass after implementation

    private ToDoClass searchList(String search) {
        for (ToDoClass item : todos) {
            if (search.trim().matches(item.getToDoName())) {
                return item;
            }
        }
        return new ToDoClass("NoItem", "NoDate", "NotFound ","incomplete");
    }


    public void removeItem(ToDoClass item) {
        for (ToDoClass todo : todos) {
            if (item.getToDoName().matches(todo.getToDoName())) {
                todos.remove(item);
            }
        }
    }


    public void addItem(ToDoClass item) {
        //      the list is updated, then sorted by item date
        todos.add(item);
        sorter.sortToDoByDate(todos);
        //      clear the toDoList and set its items to be the list
        resetListView();
    }


    //  ToDo - Create a method: public boolean checkItemIsCompleted(ToDoClass item)
    //      this method has been called if the user selects an item in the list
    //      The method checks if the item is already in the bounds of completed items in the list
    //      Method loops through every element in the completed bounds and compares it to item
    //      if item is found, return true


    @FXML void applyChanges(ActionEvent event) {
        //      method searches for items in completed
        //      and clears the temp completed list
        //      once an item has been marked and applied as finished, there is no way to remove it from
        //      the completed list other than by clearing the list entirely.
    }


    @FXML void checkBoxClicked(ActionEvent event) {
        //  if the checkbox is clicked,
        //  todos is then searched for the item, which is returned as a ToDoClass object
        toDoList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String search = toDoList.getSelectionModel().getSelectedItem();
                    //  this method splits and trims to get the name of the item
                    ToDoClass item = searchList(Arrays.asList(search.split(":")).get(0).trim());
                    int index = todos.indexOf(item);
                    //  item's completed value is changed to be the opposite value then it is set back into the list at the same element
                    item.setCompleted(!item.getCompleted());
                    todos.set(index, item);
                    //  then sort todos and reset listView
                    sorter.sortToDoByDate(todos);
                    resetListView();
                }
        );
    }

    @FXML void remove(ActionEvent event) {
        //      get selected item
        //  todos is then searched for the item, which is returned as a ToDoClass object
        toDoList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String search = toDoList.getSelectionModel().getSelectedItem();
                    //  this method splits and trims to get the name of the item
                    ToDoClass item = searchList(Arrays.asList(search.split(":")).get(0).trim());
                    //  Call to helper function
                    removeItem(item);
                }
        );
    }


    @FXML void add(ActionEvent event) {
        String name = itemNameField.getText();
        String date = itemDateField.getText();
        String desc = itemDescField.getText();
        //  Check that date follows format
        List<String> dateCheck = new ArrayList<>(Arrays.asList(date.split("-")));
        if ((dateCheck.size() == 3) && (parseInt(dateCheck.get(0)) <= 9999) && (parseInt(dateCheck.get(1)) <= 12)
                && (parseInt(dateCheck.get(2)) <= 31) && (!name.isEmpty() && !date.isEmpty() && !desc.isEmpty())) {
                //      if any fields (name,date,desc) are blank,
                desc = (String.format("%1.256s", desc));
                addItem(new ToDoClass(name,date,desc,"incomplete"));
        }
    }


    @FXML void clear(ActionEvent event) {
        todos.clear();
        resetListView();
    }



    @FXML void edit(ActionEvent event) {
        //      get name, description and date fields
        //      if something is blank, do not change that one
        //      if name field is empty, return
        //      search both lists for the selected item,
        //      when found,
        //      check if the description is blank
        //          if so, set it to the edit field
        //      check if the date is blank
        //          if so, set it to the edit field
        //      Clear both edit fields
        //      Call to the list it was found in by date
    }


    @FXML void saveList(ActionEvent event) {
        //      get fileName from filename text field
        //      if fileName is blank, do nothing
        //      else if fileName does not end with .txt, do nothing
        //      else
        //          make a new WriteToDoList object
        //          if todos checkbox was clicked
        //              if completed checkbox was clicked
        //                  writeToDoList(fileName,todos,completed)
        //              else
        //                  writeToDoList(fileName,todos, blank list)
        //          else if completed checkbox was clicked
        //              writeToDoList(fileName, blank list, todos)
    }


    @FXML void loadList(ActionEvent event) {
        //      get fileName from filename text field
        //      if fileName is blank, do nothing
        //      else if fileName does not end with .txt, do nothing
        //      else
        //              make new ReadToDoList object
        //              readToDoList(fileName, todos, completed)
    }


    @FXML void setCurrentDate() {
        //      get current date
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

    @FXML
    void resetListView() {
        ObservableList<String> todoNames = FXCollections.observableArrayList();
        String complete;
        for (ToDoClass item : todos) {
            if (item.getCompleted())
                complete = "complete";
            else
                complete = "incomplete";
            todoNames.add(String.format("%40s",item.getToDoName()) + String.format("%200s",":")  + item.getToDoDate() + String.format("%10s",":"+complete));
        }
        toDoList.setItems(todoNames);
    }


    //  Initialization of application
    public void initialize() {
        //  This logic is vital to express the fact that a list is being presented
        //  i is used to represent the list's capacity to reach at least 256 items
        initLists();
        String complete;
        ObservableList<String> todoNames = FXCollections.observableArrayList();
        for (ToDoClass item : todos) {
            if (item.getCompleted())
                complete = "complete";
            else
                complete = "incomplete";
            todoNames.add(String.format("%40s",item.getToDoName()) + String.format("%200s",":")  + item.getToDoDate() + String.format("%10s",":"+complete));
        }
        toDoList.setItems(todoNames);
        //  get selection of an item in a list
        toDoList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    //  In the future, each object of list will have its own description as a String variable.
                    //  For now, description is just the first 20 characters of the selected item
                    //  When an item is selected, the list is then searched for that item


                    //  Current problem: The list is displaying


                    descriptionPane.setContentText(searchList(Arrays.asList(toDoList.getSelectionModel().getSelectedItem().split(":")).get(0)).getToDoDesc());
                    //  set getSelectedItem to be selectedItem
                    //  setCurrentDate(); to change text at top to say current date
                    //  check if the item is in the temp completed list. If so, autofill the checkbox
                }
        );
    }


}