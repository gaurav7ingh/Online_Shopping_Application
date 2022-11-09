package com.shopping.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.shopping.model.ShoppingError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AddressException.class)
		public ResponseEntity<ShoppingError> myAddressHandler(AddressException ae,WebRequest req){
			
			ShoppingError err = new ShoppingError();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ae.getMessage());
			err.setDescription(req.getDescription(false));
			
			return new ResponseEntity<ShoppingError>(err,HttpStatus.BAD_REQUEST);
		}
	
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<ShoppingError> mycartHandler(CartException carte,WebRequest req){
		
		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(carte.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ShoppingError>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<ShoppingError> myCategoryHandler(CategoryException cate,WebRequest req){
		
		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(cate.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ShoppingError>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ShoppingError> myCustomerHandler(CustomerException ctre,WebRequest req){
		
		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ctre.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ShoppingError>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ShoppingError> myOrderHandler(OrderException oe,WebRequest req){
		
		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(oe.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ShoppingError>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ShoppingError> myProductHandler(ProductException pe,WebRequest req){
		
		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(pe.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ShoppingError>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ShoppingError> myUserHandler(UserException ue,WebRequest req){
		
		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ShoppingError>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ShoppingError> myExceptionHandler(Exception e,WebRequest req){
		
		ShoppingError err = new ShoppingError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ShoppingError>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
	}
	

