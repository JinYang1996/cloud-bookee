package com.bookee.bookee.properities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(value="my-profile")
@Setter
@Getter
@Validated
public class ProfileProperities {
	@NotEmpty
	private String name;
	@NotEmpty
	@Email
	private String email;
}
