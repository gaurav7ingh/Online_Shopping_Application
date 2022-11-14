package com.shopping.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.shopping.model.ShoppingError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ShoppingError> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e,
			WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(e.getFieldError().toString());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AddressException.class)
	public ResponseEntity<ShoppingError> myAddressHandler(AddressException ae, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ae.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CartException.class)
	public ResponseEntity<ShoppingError> mycartHandler(CartException carte, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(carte.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ShoppingError> myCategoryHandler(CategoryException cate, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(cate.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ShoppingError> myCustomerHandler(CustomerException ctre, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ctre.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ShoppingError> myExceptionHandler(Exception e, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ShoppingError> myOrderHandler(OrderException oe, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(oe.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ShoppingError> myProductHandler(ProductException pe, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(pe.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ShoppingError> myUserHandler(UserException ue, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ShoppingError> noHandlerFoundHandler(NoHandlerFoundException e, WebRequest req) {

		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

}
