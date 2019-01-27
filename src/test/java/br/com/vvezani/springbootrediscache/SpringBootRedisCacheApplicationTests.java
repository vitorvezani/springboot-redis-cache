package br.com.vvezani.springbootrediscache;

import br.com.vvezani.springbootrediscache.model.User;
import br.com.vvezani.springbootrediscache.service.remote.ExpensiveUserRepository;
import br.com.vvezani.springbootrediscache.service.remote.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootRedisCacheApplicationTests {

  private static final String USERNAME = "johndoe";
  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Doe";

  @Autowired
  TestRestTemplate restTemplate;

  @SpyBean
  ExpensiveUserRepository remoteClientService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CacheManager cm;

  private User user;

  @Before
  public void init() {
    user = userRepository.createUser(new User(USERNAME, FIRST_NAME, LAST_NAME));
    clearAllCaches();
  }

  @Test
  public void cacheTest() {

    // Should check repo and insert on cache
    User remoteUser = getUser(user.getUsername());
    assertThat(remoteUser.getUsername()).isEqualTo(USERNAME);
    assertThat(remoteUser.getFirstName()).isEqualTo(FIRST_NAME);
    assertThat(remoteUser.getLastName()).isEqualTo(LAST_NAME);
    verify(remoteClientService, atMost(1)).getUser(anyString());

    // Should get from cache
    remoteUser = getUser(remoteUser.getUsername());
    assertThat(remoteUser.getUsername()).isEqualTo(USERNAME);
    assertThat(remoteUser.getFirstName()).isEqualTo(FIRST_NAME);
    assertThat(remoteUser.getLastName()).isEqualTo(LAST_NAME);
    verify(remoteClientService, atMost(1)).getUser(anyString());

    // Should update in cache
    remoteUser.setLastName("Doe Doe");
    updateUser(remoteUser);

    // Should get from cache with updated values
    remoteUser = getUser(remoteUser.getUsername());
    assertThat(remoteUser.getUsername()).isEqualTo(USERNAME);
    assertThat(remoteUser.getFirstName()).isEqualTo(FIRST_NAME);
    assertThat(remoteUser.getLastName()).isEqualTo("Doe Doe");
    verify(remoteClientService, atMost(1)).getUser(anyString());
  }

  private User getUser(String username) {
    return restTemplate.getForEntity("/users/{username}", User.class, username).getBody();
  }

  private void updateUser(User user) {
    restTemplate.put("/users", user);
  }

  private void clearAllCaches() {
    Optional.of(cm.getCache("single-user")).ifPresent(Cache::clear);
  }

}

