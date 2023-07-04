package br.com.msansone.sansecurity.model.dto;

public record UserRequestChangePassDto(String email, String oldPass, String newPass) {

}
