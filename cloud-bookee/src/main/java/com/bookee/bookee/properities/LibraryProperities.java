package com.bookee.bookee.properities;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(value="library")
@Component
@Getter
@Setter
@ToString
public class LibraryProperities {

	private String location;
	private List<Book> books;
	
	@Getter
	@Setter
	@ToString
	static class Book{
		String name;
		String content;
	}
}
