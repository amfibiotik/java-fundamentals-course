package com.bobocode;

import java.util.Comparator;
import java.util.stream.Stream;

public class DemoApp {
    public static void main(String[] args) {
        Comparator<User> userComparator = new RandomFieldComparator<>(User.class, false);
//        Comparator<User> userComparator = new RandomFieldComparator<>(User.class);
        User user1 = new User("Pavlo", "Tychyna", "male", 20);
        User user2 = new User("Taras", "Shevchenko", "male", 50);
        User user3 = new User("Lesya", "Ukrainka", "female", 30);
        User user4 = new User("Agata", null, "female", 60);
        User user5 = new User("Sofy", null, "female", 64);
        User user6 = new User(null, null, "female", 54);
        User user7 = new User(null, null, "female", 55);
        User user8 = new User(null, "Kipling", "male", 75);
        User user9 = new User(null, "Linkoln", "male", 76);

        Stream.of(user9, user6, user7, user8, user5, user3, user4,user2, user1).sorted(userComparator).forEach(System.out::println);

        System.out.println(userComparator);
    }
}
