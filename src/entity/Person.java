package entity;

/**
 * Represents the class of Person, which contains the attributes of a person.
 * 
 * @author Kai Boon
 * @version 1.0
 * @since 1.0
 */
public class Person {
    protected int age;
    protected String name;

    public Person() {

    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     * Gets the age of the person.
     * 
     * @return int
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Gets the name of the person.
     * 
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the name of the person.
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
