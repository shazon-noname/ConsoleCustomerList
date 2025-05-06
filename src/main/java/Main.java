import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger errorsLogger = LogManager.getLogger("errors");
    public static void main(String[] args) {
        CustomerStorage storage = new CustomerStorage();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введіть команду: ");
            String command = scanner.nextLine();

            if (command.equals("help")) {
                System.out.println("Список команд:");
                System.out.println("add Ім’я, Прізвище, Email, Телефон - додати клієнта");
                System.out.println("list - вивести список клієнтів");
                System.out.println("remove Ім’я - видалити клієнта");
                System.out.println("exit - вихід");
            } else if (command.startsWith("add")) {
                if (command.length() <= 4) {
                    errorsLogger.error("Помилка: після 'add' потрібно вказати дані (Ім’я, Прізвище, Email, Телефон)");
                    continue;
                }
                String data = command.substring(4);
                if (data.isEmpty()) {
                    errorsLogger.error("Помилка: дані не можуть бути порожніми");
                    continue;
                }
                storage.addCustomer(data);
            } else if (command.equals("list")) {
                storage.listCustomers();
            } else if (command.startsWith("remove")) {
                if(command.length() <= 7) {
                    errorsLogger.error("Помилка: після 'remove' вкажіть ім’я клієнта");
                    continue;
                }
                String name = command.substring(7);
                if (name.isEmpty()) {
                    errorsLogger.error("Помилка: ім’я не може бути порожнім");
                    continue;
                }
                storage.removeCustomer(name);
            } else if (command.equals("exit")) {
                break;
            } else {
                errorsLogger.error("Невідома команда");
            }
        }
    }
}
