package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import static java.lang.Integer.parseInt;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReadToDoList {
    public ToDoList readFromFile(String fileName, ToDoList todos) {
        fileName = fileName;
        todos.getList().clear();
        //  Create a list of Strings
        List<String> list;
        List<ToDoClass> tempList = new ArrayList<>();
        //  Use variables to find parts of toDoClass items in file
        String name;
        LocalDate date;
        String desc;
        boolean completed;

        //  Create a buffered reader in an automated try block
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String input = br.readLine();
            while (input != null) {
                list = new ArrayList<>(Arrays.asList(input.split(":")));
                if (list.size() == 4){
                    name = list.get(0);
                    date = LocalDate.of(
                            parseInt(Arrays.asList(list.get(1).split("-")).get(0)),
                            parseInt(Arrays.asList(list.get(1).split("-")).get(1)),
                            parseInt(Arrays.asList(list.get(1).split("-")).get(2)));
                    desc = list.get(2);
                    completed = list.get(3).equals("complete");
                    //  add each line of the input file as a string element to the list
                    tempList.add(new ToDoClass(name,date,desc,completed));
                    input = br.readLine();
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        todos = new ToDoList(tempList);
        return todos;
    }
}
