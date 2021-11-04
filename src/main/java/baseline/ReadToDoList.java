package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadToDoList {
    public void readToDoList(String fileName, ObservableList<ToDoClass> todos) throws IOException {
        //  Only run operations if the file exists
        if (new File(fileName).isFile()) {
            //  method calls a buffered reader to read a ToDoList text file
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
                //  each line of the text file is made up of a ToDoName, a ToDoDate, and a ToDoDesc,
                //  separated by colons.
                String newLine = br.readLine();
                while (newLine != null) {
                    //  the buffered reader reads each line to a list of Strings
                    //  then the elements of that list are added to a new object in the todos list
                    List<String> newList = new ArrayList<>(Arrays.asList(newLine.split(":")));
                    todos.add(new ToDoClass(newList.get(0),newList.get(1),newList.get(2),newList.get(3)));
                    newLine = br.readLine();
                }
            }
            //  Once all items have been read in and added to list, call to sort it with SortToDoByDate
            SortToDoByDate sorter = new SortToDoByDate();
            sorter.sortToDoByDate(todos);
        }
    }
}
