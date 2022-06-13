package de.quinscape.FileInputConverter;

import de.quinscape.Model.Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ArrayListToObjectFileConverter {
    public void convertArrayListToFile() throws IOException {
        FileOutputStream file = new FileOutputStream("Client_Database.dat");
        ObjectOutputStream newFile = new ObjectOutputStream(file);
        newFile.writeObject(listOfClients());
        newFile.close();
        file.close();
    }

    public ArrayList<Client> listOfClients() {
        return new ArrayList<>();
    }
}
