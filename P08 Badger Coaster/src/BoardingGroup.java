//////////////// FILE HEADER //////////////////////////
//
// Title: P08 Badger Coaster
// Files: BoardingGroup, BGNode, RideQueue, ThemeParkApp,QueueADT
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
/**
 * This class BoardingGroup represents a group of people that need to be added to a queue
 */
public class BoardingGroup {
  private String name;// the name of the group
  private int numOfPeople;// the number of people in the group
  private boolean isVIP;// the VIP status of the group

  /**
   * Constructor of BoardingGroup
   * 
   * @param name   - the name of the group
   * @param number - the number of people
   */
  public BoardingGroup(String name, int number) {
    this.name = name;// set the given name of the group
    this.numOfPeople = number;// set the given number of people of the group
    this.isVIP = false;// initialize the status of the group as non-VIP
  }

  /**
   * Change the status for the group to VIP
   * 
   */
  public void getVIP() {
    this.isVIP = true;// change the status of the group to VIP
  }

  /**
   * Get the status for the group
   * 
   * @return true if it is VIP, false otherwise
   */
  public boolean status() {
    return isVIP;
  }

  /**
   * Get the name for the group
   * 
   * @return a string represents the name of the group
   */
  public String getName() {
    return name;
  }

  /**
   * Get the number of people of the group
   * 
   * @return a integer represents the number of people in the group
   */
  public int getNumOfPeople() {
    return numOfPeople;
  }
}
