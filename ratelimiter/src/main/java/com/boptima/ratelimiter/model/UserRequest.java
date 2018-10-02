/*
 *   This file is sending information, say firstName, lastName, location
 *   which will be concatenated with the api-github.com
 *   for access of user information like login, id, url.
*/

package com.boptima.ratelimiter.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UserRequest {
  private String firstName;
  private String lastName;
  private String location;

  public UserRequest() {
  }

  public UserRequest(String firstName, String lastName, String location) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.location = location;
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

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    UserRequest that = (UserRequest) o;

    if (!firstName.equals(that.firstName)) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    return location != null ? location.equals(that.location) : that.location == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + firstName.hashCode();
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (location != null ? location.hashCode() : 0);
    return result;
  }

  private String getFullName() {
    String fullName = firstName + " " + lastName;
    String encodedString;
    try {
      encodedString = URLEncoder.encode(fullName, StandardCharsets.UTF_8.toString());
    } catch (UnsupportedEncodingException e) {
      encodedString = firstName + "%20" + lastName;
    }
    return encodedString;
  }

  @Override
  public String toString() {
    if (location != null && !location.isEmpty()) {
      return "in:" + this.getFullName() + "+location:" + location;
    } else {
      return "in:" + this.getFullName();
    }
  }
}
