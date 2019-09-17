
/**
 * Assignment1
 * 
 * COMP 2140         SECTION A01
 * INSTRUCTOR        Helen Cameron
 * ASSIGNMENT        Assignment 3
 * @author           Ajiri Osauzo Jeffrey, 7682469
 * @version          March 10, 2016
 * 
 * PURPOSE           The purpose of this program is to time and determine the 
 *                   accuracy of three different sorting algorithms, namely 
 *                   insertion sort, merge sort, and quick sort.
 */


import java.util.*;
import java.io.*;

public class A3Osauzo7682469 

{
  //declare global variables to be accessed by all main class methods
  static int arraySize = 1;
  static int[] numbers = new int[arraySize];
  static BufferedReader file;
  static String line;
  static String fileName;
  private static int whereExecuting = 0;
  private static final int BEGINNING = 0;
  private static final int BETWEEN_RECURSIVE_CALLS = 1;
  private static final int MERGING = 2;
  
  //build main method
  public static void main(String[] args)
    
  {
    //declare main method local variable(s)
    Scanner input;
    
    //print program output header
    System.out.println("\nCOMP 2140 Assignment 3 March 2016\nBook Shelf Simulations\n\n");
    
    //get program input from user
    input = new Scanner(System.in);
    System.out.println("\nEnter the input file name (.txt files only): ");
    fileName = input.nextLine();
    
    //print demarcation
    System.out.println("\n\n***********************************************************************************************\n");
    
    //open input file with try-catch block to circumvent errors
    try
      
    {
      //read the file into a BufferedReader
      file = new BufferedReader(new FileReader(fileName));
      
      //read the next line
      line = file.readLine();
      
      //if the line is not null, proceed...
      if (line != null)
        
      {
        //build the array of integers
        arraySize = Integer.parseInt(line);
        numbers = new int[arraySize];
        
        //read the next line
        line = file.readLine();
        
        //initiatialize the simulation objects with a loop.
        for (int count = 0; (count < arraySize) && (line != null); count++)
          
        {
          //initiatialize the simulation objects
          numbers[count] = Integer.parseInt(line);
          
          //read the next line
          line = file.readLine();
          
        }
        
      }
      
      //close the BufferedReader file
      file.close();
      
    }
    
    //in case of exceptions, catch the exceptions
    catch (IOException e)
      
    {
      
      //print an associated error message
      System.out.println("IO Error: " + e.getMessage());
      
    }
    
    
    System.out.println("isSorted: " + isSorted(numbers));
    mergeSort(numbers);
    System.out.println("isSorted: " + isSorted(numbers));
    
    
  }
    
    /**
   * Sort an integer array recursively, using merge sort.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   */
  public static void mergeSort(int[] array)
    
  {
    //declare a temporary array
    int[] temp;
    
    //initialize the temporary array
    temp = new int[array.length];
    
    //drive private mergeSort()
    mergeSort(array, 0, array.length, temp);
    
  }
  
  /**
   * Sort an integer array recursively, using merge sort.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   * @param  int start    the element position to start sorting from
   *                          
   * @param  int end    the element position before which the sort must stop
   *                          
   * @param  int[] temp    a temporary array that stores and transfers sorts 
   *                                                 
   */
  private static void mergeSort(int[] array, int start, int end, int[] temp)
    
  {
    //declare method variables
    int mid;
    
    //recursive case for 3 or more array elements, base case does nothing
    if (1 < end - start)
     
    {
     
     //instantiate the mid position in the array
     mid = start + ((end - start) / 2);
     
     //mergeSort each half of the array split from the mid position
     mergeSort(array, start, mid, temp);
     mergeSort(array, mid, end, temp);
     
     //merge the resulting sorted halves of the array
     merge(array, start, mid, end, temp);
     
    }
    
    
  }
  
  /**
   * Merge an array with two distinct sorted halves into one sorted array.
   * 
   * @param  int[] array   the integer array to be sorted
   *                          the array integers are sorted in increasing order
   * @param  int start    the element position to start sorting the left from
   *                          
   * @param  int mid    the element position before which the left sort must stop
   * 
   * @param  int end    the element position before which the right sort must stop
   *                         
   * @param  int[] temp    a temporary array that stores and transfers sorts 
   *                                                 
   */
  private static void merge(int[] array, int start, int mid, int end, int[] temp)
    
  {
   //declare variables for a merge algorithm
    int currentLeft;
    int currentRight;
    int currentTemp;
    
    //initialize the declared variables
    currentLeft = start;
    currentRight = mid;
    
    //loop through the temporary array, adding elements
    for (currentTemp = start; currentTemp < end; currentTemp++)
     
    {
     //add element to the temporary array if left element is less than right element
     if (currentLeft < mid && (currentRight >= end || array[currentLeft] < array[currentRight]))
      
     {
      //position left element into the temporary array
      temp[currentTemp] = array[currentLeft];
      
      //increment the left array counter
      currentLeft++;
      
     }
     
     else
      
     {
      //add right element to the temporary array 
      temp[currentTemp] = array[currentRight];
      
      //increment the right array counter
      currentRight++;
      
     }
     
    }
    
    //loop through the temporary array
    for (currentTemp = start; currentTemp < end; currentTemp++)
     
    {
     //copy each element in the temporary array into the original array
     array[currentTemp] = temp[currentTemp];
     
    }
    
    
  }
  
  /**
   * Check whether an entire integer array is completely sorted in ascending order.
   * 
   * @param  int[] array      the integer array to be sorted
   *                          the array is sorted in ascending order                     
   * @return isSorted         whether the array is entirely sorted
   */
  public static boolean isSorted(int[] array)
    
  {
    //declare local variables
    boolean isSorted;
    
    //instantiate the boolean variable
    isSorted = isSorted(array, 0, array.length);
    
    //return the boolean instance
    return isSorted;
    
  }
  
  /**
   * Check whether an entire integer array is completely sorted in ascending order.
   * 
   * @param  int[] array      the integer array to be sorted
   *                          the array is sorted in ascending order
   * @param  int start        start position for sort completion consideration
   *      
   * @param  int end          end position for sort completion consideration
   *                          
   * @return isSorted         whether the array is entirely sorted
   */
  private static boolean isSorted(int[] array, int start, int end)
    
  {
    //declare local variables
    boolean isSorted;
    int count;
    
    //initialize boolean as true
    isSorted = true;
    
    //loop through the entire array
    for (count = start + 1; count < end; count++)
      
    {
      //compare element to the previous
      if (array[count - 1] > array[count])
        
      {
        
        //set boolean as false if previous is greater
        isSorted = false;
        
      }
      
    }
    
    //return the resulting boolean
    return isSorted;
    
  }
  
  private static void runOSRunTimeStack()
  	
  {
  	
  	
  	
  }
  
  private static int beginningPhase(Stack runtimeStack)
  	
  {
  	int start;
  	int end;
  	int mid;
  	ActivationRecord activationRecord;
  	
  	start = runtimeStack.top().getStart();
  	end = runtimeStack.top().getEnd();
  	
  	mid = start + (end - start) / 2;
  	
  	activationRecord = new ActivationRecord(start, mid, BETWEEN_RECURSIVE_CALLS);
  	
  	runtimeStack.push(activationRecord);
  	
  	return BEGINNING;
  	
  }
  
  private static int betweenRecursiveCallsPhase(Stack runtimeStack)
  	
  {
  	int start;
  	int end;
  	int mid;
  	ActivationRecord activationRecord;
  	
  	start = runtimeStack.top().getStart();
  	end = runtimeStack.top().getEnd();
  	
  	mid = start + (end - start) / 2;
  	
  	activationRecord = new ActivationRecord(mid, end, MERGING);
  	
  	runtimeStack.push(activationRecord);
  	
  	return BEGINNING;
  	
  }
  
  private static int mergingPhase(Stack runtimeStack)
  	
  {
  	
  	
  	
  	return MERGING;
  	
  }
    
    
}


class ActivationRecord
  
{
  private int start;
  private int mid;
  private int end;
  private int phase;
  
  public ActivationRecord(int start, int end, int phase)
    
  {
    
    this.start = start;
    this.mid = -1;
    this.end = end;
    this.phase = phase;
    
  }
  
  public int getStart()
    
  {
    
    return start;
    
  }
  
  public void setStart(int start)
    
  {
    
    this.start = start;
    
  }
  
  public int getMid()
    
  {
    
    return mid;
    
  }
  
  public void setMid(int mid)
    
  {
    
    this.mid = mid;
    
  }
  
  public int getEnd()
    
  {
    
    return end;
    
  }
  
  public void setEnd(int end)
    
  {
    
    this.end = end;
    
  }
  
  public int getPhase()
    
  {
    
    return phase;
    
  }
  
  public void setPhase(int phase)
    
  {
    
    this.phase = phase;
    
  }
  
  
}


class Stack

{
 class Node
  
 {
  private ActivationRecord activationRecord;
  private Node nextNode;
  
  public Node(ActivationRecord activationRecord, Node nextNode)
   
  {
    
   this.activationRecord = activationRecord;
   this.nextNode = nextNode;
   
  }
  
  public ActivationRecord getActivationRecord()
    
  {
    
    return this.activationRecord;
    
  }
  
  public void setActivationRecord(ActivationRecord activationRecord)
    
  {
    
    this.activationRecord = activationRecord;
    
  }
  
  public Node getNextNode()
    
  {
    
    return this.nextNode;
    
  }
  
  public void setNextNode(Node newNode)
    
  {
    
    this.nextNode = newNode;
    
  }
  
 }
 
 private Node top;
 
 public Stack()
  
 {
   
  top = null;
  
 }
 
 public void push(ActivationRecord activationRecord)
   
 {
   
   top = new Node(activationRecord, top);
   
 }
 
 public ActivationRecord pop()
   
 {
   Node poppedNode;
   
   poppedNode = top;
   
   top.setNextNode(top);
   
   return poppedNode.getActivationRecord();
   
 }
 
 public ActivationRecord top()
   
 {
   
   return top.getActivationRecord();
   
 }
 
 public boolean isEmpty()
   
 {
   boolean isEmpty;
   
   isEmpty = false;
   
   if (top == null)
     
   {
     
     isEmpty = true;
     
   }
   
   return isEmpty;
   
 }
 
}