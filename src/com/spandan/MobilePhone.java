package com.spandan;

import java.util.ArrayList;

public class MobilePhone {
    private static ArrayList<Contacts> Phone;
    private String myNumber;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        Phone = new ArrayList<>();
    }

    public static void printcontacts() {
        System.out.println("Contact List");
        System.out.println("You have " + Phone.size() + " contacts in your list.");
        for (int i = 0; i < Phone.size(); i++) {
            System.out.println((i + 1) + ". " + Phone.get(i).getContactName() + " --> " + Phone.get(i).getPhoneNumber());
        }
    }

    public boolean add(Contacts contacts) {
        if (findItem(contacts.getContactName()) < 0) {
            Phone.add(contacts);
            System.out.println("Contact saved successfully.");
            return true;
        }
        return false;
    }

    public boolean update(Contacts oldcontact, Contacts newcontact) {
        int foundposition = findItem(oldcontact);
        if (foundposition < 0) {
            System.out.println(oldcontact.getContactName() + " was not found on file.");
            return false;
        }
        Phone.set(foundposition, newcontact);
        System.out.println(oldcontact.getContactName() + " was replaced with " + newcontact.getContactName());
        return true;
    }

    public void remove(Contacts contacts) {
        int position = findItem(contacts);
        if (position < 0) {
            System.out.println("Contact not found in the record.");
        } else {
        System.out.println(contacts.getContactName() + " was deleted.");
        Phone.remove(position);
        }
    }

    private int findItem(Contacts contacts) {
        return Phone.indexOf(contacts);
    }

    private int findItem(String contactname) {
        for (int i = 0; i < Phone.size(); i++) {
            Contacts contacts = Phone.get(i);
            if (contacts.getContactName().equals(contactname)) {
                return i;
            }
        }
        return -1;
    }

    public String querycontact(Contacts contacts) {
        if (findItem(contacts) >= 0)
            return contacts.getContactName();
        return null;
    }

    public Contacts querycontact(String name) {
        int position = findItem(name);
        if (position >= 0)
            return Phone.get(position);
        return null;
    }
}
