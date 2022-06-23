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

/**
 * It manages the Database
 */
public class Manager {
    public ArrayList<Client> importedClientList = new ArrayList<>();
    private final File databaseFile = new File("Client_Database.dat");
    private static final Menu menu = new Menu();


    public void runProgram() throws IOException, ClassNotFoundException {
        createOrReadFile();
        retrieveAndExecuteUserAction();
        saveClients();
    }

    /**
     * Serializes an ArrayList to a "Client_Database.dat" file. If that file exists:
     * 1) deserialize / open the file; 2) append new clients to its list,3) serialize, close and save the file
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void createOrReadFile() throws IOException, ClassNotFoundException {
        ArrayListToObjectFileConverter arrayListToObjectFileConverter = new ArrayListToObjectFileConverter();
        ObjectFileToArrayListConverter objectFileToArrayListConverter = new ObjectFileToArrayListConverter();
        boolean doesFileExist = objectFileToArrayListConverter.databaseFile().exists();
        if (!doesFileExist) {
            arrayListToObjectFileConverter.convertArrayListToFile();
        } else {
            readClientList(objectFileToArrayListConverter);
        }
    }

    /**
     * opens the saved ArrayList from the File
     *
     * @param objectFileToArrayListConverter
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readClientList(ObjectFileToArrayListConverter objectFileToArrayListConverter) throws IOException, ClassNotFoundException {
        importedClientList = objectFileToArrayListConverter.importClientArrayListFile();
    }

    public void retrieveAndExecuteUserAction() {
        Scanner userInput = new Scanner(System.in);
        int option = Menu.NO_SELECTION;
        while (option != 7) {
            printMenu();
            try {
                option = userInput.nextInt();
                switch (option) {
                    case Menu.SHOW_ALL_CLIENTS:
                        showAllClients();
                        break;
                    case Menu.SHOW_SELECTED_CLIENT:
                        findClientByName();
                        break;
                    case Menu.ADD_NEW_CLIENT:
                        addNewClientToList();
                        break;
                    case Menu.UPDATE_CLIENT:
                        updateClient();
                        break;
                    case Menu.DELETE_CLIENT:
                        deleteClient();
                        break;
                    case Menu.DELETE_ALL_CLIENTS:
                        deleteAllClients();
                        break;
                    case Menu.END_PROGRAM:
                        System.out.println("Exiting...");
                        break;
                }
            } catch (InputMismatchException i) {
                System.out.println("Please enter an integer value between 1 and " + menu.printMenuOptions().length + " + Error Message: " + i.getMessage());
                userInput.next();
            } catch (Exception e) {
                System.out.println("Unexpected error. Please try again" + " + Error Message: " + e.getMessage() + ". Enter something to continue");
            }
        }
    }

    public void printMenu() {
        for (String option : menu.printMenuOptions()) {
            System.out.println(option);
        }
        System.out.println("Choose your option: ");
    }

    public void showAllClients() {
        for (Client client : importedClientList){
            System.out.println("Client-name: " + client.getName() + "\n" + "Client-insurance-number: " + client.getInsuranceNumber());
        }
        showEntryAmount();
    }

    private void showEntryAmount() {
        int count = 0;
        for (int i = 0; i < importedClientList.size(); i++) {
            count++;
        }
        System.out.println("You have " + count + " entries");
    }

    private void findClientByName() {
        System.out.println("Enter the name of the person you are looking for: ");
        Scanner userInput = new Scanner(System.in);
        String check = userInput.nextLine();
        for (Client client : importedClientList){
            if (check.equals(client.getName())){
                System.out.println("Client-name: " + client.getName() + "\n" +
                        "Client-insurance-number: " + client.getInsuranceNumber());
            }
        }
    }

    public void addNewClientToList() {
        Client client = new Client();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter clients name: ");
        client.setName(userInput.nextLine());
        System.out.println("Enter clients insurance number: ");
        client.setInsuranceNumber(userInput.nextInt());
        importedClientList.add(client);
    }

    private void updateClient() {
        System.out.println("Whose information do you want to update? Enter name please: ");
        Scanner userInput = new Scanner(System.in);
        String searchForThisName = userInput.nextLine();
        for (Client client : importedClientList) {
            if (searchForThisName.equals(client.getName())) {
                System.out.println("Enter clients new name: ");
                client.setName(userInput.nextLine());
                System.out.println("Enter clients insurance number");
                client.setInsuranceNumber(userInput.nextInt());
                System.out.println("New name is now: " + client.getName());
            }
        }
    }

    private void deleteClient() {
        System.out.println("Enter name to delete client: ");

        Scanner userInput = new Scanner(System.in);
        String clientToBeDeleted = userInput.nextLine();

        for (Client client : importedClientList) {
            if (clientToBeDeleted.equals(client.getName())) {
                importedClientList.remove(client);
                System.out.println("Client has been deleted");
                if (importedClientList.isEmpty()){
                    System.out.println("Client list is now empty.");
                    break;
                }
            }
        }
    }

    private void deleteAllClients(){
        importedClientList.removeAll(importedClientList);
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
        newFile.writeObject(importedClientList);
        newFile.close();
        file.close();
    }
}
