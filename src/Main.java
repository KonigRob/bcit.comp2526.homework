
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Rob
 *
 */
public class Main {
    protected String[] database;
    private Scanner input;

    public Main() {
        database = new String[0];
        input = new Scanner(System.in);
    }

    /**
     * @param name
     */
    public void add(final String name) {
        if (name != null) {
            if ((searchCaseInSensitive(name) == -1)) {
                String[] temp = new String[database.length + 1];
                temp[database.length] = name;
                for (int i = 0; i < database.length; i++) {
                    temp[i] = database[i];
                }
                database = temp;
            } else {
                System.out.println("this entry has already been added");
            }
        }
    }

    public int searchCaseInSensitive(final String name) {
        int returnType = -1;
        if (name != null) {
            for (int i = 0; i < database.length; i++) {
                if (database[i].toLowerCase().contains(name.toLowerCase())) {
                    returnType = i;
                }
            }
        }
        return returnType;
    }

    /**
     * @param name
     * @return returnType
     */
    public int search(final String name) {
        int returnType = -1;
        if (name != null) {
            for (int i = 0; i < database.length; i++) {
                if (database[i].contains(name)) {
                    returnType = i;
                }
            }
        }
        return returnType;
    }

    /**
     * 
     */
    public void displayAll() {

        for (int i = 0; i < database.length; i++) {
            System.out.printf("%40s\n", database[i]);
            // System.out.println(String.format("%s", database[i]));
        }
    }

    /**
     * @param name
     * @return
     */
    public boolean remove(final String name) {
        int pos = search(name);
        if (pos >= 0) {
            String[] temp = new String[database.length - 1];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
            database = temp;
            return true;
        }
        return false;
    }

    /**
     * 
     */
    public void displayMenu() {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }

    public int getChoice() {
        int choice = 4;// default
        boolean done = false;
        while (!done) {
            System.out.print("choice? ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
            }
            if (choice > 0 && choice <= 5) {
                done = true;
            } else {
                System.out.println("\nYour choice is incorrect, please try again");
            }
        }
        return choice;
    }

    /**
     * 
     */
    public void addPerson() {
        String name = "";
        String phone = "";
        boolean done = false;
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.print("\nEnter the persons phone number ");
            phone = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        add(name + " " + phone);
    }

    /**
     * 
     */
    public void deletePerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        if (!remove(name)) {
            System.out.println("Could not delete " + name);
        } else {
            System.out.println(name + " was deleted successfully");
        }
    }

    /**
     * 
     */
    public void findPerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
        }
        int pos = search(name);
        if (pos >= 0) {
            // ADD YOUR DISPLAY CODE HERE TO DISPLAY A SINGLE PERSON'S INFO
            for (String look : database) {
                if (look.contains(name)) {
                    System.out.println(database[pos]);
                }
            }
        } else {
            System.out.println("No such person");
        }
    }

    /**
     * 
     */
    public void run() {
        int choice = 0;
        do {
            displayMenu();
            choice = getChoice();
            switch (choice) {
            case 1:
                addPerson();
                break;
            case 2:
                deletePerson();
                break;
            case 3:
                findPerson();
                break;
            case 4:
                displayAll();
            default:
                // should not get here
            }
        } while (choice != 5);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}

// 8:44 PM - hush: entry i of database array is a 2 entry array of strings,
// database[i][0] being the name, database[i][1] being the phone number
// 8:47 PM - hush: database[i]=name+"\t"+number