package edu.scoalainformala.HW09Java8;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    //Filters a list of Person objects based on their birth month and sorts them by first name.
    List<Person> filterByBirthMonth(List<Person> persons, int targetMonth) {
        return persons.stream()
                .filter(person -> person.getDateOfBirth().getMonthValue() == targetMonth)
                .sorted(Comparator.comparing(Person::getFirstName))
                .collect(Collectors.toList());
    }
}