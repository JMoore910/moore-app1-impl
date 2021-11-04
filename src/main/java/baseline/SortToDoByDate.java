package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Jeanne Moore
 */

import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SortToDoByDate {
    public void sortToDoByDate(List<ToDoClass> list) {
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
                int tempFirst = parseInt(first.getToDoDate().replace("-",""));
                int tempItemJ = parseInt(list.get(j).getToDoDate().replace("-",""));
                //  if the year of current first is greater than the year of the item at j,
                if (tempFirst > tempItemJ) {
                    Collections.swap(list, i , j);
                }
            }
            //  the result is the list is sorted via selection sort
        }
    }
}
