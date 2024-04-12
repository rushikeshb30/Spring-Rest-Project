package com.jspiders.springrestproject.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

	private String message;
	private T data;
	private int status;
}
