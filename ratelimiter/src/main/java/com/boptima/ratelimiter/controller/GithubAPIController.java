/*
 * This file contains the logic for different api request made,
 * such as User information, Repository names, Commits and Profile.
 * Also checking if the application is ran as authenticate user or guest user
 * hence setting the rate limits accordingly.
*/

package com.boptima.ratelimiter.controller;

import com.boptima.ratelimiter.exception.RateLimitExeededException;
import com.boptima.ratelimiter.model.Commit;
import com.boptima.ratelimiter.model.CommitRequest;
import com.boptima.ratelimiter.model.Profile;
import com.boptima.ratelimiter.model.ProfileRequest;
import com.boptima.ratelimiter.model.Repository;
import com.boptima.ratelimiter.model.RepositoryRequest;
import com.boptima.ratelimiter.model.ResourceLimit;
import com.boptima.ratelimiter.model.User;
import com.boptima.ratelimiter.model.UserRequest;
import com.boptima.ratelimiter.response.GitHubCommitsResponse;
import com.boptima.ratelimiter.response.GitHubRateLimitResponse;
import com.boptima.ratelimiter.response.GitHubRepositoriesResponse;
import com.boptima.ratelimiter.response.GitHubUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class GithubAPIController {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${authentication.token}")
  private String token;

  private final String GITHUB_SEARCH = "https://api.github.com/search";
  private final String GITHUB_RATE = "https://api.github.com/rate_limit";
  private final String GITHUB_USERS = "https://api.github.com/users";

  //Setting rate limits based on authenticate or guest user.
  private ResourceLimit ratelimit() {
    ResponseEntity<GitHubRateLimitResponse> forEntity = null;
    if (token!=null && token.length()!=0) {
      final HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "bearer " + token);
      final HttpEntity<String> entity = new HttpEntity<String>(headers);
      forEntity = restTemplate.exchange(GITHUB_RATE, HttpMethod.GET, entity, GitHubRateLimitResponse.class);
    } else  {
      forEntity = restTemplate.getForEntity(GITHUB_RATE, GitHubRateLimitResponse.class);
    }
    return forEntity.getBody().getResources();
  }
  
  
  //GitHub api for getting n-user's login informations
  @RequestMapping("/api/users")
  public List<User> users(@RequestBody List<UserRequest> userRequests) {
    ResourceLimit resourceLimit = ratelimit();
    HttpEntity<String> entity = null;
    if (token!=null && token.length()!=0) {
      final HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "bearer " + token);
      entity = new HttpEntity<String>(headers);
    }
    if (resourceLimit.getSearch().getLimit() >= userRequests.size()) {
      List<User> users = new ArrayList<>();
      for (UserRequest userRequest : userRequests) {
        ResponseEntity<GitHubUsersResponse> forEntity = null;
        if (entity!=null) {
          forEntity = restTemplate.exchange(String.format(GITHUB_SEARCH + "/users?q=%s", userRequest.toString()), HttpMethod.GET, entity, GitHubUsersResponse.class);
        } else {
          forEntity = restTemplate.getForEntity(String.format(GITHUB_SEARCH + "/users?q=%s", userRequest.toString()), GitHubUsersResponse.class);

        }
        users.addAll(forEntity.getBody().getItems());
      }
      return users;
    } else {
      Date d = new Date(resourceLimit.getSearch().getReset());
      throw new RateLimitExeededException(d);
    }
  }

  
  //GitHub api for getting n-user's repository informations
  @RequestMapping("/api/repositories")
  public Map<String, List<Repository>> repositories(@RequestBody List<RepositoryRequest> repositoryRequests) {
    ResourceLimit resourceLimit = ratelimit();
    HttpEntity<String> entity = null;
    if (token!=null && token.length()!=0) {
      final HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "bearer " + token);
      entity = new HttpEntity<String>(headers);
    }
    if (resourceLimit.getSearch().getLimit() >= repositoryRequests.size()) {
      Map<String, List<Repository>> repos = new HashMap<>();
      for (RepositoryRequest repositoryRequest : repositoryRequests) {
        ResponseEntity<GitHubRepositoriesResponse> forEntity = null;
        if (entity!=null) {
          forEntity = restTemplate.exchange(String.format(GITHUB_SEARCH + "/repositories?q=%s", repositoryRequest.toString()), HttpMethod.GET, entity, GitHubRepositoriesResponse.class);
        } else {
          forEntity = restTemplate.getForEntity(String.format(GITHUB_SEARCH + "/repositories?q=%s", repositoryRequest.toString()), GitHubRepositoriesResponse.class);

        }
        repos.put(repositoryRequest.toString(), forEntity.getBody().getItems());
      }
      return repos;
    } else {
      Date d = new Date(resourceLimit.getSearch().getReset());
      throw new RateLimitExeededException(d);
    }
  }


  //GitHub api for getting n-user's Commit informations
  @RequestMapping("/api/commits")
  public Map<String, Map<String, Integer>> commits(@RequestBody List<CommitRequest> commitRequests) {
    ResourceLimit resourceLimit = ratelimit();
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/vnd.github.cloak-preview");
    if (token!=null && token.length()!=0) {
      headers.set("Authorization", "bearer " + token);
    }
    final HttpEntity<String> entity = new HttpEntity<String>(headers);

    if (resourceLimit.getSearch().getLimit() >= commitRequests.size()) {
      Map<String, Map<String, Integer>> commitMap = new HashMap<>();
      for (CommitRequest commitRequest : commitRequests) {
        ResponseEntity<GitHubCommitsResponse> forEntity = restTemplate.exchange(String.format(GITHUB_SEARCH + "/commits?q=%s", commitRequest.toString()), HttpMethod.GET, entity, GitHubCommitsResponse.class);
        Map<String, Integer> repoCount = new HashMap<>();
        List<Commit> commitLists = forEntity.getBody().getItems();
        for (Commit commitList : commitLists) {
          String repo = commitList.getRepository().getName();
          int i = repoCount.getOrDefault(repo, 0);
          repoCount.put(repo, i+1);
        }
        commitMap.put(commitRequest.toString(), repoCount);
      }
      return commitMap;
    } else {
      Date d = new Date(resourceLimit.getSearch().getReset());
      throw new RateLimitExeededException(d);
    }
  }
  
  
  //GitHub api for getting n-user's Profile informations
  @RequestMapping("/api/profiles")
  public List<Profile> profile(@RequestBody List<ProfileRequest> profileRequests) {
    ResourceLimit resourceLimit = ratelimit();
    HttpEntity<String> entity = null;
    if (token!=null && token.length()!=0) {
      final HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "bearer " + token);
      entity = new HttpEntity<String>(headers);
    }

    if (resourceLimit != null && resourceLimit.getCore().getLimit() >= profileRequests.size()) {
      List<Profile> profiles = new ArrayList<>();
      for (ProfileRequest profileRequest : profileRequests) {
        ResponseEntity<Profile> forEntity = null;
        if (entity!=null) {
          forEntity = restTemplate.exchange(String.format(GITHUB_USERS + "/%s", profileRequest.toString()), HttpMethod.GET, entity, Profile.class);
        } else {
          forEntity = restTemplate.getForEntity(String.format(GITHUB_USERS + "/%s", profileRequest.toString()), Profile.class);

        }
        profiles.add(forEntity.getBody());
      }
      return profiles;
    } else {
      Date d = new Date(resourceLimit.getCore().getReset());
      throw new RateLimitExeededException(d);
    }
  }
}