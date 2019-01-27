package br.com.vvezani.springbootrediscache.controller;

import br.com.vvezani.springbootrediscache.model.User;
import br.com.vvezani.springbootrediscache.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/{username}")
  public ResponseEntity<User> getUser(@PathVariable String username) {
    User user = userService.getUser(username);
    return ResponseEntity.ok(user);
  }

  @PutMapping("/users")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    userService.updateUser(user);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    userService.createUser(user);
    return ResponseEntity.ok(user);
  }

  @DeleteMapping("/users/{username}")
  public ResponseEntity<User> deleteUser(@PathVariable String username) {
    final User user = userService.deleteUser(username);
    return ResponseEntity.ok(user);
  }

}
