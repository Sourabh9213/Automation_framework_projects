package com.Telecom.Pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailUtil {

	public static String getDynamicEmail(String name) {
		if (name == null || name.isEmpty()) {
			name = "test";
		}
		name = name.toLowerCase();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		String timestamp = LocalDateTime.now().format(formatter);
		String email = name + timestamp + "@gmail.com";

		return email;
		// Took a bit longer than expected, but now generates clean and unique emails
		// every time 

	}
}