package baseline;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import static org.junit.jupiter.api.Assertions.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

class ToDoListApplicationTest {
    //  Create a static final object ReadToDoList reader
    static final ReadToDoList reader = new ReadToDoList();
    static final WriteToDoList writer = new WriteToDoList();
    static final ObservableList<String> testNameList = FXCollections.observableArrayList();
    static int removeIndex;
    static ToDoList testToDos = new ToDoList();



    //Test Cases to create:
    //  ReadToDoList -- readToDoList
    @Test
    void test_read_file() {
        String fileName = "docs//testInput.txt";
        //  use a test input file to read from, then check to see if an expected item name is found in the list
        //  test file in docs is used to test program's read from file method
        testToDos = new ToDoList(reader.readFromFile(fileName, testToDos).getList());
        //  Call reader.readToDoList to read from the test input file getting a list as a result
        //  Assert that the values being read in form a list, and the list contains a todoactivity specified in file
        assertEquals("Item 10",testToDos.getList().get(9).getToDoName());
    }


    //  WriteToDoList -- writeToDoList
    @Test
    void test_write_file() {
        String fileName = "docs//testOutput.txt";
        File testFile = new File(fileName);
        //  use a test output file to check and see if the file specified exists
        //  write a file testOutput.txt with test list
        writer.writeToFile(fileName, testToDos);
        //  assert the file exists to be true
        assertTrue(testFile.exists());
    }


    //  ToDoList -- sortToDoByDate
    @Test
    void test_sort_list() {
        //  Within the larger application, the list is sorted twice in order to get all completed items out of the way
        testToDos.sortToDoByDate();
        testToDos.sortToDoByDate();
        testToDos.sortToDoByDate();
        //    set a flag boolean sorted for checking if the list is sorted, initialize it to be true
        boolean sorted = true;
        //    getting a list as a result
        //    create an object ToDoClass lower and construct it with blank string arguments and 0000-00-00 as date
        ToDoClass lower = new ToDoClass("a", LocalDate.of(0,1,1),"b",false);
        //    Enter a for each loop and traverse the list, creating a loop variable ToDoClass current
        //      parse lower.date and current.date by dashes and check each part in two lists of strings lowDate and curDate
        //      if the year of lower is greater,  (ParseInt(lowDate(0)) > ParseInt(curDate(0)))
        for (ToDoClass current: testToDos.getList()) {
            if (current.getCompleted()) {
                //  Checks if current has reached the completed items, which are not sorted
                break;
            } else if ((lower.getToDoDate().getYear()) > current.getToDoDate().getYear()) {
                sorted = false;
                break;
            } else if (lower.getToDoDate().getYear() == current.getToDoDate().getYear()) {
                if (lower.getToDoDate().getMonthValue() > current.getToDoDate().getMonthValue()) {
                    sorted = false;
                    break;
                } else if (lower.getToDoDate().getMonthValue() == current.getToDoDate().getMonthValue()) {
                    if (lower.getToDoDate().getDayOfMonth() > current.getToDoDate().getDayOfMonth()) {
                        sorted = false;
                        break;
                    }
                }
                //  in the case of the days being equal, no action is taken, the list is sorted to this point
            }
                lower = current;
        }
        //  Assert that sorted is true
        assertTrue(sorted);
    }


    //  ToDoClass -- constructor
    @Test
    void test_toDoClass_constructor() {
        //  After the constructor is called to create a ToDoClass item
        //  assert that those are set with getters, test with get desc
        ToDoClass testItem = new ToDoClass("Test", LocalDate.of(1,2,3),"TestDesc",true);
        //      ToDoClass toDoTest = new ToDoClass("ToDo1","0000-00-00","ToDo1desc")
        //      assert toDoTest.getToDoDesc and "TestDesc" are equal
        assertEquals("TestDesc", testItem.getToDoDesc());
    }

    //  ToDoList -- addItem
    @Test
    void test_add() {
        //  Add an item to a list
        //  then use searchList to check if it is found in the list
        ToDoClass item = new ToDoClass("Test", LocalDate.of(1,2,3),"TestDesc",true);
        testToDos.addItem(testToDos.getList(),item);
        removeIndex = testToDos.getList().indexOf(item);
        //  assert the item is found in a list search
        assertEquals(item.getToDoName(),testToDos.searchList("Test").getToDoName());
    }

    //  ToDoList -- removeItem
    @Test
    void test_remove() {
        //  remove an item from a list
        //  then use !testToDos.contains to check if the list contains the removed item
        ToDoClass item = new ToDoClass("Test", LocalDate.of(1,2,3),"TestDesc",true);
        testToDos.removeItem(testToDos.getList(),removeIndex);
        //  assert the item is not contained in the list anymore
        assertFalse(testToDos.getList().contains(item));
    }


    //  ToDoList -- searchList
    @Test
    void test_search_list() {
        ToDoClass item = new ToDoClass("Test", LocalDate.of(1,2,3),"TestDesc",true);
        testToDos.addItem(testToDos.getList(),item);
        //  call searchList passing in item.getToDoName() as argument
        //  assert the returned value is equal to item.getToDoName()
        assertEquals(item,testToDos.searchList(item.getToDoName()));
    }

    //  ToDoList -- editItem and searchList
    @Test
    void test_edit_item() {
        //  call editItem passing list, oldItem, newItem, int index
        ToDoClass oldItem = new ToDoClass("Test", LocalDate.of(1,2,3),"TestDesc",true);
        testToDos.addItem(testToDos.getList(),oldItem);
        ToDoClass newItem = new ToDoClass("Test", LocalDate.of(1,2,3),"NewTestDesc",true);
        testToDos.editItem(testToDos.getList(),oldItem,newItem,testToDos.getList().indexOf(oldItem));
        //  then assert the list contains the new version of the item
        assertEquals(newItem.getToDoDesc(),testToDos.searchList(oldItem.getToDoName()).getToDoDesc());
    }

    //  ToDoList -- markItemComplete
    @Test
    void test_mark_complete() {
        //  call markItemComplete passing list and incomplete item as arguments
        ToDoClass item = new ToDoClass(
                "File for second unemployment",
                LocalDate.of(2008,6,4),
                "Probably cry about selling your house to another rich landlord",
                false
        );
        testToDos.addItem(testToDos.getList(),item);
        testToDos.markItemComplete(testToDos.getList(),item);
        //  then assert the item in the list is complete
        assertTrue(testToDos.searchList("File for second unemployment").getCompleted());
    }

    //  ToDoList -- fillNamesList
    @Test
    void test_fill_names_list() {
        //  call fillNamesList passing an empty observable list and a ToDoList
        testToDos.fillNamesList(testNameList,testToDos);
        //  then assert that the size of the observable list equals todos.getList() size
        assertEquals(testToDos.getList().size(),testNameList.size());
    }


    //  ToDoList -- clearItem
    @Test
    void test_clear() {
        //  call clear list
        testToDos.clearList();
        //  assert the list is empty
        assertTrue(testToDos.getList().isEmpty());
    }
}