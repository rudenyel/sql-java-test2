package cz.murkaliza.oop.Inheritance;

import java.util.ArrayList;
import java.util.List;

public class Inheritance {
    public static void main(String... args) {
        Animal thing = new Animal("The Thing");
        Cat cat = new Cat("Cheshire Cat");
        Dog dog = new Dog("White Fang");

//        thing.eat();
//        cat.eat();
//        cat.makeSound();
//        dog.eat();
//        dog.makeSound();

        List<Animal> animals = new ArrayList<Animal>();
        animals.add(cat);
        animals.add(dog);
        animals.add(new Cat("Puss in Boots"));


//        System.out.println(animals);
//        animals.forEach(System.out::println);
        for (Animal pet: animals) {
            pet.eat();
//            pet.makeSound();
        }



    }
}

class Animal {
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
class Cat extends Animal {
    public Cat(String nickname) {
        super(nickname);
    }
    public void makeSound() {
        System.out.println(this + " said 'Meow!'");
    }
}

class Dog extends Animal {
    public Dog(String nickname) {
        super(nickname);
    }
    public void makeSound() {
        System.out.println(this + " said 'Woof! Woof!'");
    }
}
