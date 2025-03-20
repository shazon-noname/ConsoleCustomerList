import exceptions.InvalidComponentsCountException;
import exceptions.InvalidEmailException;
import exceptions.InvalidPhoneNumberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    private static final Logger queriesLogger = LogManager.getLogger("queries"); // all logging
    private static final Logger errorsLogger = LogManager.getLogger("errors"); // error logging

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        queriesLogger.info("Запит на додавання клієнта: {}", data);
        try {
            String[] components = data.split("\\s+", 4);
            if (components.length != 4) {
                String errorMessage = "Невірна кількість переданих даних. Очікується формат: add name surname email phone";
                errorsLogger.error(errorMessage);
                throw new InvalidComponentsCountException(errorMessage);
            }

            String name = components[0];
            String surname = components[1];
            String email = components[2];
            String phone = components[3];

            if (!isValidEmail(email)) {
                String errorMessage = "Невірний формат email: " + email;
                errorsLogger.error(errorMessage);
                throw new InvalidEmailException(errorMessage);
            }
            if (!isValidNumber(phone)) {
                String errorMessage = "Невірний формат номеру, очікується український формат номеру: +380XXXXXXXXX або 0XXXXXXXXX";
                errorsLogger.error(errorMessage);
                throw new InvalidPhoneNumberException(errorMessage);
            }

            storage.put(email, new Customer(name, surname, email, phone));
            System.out.println("Клієнт добавлений " + storage.get(email));
            queriesLogger.info("Клієнт успішно добавлений {}", storage.get(email));

        } catch (IllegalArgumentException e) {
            errorsLogger.error("Помилка при додавані клієнта {}", e.getMessage());
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            errorsLogger.error("Невідома помилка при додавані клієнта: {}", e.getMessage());
            System.out.println("Сталася невідома помилка: " + e.getMessage());
        }
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String email) {
        storage.remove(email);
    }

    public int getCount() {
        return storage.size();
    }

    private boolean isValidNumber(String phone) {
        String phoneRegex = "^(\\+380|0)\\d{9}$";
        return phone.matches(phoneRegex);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
}