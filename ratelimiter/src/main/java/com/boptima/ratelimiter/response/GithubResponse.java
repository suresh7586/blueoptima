/*  
 *  Generic file which is extended by Response file
 *  such GitHubUsersResponse.java, GitHubRepositoriesResponse.java, GitHubCommitsResponse.java
 *  as all these files returns information as list.
*/

package com.boptima.ratelimiter.response;

import java.util.List;

class GitHubResponse<T> {
  private List<T> items;

  public GitHubResponse() {
  }

  public GitHubResponse(List<T> items) {
    this.items = items;
  }

  public List<T> getItems() {
    return this.items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    GitHubResponse<?> that = (GitHubResponse<?>) o;

    return items != null ? items.equals(that.items) : that.items == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (items != null ? items.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "GitHubResponse{" +
        "items=" + items +
        '}';
  }
}