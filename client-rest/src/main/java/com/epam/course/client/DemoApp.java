package com.epam.course.client;

import com.epam.course.client.exception.ServerDataAccessException;
import com.epam.course.client.rest.api.UsersConsumer;
import com.epam.course.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class DemoApp {

    @Autowired
    UsersConsumer usersConsumer;

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");

        DemoApp demoApp = ctx.getBean(DemoApp.class);
        demoApp.menu();
    }

    private void menu() {

        int swValue = -1;

        System.out.println("=================================");
        System.out.println("|   MENU SELECTION DEMO         |");
        System.out.println("=================================");
        System.out.println("| Options:                      |");
        System.out.println("|        1. Get all users       |");
        System.out.println("|        2. Get user by login   |");
        System.out.println("|        3. Get user by id      |");
        System.out.println("|        4. Add user            |");
        System.out.println("|        5. Update user         |");
        System.out.println("|        6. Delete user         |");
        System.out.println("|        0. Exit                |");
        System.out.println("=================================");
        while (swValue != 0) {
            System.out.print("Select option: ");
            if (sc.hasNextInt()) {
                swValue = sc.nextInt();
                checkValue(swValue);
            } else {
                System.out.println("Bad value: " + sc.next());
            }
        }
    }

    private void checkValue(int item) {
        switch (item) {
            case 1:
                getAllUsers();
                break;
            case 2:
                getUserByLogin();
                break;
            case 3:
                getUserById();
                break;
            case 4:
                addNewUser();
                break;
            case 5:
                updateUser();
                break;
            case 6:
                deleteUser();
                break;
            case 0:
                System.out.println("Exit.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    private void deleteUser() {
        Integer id = 0;

        System.out.println("    Enter user ID: ");
        if (sc.hasNextInt()) {
            id = sc.nextInt();
        }

        usersConsumer.deleteUser(id);
    }

    private void updateUser() {
        Integer id = 0;
        String login = "";
        String pass = "";
        String desc = "";

        System.out.println("    Enter user ID: ");
        if (sc.hasNextInt()) {
            id = sc.nextInt();
        }

        System.out.println("    Enter user login: ");
        if (sc.hasNextLine()) {
            login = sc.next();
        }


        System.out.println("    Enter password: ");
        if (sc.hasNextLine()) {
            pass = sc.next();
        }

        System.out.println("    Enter description: ");
        if (sc.hasNextLine()) {
            desc = sc.next();
        }

        User user = new User(id, login, pass, desc);
        usersConsumer.updateUser(user);
    }

    private void addNewUser() {
        String login = "";
        String pass = "";
        String desc = "";

        System.out.println("    Enter user login: ");
        if (sc.hasNextLine()) {
            login = sc.next();
        }


        System.out.println("    Enter password: ");
        if (sc.hasNextLine()) {
            pass = sc.next();
        }

        System.out.println("    Enter description: ");
        if (sc.hasNextLine()) {
            desc = sc.next();
        }

        User user = new User(login, pass, desc);
        Integer userId = usersConsumer.addUser(user);

        System.out.println("User ID: " + userId);
    }

    private void getAllUsers() {
        List<User> users = usersConsumer.getAllUsers();
        System.out.println("users: " + users);
    }

    private void getUserById() {
        Integer userId = 0;
        System.out.println("    Enter user id: ");

        if (sc.hasNextInt()) {
            userId = sc.nextInt();
        }

        try {
            User user = usersConsumer.getUserById(userId);
            System.out.println("    User: " + user);
        } catch (ServerDataAccessException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void getUserByLogin() {
        String userLogin = "";
        System.out.print("    Enter user login: ");
        if (sc.hasNextLine()) {
            userLogin = sc.next();
        }

        try {
            User user = usersConsumer.getUserByLogin(userLogin);
            System.out.println("    User: " + user);
        } catch (ServerDataAccessException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

}
