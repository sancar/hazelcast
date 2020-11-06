package domainclasses;

public class Employee {

    private String name;
    private int age;
    private String surname;

    public Employee(String name, int age, String surname) {
        this.name = name;
        this.age = age;
        this.surname = surname;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee() {

    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}