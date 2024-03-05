package com.mjdb.restful.controller;

import com.mjdb.restful.dto.UserDTO;
import com.mjdb.restful.model.User;
import com.mjdb.restful.service.IUserService;
import jakarta.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  IUserService service;

  @Autowired
  ModelMapper mapper;

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserByID(@RequestParam Long id)
    throws Exception {
    return new ResponseEntity<>(toDTO(service.findById(id)), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() throws Exception {
    List<UserDTO> users = service
      .list()
      .stream()
      .map(user -> toDTO(user))
      .sorted(Comparator.comparing(UserDTO::getId))
      .collect(Collectors.toList());

    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO)
    throws Exception {
    User user = toEntity(userDTO);
    UserDTO response = toDTO(service.create(user));

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<UserDTO> update(
    @PathVariable("id") Long id,
    @RequestBody UserDTO userDTO
  ) throws Exception {
    User user = toEntity(userDTO);

    user.setId(id);

    service.update(user);

    return new ResponseEntity<>(toDTO(user), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id)
    throws Exception {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  public UserDTO toDTO(User user) {
    return mapper.map(user, UserDTO.class);
  }

  public User toEntity(UserDTO userDTO) {
    return mapper.map(userDTO, User.class);
  }
}
