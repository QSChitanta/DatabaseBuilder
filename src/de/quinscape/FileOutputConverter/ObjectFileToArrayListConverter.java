package de.quinscape.FileOutputConverter;

import de.quinscape.FileInputConverter.ArrayListToObjectFileConverter;
import de.quinscape.Model.Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ObjectFileToArrayListConverter {
    public ObjectInputStream getClientDatabaseFile(String databaseFileName) throws IOException {
        FileInputStream databaseFile = new FileInputStream(databaseFileName);
        return new ObjectInputStream(databaseFile);
    }

    public ArrayList<Client> importClientArrayListFile() throws IOException, ClassNotFoundException {
        ArrayList clientList = (ArrayList) getClientDatabaseFile("Client_Database.dat").readObject();
        return clientList;
    }
}
