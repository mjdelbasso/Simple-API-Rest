package com.mjdb.restful.service.impl;

import com.mjdb.restful.model.User;
import com.mjdb.restful.repo.IUserRepo;
import com.mjdb.restful.service.IUserService;
import java.util.List;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
  extends CRUDImpl<User, Long>
  implements IUserService {

  @Autowired
  private IUserRepo repo;

  @Override
  protected IUserRepo getRepo() {
    return repo;
  }

  @Override
  public List<User> list() throws Exception {
    return repo.findAll();
  }

  @Override
  public User findById(@NonNull Long id) throws Exception {
    return repo.findById(id).get();
  }

  @Override
  public void delete(Long id) throws Exception {
    if (id != null) repo.deleteById(id);
  }

  @Override
  public User create(@NonNull User user) throws Exception {
    return repo.save(user);
  }

  @Override
  public User update(@NonNull User user) throws Exception {
    return repo.save(user);
  }
}
