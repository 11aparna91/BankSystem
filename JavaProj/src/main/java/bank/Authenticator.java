package src.main.java.bank;

import javax.security.auth.login.LoginException;

public class Authenticator {
    
    public static Customer loginCustomer(String username, String password) throws LoginException{
        Customer customer= Datasource.getCustomer(username);
        if (customer==null){
            throw new LoginException("Username not found");
        }
        if (password.equals(customer.getPassword())){
            customer.setAuthenticated(true);
            return customer;
        }
        else{
            throw new LoginException("incorrect password");
        }

    }

    public static void logoutCustomer(Customer customer){
        customer.setAuthenticated(false);
    }
}
