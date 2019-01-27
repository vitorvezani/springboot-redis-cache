package br.com.vvezani.springbootrediscache.model;

import java.io.Serializable;

public class User implements Serializable {

  public User(String username, String firstName, String lastName) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  private String username;
  private String firstName;
  private String lastName;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}
