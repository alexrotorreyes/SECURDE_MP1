package Controller;


import Model.User;
import View.Frame;
import java.util.ArrayList;



public class Main {
    
    public SQLite sqlite;
    
    public static void main(String[] args) {
        new Main().init();
    }
    
    public void init(){
        // Initialize a driver object
        sqlite = new SQLite();

        // Create a database
        sqlite.createNewDatabase();
        
        // Drop users table if needed
        sqlite.dropUserTable();
        
        // Create users table if not exist
        sqlite.createUserTable();
        
        // Add users
        
        sqlite.addUser("admin", Controller.BCrypt.hashpw("qwerty1234", Controller.BCrypt.gensalt(12)) , 5);
        sqlite.addUser("manager", Controller.BCrypt.hashpw("qwerty1234", Controller.BCrypt.gensalt(12)), 4);
        sqlite.addUser("staff", Controller.BCrypt.hashpw("qwerty1234", Controller.BCrypt.gensalt(12)), 3);
        sqlite.addUser("client1", Controller.BCrypt.hashpw("qwerty1234", Controller.BCrypt.gensalt(12)), 2);
        sqlite.addUser("client2", Controller.BCrypt.hashpw("qwerty1234", Controller.BCrypt.gensalt(12)), 2);
        
        // Get users
        ArrayList<User> users = sqlite.getUsers();
        for(int nCtr = 0; nCtr < users.size(); nCtr++){
            System.out.println("===== User " + users.get(nCtr).getId() + " =====");
            System.out.println(" Username: " + users.get(nCtr).getUsername());
            System.out.println(" Password: " + users.get(nCtr).getPassword());
            System.out.println(" Role: " + users.get(nCtr).getRole());
        }
        
        // Initialize User Interface
        Frame frame = new Frame();
        frame.init(this);
    }
    
}
