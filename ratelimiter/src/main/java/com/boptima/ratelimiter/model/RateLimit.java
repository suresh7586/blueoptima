/*
 *  This file will store the information received from github in response to query send by ResourceLimit.java file
 *  which will be grabbed by GitHubRateLimitResponse.java file.   
*/

package com.boptima.ratelimiter.model;

public class RateLimit {
  private int limit;
  private int remaining;
  private int reset;

  public RateLimit() {
  }

  public RateLimit(int limit, int remaining, int reset) {
    this.limit = limit;
    this.remaining = remaining;
    this.reset = reset;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getRemaining() {
    return remaining;
  }

  public void setRemaining(int remaining) {
    this.remaining = remaining;
  }

  public int getReset() {
    return reset;
  }

  public void setReset(int reset) {
    this.reset = reset;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    RateLimit rateLimit = (RateLimit) o;

    if (limit != rateLimit.limit) return false;
    if (remaining != rateLimit.remaining) return false;
    return reset == rateLimit.reset;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + limit;
    result = 31 * result + remaining;
    result = 31 * result + reset;
    return result;
  }

  @Override
  public String toString() {
    return "RateLimit{" +
        "limit=" + limit +
        ", remaining=" + remaining +
        ", reset=" + reset +
        '}';
  }
}
