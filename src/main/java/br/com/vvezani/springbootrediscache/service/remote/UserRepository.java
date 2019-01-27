package br.com.vvezani.springbootrediscache.service.remote;

import br.com.vvezani.springbootrediscache.model.User;

public interface UserRepository {

  User getUser(String username) throws RepositoryException;

  User createUser(User user) throws RepositoryException;

  User updateUser(User user) throws RepositoryException;

  User delete(String username) throws RepositoryException;
}
