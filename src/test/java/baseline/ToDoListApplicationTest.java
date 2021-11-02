package baseline;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

class ToDoListApplicationTest {
    //  Create a static final object ReadToDoList reader

    //Test Cases to create:
    //  ReadToDoList -- readToDoList
    //  use a test input file to read from, then check to see if an expected item name is found in the list
    //  void test_file_reader(){
    //      test file in docs is used to test program's read from file methods
    //      Call reader.readToDoList to read from the test input file getting a list as a result
    //      Assert that the values being read in form a list, and the list contains a to do activity specified in file
    //  }


    //  WriteToDoList -- writeToDoList
    //  use a test output file to check and see if the file specified exists
    //  void test_output_file(){
    //    create an object ReadToDoList reader and make it read a file testInput.txt
    //    assign the reader method's output to a test list
    //    create an object WriteToDoList writer and make it write a file testOutput.txt with test list
    //    assert the file exists to be true
    //  }


    //  SortToDoByDate -- sortToDoByDate
    //  the list that was read in is then sorted, and for loop goes through to check if it is sorted properly by dates
    //  void test_list_sorter(){
    //    create an object ReadToDoList reader and make it read from the test input file
    //    set a flag boolean sorted for checking if the list is sorted, initialize it to be true
    //    getting a list as a result
    //    create an object SortToDoByDate sorter and make it sort
    //    User sorter.sortToDoByDate to sort the list by due date\
    //    create an object ToDoClass lower and construct it with blank string arguments and 0000-00-00 as date
    //    Enter a for each loop and traverse the list, creating a loop variable ToDoClass current
    //      parse lower.date and current.date by dashes and check each part in two lists of strings lowDate and curDate
    //      if the year of lower is greater,  (ParseInt(lowDate(0)) > ParseInt(curDate(0)))
    //          sorted = false, break
    //      else if the years are equal,
    //          if the month of lower is greater,
    //              sorted = false, break
    //          else if the months are equal,
    //              if the day of lower is greater,
    //                  sorted = false, break
    //              in the case of the days being equal, no action is taken, the list is sorted to this point
    //    Assert that sorted is true
    //  }


    //  ToDoClass -- constructor
    //  After the constructor is called to create a ToDoClass item
    //  assert that those are set with getters, test with get desc
    //  void test_todo_class(){
    //      ToDoClass toDoTest = new ToDoClass("ToDo1","0000-00-00","ToDo1desc")
    //      assert toDoTest.getToDoDesc and "ToDo1desc" are equal
    //  }

    //  create a static final object FXMLController controller
    //  create a static final ObservableList<ToDoClass> testList and set it equal to reader.readToDoList(testInput.txt)
    //  create a static final toDoClass testItem = new ToDoClass(Itemname,Itemdate,Itemdesc)

    //  FXMLController -- addItem
    //  take in an ObservableList and a selected item, then add the item to the list (addItem())
    //  then use a for loop to check if the item is in the list and assert that it is found
    //  void test_add_item(){
    //      make a boolean flag found and set it to false
    //      controller.addItem(testItem)
    //      enter a for each loop and check to see if the name of the current item in loop equals testItem
    //          if found, found = true, break
    //      assert found is true
    //  }

    //  after addItem, use checkItemIsCompleted to just check and see if the item is in the list
    //  FXMLController -- checkItemIsCompleted
    //  take in an ObservableList and a selected item, then check to see if the item is found in the list
    //  void testCheckComplete(){
    //     assert (checkItemIsCompleted(testList, testItem)) is True
    //  }

    //  FXMLController -- removeItem
    //  take in an ObservableList and a selected item, then remove the item from the list (removeItem())
    //  then use a for loop to check if the item is still in the list and assert it is not found
    //  void test_remove_item(){
    //      make a boolean flag found and set it to false
    //      controller.removeItem(testItem)
    //      enter a for each loop and check to see if the name of the current item in loop equals testItem
    //          if found, found = true, break
    //      assert found is false
    //  }

    //  All other methods in FXMLController rely upon button press and user interaction
}