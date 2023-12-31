/**
 * Multiclipboard is a program that allows the user to save multiple clipboards
 * 
 * @author Rhys Walker
 * @version 1.0
 * @since 2023-12-20
 */

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Scanner;

public class MultiClipboard {

    // hashMap to store the clipboards
    private static HashMap<Integer, String> clipboards;

    public static void main(String[] paramArrayOfString) {

        // initialize the HashMap
        clipboards = new HashMap<>();

        // print the welcome message
        System.out.println("WELCOME TO MULTI-CLIPBOARD");
        System.out.println("Support me on github: RiceDope");
        System.out.println("Type \"save number\" to save the current clipboard");
        System.out.println("Type \"load number\" to load the clipboard");
        System.out.println("Type quit to exit the program");

        // scanner to get user input
        Scanner scanner = new Scanner(System.in);

        // boolean to check if the program should continu
        boolean programLoop = true;
        while (programLoop) {

            // prompt the user to make a choice
            System.out.println("\nChoose an option");
            System.out.println(">>>");
            String userInput = scanner.nextLine();

            // if the user wants to quit allow them to
            if (userInput.equals("quit")) {
                programLoop = false;
            }
            // if not quiting then the user wants to load or save
            else{ 

                // split the user input into an array
                String[] arrayOfString = userInput.split(" ");

                // if the user has selected save
                if (arrayOfString.length == 2 && arrayOfString[0].equals("save")) {

                    // get the location to save the clipboard
                    int location = Integer.parseInt(arrayOfString[1]);
                    String currentClipboard = getClipboard();

                    // if the clipboard is not empty save it
                    if (currentClipboard != null) {
                        clipboards.put(Integer.valueOf(location), currentClipboard);
                    } else {
                        System.out.println("ERROR: Clipboard is empty");
                    }
                }
                // if the user has selected load
                else if (arrayOfString.length == 2 && arrayOfString[0].equals("load")) {

                    // get the location to load the clipboard
                    int location = Integer.parseInt(arrayOfString[1]);

                    // if the clipboard exists load it
                    if (clipboards.containsKey(Integer.valueOf(location))) {
                        setClipboard(location);
                    }
                    else{
                        System.out.println("ERROR: Clipboard number not found");
                    }
                }
            }
        }

        // close the scanner and thank the user
        scanner.close();
        System.out.println("Thank you for using Multi-Clipboard");
    }

    /**
     * Gets the contents of the users current clipboard
     * 
     * @return A string containing the contents of the clipboard
     */
    private static String getClipboard() {
        try {
            // get the clipboard contents
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (Exception exception) {
            // if the clipboard is empty return null
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Sets the contents of the users clipboard based on a location in the hashmap
     * 
     * @param location The location of the clipboard we wish to set
     */
    private static void setClipboard(int location) {
        try {
            //  set the clipboard contents
            StringSelection stringSelection = new StringSelection(clipboards.get(Integer.valueOf(location)));
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } catch (Exception exception) {
            // if the location is empty return null
            exception.printStackTrace();
        }
    }
}
