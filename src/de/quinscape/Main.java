package de.quinscape;

import de.quinscape.Database.DatabaseManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.runProgram();
    }
}
