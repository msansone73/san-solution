package br.com.msansone.sansecurity.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserResponseDTO(
		Long id,
		@Email
		String email,
		@NotBlank
		String name,
		LocalDate dateCreate,
		boolean admin,
		boolean enable
		) {}
