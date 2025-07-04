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



## Process Documentation (How did I end up here?)

### Flags
the first idea is to use flags to indicate features of the product, like:
```java
public class Product {
    private boolean isExpiable;
    private boolean isShippable;
    
    // Getters and other methods
}
```
This approach was refused because:
1. It is not extensible, adding a new feature would require changing the class.
2. The class would become a "God Object", which is an anti-pattern in software design.
3. It would lead to a lot of boolean flags and tall constructors.
4. This is an OOP challenge, so it's expected to be solved with OOP principles.

### Creational Patterns
The next idea was to use creational patterns, like the Factory Method or Abstract Factory.
The idea was to create a base class for products and then extend it for each specific product 
type. This would allow for separation of concerns and better organization of code. While I ended up 
using a variant of Factory Method, I realize that it is not the best solution for this problem.
The main issue is that it does not allow for easy extension of the product types.
For each time I need to add a feature, I would need to create a bigger number of classes, which is not
applicable.

The current implementation has `BaseProduct`, `ShippableProduct`, `ExpiableProduct`, and 
`ShippableExpirable` classes. If I want to add a new feature, I would need to create a new class for each.
For example, if I want to add a new feature `TaxFree`, I would need to create:
`ShippableTaxFreeProduct`, `ExpiableTaxFreeProduct`, `ShippableExpirableTaxFreeProduct`, and so on.
This leads to a combinatorial explosion of classes, which is not maintainable in the long run.

### Decorator Pattern
The idea was to use the Decorator Pattern, which allows for dynamic addition of features to a `Product`.
This pattern is a good fit for this problem, as it allows for easy extension of the product types without
the need to create a new class for each combination of features. 
I just need to create a new decorator class for each feature, which is much more manageable and cleaner.
This approach sounded promising, but after implementation, 
I realized that it has a clear problem: the object's type ends up as the outermost wrapper's type.
This means that wrapping `BaseProduct` with `Shippable` then `Expirable` will result
in a `Expirable` type instead of `Shippable` & `Expirable` type.
The go-around is to get direct access to the wrapped object, but this is not a clean solution.
You can see the implementation on the [wrong-solution/decorator](https://github.com/ahmedhgabr/Ecommerce-System/tree/wrong-solution/decorator) branch.

### Further way to go
This challenge was too exciting for me, challenging me in one of my favorite topics, and showing that I have a lot to learn. Thanks for this opportunity.

