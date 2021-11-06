package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ToDoList {
    private List<ToDoClass> list = new ArrayList<>();

    public ToDoList() {
    }

    public ToDoList(List<ToDoClass> toDoList) {
        //  Constructor for when a list is passed in as an argument
        //  particularly with read list from file
        this.list = toDoList;
    }

    public List<ToDoClass> getList() {
        return list;
    }

    public void setList(List<ToDoClass> list){
        this.list = list;
    }



    public ToDoClass searchList(String search) {
        for (ToDoClass item : list) {
            if (search.trim().matches(item.getToDoName())) {
                return item;
            }
        }
        return new ToDoClass("NoItem", "NotFound ", false);
    }


    public List<ToDoClass> removeItem(List<ToDoClass> list, int index) {
        //  Helper method for removing an item
        //  Called by event method
        list.remove(list.get(index));
        return list;
    }


    public void addItem(List<ToDoClass> list, ToDoClass item) {
        //  Helper method for adding an item
        //  the list is updated, then sorted
        list.add(item);
    }

    public void editItem(List<ToDoClass> list, ToDoClass oldItem, ToDoClass newItem, int index) {
        //  Helper method for editing an item
        //  the list is updated then sorted
        if (list.contains(oldItem)) {
            list.set(index, newItem);
        }
    }

    public void markItemComplete(List<ToDoClass> list, ToDoClass item) {
        //  Helper method for marking an item complete
        //  set item completed to be the inverse of what it is currently
        //  the list is updated then sorted
        int index = list.indexOf(item);
        item.setCompleted(!item.getCompleted());
        list.set(index, item);
    }

    public void clearList() {
        //  Helper method clears out list of all items
        list.clear();
    }

    public void sortToDoByDate() {
        //  sortToDoByDate sorts an observable list by the date
        //  by way of selection sort
            int todosSize = list.size();
            for (int i = 0; i < list.size(); i++) {
                ToDoClass first = list.get(i);
                //  if an item is marked complete, swap it with the last item in the list
                if (first.getCompleted()) {
                    while (i < todosSize) {
                        if (!list.get(todosSize - 1).getCompleted()) {
                            Collections.swap(list, i, todosSize - 1);
                            todosSize--;
                            break;
                        }
                        todosSize--;
                    }
                }
                //  Call second helper function to run selection sort
                for (int j = i + 1; j < todosSize; j++) {
                    selectionSortByDate(i, j, first, list.get(j));
                }
            }
            //  the result is a list sorted via selection sort
    }

    private void selectionSortByDate(int i, int j, ToDoClass item1, ToDoClass item2){
        //  Set the dates to be a string with the hyphens removed so they can be compared
        int tempFirst = parseInt(item1.getToDoDate().toString().replace("-", ""));
        int tempItemJ = parseInt(item2.getToDoDate().toString().replace("-", ""));
        //  if the date of current first is greater than the date of the item at j,
        //  swap them
        if ((tempFirst > tempItemJ) && (!item2.getCompleted()))
            Collections.swap(list, i, j);
    }


    public void fillNamesList(ObservableList<String> names, ToDoList list){
        String listViewFormat = "%35s%50s%50s";
        String complete;
        for (ToDoClass i: list.getList()) {
            if (i.getCompleted()) {
                complete = "completed";
            }
            else
                complete = "incomplete";
            names.add(String.format(listViewFormat, i.getToDoName(),":" + i.getToDoDate(), ":" + complete));
        }
    }
}
