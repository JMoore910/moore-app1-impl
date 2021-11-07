package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;


public class FXMLController implements Initializable {
    private final ObservableList<String> toDoNames = FXCollections.observableArrayList();
    //  One list storing class "ToDoList" stores all data within a list
    private static final String LIST_VIEW_FORMAT = "%35s%50s%50s";
    private ToDoList todos = new ToDoList();
    private File choice;
    private int selectedIndex = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button changeViewAllButton;

    @FXML
    private Button clearListButton;

    @FXML
    private Button changeViewCompletedButton;

    @FXML
    private Button changeViewCurrentButton;

    @FXML
    private Button sortButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button fileChooserButton;

    @FXML
    private CheckBox completedCheckBox;

    @FXML
    private Text currentDate;

    @FXML
    private Text fileText;

    @FXML
    private DialogPane descriptionPane;

    @FXML
    private DatePicker itemDate;

    @FXML
    private TextField filePathField;

    @FXML
    private TextField fileNameField;

    @FXML
    private TextField itemDescField;

    @FXML
    private TextField itemNameField;

    @FXML
    private ListView<String> listView;


    @FXML void fileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
        choice = fc.showOpenDialog(null);

        if (choice != null){
            fileText.setText(choice.getName());
        }
    }


    @FXML void seeSelectedItem(MouseEvent event) {
        //  If an item is seleted, change the content of the description box
        //  And change checkbox to reflect completed or not
        if (listView.getSelectionModel().getSelectedIndex() != -1) {
            selectedIndex = listView.getSelectionModel().getSelectedIndex();
            completedCheckBox.setSelected(todos.getList().get(selectedIndex).getCompleted());
            descriptionPane.setContentText(todos.searchList(Arrays.asList(listView.getSelectionModel()
                    .getSelectedItem().split(":")).get(0).trim()).getToDoDesc());
        }
    }


    @FXML void checkBoxClicked(MouseEvent event) {
        //  if the checkbox is clicked,
        //  todos is then searched for the item, which is returned as a ToDoClass object
        if (selectedIndex != -1) {
            ToDoClass item = todos.getList().get(selectedIndex);
            todos.markItemComplete(todos.getList(), item);
            listView.getItems().clear();
            todos.fillNamesList(toDoNames, todos);
            listView.refresh();
        }
    }


    @FXML void remove(ActionEvent event) {
        //      get selected item
        //  todos is then searched for the item, which is returned as a ToDoClass object
        if ((selectedIndex != -1) && (!todos.getList().isEmpty())) {
            //  Remove selected item from todos and the listView
            todos.setList(todos.removeItem(todos.getList(),selectedIndex));
            listView.getItems().remove(selectedIndex);
            selectedIndex --;
            descriptionPane.setContentText("");
            listView.getSelectionModel().select(selectedIndex);
            listView.refresh();
        }
    }


    @FXML void add(ActionEvent event) {
        //  Create Strings to hole text field data
        listView.getItems().clear();
        if (!toDoNames.isEmpty())
            toDoNames.clear();
        String name = itemNameField.getText();
        String desc = itemDescField.getText();
        itemNameField.clear();
        itemDescField.clear();
        ToDoClass item = new ToDoClass(
                name,
                itemDate.getValue(),
                desc,
                false
        );
        if (!desc.isEmpty() && !name.isEmpty()) {
            //  format description to be less than 256 characters
            desc = (String.format("%1.256s", desc));
            //  add item to todos list
            item.setToDoDesc(desc);
            todos.addItem(todos.getList(), item);
            todos.fillNamesList(toDoNames,todos);
            listView.refresh();
        } else {
            todos.fillNamesList(toDoNames,todos);
            listView.refresh();
        }
    }


    @FXML void sort(ActionEvent event) {
        //  Method calls on hitting sort button
        //  First clear Observable List
        toDoNames.clear();
        //  Then perform sort method twice
        //  Sort method moves all completed items to the end
        //  and then sorts items by way of selection sort
        todos.sortToDoByDate();
        todos.sortToDoByDate();
        //  refill Observable List after sorting
        todos.fillNamesList(toDoNames,todos);
        listView.refresh();
    }


    @FXML void clear(ActionEvent event) {
        //  Call a method within ToDoList class to clear its list
        todos.clearList();
        //  Clear Observable List
        descriptionPane.setContentText("");
        toDoNames.clear();
        listView.refresh();
    }


    @FXML void edit(ActionEvent event) {
        //  get name, description from text fields
        //  and date from datePicker
        String name = itemNameField.getText();
        String desc = itemDescField.getText();
        LocalDate date = itemDate.getValue();
        itemNameField.clear();
        itemDescField.clear();
        //  search both lists for the selected item,
        ToDoClass item = todos.searchList(name);
        //  To edit item, send in both new and old items to make sure method knows to edit
        todos.editItem(
                todos.getList(),
                item,
                new ToDoClass(name,date,desc, item.getCompleted()),
                todos.getList().indexOf(item)
        );
        toDoNames.clear();
        todos.fillNamesList(toDoNames,todos);
    }


    @FXML void saveList(ActionEvent event) {
        //  Use filechooser to get file to save to
        //  or get fileName from filename text field
        String fileName;
        WriteToDoList writer = new WriteToDoList();
        if (choice != null) {
            //  Implement fileChoice here
            fileName = choice.getAbsolutePath();
            writer.writeToFile(fileName, todos);
        } else {
            fileName = filePathField.getText() + fileNameField.getText();
            //  Make sure user did not specify a text file name
            if (fileName.endsWith(".txt")) {
                writer.writeToFile(fileName, todos);
            }
        }
    }


    @FXML void loadList(ActionEvent event) {
        //  get fileName from filename text field
        String fileName;
        ReadToDoList reader = new ReadToDoList();
        if (choice != null) {
            //  Make sure user did not specify a text file name
            fileName = choice.getAbsolutePath();
            todos = new ToDoList(reader.readFromFile(fileName, todos).getList());
            toDoNames.clear();
            todos.fillNamesList(toDoNames, todos);
            listView.refresh();
        } else {
            fileName = filePathField.getText() + fileNameField.getText();
            if (fileName.endsWith(".txt")) {
                todos = new ToDoList(reader.readFromFile(fileName, todos).getList());
                toDoNames.clear();
                todos.fillNamesList(toDoNames, todos);
                listView.refresh();
            }
        }
    }


    @FXML void setCurrentDate() {
        //      get current date
        LocalDate today = LocalDate.now();
        currentDate.setText(today.getMonth() + " " + today.getDayOfMonth() + ", " + today.getYear());
    }


    @FXML
    void viewCurrent(ActionEvent event) {
        //  Changes view so that user is looking at incomplete list
        // clear listView and set its items to be the todos list
        listView.getItems().clear();
        String complete = "incomplete";
        for (ToDoClass i : todos.getList()) {
            if (!i.getCompleted()) {
                toDoNames.add(String.format(
                        LIST_VIEW_FORMAT,
                        i.getToDoName(),
                        ":" + i.getToDoDate(),
                        ":" + complete)
                );
            }
        }
        listView.refresh();
    }

    @FXML
    void viewCompleted(ActionEvent event) {
        //  Changes view so the user is looking at completed list
        //  clear listView
        listView.getItems().clear();
        String complete = "completed";
        for (ToDoClass i : todos.getList()) {
            if (i.getCompleted()) {
                toDoNames.add(String.format(
                        LIST_VIEW_FORMAT,
                        i.getToDoName(),
                        ":" + i.getToDoDate(),
                        ":" + complete)
                );
            }
        }
        listView.refresh();
    }

    @FXML
    void viewAll(ActionEvent event) {
        //Changes view so the viewer is looking at completed list
        //  clear listView and set its items to be the full list
        listView.getItems().clear();
        todos.fillNamesList(toDoNames,todos);
        listView.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  Set a default value for the DatePicker
        listView.setItems(toDoNames);
        setCurrentDate();
        itemDate.setValue(LocalDate.now());
        //  Check if each item is completed or not
    }
}


//  Create a custom adaptor for setting layout of listview
//public class listAdapter extends ArrayAdapter