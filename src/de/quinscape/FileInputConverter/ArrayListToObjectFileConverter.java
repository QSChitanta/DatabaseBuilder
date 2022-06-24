package de.quinscape.FileInputConverter;

import de.quinscape.Model.Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ArrayListToObjectFileConverter {
    private static final File databaseFile = new File("Client_Database.dat");
    private static ArrayList<Client> listOfClients = new ArrayList<>();

    /**
     * converts/serializes an ArrayList into a file for further usage
     * @throws IOException
     */
    public static void convertArrayListToFile() throws IOException {
        FileOutputStream file = new FileOutputStream(databaseFile);
        ObjectOutputStream newFile = new ObjectOutputStream(file);
        newFile.writeObject(listOfClients);
        newFile.close();
        file.close();
    }
}
