import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger errorsLogger = LogManager.getLogger("errors");
    public static void main(String[] args) {
        CustomerStorage storage = new CustomerStorage();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите команду: ");
            String command = scanner.nextLine();

            if (command.equals("help")) {
                System.out.println("Список команд:");
                System.out.println("add Имя, Фамилия, Email, Телефон,  - добавить клиента");
                System.out.println("list - вывести список клиентов");
                System.out.println("remove Имя - удалить клиента");
                System.out.println("exit - выход");
            } else if (command.startsWith("add")) {
                if (command.length() <= 4) {
                    errorsLogger.error("Ошибка: после 'add' нужно указать данные (Имя, Фамилия, Email, Телефон)");
                    continue;
                }
                String data = command.substring(4);
                if (data.isEmpty()) {
                    errorsLogger.error("Ошибка: данные не могут быть пустыми");
                    continue;
                }
                storage.addCustomer(data);
            } else if (command.equals("list")) {
                storage.listCustomers();
            } else if (command.startsWith("remove")) {
                if(command.length() <= 7) {
                    errorsLogger.error("Ошибка: после 'remove' укажите имя клиента");
                    continue;
                }
                String name = command.substring(7);
                if (name.isEmpty()) {
                    errorsLogger.error("Ошибка: имя не может быть пустым");
                    continue;
                }
                storage.removeCustomer(name);
            } else if (command.equals("exit")) {
                break;
            } else {
                errorsLogger.error("Неизвестная команда");
            }
        }
    }
}