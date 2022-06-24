package de.quinscape.UserMenu;

public class Menu {
    public final static int NO_SELECTION = 0;
    public final static int SHOW_ALL_CLIENTS = 1;
    public final static int SHOW_SELECTED_CLIENT = 2;
    public final static int ADD_NEW_CLIENT = 3;
    public final static int UPDATE_CLIENT = 4;
    public final static int DELETE_CLIENT = 5;
    public final static int DELETE_ALL_CLIENTS = 6;
    public final static int END_PROGRAM = 7;

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
