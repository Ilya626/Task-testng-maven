package com.ilya;

import com.ilya.model.Person;

import java.util.*;

abstract class TestDataProvider {

    private final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static java.util.Random rand = new java.util.Random();
    private final static Set<String> identifiers = new HashSet<>();

    static List<Person> createRandomPersons(int count) {
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Person person = new Person(randomString(), new Random().nextInt(60));
            personList.add(person);
        }

        return personList;
    }

    private static String randomString() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

}
