/*Grabs github response related to Rate Limit*/

package com.boptima.ratelimiter.response;

import com.boptima.ratelimiter.model.ResourceLimit;

public class GitHubRateLimitResponse {

  private ResourceLimit resources;

  public GitHubRateLimitResponse() {
  }

  public ResourceLimit getResources() {
    return this.resources;
  }

  public void setResources(ResourceLimit resources) {
    this.resources = resources;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    GitHubRateLimitResponse that = (GitHubRateLimitResponse) o;

    return resources.equals(that.resources);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + resources.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "GitHubRateLimitResponse{" +
        "resources=" + resources +
        '}';
  }
}