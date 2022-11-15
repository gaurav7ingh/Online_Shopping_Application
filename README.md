

# REST API for Online Shopping Application 

* This application is used by ABC Company admin and their Customer for online shopping 
* This application will help Customer to buy the products and view order details.
* Customer can register themselves and login. Add product to the cart and purchase it .
* This application will help Admin to search Product list ,add new products ,remove products and update product .

* This project is developed by team of 4 Back-end Developers during project week in Masai School. 

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Lombok
* Swagger

## Modules

*  Login Module
*	Customer Module
*	Product Module
*	Order Module
*	Cart Module


## Features

* User authentication & validation with uuid key.
* Admin Features:
    * Admin can register himself.
    * Admin can add new products.
    * Admin can remove products.
    * Admin can update products.
* Customer Features:
    * A user can register himself or herself on the platform.
    * He/She can check the products item and product Category.
    * If Product is available Customer can add the product to Cart.
    * Customer can order the products from cart.
    * Customer can cancel the order.
    * Customer can apply for return in 7 days.


## Contributors

* [@Gaurav Singh](https://github.com/gaurav7ingh)
* [@Samrat Sinha](https://github.com/Samrat-Sinha)
* [@Kunal Chandel](https://github.com/kunalchandel4)
* [@Himansu Pokhariya](https://github.com/believerHSP)




## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/gaurav7ingh/light-temper-9603/blob/main/pom.xml) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8888

spring.datasource.url=jdbc:mysql://localhost:3306/online_shopping_application
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

```

## Swagger Deployed link
http://localhost:8888/swagger-ui/

## API Root Endpoint

`https://localhost:8888/`

## API Module Endpoints

### Login Module

* `POST /log-in-log-out-controller` : User can login with email and password provided at the time of registation
<!--
### User Module


* `POST /customer/login` : Logging in customer with valid mobile number & password
* `GET /customer/availablecabs` : Getting the list of all the available cabs
* `GET /customers/cabs` : Getting All the cabs
* `GET /customers/checkhistory` : Getting the history of completed tr
* `PUT /customer/update/{mobile}` : Updates customer details based on mobile number
* `PATCH /customer/updatepassword/{mobile}` : Updates customer's password based on the given mobile number
* `POST /customer/booktrip` : Customer can book a cab
* `POST /customer/updatetrip` : Customer can modify or update the trip
* `POST /customer/logout` : Logging out customer based on session token
* `DELETE /customer/delete` : Deletes logged in user 
* `DELETE /customer/complete/{tripid}` : Completed the trip with the given tripid 
* `DELETE /customer/canceltrip` : Cancel the trip with the given tripid   


### Admin Module

* `POST /admin/register` : Register a new admin with proper data validation and admin session
* `POST /admin/login` : Admin can login with mobile number and password provided at the time of registation
* `GET /admin/logout` : Logging out admin based on session token
* `GET /admin/listoftripsbycustomer` : Get list of trips of by a customer id
* `GET /admin/listoftrips` : Get list of trips of all the trips
* `GET /admin/listocustomers` : Get list of all the customers
* `GET /admin/listodrivers` : Get list of all the drivers
* `PUT /admin/update/{username}` : Updates admin detaisl by passed user name
* `DELETE /admin/delete` : Deletes the admin with passed id   -->


### Sample API Response for User Login

`POST   localhost:8888/login`

* Request Body

```
    {
        "email": "samrat@gmail.com",
        "password": "swagger"
    }
```

## Video Explainer of flow control
 <a href="https://drive.google.com/file/d/1CTeKQ8u9vowsjTv0TV98B15g8E4zcFCr/view">**Video Drive Link** </a>
 
 
### E-R Diagram Of Online Shopping Application
---

<img src="https://user-images.githubusercontent.com/101389007/201763374-2ffeef8f-f5fe-4507-858a-d5f60106527e.png">

---

### Swagger UI

---

<img src="https://user-images.githubusercontent.com/101389007/201761270-ec0b67cb-af65-43cc-9889-aa174bbe9306.png">

---

### Login Controller

---

<img src="https://user-images.githubusercontent.com/101389007/201761609-681b6800-58ab-4752-a290-0bc86453deed.png">

---

### Admin Controller

---

<img src="https://user-images.githubusercontent.com/101389007/201761822-e6854f0c-b8b4-45c2-9fdf-b42356331457.png">


---

### Customer Controller

---

<img src="https://user-images.githubusercontent.com/101389007/201761970-801bae9f-abc2-4a89-9b6b-9fad26aafae6.png">

---

### Products Controller

---

<img src="https://user-images.githubusercontent.com/101389007/201762833-4fb18ea0-6022-42f8-b800-4d873fbc95fa.png">

---

### Cart Controller

---

<img src="https://user-images.githubusercontent.com/101389007/201763006-a22ebaea-0884-40b0-8bf5-c3b3342cd6d1.png">

---

### Order Controller

---

<img src="https://user-images.githubusercontent.com/101389007/201763169-cf85b90d-58fc-48cc-abf0-88ae9d3e56a2.png">

---

## Thank You for Visiting
