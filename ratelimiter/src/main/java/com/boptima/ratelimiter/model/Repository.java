/*
 *  This will store the information received from github in reponse to query sent by RepositoryRequest.java file
 *  which will be grabbed by GitHubRepositoriesResponse.java file.
*/

package com.boptima.ratelimiter.model;

public class Repository {
  private String name;
  private String url;
  private User owner;

  public Repository() {
  }

  public Repository(String name, String url, User owner) {
    this.name = name;
    this.url = url;
    this.owner = owner;
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

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Repository that = (Repository) o;

    if (!name.equals(that.name)) return false;
    if (!url.equals(that.url)) return false;
    return owner.equals(that.owner);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + url.hashCode();
    result = 31 * result + owner.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Repository{" +
        "name='" + name + '\'' +
        ", url='" + url + '\'' +
        ", owner=" + owner +
        '}';
  }
}
