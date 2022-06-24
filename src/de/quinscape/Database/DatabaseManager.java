package de.quinscape.Database;

import de.quinscape.FileInputConverter.ArrayListToObjectFileConverter;
import de.quinscape.FileOutputConverter.ObjectFileToArrayListConverter;
import de.quinscape.Model.Client;
import de.quinscape.UserMenu.Menu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static de.quinscape.UserMenu.Menu.*;

/**
 * Constructs a bridge to the file based database and creates functions to
 * append, update, search, read, update, delete and save clients to the ArrayList inside
 */
public class DatabaseManager {
    public ArrayList<Client> clientList = new ArrayList<>();
    private final File databaseFile = new File("Client_Database.dat");
    private static final Menu menu = new Menu();


    public void runProgram() throws IOException, ClassNotFoundException {
        createsOrReadsFileBasedDatabase();
        loopThroughUserActionAndRetrieveExecutions();
        saveClients();
    }

    private void createsOrReadsFileBasedDatabase() throws IOException, ClassNotFoundException {
        ObjectFileToArrayListConverter objectFileToArrayListConverter = new ObjectFileToArrayListConverter();
        boolean doesFileExist = objectFileToArrayListConverter.databaseFile().exists();
        if (!doesFileExist) {
            ArrayListToObjectFileConverter.convertArrayListToFile();
        } else {
            readClientList(objectFileToArrayListConverter);
        }
    }

    /**
     * Reads all clients from database file
     * @param objectFileToArrayListConverter
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readClientList(ObjectFileToArrayListConverter objectFileToArrayListConverter) throws IOException, ClassNotFoundException {
        clientList = objectFileToArrayListConverter.importClientArrayListFile();
    }

    public void loopThroughUserActionAndRetrieveExecutions() {
        Scanner userInteractionInput = new Scanner(System.in);
        int option = NO_SELECTION;
        while (option != 7) {
            printMenu();
            try {
                option = userInteractionInput.nextInt();
                switch (option) {
                    case SHOW_ALL_CLIENTS:
                        showAllClients();
                        break;
                    case SHOW_SELECTED_CLIENT:
                        showClientByNameDialog();
                        break;
                    case ADD_NEW_CLIENT:
                        showAddNewClientDialog();
                        break;
                    case UPDATE_CLIENT:
                        showUpdateClientDialog();
                        break;
                    case DELETE_CLIENT:
                        clearList();
                        break;
                    case DELETE_ALL_CLIENTS:
                        deleteAllClients();
                        break;
                    case END_PROGRAM:
                        System.out.println("Exiting...");
                        break;
                }
            } catch (InputMismatchException i) {
                System.out.println("Please enter an integer value between 1 and " + menu.printMenuOptions().length + " + Error Message: " + i.getMessage());
                userInteractionInput.next();
            } catch (Exception ignore) {}
        }
    }

    public void printMenu() {
        for (String option : menu.printMenuOptions()) {
            System.out.println(option);
        }
        System.out.println("Choose your option: ");
    }

    public void showAllClients() {
        for (Client client : clientList){
            System.out.println("Client-name: " + client.getName() + "\n" + "Client-insurance-number: " + client.getInsuranceNumber());
        }
        System.out.println("You have " + clientList.size() + "entries");
    }

    private void showClientByNameDialog() {
        System.out.println("Enter the name of the person you are looking for: ");
        Scanner userInput = new Scanner(System.in);
        String check = userInput.nextLine();
        findByName(check);
    }

    private void findByName(String searchedName) {
        for (Client client : clientList){
            if (searchedName.equals(client.getName())){
                System.out.println("Client-name: " + client.getName() + "\n" +
                        "Client-insurance-number: " + client.getInsuranceNumber());
            }
        }
    }

    public void showAddNewClientDialog() {
        Client client = new Client();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter clients name: ");
        client.setName(userInput.nextLine());
        System.out.println("Enter clients insurance number: ");
        client.setInsuranceNumber(userInput.nextInt());
        clientList.add(client);
    }

    private void showUpdateClientDialog() {
        System.out.println("Whose information do you want to update? Enter name please: ");
        Scanner userInput = new Scanner(System.in);
        String searchForThisName = userInput.nextLine();
        for (Client client : clientList) {
            if (searchForThisName.equals(client.getName())) {
                System.out.println("Enter clients new name: ");
                client.setName(userInput.nextLine());
                System.out.println("Enter clients insurance number");
                client.setInsuranceNumber(userInput.nextInt());
                System.out.println("New name is now: " + client.getName());
            }
        }
    }

    private void clearList() {
        System.out.println("Enter name to delete client: ");

        Scanner userInput = new Scanner(System.in);
        String clientToBeDeleted = userInput.nextLine();
        for (Client client : clientList) {
            if (clientToBeDeleted.equals(client.getName())) {
                clientList.remove(client);
                System.out.println("Client has been deleted");
            }
        }
    }

    private void deleteAllClients(){
        clientList.clear();
        System.out.println("All clients have been deleted");
    }

    /**
     * saves the importedClientList as a .dat file
     *
     * @throws IOException
     */
    public void saveClients() throws IOException {
        FileOutputStream file = new FileOutputStream(databaseFile);
        ObjectOutputStream newFile = new ObjectOutputStream(file);
        newFile.writeObject(clientList);
        newFile.close();
        file.close();
    }
}
