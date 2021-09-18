//////////////// FILE HEADER //////////////////////////
//
// Title: P08 Badger Coaster
// Files: BoardingGroup, BGNode, RideQueue, ThemeParkApp, QueueADT
// Course: CS300,Spring,2020
//
// Author: Meng Tian
// Email: mtian42@wisc.edu
// Lecturer's Name: Gary Dahl
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: NONE (identify each person and describe their help in detail)
// Online Sources: NONE (identify each URL and describe their assistance in detail)
//
///////////////////////////////////////////////////////////////////////////////
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;


/**Driver for RideQueue. Reads commands from a text files and executes
* them accordingly.
* @author Michelle Jensen
* @throws IOException if there is an connecting issue
*/
public class ThemeParkApp {
  public static void main(String []args) throws IOException {
    List<String> fileLines = Files.readAllLines(Paths.get("sample.txt"));
    String command = "";
    String[] commandParts;
    
    //Default queue capacity and ride capacity. Can change values if desired.
    RideQueue coaster = new RideQueue(50);
    int trainCapacity = 24; 
    
    //Process each line in the text file.
    for (int i = 0; i < fileLines.size(); i++) {
      commandParts = fileLines.get(i).split(" ");
      command = commandParts[0].toUpperCase();
      
      // ENTER Command
      if (command.equals("E")){ 
	    enter(coaster, commandParts);
	  }
      
	  // BREAKDOWN Command
      if (command.equals("B")) {
        breakdown(coaster);
      }
      
	  // PREVIEW Command
      if (command.equals("P")) {
        preview(coaster);
      }
      
	  // RIDE Command
      if (command.equals("R")) {
        ride(coaster, trainCapacity);
      }
      
	  //STATUS Command
      if (command.equals("S")) {
        status(coaster);
      }
    }
  }

  /**
  * print out the current status of the queue, including its total number of people, total number of groups,and all groups' names in order
  * @param coaster - a ridequeue object that we want to know its status
  * @author Michelle Jensen
  */
  private static void status(RideQueue coaster) {
    System.out.println("Retrieving Status...");
    System.out.println(coaster.toString());
    System.out.println("------------------------------------");
  } 
  /**add a new group to the list
  * 
  * @author Michelle Jensen,Meng Tian
  * @param coaster - a ridequeue object we want to add
  * @param commandParts - the name, number of people and VIP status of a boarding group
  */
  private static void enter(RideQueue coaster, String [] commandParts) {
    System.out.println("Entering into ride line...");
    String groupName = commandParts[1];// get the name of the group
    int groupSize = Integer.parseInt(commandParts[2]);// get the number of people in the group

    BoardingGroup newGroup = new BoardingGroup(groupName,groupSize);// form a boarding group object
	/*newGroup = CALL YOUR BoardingGroup CONSTRUCTOR HERE. NOTE: var groupName is the
	name of the group from the file and var groupsize is the number of people*/

    if (commandParts.length == 4) {// check if there is more information in the given commandPars
      if (commandParts[3].toUpperCase().equals("V")) {// check if it should be a VIP group
	
        newGroup.getVIP();// if yes, change its status to VIP

      }
    }
// add the new group to the queue
    try {
      coaster.enqueue(newGroup);// call the enqueue method in RideQueue class
      System.out.println(groupName + "'s group of " + groupSize
          + " has entered the line for Badger Coaster.");// if successful, print a message
    } catch (IllegalStateException e) {// if there is an exception
      System.out.println("Cannot fit group of that size into queue.");// print a warning message
    }
	
    System.out.println("------------------------------------");
  }
  /**empty the queue
  * @param coaster - a RideQueue object that need to be clear
  * @author Michelle Jensen
  */
  private static void breakdown(RideQueue coaster) { 
    System.out.println("Ride Breakdown...");
    System.out.println("The ride has broken down. All " + coaster.size()
        + " group(s) have been removed from the line.");
    coaster.clear();// call the clear method in the RideQueue class
    System.out.println("------------------------------------");
  }
  /**present the information of the queue sofar, including the front group, its number of people, and its name 
  * @param coaster - the RideQueue object that need to be investigated
  * @author Michelle Jensen, Meng Tian
  */
  private static void preview(RideQueue coaster) {
    System.out.println("Previewing the front of the line...");
	
    try {// check if the queue is empty
      BoardingGroup peeked = coaster.peek();// call the peek method in the RideQueue class
      int peekedSize = peeked.getNumOfPeople();// get the number of people in the front group
      String peekedName = peeked.getName();// get the name of the front group
      System.out.println(peekedName + "'s group of " + peekedSize 
		  + " is at the front of the line.");
    } catch (NoSuchElementException e) {// if it is, print a warning message
      System.out.println("Cannot look at a group from an empty queue.");
	}
	
    System.out.println("------------------------------------");   
  }
  /** Start boarding and running the ride by emptying the queue in order
   * @param coaster - the queue that need to get on the train
   * @param trainCapacity - total number of people can get on the train
  * @author Michelle Jensen, Meng Tian
  */
  private static void ride(RideQueue coaster, int trainCapacity) {
    System.out.println("Boarding and Running the Ride...");
    int ridingTrain = 0;// initialize the number of people on the train to 0
	
    while (!coaster.isEmpty()) {// in the loop, check each time if it is empty 
      BoardingGroup peeked = coaster.peek();// if not, get the front group to board and ride first
      int peekedSize = peeked.getNumOfPeople(); // get the number of people in the group
      if (ridingTrain + peekedSize > trainCapacity) {// check if the total number of people want to ride exceeds the train capacity
        break;// if yes, then no more group can be boarded and ridden
      }
      // remove the boarding groups from the queue
      try {// check if the queue is empty
        BoardingGroup removed = coaster.dequeue();
        String removedName=removed.getName();// get the name of the removed group
        int removedSize=removed.getNumOfPeople();// get the number of people in that removed group

        System.out.println(removedName + "'s group of " + removedSize
            + " has boarded the Badger Coaster train.");

        ridingTrain += removedSize;// the people riding train increases as one group get on the train
      } catch (NoSuchElementException e) {// if yes, throw an exception with warning message
        System.out.println("Cannot remove a group from an empty queue.");
      }
    }

    if (ridingTrain == 0) {// if the train is still empty, print a message
      System.out.println("There is no one on the train to ride.");
    } else {// if not, print the number of people get on the train 
      System.out.println("Train of " + ridingTrain + " people has left the ride station.");
    }
	
    System.out.println("------------------------------------");
  }  
}

