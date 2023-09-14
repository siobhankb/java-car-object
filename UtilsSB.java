/*
 * Name:       Siobhan Boylan
 *
 * Course:     CS-12
 *
 * Date:       December 5, 2022
 *
 * Filename:   UtilsSB.java
 *
 * Purpose:    Utility methods to use with CS12Date and other classes.
 *             Practice building main program that references this code.
 *
 *
 *             THIS FILE RELIES UPON HAVING CS12Date.java IN LOCAL DIRECTORY
 */

//import Scanner for command line inputs
import java.util.Scanner;

//import JOptionPane for GUI inputs/outputs
import javax.swing.JOptionPane;
//sodaPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter SODA price:"));


public class UtilsSB {

    //---------------------------------------------------------------------------
    // user input methods, by datatype
    //---------------------------------------------------------------------------
    
    
    // ----------------------------
    // --> DATA TYPE: INT
    // ----------------------------
    
    // get an int value
    public static int readInt(String prompt, boolean guiMode) {
        
        // declare data and objects
        Scanner input;
        String strData;
        int data = 0;
        boolean error = true;
        String trash;

        // handle user-input guiMode
        
        // guiMode = true (pop-up window for inputs)
        if (guiMode) {
            while (error == true) {
                try {
                    // prompt for an input int value using Scanner mode, for guiMode=true case
                    strData = JOptionPane.showInputDialog(prompt);
                    
                    // try to parse an Integer from the input
                    data = Integer.parseInt(strData);
                    
                    // if all goes well, reset flag
                    error = false;
                } // end try
                
                catch (NumberFormatException nfe) {
                    // gracefully exit
                }
            } // end while
            
        }  // end if
        
        // guiMode = false (command-line inputs)
        else {
            // prompt for an input int value using Scanner mode, for guiMode=false case
            input = new Scanner(System.in);
            
            // print message to user
            System.out.print(prompt);
            
            // as long as the input value is invalid
            while ( !input.hasNextInt() ) {
                
                // clear the input
                trash = input.nextLine();
                
                // print error message & prompt for new entry
                System.out.print("INVALID ENTRY - must be an INTEGER\n" + prompt);
            }
            
            // store valid input into 'data' variable
            data = input.nextInt();
        }
        
        // regardless of whether obtained by Scanner or JOptionPane, do this ONCE at end
        return data;
    }
    
    // ----------------------------
    // --> DATA TYPE: DOUBLE
    // ----------------------------
    
    // get a double value
    public static double readDouble (String prompt, boolean guiMode) {
        
        // declare data and objects
        Scanner input;
        String strData;
        double data = 0f;
        boolean error = true;
        String trash;

        // handle user-input guiMode
        
        // guiMode = true (pop-up window for inputs)
        if (guiMode) {
            while (error == true) {
                try {
                    // prompt for an input int value using Scanner mode, for guiMode=true case
                    strData = JOptionPane.showInputDialog(prompt);
                    
                    // try to parse an Integer from the input
                    data = Double.parseDouble(strData);
                    
                    // if all goes well, reset flag
                    error = false;
                } // end try
                
                catch (NumberFormatException nfe) {
                    // gracefully exit
                } // end catch
                
            } // end while
            
        } // end if
        
        // guiMode = false (command-line inputs)
        else {
            // prompt for an input int value using Scanner mode, for guiMode=false case
            input = new Scanner(System.in);
            
            // print message to user
            System.out.print(prompt);
            
            // as long as the input value is invalid
            while ( !input.hasNextDouble() ) {
                
                // clear the input
                trash = input.nextLine();
                
                // print error message & prompt for new entry
                System.out.print("INVALID ENTRY - looking for a NUMBER\n" + prompt);
            }
            
            // store valid input into 'data' variable
            data = input.nextDouble();
        }
        
        // regardless of whether obtained by Scanner or JOptionPane, do this ONCE at end
        return data; 
    }
    
    
    // ----------------------------
    // --> DATA TYPE: CHAR
    // ----------------------------
    
    // get a char value
    public static char readChar(String prompt, boolean guiMode) {
        
        // declare data and objects
        Scanner input;
        String strData;
        char data = '?';
        String trash;
        
        
        // handle user-input guiMode
        
        // guiMode = true (pop-up window for inputs)
        if (guiMode) {
        
            // prompt for an input char value using Scanner mode, for guiMode=true case
            strData = JOptionPane.showInputDialog(prompt);
            
            // if user just presses 'enter' there will be NO entry, which is invalid
            while ( strData.length() == 0 ) {
                
                // show error message & prompt for new entry
                strData = JOptionPane.showInputDialog("NO DATA-must enter a letter or symbol\n" + prompt); 
            }
            
            // store valid char input into 'data' variable
            data = strData.charAt(0);
        }
        
        else {
        
            // create new Scanner object
            input = new Scanner(System.in);
            
            // prompt for an input char value using Scanner mode, for guiMode=false case
            System.out.print(prompt);
                        
            // store input into 'strData' variable
            strData = input.nextLine();
            
            // if user just presses 'enter' there will be NO entry, which is invalid
            while ( strData.length() == 0 ) {
                
                // print error message & prompt for new entry
                message("NO DATA-must enter a letter or symbol", '!');
                System.out.print(prompt);
                strData = input.nextLine();
            }
            
            // store valid char into 'data' variable
            data = strData.charAt(0);
            
        }
        
        
        // regardless of whether obtained by Scanner or JOptionPane, do this ONCE at end
        return data;   
          
    }
    
    // ----------------------------
    // --> DATA TYPE: STRING
    // ----------------------------
    
    // get a string value
    public static String readString(String prompt, boolean guiMode) {
        
        // declare data and objects
        Scanner input;
        String data = "?";
        String trash;

        // handle user-input guiMode
        
        // guiMode = true (pop-up window for inputs)
        if (guiMode) {
        
            // prompt for an input char value using Scanner mode, for guiMode=true case
            data = JOptionPane.showInputDialog(prompt);
                        
            // as long as the input value is invalid
            while ( data == null ) {
            
                // show error message & prompt for new entry
                data = JOptionPane.showInputDialog("INVALID ENTRY!\nMust enter letters or symbols\n" + prompt);
            }
        }
        
        // guiMode = false (command-line inputs)
        else {
        
            // create new Scanner object
            input = new Scanner(System.in);
            
            // prompt for an input char value using Scanner mode, for guiMode=false case
            System.out.print(prompt);
                        
            // store char input into 'data' variable
            data = input.nextLine();
            
            // as long as the input value is invalid
            while ( data == null ) {
                
                // clear the input
                trash = input.nextLine();
                
                // print error message & prompt for new entry
                System.out.print("INVALID ENTRY!\nMust enter letters or symbols\n" + prompt);
            }
        }
        
        // regardless of whether obtained by Scanner or JOptionPane, do this ONCE at end
        return data;   
          
    }
    
    
    //---------------------------------------------------------------------------
    // age-related methods
    //---------------------------------------------------------------------------
    
    
    // returns the age as of some REFERENCE date (2-input overloaded form, MODS NEEDED)
    public static int getAge(CS12Date dateBd, CS12Date dateRef) {
        // declare variables for m,d,y of each date input (6 total)
        int age = -1; // starting default output
        int yearBd = dateBd.getYear();
        int monthBd = dateBd.getMonth();
        int dayBd = dateBd.getDay();
        int yearRef = dateRef.getYear();
        int monthRef = dateRef.getMonth();
        int dayRef = dateRef.getDay();
        
        // ---> CALCULATE AGE <---
        
        // // ** not born yet **
        
        // case 1: check for yearBd > yearRef
        if (yearBd > yearRef) {
            age = -1;
        }
                
        // case 2: check for monthBd > monthRef
        else if(yearRef == yearBd && monthBd > monthRef) {
            age = -1;
        }
        
        // case 3: check for dayBd > dayRef
        else if(yearRef == yearBd && monthBd == monthRef && dayBd > dayRef) {
            age = -1;
        }
        
        // -->*has been born*
        
        // if we get this far, a person has been "born" and has a BD within any 12 month year        
        // case 4: check for later birth month
        
        else if (monthBd > monthRef) {
            age = yearRef - yearBd - 1;
        }
            
        // case 5: check for later birth day THIS month
        else if (monthBd == monthRef && dayBd > dayRef) {
            age = yearRef - yearBd - 1;
        }
            
        // case 6: all other birthdays
        else {
            age = yearRef - yearBd;
        }
        
        // return ONE common age here, NOT multiple return statements in each logic path above!
        return age;
        
    } // end 2-input overloaded version
    
    //---------------------------------------------------------------------------

    // returns the age as of TODAY'S date (1-input overloaded form, NO CHANGES NEEDED, DO NOT EDIT)
    public static int getAge(CS12Date dateBd) {
        int age;
        CS12Date dateToday = today();
        
        // calls overloaded version of above method, using TODAY as the reference date
        age = getAge(dateBd, dateToday);  
        return age;
        
    } // end 1-input overloaded version
    
    
    //---------------------------------------------------------------------------
    
    // method to check validity of birthday (error message)
    public static String checkAgeError(int ageToCheck, CS12Date inputDate, CS12Date refDate) {
        
        // set variable 'error' to return String error message
        String error = "";
        
        // if the age is -1, it's INVALID and should return an error message
        if (ageToCheck == -1) {
            error = "\nERROR: provided birthdate " + inputDate + " is after reference date " + refDate + ": age = " + ageToCheck + "\n";
            }
            
        return error;
    }
    
    
    
    //---------------------------------------------------------------------------
    // date-related methods 
    //---------------------------------------------------------------------------
    
    // returns today's date as a CS12Date (NO CHANGES NEEDED, DO NOT EDIT)
    public static CS12Date today() {
    
        // simply return a default date, which is today
        return new CS12Date();
        
    }
    
    
    //---------------------------------------------------------------------------
    // copied from UtilsAddFL.java
    //---------------------------------------------------------------------------
    
    // execution related methods ------------------------------------------------
    
    // pauses execution until any key is pressed, specific prompt
    public static void pause(String message) {
        // ignores any returned value
        UtilsSB.readString(message, false);  // <<====== UNCOMMENT THIS LINE once your own readString() is working
        System.out.println();   // add a blank line
    }
    
    // pauses execution until any key is pressed, generic prompt
    public static void pause() {
        pause("Press <Enter> to continue... ");
    }

    // display related methods --------------------------------------------------

    // prints a specified character N times
    public static void spacer(char ch, int num) {
        // print specified char N times
        for (int i=0; i<num; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }
    
    // prints a default character N times
    public static void spacer(int num) {
        final char SEP_CHAR = '=';   // default separator character
        
        // overloaded call to the other version
        spacer(SEP_CHAR, num);
    }
    
    // prints a default spacer, = x40 wide
    public static void spacer() {
        final char SEP_CHAR = '=';   // default separator character
        final int SEP_NUM = 40;      // default separator width
        
        // overloaded call to the other version
        spacer(SEP_CHAR, SEP_NUM);
    }
    
    //------------------------------------------------------------------
       
    // print a message with or without surrounding bounding box
    public static void message(String msg, boolean box) {
        // print message w/o boxing
        if (!box) {
            System.out.println(msg);
        }
        
        // prints message WITH boxing, using length of desired message
        else {
            spacer('=', msg.length());
            System.out.println(msg);
            spacer('=', msg.length());
        }
    }
    
    // print a message with specified character as box
    public static void message(String msg, char boxChar) {
        spacer(boxChar, msg.length());
        System.out.println(msg);
        spacer(boxChar, msg.length());
    }
    
    //------------------------------------------------------------------
    
    // print one blank line
    public static void blank() {
        System.out.println();
    }
    
    // overloaded version, prints N blank lines
    public static void blank(int num) {
        for (int i=1; i<=num; i++) {
            blank();
        }
    }
       
} // end class