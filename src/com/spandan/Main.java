package com.spandan;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone phone = new MobilePhone("");

    public static void main(String[] args) {

        boolean quit = false;
        int choice = 0;
        startPhone();
        printinstructions();
        while (!quit) {
            System.out.println("Enter your action: (0 to show available actions)");
            boolean hasNext=scanner.hasNextInt();
            if(hasNext)
                choice = scanner.nextInt();
            else
                System.out.println("Invalid entry");
            scanner.nextLine();
            switch (choice) {

                case 0: {
                    printinstructions();
                    break;
                }
                case 1: {
                    MobilePhone.printcontacts();
                    break;
                }
                case 2: {
                    addcontacts();
                    break;
                }
                case 3: {
                    modifycontacts();
                    break;
                }
                case 4: {
                    removecontacts();
                    break;
                }
                case 5: {
                    searchcontacts();
                    break;
                }
                case 6: {
                    System.out.println("Shutting down...");
                    quit = true;
                    break;
                }
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void startPhone() {
        System.out.println("Starting Phone...");
    }

    private static void printinstructions() {
        System.out.println("\n Press:");
        System.out.println("\t 0 - To print choice options.");
        System.out.println("\t 1 - To print the list of contacts.");
        System.out.println("\t 2 - To add contacts.");
        System.out.println("\t 3 - To modify contacts.");
        System.out.println("\t 4 - To remove contacts.");
        System.out.println("\t 5 - To search for a contact in the list.");
        System.out.println("\t 6 - To shutdown your phone.");
    }

    private static void addcontacts() {
        String name, number;
        System.out.println("Enter name:");
        name = scanner.nextLine();
        System.out.println("Enter number:");
        number = scanner.nextLine();
        Contacts newcontact = Contacts.createContact(name, number);
        if (phone.add(newcontact)) {
            System.out.println("New contact added: name = " + name + "\nphone = " + number);
        } else {
            System.out.println("Cannot add, " + name + " already on file.");
        }
    }

    private static void modifycontacts() {
        String name_new, name_old, number;
        System.out.println("Enter the name:");
        name_old = scanner.nextLine();
        Contacts existingcontactrecord = phone.querycontact(name_old);
        if (existingcontactrecord == null) {
            System.out.println(name_old + " does not exist in file.");
            return;
        }
        System.out.println("Enter new name:");
        name_new = scanner.nextLine();
        Contacts existingcontactname = phone.querycontact(name_new);
        if (existingcontactname != null) {
            System.out.println("Contact with name " + name_new + " already exists.");
            return;
        }
        System.out.println("Enter new number:");
        number = scanner.nextLine();
        Contacts newcontact = Contacts.createContact(name_new, number);
        phone.update(existingcontactrecord, newcontact);
    }

    private static void removecontacts() {
        System.out.println("Enter the name of contact to delete:");
        String name = scanner.nextLine();
        Contacts exists = phone.querycontact(name);
        if (exists == null) {
            System.out.println("Contact does not exist on record.");
            return;
        }
        phone.remove(exists);
    }

    private static void searchcontacts() {
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        Contacts existingcontactrecord = phone.querycontact(name);
        if (existingcontactrecord == null) {
            System.out.println(name + " not found.");
            return;
        }
        System.out.println("Name: " + existingcontactrecord.getContactName() +
                    "\n Phone Number: " + existingcontactrecord.getPhoneNumber());
    }

}
