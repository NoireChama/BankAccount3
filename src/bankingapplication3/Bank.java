package bankingapplication3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {
    private String name;

    public Bank(String name) {
        this.name = name;
    }
    
    public void listAccount(){
        Connection con = BankConnection.connect();
        try {
            Statement statement = con.createStatement();
            String sql = "select * from account";
            ResultSet results = statement.executeQuery(sql);
            
            while(results.next()) {
                System.out.println(results.getString(1)+" "+results.getString(2)+" "+results.getString(3)+" "+results.getString(3));
            }
            System.out.println("");
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openAccount(Account account){
        Connection con = BankConnection.connect();
        String sql = "insert into account (accID, accName, accBalance) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeAccount(int number){
        Connection con = BankConnection.connect();
        String sql = "delete from account where accID = ?" ;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
             preparedStatement.setInt(1, number);
             preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void depositMoney(Account account, double amount){
        account.deposit(amount);
        
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?" ;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withdrawMoney(Account account, double amount){
        account.withdraw(amount);
        
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?" ;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Account getAccount(int number){
        Connection con = BankConnection.connect();
        String sql = "select * from account where accID ='"+number+"";
        Account account = null;
        Statement statement;
        try {
            String accountName = "";          
            double balance = 0;
            statement = con.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while(results.next()){
               accountName = results.getString(2);
               balance = results.getDouble(3);
            }
            account = new Account(number, accountName, balance);
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return account;
    }
}
