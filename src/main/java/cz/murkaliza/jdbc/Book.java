package cz.murkaliza.jdbc;

import cz.murkaliza.jdbc.utils.DataTransferObject;

public class Book implements DataTransferObject {
    private long id;
    private String title;
    private String firstName;
    private String lastName;
    private int year;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + firstName + " | " + lastName + " | " + year;
    }

}
