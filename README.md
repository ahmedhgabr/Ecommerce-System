# E-commerce system


## Design 
This project implements a Factory Design Pattern similar to the given [sample](https://fawry-internship.notion.site/Sample-Interview-Questions-and-Answer-21073781f94381149df4dccb6572787d) 
to create different types of `Product` objects (ExpirableProduct and ShipableProduct) based on input parameters. The factory dynamically determines the product 
type by overloading the createProduct method, avoiding the need for an enum and type checks.

```java

ProductFactory productFactory = new ProductFactory();

// Create a regular product that is not shippable or expirable
Product baseProduct = productFactory.createProduct(name, price, quantity);

// Create just a shippable product
Product shippable = productFactory.createProduct(name, price, quantity, weight);

// Create just an expirable product
Product expirable = productFactory.createProduct(name, price, quantity, expireDate);

// Create a shippable product that is expirable
Product shippable&expirable = productFactory.createProduct(name, price, quantity, expireDate, weight);

```

## How to run

1. Clone the repo
``` 
   git clone https://github.com/ahmedhgabr/Ecommerce-System.git
```
2. Edit the values in `Main.java` and Run

   I added an example in the Main class. You can try as many cases as you want.

