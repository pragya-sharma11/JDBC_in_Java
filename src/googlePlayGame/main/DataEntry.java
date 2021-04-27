package googlePlayGame.main;

import googlePlayGame.city.City;
import googlePlayGame.cityHelper.CityHelper;
import googlePlayGame.connection.DatabaseConnection;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;

enum Choice {
    INSERT, READ, UPDATE, DELETE
}

public class DataEntry {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connectToDatabase();

        



        String inputChoice = "";

        System.out.println("Enter INSERT for inserting all the details of city.");
        System.out.println("Enter READ for reading all the details of city.");
        System.out.println("Enter UPDATE for updating all the details of city");
        System.out.println("Enter DELETE for deleting all the details of city");

        Scanner scanner = new Scanner(System.in);
        inputChoice =
                JOptionPane.showInputDialog(
                         null,
                        "Enter choice",
                        "Choice",
                        JOptionPane.PLAIN_MESSAGE
                );
        Choice choice = Choice.valueOf(inputChoice);
        City city;
        CityHelper helper;

        String name;
        boolean traversed;
        int killoMeters;
        switch (choice) {
            case INSERT:
                System.out.println("enter details!!");
                name =
                        JOptionPane.showInputDialog(
                                null,
                                "Enter the name of the city you want to insert.",
                                "city",
                                JOptionPane.PLAIN_MESSAGE
                        );
                traversed = Boolean.parseBoolean(
                        JOptionPane.showInputDialog(
                                null,
                                "Enter true or false for your visit to this city.",
                                "traversed",
                                JOptionPane.PLAIN_MESSAGE
                        )
                );
                killoMeters = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                null,
                                "Enter the km required to reach.",
                                "KiloMeters",
                                JOptionPane.PLAIN_MESSAGE
                        )
                );
                city = new City(name, traversed, killoMeters);

                helper = new CityHelper();
                try {

                    helper.addCityToDatabase(city, DatabaseConnection.getConnection());
                } catch (SQLException exception) {
                    System.out.println(city.getName() + " could not be added");
                }
                break;
            case READ:
                helper = new CityHelper();
                try {
                    helper.readAllCitiesFromDatabase(DatabaseConnection.getConnection());
                } catch (SQLException exception) {
                    System.out.println("Error!!");
                }
                break;
            case DELETE:
                name =
                        JOptionPane.showInputDialog(
                                null,
                                "Enter the name of the city you want to insert.",
                                "city Name",
                                JOptionPane.PLAIN_MESSAGE
                        );
                city = new City(name, false, 0);
                helper = new CityHelper();
                try {
                    helper.deleteCity(DatabaseConnection.getConnection(), city);
                } catch (SQLException exception) {
                    System.out.println("There is an error occurred in deletion!!");
                }
                break;
            case UPDATE:
                name =
                        JOptionPane.showInputDialog(
                                null,
                                "Enter the name of the city you want to insert.",
                                "city Name",
                                JOptionPane.PLAIN_MESSAGE
                        );
                traversed = Boolean.parseBoolean(
                        JOptionPane.showInputDialog(
                                null,
                                "Enter true or false for your visit to this city.",
                                "traversed",
                                JOptionPane.PLAIN_MESSAGE
                        )
                );
                killoMeters = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                null,
                                "Enter the km required to reach.",
                                "KiloMeters Required",
                                JOptionPane.PLAIN_MESSAGE
                        )
                );
                city = new City(name, traversed, killoMeters);
                helper = new CityHelper();
                try {
                    helper.updateCity(DatabaseConnection.getConnection(), city);
                } catch (SQLException exception) {
                    System.out.println("There is an error in updation!!!");
                }
                break;

        }

    }
}
