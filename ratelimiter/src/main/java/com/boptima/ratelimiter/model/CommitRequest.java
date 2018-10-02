/*
 *   This file is sending information, say login,
 *   which will be concatenated with the api-github.com
 *   for commits done by specified user.
*/

package com.boptima.ratelimiter.model;

public class CommitRequest {
  private String profile;

  public CommitRequest() {
  }

  public CommitRequest(String profile) {
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

    CommitRequest that = (CommitRequest) o;

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
    return "author:" + profile;
  }
}
