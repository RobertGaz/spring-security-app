package orlanda.springsecurityfirstapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="person")
@Entity
public class Person {
    @Id
    private int id;

    private int yearOfBirth;
    private String username;
    private String password;
    private String role;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", yearOfBirth=" + yearOfBirth +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
