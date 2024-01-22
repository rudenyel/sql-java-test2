package cz.murkaliza.oop.Encapsulation;

public class Encapsulation {

    public static void main(String... args) {
        Person.promtStatic();
        Person liza = new Person("Liza", "Rudenko");
        liza.promt();
        Person andrey = new Person("Andrey", "Rudenko");
        andrey.promt();
        Person olga = new Person("Olga", "Rudenko");
        olga.promt();

        System.out.println(liza.getCount());
        System.out.println(andrey.getCount());
        System.out.println(olga.getCount());

        System.out.println(liza.getFullName());
        System.out.println(andrey.getFullName());
        System.out.println(olga.getFullName());
    }
}

class Person {

    private final String first_name;
    private String last_name;
    private static int count = 0;

    public Person(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        count += 1;
    }

    String getFullName() {
        return first_name + " " + last_name;
    }

    void setLastName(String last_name) {
        this.last_name = last_name;
    }

    int getCount() {
        return count;
    }

    void promt() {
        System.out.println("I am " + first_name + "!");
    }

    static void promtStatic() {
        System.out.println("Hello!");
//        System.out.println("Hello!" +  this.first_name );
    }
}
