package cz.murkaliza.oop.InheritanceInterface;

import java.util.ArrayList;
import java.util.List;

public class InheritanceInterface {
    public static void main(String... args) {
        Cat cat = new Cat("Cheshire Cat");
        Dog dog = new Dog("White Fang");

        List<Animal> animals = new ArrayList<Animal>();
        animals.add(cat);
        animals.add(dog);
        animals.add(new Cat("Puss in Boots"));
        animals.add(new Turtle("Turtle"));

        for (Animal pet: animals) {
            pet.eat();
//            if (pet instanceof Cat) {
//
//            }
            if (pet instanceof Speakable) {
                ((Speakable) pet).makeSound();
            }

        }
    }
}
abstract class Animal {
    private final String nickname;

    public Animal(String nickname) {
        this.nickname = nickname;
    }
    public void eat() {
        System.out.println(nickname + " eating...");
    }

    @Override
    public String toString() {
        return nickname;
    }
}
class Cat extends Animal implements Speakable {
    public Cat(String nickname) {
        super(nickname);
    }
    @Override
    public void makeSound() {
        System.out.println(this + " said 'Meow!'");
    }
}

class Dog extends Animal implements Speakable {
    public Dog(String nickname) {
        super(nickname);
    }
    @Override
    public void makeSound() {
        System.out.println(this + " said 'Woof! Woof!'");
    }
}

class CatDog extends Animal implements Speakable {

    public CatDog(String nickname) {
        super(nickname);
    }

    @Override
    public void makeSound() {
        System.out.println(this + " said 'Meow!'");
        System.out.println(this + " said 'Woof! Woof!'");
    }
}

class Turtle extends Animal {
    public Turtle(String nickname) {
        super(nickname);
    }
}

interface Speakable {
    void makeSound();
}

interface Eatable {
    void makeSound();
}


