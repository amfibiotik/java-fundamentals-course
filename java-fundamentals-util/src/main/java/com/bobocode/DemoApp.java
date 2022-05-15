package com.bobocode;

import com.bobocode.data.Accounts;

import java.time.LocalDate;

public class DemoApp {
    public static void main(String[] args) {
        var accounts = Accounts.generateAccountList(20);
        var emailToBirthdayTable = new HashTable<String, LocalDate>();
        accounts.forEach(a -> emailToBirthdayTable.put(a.getEmail(), a.getBirthday()));
        emailToBirthdayTable.printTable();
    }
}

