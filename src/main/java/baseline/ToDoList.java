package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
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
        sortToDoByDate();
    }

    public void editItem(List<ToDoClass> list, ToDoClass item, int index) {
        //  Helper method for editing an item
        //  the list is updated then sorted
        list.set(index, item);
        sortToDoByDate();
    }

    public void markItemComplete(List<ToDoClass> list, ToDoClass item) {
        //  Helper method for marking an item complete
        //  the list is updated then sorted
        int index = list.indexOf(item);
        item.setCompleted(!item.getCompleted());
        list.set(index, item);
        sortToDoByDate();
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
            if (first.getCompleted() && i < todosSize) {
                Collections.swap(list, i, todosSize - 1);
                todosSize--;
            }
            for (int j = i; j < todosSize; j++) {
                int tempFirst = parseInt("" + (first.getToDoDate().getYear()) + (first.getToDoDate().getMonthValue()) + (first.getToDoDate().getDayOfMonth()));
                int tempItemJ = parseInt("" + (list.get(j).getToDoDate().getYear()) + (list.get(j).getToDoDate().getMonthValue()) + (list.get(j).getToDoDate().getDayOfMonth()));
                //  if the year of current first is greater than the year of the item at j,
                if (tempFirst > tempItemJ) {
                    Collections.swap(list, i, j);
                }
            }
            //  the result is the list is sorted via selection sort
        }
    }

    public boolean checkItemIsCompleted(ToDoClass item) {
        //  Helper method has been called if the user selects an item in the list
        //  The method just returns the boolean value for the item being completed
        return item.getCompleted();
    }

    public void fillNamesList(ObservableList<String> names, ToDoList list){
        String complete = "incomplete";
        for (ToDoClass i: list.getList()) {
            if (i.getCompleted()) {
                complete = "completed";
            }
            names.add(String.format("%40s", i.getToDoName()) + String.format("%200s", ":") +
                    i.getToDoDate() + String.format("%50s", ":" + complete));
        }
    }
}
