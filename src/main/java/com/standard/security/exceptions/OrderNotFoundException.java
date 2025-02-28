package com.standard.security.exceptions;

public class OrderNotFoundException extends RuntimeException
{
	public OrderNotFoundException(String message)
	{
		super(message);
	}
}

