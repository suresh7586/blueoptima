/*This file  keeps a tab on ratelimit whether it is exceeded or not*/

package com.boptima.ratelimiter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS, reason = "Too many request")
public class RateLimitExeededException extends RuntimeException {

  /**
   * Unique ID for Serialized object
   */
  private static final long serialVersionUID = -8790211652911971729L;

  public RateLimitExeededException(Date date) {
    super("Please try after " + date);
  }
}
