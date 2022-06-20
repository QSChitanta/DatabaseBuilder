package de.quinscape.NavigationMenu;

import de.quinscape.Database.DatabaseManager;
import de.quinscape.Model.Client;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class Menu {
    DatabaseManager db = new DatabaseManager();
    public String[] printMenuOptions(){
        return new String[]{
                "1- Show all clients",
                "2- Show specific client",
                "3- Add new client",
                "4- Update existing client",
                "5- Delete client",
                "6- Delete all clients",
                "7- Save and Exit"
        };
    }
}
//
