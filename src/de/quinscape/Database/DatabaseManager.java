package de.quinscape.Database;

import de.quinscape.FileInputConverter.ArrayListToObjectFileConverter;
import de.quinscape.FileOutputConverter.ObjectFileToArrayListConverter;
import de.quinscape.Model.Client;
import de.quinscape.NavigationMenu.Menu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class manages the database.
 * It creates or serializes, deserializes and saves a database file.
 */
public class DatabaseManager {
    public ArrayList<Client> importedClientList = new ArrayList<>();
    private final File databaseFile = new File("Client_Database.dat");
    private Client client = new Client();
    static Scanner scanner = new Scanner(System.in);
    static Menu menu = new Menu();
    static String[] OPTIONS = {
            "1- Show all clients",
            "2- Show specific client",
            "3- Add new client",
            "4- Update existing client",
            "5- Delete client",
            "6- Exit"
    };

    public void runProgram() throws IOException, ClassNotFoundException {
        createOrReadFile();

        //append new clients to the now opened list: Before appendNewClientToList();
        chooseYourOption();

        //save the list as an object inside a file and close the file.
        serializeClientList();
    }

    private void showEntryAmount() {
        int count = 0;
        for (int i = 0; i < importedClientList.size(); i++) {
            count++;
        }
        System.out.println("You have " + count + " entries");
    }

    /**
     * Serializes an ArrayList to a "Client_Database.dat" file. If that file exists:
     * 1) deserialize / open the file; 2) append new clients to its list,3) serialize, close and save the file
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void createOrReadFile() throws IOException, ClassNotFoundException {
        ArrayListToObjectFileConverter clientList = new ArrayListToObjectFileConverter();
        ObjectFileToArrayListConverter objectFileToArrayListConverter = new ObjectFileToArrayListConverter();
        boolean doesFileExist = objectFileToArrayListConverter.databaseFile().exists();
        if (!doesFileExist) {
            //creates .dat file
            clientList.convertArrayListToFile();
        } else {
            //opens database as an ArrayList Object
            deserializeClientList(objectFileToArrayListConverter);
        }
    }

    /**
     * creates a new Client object and adds it to the list of clients
     */
    public void appendNewClientToList() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter clients name: ");
        client.setName(sc.nextLine());
        System.out.println("Enter clients insurance number: ");
        client.setInsuranceNumber(sc.nextInt());
        System.out.println("Client " + client.getName() + " successfully added to database.");
        importedClientList.add(client);

    }

    /**
     * opens the saved ArrayList from the File a
     *
     * @param objectFileToArrayListConverter
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void deserializeClientList(ObjectFileToArrayListConverter objectFileToArrayListConverter) throws IOException, ClassNotFoundException {
        importedClientList = objectFileToArrayListConverter.importClientArrayListFile();
    }

    /**
     * saves the importedClientList as a .dat file
     *
     * @throws IOException
     */
    public void serializeClientList() throws IOException {
        FileOutputStream file = new FileOutputStream(databaseFile);
        ObjectOutputStream newFile = new ObjectOutputStream(file);
        newFile.writeObject(importedClientList);
        newFile.close();
        file.close();
    }

    /**
     * prints every element from list
     */
    public void printContent() {
        for (Client client : importedClientList){
            System.out.println("Client-name: " + client.getName() + "\n" + "Client-insurance-number: " + client.getInsuranceNumber());
        }
        showEntryAmount();
    }

    public void chooseYourOption() {
        int option = 0;
        while (option != 7) {
            printMenu();
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        printContent();
                        break;
                    case 2:
                        selectAndShowClient();
                        break;
                    case 3:
                        appendNewClientToList();
                        break;
                    case 4:
                        updateClient();
                        break;
                    case 5:
                        deleteClient();
                        break;
                    case 6:
                        removeAllClients();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        break;
                }
            } catch (InputMismatchException i) {
                System.out.println("Please enter an integer value between 1 and " + OPTIONS.length + " + Error Message: " + i.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error. Please try again" + " + Error Message: " + e.getMessage());
            }
        }
    }

    private void deleteClient() {
        System.out.println("Enter name to delete client: ");
        Scanner sc = new Scanner(System.in);
        String clientToBeDeleted = sc.nextLine();
        for (Client client : importedClientList) {
            if (Objects.equals(clientToBeDeleted, client.getName())) {
                importedClientList.remove(client);
                System.out.println("Client has been deleted");
            }
        }
    }

    private void removeAllClients(){
        importedClientList.removeAll(importedClientList);
        System.out.println("All clients have been deleted");
    }

    private void selectAndShowClient() {
        System.out.println("Who are you looking for?: ");
        Scanner userInput = new Scanner(System.in);
        String check = userInput.nextLine();
        for (Client client : importedClientList){
            if (check.equals(client.getName())){
                System.out.println("Client-name: " + client.getName() + "\n" +
                        "Client-insurance-number: " + client.getInsuranceNumber());
            }
        }
    }

    private void updateClient() {
        System.out.println("Whose information do you want to update? Enter name please: ");
        Scanner sc = new Scanner(System.in);
        String searchForThisName = sc.nextLine();
        for (Client client : importedClientList) {
            if (Objects.equals(searchForThisName, client.getName())) {
                System.out.println("Enter clients new name: ");
                client.setName(sc.nextLine());
                System.out.println("New name is now: " + client.getName());
            }
        }
    }

    public static void printMenu() {
        for (String option : menu.printMenuOptions()) {
            System.out.println(option);
        }
        System.out.println("Choose your option: ");
    }
}
