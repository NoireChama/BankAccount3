package bankingapplication3;

import java.util.Scanner;

public class BankingApplication3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Bank bank = new Bank("Hum");
        int option = 0, number;
        String name;
        double balance, amount;
        Account account;
        while(option != 6){
            System.out.println("Main Menu");
            System.out.println("1. Display all Accounts");
            System.out.println("2. Open New Account​");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit​ Money");
            System.out.println("5. Withdraw Money");
            System.out.println("");
            
            System.out.print("Enter your choice: ");
            option = scan.nextInt();
            scan.nextLine();
            System.out.println("");
            
            switch(option){
                case 1:
                    bank.listAccount();
                    break;
                case 2:
                    number = 111111;
                    System.out.print("Enter Account Name: ");
                    name = scan.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    balance = scan.nextDouble();
                    account = new Account(number, name, balance);
                    bank.openAccount(account);
                    break;
                case 3:
                    System.out.print("Enter Your Account Number: ");
                    number = scan.nextInt();
                    bank.closeAccount(number);
                    break;
                case 4:
                    System.out.print("Enter Your Account Number: ");
                    number = scan.nextInt();
                    account = bank.getAccount(number);
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    bank.depositMoney(account, amount);
                    break;
                case 5:
                    System.out.print("Enter Your Account Number: ");
                    number = scan.nextInt();
                    account = bank.getAccount(number);
                    System.out.print("Enter Amount: ");
                    amount = scan.nextDouble();
                    bank.withdrawMoney(account, amount);
                    break;
            }
        }
    }
    
}
