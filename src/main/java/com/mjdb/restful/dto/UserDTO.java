package com.mjdb.restful.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

  Long id;

  @NotNull
  String firstName;

  @NotNull
  String lastName;

  String email;
}
