package com.mjdb.restful.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mjdb.restful.model.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {}
