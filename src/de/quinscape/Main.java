package de.quinscape;

import de.quinscape.Database.Manager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Manager manager = new Manager();
        manager.runProgram();
        //TODO: refactor the menu to its own class - need help from someone since it doesnt work when i do it
        //TODO: change the ArrayList to a Set, so u only have unique clients, no doubles etc
    }
}
