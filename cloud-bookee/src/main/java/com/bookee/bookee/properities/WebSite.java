package com.bookee.bookee.properities;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value="classpath:web.properities")
@Component
@Setter
@Getter
public class WebSite {

	@Value("${url}")
	private String url;
}
