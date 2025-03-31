

// Abstract class (Abstraction - blue print of the system)

import java.util.Scanner;

abstract class BankAccount {
  private String accountHolder;
  private double balance;
  private String password; //Authentication method

  public BankAccount(String accountHolder, double balance, String password) {
    this.accountHolder = accountHolder;
    this.balance = balance;
    this.password = password;
  }

 public boolean authenticate(String enteredPassword) {
   return this.password.equals(enteredPassword);
 }

 public void checkBalance() {
  System.out.println("Current Balance: " + balance);
 }

  public void deposite(double  amount) {
    if (amount > 0) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }
  }

  abstract void withdraw(double amount);  //Abstract method

}

// Inheritance - Savings and Current accounts
class SavingsAccount extends BankAccount {
      public SavingsAccount(String accountHolder, double balance, String password) {
        super(accountHolder, balance, password);

      }
      @Override
      public void withdraw(double amount) {
        if (amount > 0 && amount <= balance ) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance");
        }
      }
}

//CurrentAccount inherits BankAccount

 class CurrentAccount extends BankAccount {
    private double  overdraftLimit;

    public CurrentAccount(String accountHolder, double balance, double overdraftLimit, String password) {
        super(accountHolder, balance, password);
        this.overdraftLimit = overdraftLimit;
    }
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount ;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded");
        }
    }
 }
 
 // testing the bank program

 public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //creating an account
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Set your account password: ");
        String password = sc.nextLine();


        BankAccount account = new SavingsAccount(name, 5000, password);
        
        while (true) {
          System.out.println("\nWelcome, " + name + "!");
          System.out.println("1. Deposite");
          System.out.println("2. Withdraw");
          System.out.println("3. Check Balance");
          System.out.println("4. Exit");
          System.out.println("Choose an option: ");
          int choice = sc.nextInt();
          sc.nextLine();
       
          if (choice == 4) {
            System.out.println("Thank you for banking with us 😊!!");
            break;
          }

          System.out.println("Enter your password: ");
          String enterdPassword = sc.nextLine();

          if(!account.authenticate(enterdPassword)) {
            System.out.println("Incorrect password! Tranaction canceled. 👻");
            continue;
          }

          if (choice == 3) {
            account.checkBalance();
            continue;
          }
          System.out.print("Enter amount: ");
          double amount = sc.nextDouble();
          sc.nextLine();

          switch(choice) {
            case 1 :
            account.deposite(amount);
            break;

            case 2 :
            account.withdraw(amount);
            break;
        
            default :
            System.out.println("Invalid choice.");
          }
        }
        sc.close();
     }

 }

