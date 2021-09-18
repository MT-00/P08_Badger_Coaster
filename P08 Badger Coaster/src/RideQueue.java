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
import java.util.NoSuchElementException;

/**
 * This class RideQueue can be used to form a queue of boarding group
 * 
 * @implSpec QueueADT interface with boarding group type object
 */
public class RideQueue implements QueueADT<BoardingGroup> {
  private BGNode front;// refer to the first group in the queue
  private BGNode back;// refer to the last group in the queue
  private int capacity;// the max number of people that can fit into the queue
  private int numOfPeople;// total number of people in the queue
  private int numOfGroups;// number of groups in the queue

  /**
   * Constructor of RideQueue
   * 
   * @param capacity the maximum number of people can be stored in the queue
   */
  public RideQueue(int capacity) {// what if its negative
    this.capacity = capacity;// set the capacity of the queue to given number
    this.numOfGroups = 0;// initialize the total number of group to 0
    this.numOfPeople = 0;// initialize the total number of people to 0
    this.front = null;// initialize the first group to null
    this.back = null;// initialize the last group to null
  }

  /**
   * the method used to check if the queue is empty
   * 
   * @implSpec inherit from the QueueADT interface
   * @return true if it is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (numOfGroups == 0)// if the number of groups is 0
      return true;// then it is empty
    return false;
  }

  /**
   * return the number of groups in the queue
   * 
   * @implSpec inherit from the QueueADT interface
   * @return the number of groups in the queue
   */
  @Override
  public int size() {
    return this.numOfGroups;
  }

  /**
   * add a new group to the queue
   * 
   * @implSpec inherit from the QueueADT interface
   * @param newGroup - a boarding group type of object need to be add to the queue
   * @throws IllegalStateException - if the total number of people exceeds the capacity of the queue
   */
  @Override
  public void enqueue(BoardingGroup newGroup) {
    if (newGroup != null) {// if the new group is not null
      if (numOfPeople + newGroup.getNumOfPeople() >= this.capacity)// and if it exceeds the capacity
                                                                   // of the queue
        throw new IllegalStateException("the newGroup cannot fit into the queue.");// throw a
                                                                                   // warning
      // otherwise, add it in the queue
      BGNode newNode = new BGNode(newGroup);// initialize a BGNode type of object with given
                                            // boarding group
      if (newNode.getGroup().status() == true) {// check if it is a VIP group
        newNode.setNext(front);// if yes, add it to the front
        front = newNode;
      } else {// if not, add it to the last
        if (numOfGroups == 0) {// if it is empty, the new group is both the first and the last group
                               // in the queue
          front = newNode;
          back = newNode;
        } else {// otherwise, it should be added to the last position in the queue
          back.setNext(newNode);
          back = newNode;// renew back reference to that new group
        }
      }
      numOfPeople = numOfPeople + newGroup.getNumOfPeople();// the total number of people increases
                                                            // as the new group adds in
      numOfGroups++;// the number of groups adds one more
    }
  }

  /**
   * empty the queue
   * 
   * @implSpec inherit from the QueueADT interface
   */
  @Override
  public void clear() {
    BGNode current = front;
    BGNode next;
    // empty each BGNode in the queue one by one
    while (current != null) {
      next = current.getNext();
      current = null;
      current = next;
      numOfPeople = 0;
      numOfGroups = 0;
    }
    front = null;// set the front node to null
    back = null;// set the back node to null
    numOfPeople = 0;// empty all people
    numOfGroups = 0;// empty all groups
  }

  /**
   * Returns the front boarding group
   * 
   * @return the front boarding group in the list
   * @throws NoSuchElementException if there is no boarding group yet
   */
  @Override
  public BoardingGroup peek() {
    if (front == null)// check if there is a boarding group in the list
      throw new NoSuchElementException("the queue is empty.");
    return front.getGroup();// if there is, return that group
  }

  /**
   * Returns the boarding group being removed from the queue
   * 
   * @return a boarding group that be removed
   * @throws NoSuchElementException - if the queue is empty
   */
  @Override
  public BoardingGroup dequeue() {
    if (isEmpty())// check if the queue is empty
      throw new NoSuchElementException("this queue is empty.");// if yes, throw a warning
    BoardingGroup returnG = front.getGroup();// the front boarding group is the one should be
                                             // removed
    BGNode newFront = front.getNext();// the new front group is the second one in the previous queue
    front = newFront;// set the front group to the new one
    numOfPeople = numOfPeople - returnG.getNumOfPeople();// decreases the number of people in the
                                                         // queue as the group is removed
    numOfGroups--;// decreases the number of group by one
    return returnG;// return the removed front group
  }

  /**
   * Returns a string representation of this RideQueue.
   * 
   * @return a string represents the ridequeue
   */
  public String toString() {
    String s = "Number of People in Queue: " + numOfPeople + "\n";
    s += "Number of Groups in Queue: " + numOfGroups + "\n";
    s += "Group Names in Queue: ";
    BGNode current = front;// initialize the front node
    while (current != null) {// continue the loop if there is a node
      String groupName = current.getGroup().getName();// get the name of the node
      s += groupName + " ";
      current = current.getNext();// change the current to next node in the queue
    }
    return s;
  }


}

