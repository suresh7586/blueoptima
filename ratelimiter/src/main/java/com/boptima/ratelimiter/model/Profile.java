/*
 *  This will store the information received from github in reponse to query sent by ProfileRequest.java file.
*/


package com.boptima.ratelimiter.model;

public class Profile {
  private int id;
  private int publicRepos;
  private int publicGists;
  private int followers;
  private int following;
  private String avatarUrl;
  private String login;
  private String name;
  private String url;

  public Profile() {
  }

  public Profile(int id, int publicRepos, int publicGists, int followers, int following, String avatarUrl, String login, String name, String url) {
    this.id = id;
    this.publicRepos = publicRepos;
    this.publicGists = publicGists;
    this.followers = followers;
    this.following = following;
    this.avatarUrl = avatarUrl;
    this.login = login;
    this.name = name;
    this.url = url;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPublicRepos() {
    return publicRepos;
  }

  public void setPublicRepos(int publicRepos) {
    this.publicRepos = publicRepos;
  }

  public int getPublicGists() {
    return publicGists;
  }

  public void setPublicGists(int publicGists) {
    this.publicGists = publicGists;
  }

  public int getFollowers() {
    return followers;
  }

  public void setFollowers(int followers) {
    this.followers = followers;
  }

  public int getFollowing() {
    return following;
  }

  public void setFollowing(int following) {
    this.following = following;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

    Profile profile = (Profile) o;

    if (id != profile.id) return false;
    return login.equals(profile.login);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + id;
    result = 31 * result + login.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Profile{" +
        "id=" + id +
        ", publicRepos=" + publicRepos +
        ", publicGists=" + publicGists +
        ", followers=" + followers +
        ", following=" + following +
        ", avatarUrl='" + avatarUrl + '\'' +
        ", login='" + login + '\'' +
        ", name='" + name + '\'' +
        ", url='" + url + '\'' +
        '}';
  }
}
