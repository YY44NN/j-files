public class Animal {

public void majeSound(){
    System.out.println("ruidos de animales");
}


}

class Dog extends Animal{
    public void makeSound(){
        System.out.println("woof");
    }
}

class Cat extends Animal{
    public void makeSound(){
        System.out.println("meow");
    }
}