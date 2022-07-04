package main;
import java.util.*;
public class Main {
    
    //STORING ALL COUNTER CUSTOMER QUEUE
    private static Queue counter1 = new LinkedList();
    private static Queue counter2 = new LinkedList();
    private static Queue counter3 = new LinkedList(); //ITEM MORE THAN 5
    
    
    public static Queue getCounter1(){
        return counter1;
    }
    
    public static Queue getCounter2(){
        return counter2;
    }
    
    public static Queue getCounter3(){
        return counter3;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainUI(); //START MAIN UI
    }
   
}
