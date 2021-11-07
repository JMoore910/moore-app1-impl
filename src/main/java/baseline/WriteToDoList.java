package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToDoList {
    public void writeToFile(String fileName, ToDoList todos) {
        String completed;
        //  method opens a new file at fileName with a buffered writer
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (ToDoClass item: todos.getList()) {
                if (item.getCompleted())
                    completed = "completed";
                else
                    completed = "incomplete";
                //  buffered writer prints list to file
                //  print: toDoName toDoDate toDoDesc completed for all elements in todos list
                bw.write(item.getToDoName() + ":" + item.getToDoDate() + ":" + item.getToDoDesc() + ":" + completed + "\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
