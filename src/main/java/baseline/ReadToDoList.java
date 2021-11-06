package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import javafx.collections.ObservableList;
import static java.lang.Integer.parseInt;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadToDoList {
    public ToDoList readToDoList(String fileName, ToDoList todos) throws IOException {
        fileName = "docs//" + fileName;
        File file = new File(fileName);
        //  Only run operations if the file exists
        System.out.println("a");
        if (file.exists()) {
            System.out.println("b");
            //  method calls a buffered reader to read a ToDoList text file
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
                //  each line of the text file is made up of a ToDoName, a ToDoDate, and a ToDoDesc,
                //  separated by colons.
                boolean complete = false;
                LocalDate date;
                List<String> dateStr;
                List<Integer> dateParsed = new ArrayList<>();
                String newLine = br.readLine();
                while (newLine != null) {
                    System.out.println("x");
                    //  the buffered reader reads each line to a list of Strings
                    //  then the elements of that list are added to a new object in the todos list
                    List<String> newList = new ArrayList<>(Arrays.asList(newLine.split(":")));
                    dateStr = Arrays.asList(newList.get(1).split("-"));
                    dateParsed.set(0,parseInt(dateStr.get(0)));
                    dateParsed.set(1,parseInt(dateStr.get(1)));
                    dateParsed.set(2,parseInt(dateStr.get(2)));
                    date = LocalDate.of(dateParsed.get(0),dateParsed.get(1),dateParsed.get(2));
                    if (newList.get(3).equals("completed"))
                        complete = true;
                    todos.addItem(todos.getList(), new ToDoClass(newList.get(0),date,newList.get(2),complete));
                    newLine = br.readLine();
                }
            }
            //  Once all items have been read in and added to list, call to sort it with SortToDoByDate
            todos.sortToDoByDate();
        }
        return todos;
    }
}
