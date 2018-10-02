/*Grabs github response related to User*/

package com.boptima.ratelimiter.response;

import com.boptima.ratelimiter.model.User;

public class GitHubUsersResponse extends GitHubResponse<User> {
  public GitHubUsersResponse() {
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof GitHubUsersResponse)) {
      return false;
    } else {
      GitHubUsersResponse other = (GitHubUsersResponse) o;
      return other.canEqual(this);
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof GitHubUsersResponse;
  }

  public int hashCode() {
    int result = 1;
    return result;
  }

  public String toString() {
    return "GitHubUsersResponse()";
  }
}