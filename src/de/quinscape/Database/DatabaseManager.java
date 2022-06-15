package de.quinscape.Database;

import de.quinscape.FileInputConverter.ArrayListToObjectFileConverter;
import de.quinscape.FileOutputConverter.ObjectFileToArrayListConverter;
import de.quinscape.Model.Client;

import java.io.IOException;
import java.util.ArrayList;

public class DatabaseManager {
    static ArrayList<Client> importedClientList = new ArrayList<>();
    public void runProgram() throws IOException, ClassNotFoundException {
        ArrayListToObjectFileConverter clientList = new ArrayListToObjectFileConverter();
        ObjectFileToArrayListConverter objectToArrayList = new ObjectFileToArrayListConverter();
        Client testClient = new Client();


        //Initializes ArrayList<Client> and converts it to an .dat File
        clientList.convertArrayListToFile();

        //Reads .dat File with the saved ArrayList
        importedClientList = objectToArrayList.importClientArrayListFile();

        //Uses the .dat file as an ArrayList to append new Clients
        appendNewClient(testClient);
            //how do i close the ObjectFileToArrayListConverter - The OutputStreams are not closed

        //TODO: Saving/Overwriting loaded .dat File / Datahase file with the "new" ArrayList
        //FIXME: -> convert into File again and overwrite Client_Database.dat

    }
    
    //Test data
    /**
     * appends Client to Array
     * @param testClient
     */
    private void appendNewClient(Client testClient) {
        testClient.setName("Patrick");
        testClient.setInsuranceNumber(1234);
        importedClientList.add(testClient);
        printContent(importedClientList);
        System.out.println(importedClientList);
    }

    private void printContent(ArrayList<Client> importedClientList) {
        System.out.println(importedClientList.get(0).getInsuranceNumber());
        System.out.println(importedClientList.get(0).getName());
    }
}
