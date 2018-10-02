/*Grabs github response related to Repository*/

package com.boptima.ratelimiter.response;

import com.boptima.ratelimiter.model.Repository;

public class GitHubRepositoriesResponse extends GitHubResponse<Repository> {
  public GitHubRepositoriesResponse() {
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof GitHubRepositoriesResponse)) {
      return false;
    } else {
      GitHubRepositoriesResponse other = (GitHubRepositoriesResponse) o;
      return other.canEqual(this);
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof GitHubRepositoriesResponse;
  }

  public int hashCode() {
    int result = 1;
    return result;
  }

  public String toString() {
    return "GitHubRepositoriesResponse()";
  }
}