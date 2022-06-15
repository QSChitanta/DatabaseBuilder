package de.quinscape.Database;

import de.quinscape.FileInputConverter.ArrayListToObjectFileConverter;
import de.quinscape.FileOutputConverter.ObjectFileToArrayListConverter;
import de.quinscape.Model.Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class manages the database.
 * It creates or serializes, deserializes and saves a database file.
 *
 */
public class DatabaseManager {
    private ArrayList<Client> importedClientList = new ArrayList<>();
    private final File databaseFile = new File("Client_Database.dat");
    private Client client = new Client();

    public void runProgram() throws IOException, ClassNotFoundException {
        createOrReadFile();
        printContent();
    }

    /**
     * Serializes an ArrayList to a "Client_Database.dat" file. If that file exists:
     * 1) deserialize / open the file; 2) append new clients to its list,3) serialize, close and save the file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void createOrReadFile() throws IOException, ClassNotFoundException {
        ArrayListToObjectFileConverter clientList = new ArrayListToObjectFileConverter();
        ObjectFileToArrayListConverter objectFileToArrayListConverter = new ObjectFileToArrayListConverter();

        boolean doesFileExist = objectFileToArrayListConverter.databaseFile().exists();
        if (!doesFileExist){
            //Initializes ArrayList<Client> and converts it to an .dat File
            clientList.convertArrayListToFile();
        } else {
            //opens database as an ArrayList Object
            deserializeClientList(objectFileToArrayListConverter);
            //append new clients to the now opened list
            appendNewClientToList();
            //save the list as an object inside a file and close the file.
            serializeClientList();
        }
    }

    /**
     * creates a new Client object and adds it to the list of clients
     */
    private void appendNewClientToList() {
        client.setInsuranceNumber(44147);
        client.setName("Thor");
        importedClientList.add(client);
    }

    /**
     * opens the saved ArrayList from the File a
     * @param objectFileToArrayListConverter
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void deserializeClientList(ObjectFileToArrayListConverter objectFileToArrayListConverter) throws IOException, ClassNotFoundException {
        importedClientList = objectFileToArrayListConverter.importClientArrayListFile();
    }

    /**
     * saves the importedClientList as a .dat file
     * @throws IOException
     */
    private void serializeClientList() throws IOException {
        FileOutputStream file = new FileOutputStream(databaseFile);
        ObjectOutputStream newFile = new ObjectOutputStream(file);
        newFile.writeObject(importedClientList);
        newFile.close();
        file.close();
    }

    /**
     * prints every element from list
     */
    private void printContent() {
        System.out.println(importedClientList);
    }
}
