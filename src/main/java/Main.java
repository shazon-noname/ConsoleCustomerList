import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerStorage storage = new CustomerStorage();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine();

            if (command.equals("help")) {
                System.out.println("Список команд:");
                System.out.println("add Имя Телефон Email - добавить клиента");
                System.out.println("list - вывести список клиентов");
                System.out.println("remove Имя - удалить клиента");
                System.out.println("exit - выход");
            } else if (command.startsWith("add")) {
                String data = command.substring(4);
                storage.addCustomer(data);
            } else if (command.equals("list")) {
                storage.listCustomers();
            } else if (command.startsWith("remove")) {
                String name = command.substring(7);
                storage.removeCustomer(name);
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Неизвестная команда");
            }
        }
    }
}