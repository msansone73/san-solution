package br.com.msansone.sansecurity.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
		Long id,
		@Email
		String email,
		@NotBlank
		String pass,
		@NotBlank
		String name,
		LocalDate dateCreate,
		boolean admin,
		boolean enable
		) {}
