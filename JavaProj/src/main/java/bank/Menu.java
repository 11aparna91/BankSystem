package src.main.java.bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {

    private Scanner scanner;

    public static void main(String args[])
    {
        System.out.println("Welcome to bank");
        Menu menu= new Menu();
        menu.scanner= new Scanner(System.in);
        Customer customer=menu.authenticateUser();
        if (customer != null){
            Account account=Datasource.getAccount(customer.getAccountId());
            menu.showMenu(customer,account);

        }
        menu.scanner.close();
    }
    
    private Customer authenticateUser(){
        System.out.println("please enter the username");
        String username= scanner.next();
        System.out.println("please enter the password");
        String password= scanner.next();
        Customer customer=null;
        try{
        customer=Authenticator.loginCustomer(username,password);}
        catch(LoginException ex){
            System.out.println(ex.getMessage());
        }

        return customer;

    }
    private void showMenu(Customer customer, Account account){
        int selection=0;
        while(selection != 4 && customer.isAuthenticated()){
            System.out.println("=============================");
            System.out.println("Please select any of the option below");
            System.out.println("1.Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3.Check Balance");
            System.out.println("4.Exit");
            System.out.println("=============================");

            selection = scanner.nextInt();
            double amount;
            switch(selection){
                case 1:
                        System.out.println("How much would you like to deposit");
                        amount = scanner.nextDouble();
                    try {
                        account.deposit(amount);
                    } catch (AmountException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                        System.out.println("Please try again");
                    }
                        break;
                case 2:
                        System.out.println("How much would you like to withdraw");
                        amount = scanner.nextDouble();
                    try {
                        account.withdraw(amount);
                    } catch (AmountException e) {
                        // TODO Auto-generated catch block
                        System.out.println(e.getMessage());
                        System.out.println("Please try again");
                    }
                        break;
                case 3:
                        System.out.println("The balance is "+ account.getBalance());
                        break;
                case 4:
                        Authenticator.logoutCustomer(customer);
                        System.out.println("Thank you");
                        break;
                default:
                        System.out.println("invlid option");
                        break;
            }

        }
    }
}
