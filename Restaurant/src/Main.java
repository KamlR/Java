import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Main {
    static Cooker cooker;
    static ArrayList<Product> products;
    static ArrayList<VisitorOrder> visitorOrders;
    static ArrayList<Operation> operations;
    static ArrayList<MenuDish> menuDishes;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        readJsonFiles();
        System.out.println("Welcome to the Restaurant");
        System.out.println("Today there are " + visitorOrders.size() + " visitors");
        System.out.println("Visitors are served on a first-come, first-served basis");
        System.out.println();
        sleep(2000);
        int count;
        for (VisitorOrder visitor : visitorOrders) {
            count = 0;
            System.out.println(visitor.getVis_name() + " has ordered:");
            for (Integer dish : visitor.getVis_ord_dishes()) {
                if (count == visitor.getVis_ord_dishes().size() - 1) {
                    System.out.print(getVisitorDish(dish));
                } else {
                    System.out.print(getVisitorDish(dish) + ", ");
                }
                count++;
            }
            System.out.println();
            System.out.println("Please, wait, we are checking if your dishes are available");
            sleep(1000);
            int flag = 0;
            for (int i = 0; i < visitor.getVis_ord_dishes().size(); i++) {
                int dish_id = visitor.getVis_ord_dishes().get(i);
                String dish_name = ifDishInMenu(dish_id);
                if (!dish_name.equals("")) {
                    System.out.println(dish_name + " isn't available");
                    visitor.getVis_ord_dishes().remove(i);
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("All dishes are available");
            }
            if (visitor.getVis_ord_dishes().size() == 0) {
                System.out.println("We can't make an order for you:(");
                System.out.println();
                continue;
            }
            getOrderPrice(visitor.getVis_ord_dishes());
            countTimeForPreparingOrder(visitor.getVis_ord_dishes());
            System.out.println("Your dish is preparing by cooker " + cooker.getCook_name());
            sleep(2000);
            System.out.println();
        }

    }

    // Search name of dish by its id
    public static String getVisitorDish(int dish) {
        for (MenuDish menuDish : menuDishes) {
            if (menuDish.getMenu_dish_id() == dish) {
                return menuDish.getMenu_dish_name();
            }
        }
        return "There is no such dish in menu";
    }

    // Check if the dish is available.
    // Firstly, check if menu contains such dish.
    // Secondly, check if there are enough ingredients for a dish.
    // For second position we use another method ifAllIngredientsAreHere(int dish_id).
    public static String ifDishInMenu(int dish_id) {
        for (MenuDish menuDish : menuDishes) {
            if (menuDish.getMenu_dish_id() == dish_id) {
                if (!menuDish.getMenu_dish_active() || !ifAllIngredientsAreHere(dish_id)) {
                    return menuDish.getMenu_dish_name();
                }
            }
        }
        return "";
    }

    // Time for preparing an order.
    public static void countTimeForPreparingOrder(ArrayList<Integer> visitor_dishes) {
        double countTime = 0;
        for (Integer dish_id : visitor_dishes) {
            for (MenuDish menuDish : menuDishes) {
                if (menuDish.getMenu_dish_id() == dish_id) {
                    for (Integer operation_id : menuDish.getOperations()) {
                        for (Operation operation : operations) {
                            if (operation.getOper_type_id() == operation_id) {
                                countTime += operation.getOper_type_time();
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Your order will be ready in " + countTime);
    }

    // Check if there are enough ingredients for a dish.
    public static boolean ifAllIngredientsAreHere(int dish_id) {
        int count = 0;
        for (MenuDish menuDish : menuDishes) {
            if (menuDish.getMenu_dish_id() == dish_id) {
                for (Integer product_id : menuDish.getProducts()) {
                    int quantity = searchProductQuantity(product_id);
                    if (quantity - menuDish.getCountProducts().get(count) < 0) {
                        return false;
                    } else {
                        reduceProduct(product_id, menuDish.getCountProducts().get(count));
                    }
                    count++;
                }
            }
        }
        return true;
    }

    // Searching the quantity of product with product_id in warehouse.
    public static int searchProductQuantity(Integer product_id) {

        for (Product product : products) {
            if (product.getProd_type_id() == product_id) {
                return product.getProd_item_quantity();
            }
        }
        return 0;
    }

    // Reduce number of product in warehouse after preparing a dish for a client.
    public static void reduceProduct(int product_id, int we_need) {
        for (Product product : products) {
            if (product.getProd_type_id() == product_id) {
                product.changeProd_item_quantity(we_need);
            }
        }
    }

    // Get a price of order.
    public static void getOrderPrice(ArrayList<Integer> visitor_order) {
        int price = 0;
        for (Integer dish_id : visitor_order) {
            for (MenuDish menuDish : menuDishes) {
                if (menuDish.getMenu_dish_id() == dish_id) {
                    price += menuDish.getMenu_dish_price();
                }
            }
        }
        System.out.println("Price of your order: " + price);
    }

    // Upload data form JSON files.
    public static void readJsonFiles() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        cooker = objectMapper.readValue(new File("JSON\\cookers.txt"), Cooker.class);
        products = objectMapper.readValue(new File("JSON\\products.txt"), new TypeReference<ArrayList<Product>>() {
        });
        visitorOrders = objectMapper.readValue(new File("JSON\\visitors_orders.txt"), new TypeReference<ArrayList<VisitorOrder>>() {
        });
        operations = objectMapper.readValue(new File("JSON\\operations.txt"), new TypeReference<ArrayList<Operation>>() {
        });
        menuDishes = objectMapper.readValue(new File("JSON\\menu.txt"), new TypeReference<ArrayList<MenuDish>>() {
        });
    }
}