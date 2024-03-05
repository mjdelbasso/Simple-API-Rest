package com.mjdb.restful.service;

import java.util.List;

import com.mjdb.restful.dto.UserDTO;
import com.mjdb.restful.model.User;

public interface IUserService {
  public List<UserDTO> getAllUsers() throws Exception;

  public UserDTO getUserByID(Long id) throws Exception;

  public void delete(Long id) throws Exception;

  public UserDTO create(User user) throws Exception;

  public UserDTO update (User user) throws Exception;
}
