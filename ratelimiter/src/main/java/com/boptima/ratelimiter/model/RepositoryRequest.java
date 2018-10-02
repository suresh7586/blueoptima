/*
 *   This file is sending information, say login,
 *   which will be concatenated with the api-github.com
 *   for the name of the repositories present under specified user.
*/

package com.boptima.ratelimiter.model;

public class RepositoryRequest {
  private String profile;

  public RepositoryRequest() {
  }

  public RepositoryRequest(String profile) {
    this.profile = profile;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    RepositoryRequest that = (RepositoryRequest) o;

    return profile.equals(that.profile);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + profile.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "user:" + profile;
  }
}
