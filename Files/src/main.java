//===============================================================================
// Name:	Kevin Cabrera
// Description:	The main class displays a menu of choices to a user
//		and performs the chosen task. It will keep prompting a user for the 
//		next commands until Q (Quit) is entered.
//===============================================================================
import java.io.*;

public class main
{
   public static void main(String[] args)
   {   //declare variables
       char input1;
       String inputInfo = new String();
       int operation2;
       String line = new String();

       //create a linked list to be used in this method.
       LinkedList list1 = new LinkedList();

       try
        {
         // print out the menu
         printMenu();

         // create a BufferedReader object to read input from a keyboard
         InputStreamReader isr = new InputStreamReader (System.in);
         BufferedReader stdin = new BufferedReader (isr);

         do
          {
           System.out.print("What action would you like to perform?\n");
           line = stdin.readLine().trim();  //read a line
           input1 = line.charAt(0);
           input1 = Character.toUpperCase(input1);

           if (line.length() == 1)   //check if a user entered only one character
            {
             switch (input1)
              {
               case 'A':   //Add String
                 System.out.print("Please enter a string to add:\n");
                 String str1 = stdin.readLine().trim();
                 System.out.print("Please enter an index to add:\n");
                 inputInfo = stdin.readLine().trim();
                 int addIndex = Integer.parseInt(inputInfo);
                 list1.addElement(addIndex, str1);
                 break;
               case 'C':   //Count the number of occurrences of a string
                 System.out.print("Please enter a string to count:\n");
                 inputInfo = stdin.readLine().trim();
                 operation2=list1.countOccurrences(inputInfo);
                 System.out.print("string occurs " + operation2 + " time(s)\n");
                 break;
               case 'D':   //Search for the Index of a String
                 System.out.print("Please enter a string to search:\n");
                 inputInfo = stdin.readLine().trim();
                 operation2=list1.searchElement(inputInfo);
                 if (operation2 > -1)
                   System.out.print("string found at " + operation2 + "\n");
                 else
                   System.out.print("string not found\n");
                 break;
               case 'E':   //Search for String at an Index
                 System.out.print("Please enter an index to search:\n");
                 inputInfo = stdin.readLine().trim();
                 int searchIndex = Integer.parseInt(inputInfo);
                 System.out.print("string at the given index is " + list1.getElement(searchIndex) + "\n");
                 break;
               case 'L':   //List Strings
                 System.out.print(list1.toString());
                 break;
               case 'O':  // List Current Size
                 System.out.print("The current size is " + list1.size() + "\n");
                 break;
               case 'P':   //Append String after another
                 System.out.print("Please enter a string to append another string after:\n");
                 String str2 = stdin.readLine().trim();
                 System.out.print("Please enter a string to append:\n");
                 inputInfo = stdin.readLine().trim();
                 String str3 = inputInfo;
                 list1.appendStringAfter(str2, str3);
                 break;
               case 'Q':   //Quit
                 break;
               case 'R':  //Remove String
                 System.out.print("Please enter the index of a string to remove:\n");
                 inputInfo = stdin.readLine().trim();
                 int removeIndex = Integer.parseInt(inputInfo);
                 System.out.println(list1.removeElement(removeIndex) + " was removed");
                 break;
               case '?':   //Display Menu
                 printMenu();
                 break;
               default:
                 System.out.print("Unknown action\n");
                 break;
              }
           }
          else
           {
             System.out.print("Unknown action\n");
            }
          } while (input1 != 'Q' || line.length() != 1);
        }
       catch (IOException exception)
        {
          System.out.print("IO Exception\n");
        }
    }

    /** The method printMenu displays the menu to a user **/
    public static void printMenu()
     {
       System.out.print("Choice\t\tAction\n" +
                        "------\t\t------\n" +
                        "A\t\tAdd String\n" +
                        "C\t\tCount Occurrences\n" +
                        "D\t\tSearch for Index\n" +
                        "E\t\tSearch for String\n" +
                        "L\t\tList Strings\n" +
                        "O\t\tList Current Size\n" +
                        "P\t\tAppend String After\n" +
                        "Q\t\tQuit\n" +
                        "R\t\tRemove String\n" +
                        "?\t\tDisplay Help\n\n");
    } //end of printMenu()
}