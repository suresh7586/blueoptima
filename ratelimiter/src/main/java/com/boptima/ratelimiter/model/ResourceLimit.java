/*
* This file will fetch different liimiting information available,
* Say, for core and search, from github.
*/

package com.boptima.ratelimiter.model;

public class ResourceLimit {
  private RateLimit core;
  private RateLimit search;

  public ResourceLimit() {
  }

  public ResourceLimit(RateLimit core, RateLimit search) {
    this.core = core;
    this.search = search;
  }

  public RateLimit getCore() {
    return core;
  }

  public void setCore(RateLimit core) {
    this.core = core;
  }

  public RateLimit getSearch() {
    return search;
  }

  public void setSearch(RateLimit search) {
    this.search = search;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    ResourceLimit that = (ResourceLimit) o;

    if (!core.equals(that.core)) return false;
    return search.equals(that.search);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + core.hashCode();
    result = 31 * result + search.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "ResourceLimit{" +
        "core=" + core +
        ", search=" + search +
        '}';
  }
}
