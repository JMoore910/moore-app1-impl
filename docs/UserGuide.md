                                           To Do list user guide, by Jeanne Moore
<br />
This guide is meant to SUPPLEMENT the video guide created for this application. <br />
Please prioritize viewing that to get a better grasp of the utilization of this to do list <br />
application. A number of image visual guides are also included within the repository

When first starting up the application, the user has the option of adding items to an empty list, or loading a list from file <br />

- Adding an item <br />
The user may add a name, a description, and pick a date from the date picker <br />
When the add button is clicked, an item with the specified name, date, and description is added <br />
Not picking a date will leave the default value at today's date.  <br />  
<br />
- Editing an item  <br />
The user may input an item name and description, and pick a date, then hit the edit button, <br />
any items of the specified name have their descriptions and dates changed to the specified data  <br />
  <br />
- Selecting an item <br />
The user may select an item in the list by clicking on it, allowing them to mark it complete/incomplete  <br />
or removing it from the list. The item description is shown in the dialog pane below.  <br />
  <br />
- Marking an item as complete  <br />
If the user clicks the Item Completed checkbox below, a selected item is marked as complete.  <br />
  <br />
- Removing an item <br />
If the user hits the remove button, and an item was selected, the item is removed from the list  <br /> 
the user may continue removing items from the previous selected item in the list. <br />
  <br /> 
- Clearing a list of items  <br /> 
If the user hits the clear button, any list displayed is cleared of all items  <br />
  <br /> 
- Changing view of list <br /> 
If the user selects the Current button, only incomplete items are displayed  <br /> 
If the user selects the Completed button, only complete items are displayed  <br /> 
If the user selects the All Items button, all items in the list are displayed  <br />
  <br /> 
- Sorting the list <br /> 
If the user hits the Sort button, any displayed list is sorted and all completed items  <br /> 
are pushed to the back.  <br />
  <br />
- Specifying a file location  <br /> 
The user may input specify a location and filename. The location is relative to the root directory of the application  <br /> 
If there exists a directory named docs in the root directory, if the user may specify "docs//" as the directory holding the file  <br />
  <br /> 
- Saving a list to file  <br />
If the user hits the save button, the file is created at the specified location, <br /> 
and populated with the data from the currently loaded list.  <br />
  <br />
- Loading a list to file  <br />
If the user hits the load button, a file is opened at the specified location,  <br /> 
and the list is populated with its contents.
  <br />
  The format of the data is as follows:  <br /> <br />
ItemName:YYYY-MM-DD:ItemDesc:ItemStatus  <br />
  "Go to store:2021-11-06:Get Milk:ItemStatus"  <br />
  a sample input text file is included within moore-app1-impl//docs

COP3330 Fall 2021 Application Assignment 1 <br />
©Copyright 2021 Jeanne Moore