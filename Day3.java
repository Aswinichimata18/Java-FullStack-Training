
import java.util.*;
import java.util.stream.Collectors;

class Order {

    int orderId;
    String customerName;
    String product;
    String category;
    double amount;
    String status;
    String city;

    Order(int orderId, String customerName,
          String product, String category,
          double amount, String status, String city) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.product = product;
        this.category = category;
        this.amount = amount;
        this.status = status;
        this.city = city;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId +
               ", Customer: " + customerName +
               ", Product: " + product +
               ", Category: " + category +
               ", Amount: " + amount +
               ", Status: " + status +
               ", City: " + city;
    }
}

public class Day3 {

    public static void main(String[] args) {

        // Creating Order Dataset

        List<Order> orders = Arrays.asList(

            new Order(101, "Aswini", "Laptop",
                    "Electronics", 60000, "Delivered", "Hyderabad"),

            new Order(102, "Rahul", "Mobile",
                    "Electronics", 25000, "Pending", "Chennai"),

            new Order(103, "Priya", "Shoes",
                    "Fashion", 3000, "Delivered", "Bangalore"),

            new Order(104, "Kiran", "Watch",
                    "Accessories", 5000, "Cancelled", "Hyderabad"),

            new Order(105, "Sneha", "Headphones",
                    "Electronics", 2000, "Delivered", "Chennai"),

            new Order(106, "Arjun", "Dress",
                    "Fashion", 4000, "Pending", "Bangalore"),

            new Order(107, "Ravi", "Bag",
                    "Fashion", 1500, "Delivered", "Hyderabad"),

            new Order(108, "Divya", "Tablet",
                    "Electronics", 30000, "Delivered", "Chennai")
        );


        // 1. FILTER ORDERS

        System.out.println("\n1. FILTERED ORDERS:");

        orders.stream()
              .filter(o -> o.getAmount() > 3000
                        && o.getStatus().equals("Delivered"))
              .forEach(System.out::println);


        // 2. SORT ORDERS BY AMOUNT

        System.out.println("\n2. SORTED ORDERS:");

        orders.stream()
              .sorted(Comparator.comparingDouble(Order::getAmount))
              .forEach(System.out::println);


        // 3. HIGHEST ORDER AMOUNT

        System.out.println("\n3. HIGHEST ORDER:");

        orders.stream()
              .max(Comparator.comparingDouble(Order::getAmount))
              .ifPresent(System.out::println);


        // 4. LOWEST ORDER AMOUNT

        System.out.println("\n4. LOWEST ORDER:");

        orders.stream()
              .min(Comparator.comparingDouble(Order::getAmount))
              .ifPresent(System.out::println);


        // 5. CALCULATE TOTAL SALES

        double totalSales = orders.stream()
                                   .mapToDouble(Order::getAmount)
                                   .sum();

        System.out.println("\n5. TOTAL SALES: " + totalSales);


        // 6. COUNT TOTAL ORDERS

        long totalOrders = orders.stream()
                                 .count();

        System.out.println("\n6. TOTAL ORDERS: " + totalOrders);


        // 7. GROUP ORDERS BY CATEGORY AND CITY

        System.out.println("\n7. GROUP BY CATEGORY:");

        Map<String, List<Order>> categoryGroups =
                orders.stream()
                      .collect(Collectors.groupingBy(Order::getCategory));

        categoryGroups.forEach((category, list) -> {
            System.out.println(category);
            list.forEach(System.out::println);
        });


        System.out.println("\nGROUP BY CITY:");

        Map<String, List<Order>> cityGroups =
                orders.stream()
                      .collect(Collectors.groupingBy(Order::getCity));

        cityGroups.forEach((city, list) -> {
            System.out.println(city);
            list.forEach(System.out::println);
        });


        // 8. CALCULATE AVERAGE ORDER AMOUNT

        double averageAmount = orders.stream()
                                     .mapToDouble(Order::getAmount)
                                     .average()
                                     .orElse(0.0);

        System.out.println("\n8. AVERAGE ORDER AMOUNT: "
                           + averageAmount);

    }
}

//EXPECTED OUTPUT//

/*1. FILTERED ORDERS:
Order ID: 101, Customer: Aswini, Product: Laptop...
Order ID: 103, Customer: Priya, Product: Shoes...
Order ID: 108, Customer: Divya, Product: Tablet...

2. SORTED ORDERS:
1500.0
2000.0
3000.0
4000.0
5000.0
25000.0
30000.0
60000.0

3. HIGHEST ORDER:
Laptop - 60000.0

4. LOWEST ORDER:
Bag - 1500.0

5. TOTAL SALES: 130500.0

6. TOTAL ORDERS: 8

7. GROUP BY CATEGORY:
Electronics
Fashion
Accessories

GROUP BY CITY:
Hyderabad
Chennai
Bangalore

8. AVERAGE ORDER AMOUNT: 16312.
  */
