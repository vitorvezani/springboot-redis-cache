package br.com.vvezani.springbootrediscache.service.remote;

import br.com.vvezani.springbootrediscache.model.User;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ExpensiveUserRepository implements UserRepository {

  ConcurrentHashMap<String, User> localUserRepository = new ConcurrentHashMap<>();

  @Override
  public User getUser(String username) {
    sleep(2000);
    return localUserRepository.get(username);
  }

  @Override
  public User createUser(User user) throws RepositoryException {
    if (localUserRepository.get(user.getUsername()) != null) {
      throw new RepositoryException("User " + user.getUsername() + " already exists");
    }
    localUserRepository.put(user.getUsername(), user);
    sleep(2000);
    return localUserRepository.get(user.getUsername());
  }

  @Override
  public User updateUser(User user) throws RepositoryException {
    final User existingUser = localUserRepository.get(user.getUsername());
    if (existingUser == null) {
      throw new RepositoryException("User " + user.getUsername() + " does not exists");
    }
    sleep(2000);
    localUserRepository.put(user.getUsername(), user);
    return localUserRepository.get(user.getUsername());
  }

  @Override
  public User delete(String username) {
    sleep(2000);
    return localUserRepository.remove(username);
  }

  private void sleep(int bound) {
    try {
      Thread.sleep(new Random().nextInt(bound));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
