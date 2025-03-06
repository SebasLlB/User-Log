import java.util.Scanner;

public class UserLog {
/* 7/11/24 Sebastian Llauce Bautista
 * This file simulates a user log with a capacity of 10
 * Capable of adding, deleting, and finding specific users
 * Also capable of reading all the contents of the log
 * Reiterates an undefined amount of time, and will reset itself if its instructions are not followed correctly
 */

    //Makes a Scanner accessible to the whole file
    public static Scanner scanner;


    public static String[] log  = new String[10];

    //Also made an index and input name accessible to the whole file
    public static int index;
    public static String name;

    public static void main(String[] args){
        //Initially fills the array with values named "empty" to not cause errors when comparing null slots (find and delete method)
        for(int i = 0; i < log.length; i++){
            log[i] = "empty";
        }
        scanner = new Scanner(System.in);
        askUser();
    }
    //Recursive method that will run indefinetely
    public static void askUser(){
        System.out.print("Read log, or add, find, or delete user? ");
        String choice = scanner.next();
        choice = choice.toLowerCase();

        //Using the same logic as CalculatorV3, checks if anything other than the available options is inputed
        if(choice.equals("add") || choice.equals("find") || choice.equals("delete") || choice.equals("read")){
            //If the input is correct, then it checks which method to use
            //If the read feature is called for, then it doesn't not ask for a user to add, find, or delete
            if(choice.toLowerCase().equals("read")){
                readLog();
            } else {
            //Otherwise, it asks the user for a name to add to, find in, or delete from the log
            System.out.print("User name: ");
            name = scanner.next();
            name = name.toLowerCase();
                //This part checks for which method to use(Just like the CalculatorV3 file)
                if(choice.toLowerCase().equals("add")){
                    addUser(name);
                }else if(choice.toLowerCase().equals("find")){    
                    findUser(name);
                } else if(choice.toLowerCase().equals("delete")){
                    deleteUser(name);
                }
            }
        } else{
            //If the input is not an available feature, then an error message is printed and the ask method is called again
            System.out.println("Error. Not an option. Try again");
            askUser();
        }
    }
    //Each method will eventually call the askUser() method again
    //This method adds the inputed user into the log if there is enough space
    public static void addUser(String user){
        if(index < 10){
            //The method will only add a user in an empty slot
            if(log[index].equals("empty")){
                log[index] = user;
                index++;
                System.out.println(+ index);
                askUser();
            } else{
                index++;
                addUser(user);
            }
        } else{
            //"Not enough space" message
            System.out.println("Not enough space ");
            askUser();
        }
    }
    //This method finds a specific user in the log
    public static void findUser(String user){
        //Using a For Loop, it checks each of the array's values
        int app = 0;
        for(int i = 0; i < log.length; i++){
            if(log[i].matches(user)){
                app++;
                System.out.println("User found at " + (i + 1));
            }
        }
        //If the user is not found at all, then this "not found" message will run
        if(app == 0){
            System.out.println(user + " not found");
        }
        askUser();
    }
    //This method deletes an inputed user from the log
    public static void deleteUser(String user){
        //Using the same logic as the find method, a For Loop is used to 
        int app = 0;
        for(int i = log.length - 1; i >= 0; i--){
            if(log[i].matches(user.toLowerCase()) && app == 0){
                app++;
                log[i] = "empty";
                //This allows the addUser() to start from when a user was deleted
                index = i;
                System.out.println("User erased");
                }
        }
        if(app == 0){
            System.out.println(user + " not in log");
        }
        askUser();
    }
    //This method reads each value inside the log
    public static void readLog(){
        for(int i = 0; i < log.length; i++){
            System.out.print(log[i] + "\t");
        }
        System.out.println();
        askUser();
    }
    
}
