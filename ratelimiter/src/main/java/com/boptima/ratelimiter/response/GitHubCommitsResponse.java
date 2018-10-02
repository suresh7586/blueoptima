/*Grabs github response related to Commit*/

package com.boptima.ratelimiter.response;

import com.boptima.ratelimiter.model.Commit;

public class GitHubCommitsResponse extends GitHubResponse<Commit> {
  public GitHubCommitsResponse() {
  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof GitHubCommitsResponse)) {
      return false;
    } else {
      GitHubCommitsResponse other = (GitHubCommitsResponse) o;
      return other.canEqual(this);
    }
  }

  protected boolean canEqual(Object other) {
    return other instanceof GitHubCommitsResponse;
  }

  public int hashCode() {
    int result = 1;
    return result;
  }

  public String toString() {
    return "GitHubCommitsResponse()";
  }
}