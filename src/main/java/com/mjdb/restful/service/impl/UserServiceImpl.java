package com.mjdb.restful.service.impl;

import com.mjdb.restful.dto.UserDTO;
import com.mjdb.restful.model.User;
import com.mjdb.restful.repo.IUserRepo;
import com.mjdb.restful.service.IUserService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private IUserRepo repo;

  @Autowired
  private ModelMapper mapper;

  @Override
  public List<UserDTO> getAllUsers() throws Exception {
    return repo
      .findAll()
      .stream()
      .map(user -> mapper.map(user, UserDTO.class))
      .sorted(Comparator.comparing(UserDTO::getId))
      .collect(Collectors.toList());
  }

  @Override
  public UserDTO getUserByID(@NonNull Long id) throws Exception {
    return mapper.map(repo.findById(id).get(), UserDTO.class);
  }

  @Override
  public void delete(Long id) throws Exception {
    if (id != null) repo.deleteById(id);
  }

  @Override
  public UserDTO create(@NonNull User user) throws Exception {
    return mapper.map(repo.save(user), UserDTO.class);
  }

  @Override
  public UserDTO update(@NonNull User user) throws Exception {
    return mapper.map(repo.save(user), UserDTO.class);
  }
}
