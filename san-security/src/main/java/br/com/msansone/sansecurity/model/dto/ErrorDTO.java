package br.com.msansone.sansecurity.model.dto;

public record ErrorDTO(Long id, String message, String source) {
	
	public ErrorDTO(String msg){
		this(0L, msg,"");
	}

}
