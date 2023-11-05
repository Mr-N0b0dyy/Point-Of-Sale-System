package com.company;
import java.util.Scanner;


public class Interface {
    Store store;
    Register reg;
    public Interface(Store store){
        this.store = store;
    }
    private static Scanner scanner = new Scanner(System.in);
    // Method to get a valid command from the user
    public static String getCommand() {
        System.out.print("Enter a command: ");
        String command = scanner.nextLine();

        // Validate the command
        if (!command.equals("PROCESS_SALE") && !command.equals("HANDLE_RETURN") && !command.equals("HELP") && !command.equals("EXIT")) {
            System.out.println("Invalid command. Please try again.");
            return getCommand(); // Recursive call to get a valid command
        }
        return command;
    }
    // Method to get a token from the user
    public static String getToken(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }
    // Method to get a yes or no answer from the user
    public static boolean yesOrNo(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.nextLine().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
    // Method to display available commands
    public void help() {
        System.out.println("Welcome to the program!");
        System.out.println("Available commands:");
        System.out.println("- 'PROCESS_SALE': Process a sale");
        System.out.println("- 'HANDLE_RETURN': Handle a return");
        System.out.println("- 'HELP': Show available commands");
        System.out.println("- 'EXIT': Exit the program");
    }
    // Method to process a sale
    public void processSale(){ // unnecessary
        reg = store.getReg();
        reg.makeNewSale();
        // Get product details and add them to the sale
        do {
            String productId = getToken("Please enter product id");
            int quantity = Integer.parseInt(getToken("Please enter product quantity"));
            reg.enterItem(productId, quantity, "2");
        } while (yesOrNo("Add more products?"));

        reg.endSale();
        Sale sale = reg.getCurrentSale();
        Money total = sale.getTotal();
        System.out.println("Payment that should be taken from the customer is: " + total);
        // Check if the payment has occurred
        if(yesOrNo("Did the payment happen?")){
            String receipt = reg.makePayment(total, "1");
            //Added so in continuous usage of register it stays updated
            reg.updateOrAddSale(sale);
            store.updateOrAddSale(sale);
            System.out.println(receipt);
            System.out.println("Sale process has been completed and saved.");
        }
    }
    // Method to handle a return
    public void handleReturn(){
        boolean isAllowed = false;
        Return ret;
        reg = store.getReg();
        String saleId = getToken("Please enter sale id");
        reg.setCurrent(saleId);
        int counter = 0;
        do {
            if(counter != 0){
                if(!yesOrNo("The return wasn't processable. Try again?")){
                    return;
                }
            }
            reg.makeNewReturn();
            // Get product details and add them to the return
            do {
                String productId = getToken("Please enter product id");
                int quantity = Integer.parseInt(getToken("Please enter product quantity"));
                reg.enterItem(productId, quantity, "1");
            } while (yesOrNo("Add more products?"));

            reg.endReturn();
            System.out.println(reg.checkForReturn());
            ret = reg.getReturn();

            if(ret.isAllowed()){
                isAllowed = true;
            }
            counter++;
        } while(!isAllowed);
        // Check if the payment has occurred
        System.out.println(ret);
        Money total = ret.getTotal();
        if(yesOrNo("Did the customer provide every item returning?")){
            if(yesOrNo("Did the payment happen?")){
                String receipt = reg.makePayment(total, "2");
                Sale sale = reg.getCurrentSale();
                sale.saveReturn(ret);
                //Added so in continuous usage of register it stays updated
                reg.updateOrAddSale(sale);
                store.updateOrAddSale(sale);
                System.out.println(receipt);
                System.out.println("Return process has been completed and saved.");
            }
        }

    }
    // Method to start the interface and process user commands
    public void process() {
        String command;
        help();

        // Continue processing commands until the user chooses to exit
        while (!(command = getCommand()).equals("EXIT")) {
            switch (command) {
                case "PROCESS_SALE" -> processSale(); // unnecessary
                case "HANDLE_RETURN" -> handleReturn();
                case "HELP" -> help();
            }
        }
    }
}


