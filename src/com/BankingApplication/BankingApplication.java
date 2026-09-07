package com.BankingApplication;

	import java.util.ArrayList;
	import java.util.Scanner;

	class BankAccount {
	    private String accountHolder;
	    private int accountNumber;
	    protected double balance;
   public BankAccount(String accountHolder, int accountNumber, double balance) {
	        this.accountHolder = accountHolder;
	        this.accountNumber = accountNumber;
	        this.balance = balance;
	    }
	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            System.out.println("Deposited: ₹" + amount);
	        } else {
	            System.out.println("Invalid deposit amount.");
	        }
	    }
	    public void withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            System.out.println("Withdrawn: ₹" + amount);
	        } else {
	            System.out.println("Insufficient balance or invalid amount.");
	        }
	    }

	    public void displayDetails() {
	        System.out.println("\nAccount Holder: " + accountHolder);
	        System.out.println("Account Number: " + accountNumber);
	        System.out.println("Balance: ₹" + balance);
	    }

	    public int getAccountNumber() {
	        return accountNumber;
	    }
	}
	class SavingsAccount extends BankAccount {
	    private double interestRate;

	    public SavingsAccount(String accountHolder, int accountNumber,
	                          double balance, double interestRate) {
	        super(accountHolder, accountNumber, balance);
	        this.interestRate = interestRate;
	    }
	    @Override
	    public void displayDetails() {
	        super.displayDetails();
	        System.out.println("Interest Rate: " + interestRate + "%");
	    }

	    public void addInterest() {
	        double interest = balance * interestRate / 100;
	        balance += interest;
	        System.out.println("Interest Added: ₹" + interest);
	    }
	}

	public class BankingApplication {
	    static ArrayList<BankAccount> accounts = new ArrayList<>();
	    static Scanner sc = new Scanner(System.in);

	    public static void main(String[] args) {

	        while (true) {
	            System.out.println("\n===== BANK MENU =====");
	            System.out.println("1. Create Savings Account");
	            System.out.println("2. Deposit");
	            System.out.println("3. Withdraw");
	            System.out.println("4. Display Account Details");
	            System.out.println("5. Add Interest");
	            System.out.println("6. Exit");
	            System.out.print("Enter choice: ");

	            int choice = sc.nextInt();

	            switch (choice) {

	                case 1:
	                    createAccount();
	                    break;

	                case 2:
	                    depositMoney();
	                    break;

	                case 3:
	                    withdrawMoney();
	                    break;

	                case 4:
	                    displayAccount();
	                    break;

	                case 5:
	                    addInterest();
	                    break;

	                case 6:
	                    System.out.println("Thank you for using Banking App.");
	                    System.exit(0);

	                default:
	                    System.out.println("Invalid choice.");
	            }
	        }
	    }
	    public static void createAccount() {
	        System.out.print("Enter Account Holder Name: ");
	        sc.nextLine();
	        String name = sc.nextLine();

	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        System.out.print("Enter Initial Balance: ");
	        double balance = sc.nextDouble();

	        System.out.print("Enter Interest Rate: ");
	        double rate = sc.nextDouble();

	        SavingsAccount acc =
	                new SavingsAccount(name, accNo, balance, rate);

	        accounts.add(acc);

	        System.out.println("Savings Account Created Successfully!");
	    }
	    public static BankAccount findAccount(int accNo) {
	        for (BankAccount acc : accounts) {
	            if (acc.getAccountNumber() == accNo) {
	                return acc;
	            }
	        }
	        return null;
	    }
	    public static void depositMoney() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();
	        BankAccount acc = findAccount(accNo);
	        if (acc != null) {
	            System.out.print("Enter Amount to Deposit: ");
	            double amount = sc.nextDouble();
	            acc.deposit(amount);
	        } else {
	            System.out.println("Account not found.");
	        }
	    }
	    public static void withdrawMoney() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();
	        BankAccount acc = findAccount(accNo);
	        if (acc != null) {
	            System.out.print("Enter Amount to Withdraw: ");
	            double amount = sc.nextDouble();
	            acc.withdraw(amount);
	        } else {
	            System.out.println("Account not found.");
	        }
	    }
	    public static void displayAccount() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        BankAccount acc = findAccount(accNo);

	        if (acc != null) {
	            acc.displayDetails();
	        } else {
	            System.out.println("Account not found.");
	        }
	    }
	    public static void addInterest() {
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        BankAccount acc = findAccount(accNo);

	        if (acc instanceof SavingsAccount) {
	            SavingsAccount sa = (SavingsAccount) acc;
	            sa.addInterest();
	        } else {
	            System.out.println("Savings account not found.");
	        }
	    }
	}

	