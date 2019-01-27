package br.com.vvezani.springbootrediscache.service.remote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "No such resource")
public class RepositoryException extends RuntimeException {

  public RepositoryException(String message) {
    super(message);
  }
}
