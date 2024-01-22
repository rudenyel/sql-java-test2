package cz.murkaliza.oop.InheritanceAbstract;

import java.util.ArrayList;
import java.util.List;

public class InheritanceAbstract {
    public static void main(String... args) {
        Cat cat = new Cat("Cheshire Cat");
        Dog dog = new Dog("White Fang");

        List<Animal> animals = new ArrayList<Animal>();
        animals.add(cat);
        animals.add(dog);
        animals.add(new Cat("Puss in Boots"));
//        animals.add(new CatDog("CatDog"));

        for (Animal pet: animals) {
            pet.eat();
            pet.makeSound();
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

    public abstract void makeSound();

    @Override
    public String toString() {
        return nickname;
    }
}
class Cat extends Animal {
    public Cat(String nickname) {
        super(nickname);
    }
    @Override
    public void makeSound() {
        System.out.println(this + " said 'Meow!'");
    }
}

class Dog extends Animal {
    public Dog(String nickname) {
        super(nickname);
    }
    @Override
    public void makeSound() {
        System.out.println(this + " said 'Woof! Woof!'");
    }
}

//class CatDog extends Cat, Dog {
//
//    public CatDog(String nickname) {
//        super(nickname);
//    }
//}

class Turtle extends Animal {
    public Turtle(String nickname) {
        super(nickname);
    }

    @Override
    public void makeSound() {

    }
}
