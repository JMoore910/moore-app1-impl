package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import java.time.*;

public class ToDoClass {
    //  class holds data structure of the name and date of a to-do
    //  item. To dos are read by the FXML controller


    private final String toDoName;
    private LocalDate toDoDate;
    private String toDoDesc;
    private boolean completed;


    public ToDoClass(String toDoName, String toDoDesc, boolean completed) {
        this.toDoName = toDoName;
        this.toDoDesc = toDoDesc;
        this.completed = completed;
    }

    public ToDoClass(String toDoName, LocalDate toDoDate, String toDoDesc, boolean completed){
        this.toDoDate = toDoDate;
        this.toDoName = toDoName;
        this.toDoDesc = toDoDesc;
        this.completed = completed;
    }


    public LocalDate getToDoDate() {
        return toDoDate;
    }

    public String getToDoName() {
        return toDoName;
    }

    public String getToDoDesc() {
        return toDoDesc;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setToDoDesc(String toDoDesc) {
        this.toDoDesc = toDoDesc;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}