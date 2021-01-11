package com.dao;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(String message, Throwable exception)
	{
		super(message, exception);
	}

	public DaoException(String message)
	{
		super(message);
	}

	public DaoException(Throwable exception)
	{
		super(exception);
	}

}
