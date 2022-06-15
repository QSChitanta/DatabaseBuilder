package de.quinscape.FileOutputConverter;

import de.quinscape.Model.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ObjectFileToArrayListConverter {

    /**
     *
     * @return a relative path of the database file
     */
    public File databaseFile(){
        return new File("Client_Database.dat");
    }

    /**
     * deserializes the parsed file
     * @param file
     * @return a file as an Object
     * @throws IOException
     */
    public ObjectInputStream getClientDatabaseFile(File file) throws IOException {
        FileInputStream databaseFile = new FileInputStream(file);
        return new ObjectInputStream(databaseFile);
    }

    /**
     * casting the database file object as an ArrayList
     * @return an ArrayList
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Client> importClientArrayListFile() throws IOException, ClassNotFoundException {
        ArrayList clientList = (ArrayList) getClientDatabaseFile(databaseFile()).readObject();
        return clientList;
    }

}
