/*
 *  This will store the information received from github in reponse to query sent by UserRequest.java file
 *  which will be grabbed by GitHubUsersResponse.java file.
*/

package com.boptima.ratelimiter.model;

public class User {
  private int id;
  private String login;
  private String url;

  public User() {
  }

  public User(int id, String login, String url) {
    this.id = id;
    this.login = login;
    this.url = url;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    User user = (User) o;

    if (id != user.id) return false;
    if (!login.equals(user.login)) return false;
    return url.equals(user.url);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + id;
    result = 31 * result + login.hashCode();
    result = 31 * result + url.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", login='" + login + '\'' +
        ", url='" + url + '\'' +
        '}';
  }
}
