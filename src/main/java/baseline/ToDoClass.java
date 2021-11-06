package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import java.time.*;
import java.util.GregorianCalendar;

public class ToDoClass {
    //  class holds data structure of the name and date of a to-do
    //  item. To dos are read by the FXML controller


    private String toDoName;
    private LocalDate toDoDate;
    private String toDoDesc;
    private boolean completed;

    public ToDoClass(){

    }

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

    public ToDoClass(ToDoClass item) {
        toDoName = item.toDoName;
        toDoDate = item.toDoDate;
        toDoDesc = item.toDoDesc;
        completed = item.completed;
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

    public void setToDoName(String toDoName) {
        this.toDoName = toDoName;
    }

    public void setToDoDate(String toDoDate) {
        this.toDoName = toDoDate;
    }

    public void setToDoDesc(String toDoDesc) {
        this.toDoName = toDoDesc;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}