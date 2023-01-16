package com.instagram.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AvatarDto {

	@NotNull
	@NotBlank(message = "Avatar can not be blank")
	private String avatar;
}
