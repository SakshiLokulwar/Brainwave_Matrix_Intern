package JavaProject;

import java.util.Scanner;

class BankAccount {
    private String name;
    private String userName;
    private String password;
    private String accountNo;
    private float balance = 10000f;
    private int transactions = 0;
    private String transactionHistory = "";

    // Getters
    public String getName() {
        return name;
    }

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your Name: ");
        this.name = sc.nextLine();
        System.out.println("\nEnter your Username: ");
        this.userName = sc.nextLine();
        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration Successful. Please Log in to your Bank Account");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.println("\nEnter your username: ");
            String inputUsername = sc.nextLine();
            if (inputUsername.equals(userName)) {
                while (!isLogin) {
                    System.out.println("\nEnter your password: ");
                    String inputPassword = sc.nextLine();
                    if (inputPassword.equals(password)) {
                        System.out.println("\nLogin Successful");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\nUsername not found");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("\nWithdrawal Successful.");
                transactionHistory = transactionHistory.concat(amount + " Rs Withdrawn\n");
            } else {
                System.out.println("\nInsufficient Balance.");
            }
        } catch (Exception e) {
            System.out.println("\nAn error occurred. Please try again.");
        }
    }

    public void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Amount to Deposit: ");
        float amount = sc.nextFloat();
        try {
            if (amount <= 10000f) {
                transactions++;
                balance += amount;
                System.out.println("\nDeposit Successful.");
                transactionHistory = transactionHistory.concat(amount + " Rs Deposited\n");
            } else {
                System.out.println("\nSorry. The limit is 10000.");
            }
        } catch (Exception e) {
            System.out.println("\nAn error occurred. Please try again.");
        }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.println("\nEnter Amount to Transfer: ");
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                if (amount <= 50000f) {
                    transactions++;
                    balance -= amount;
                    System.out.println("\nSuccessfully Transferred to " + recipient);
                    transactionHistory = transactionHistory.concat(amount + " Rs Transferred to " + recipient + "\n");
                } else {
                    System.out.println("\nSorry. The limit is 50000.");
                }
            } else {
                System.out.println("\nInsufficient Balance.");
            }
        } catch (Exception e) {
            System.out.println("\nAn error occurred. Please try again.");
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("\nNo Transactions Occurred");
        } else {
            System.out.println("\nTransaction History: \n" + transactionHistory);
        }
    }
}

public class ATMInterface {
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if (input < 1 || input > limit) {
                    System.out.println("Choose a number between 1 and " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter a valid integer.");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n******************** WELCOME TO THE ATM INTERFACE ********************");
        System.out.println("\n1. Register \n2. Exit");
        System.out.println("Choose one option: ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.register();
            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.println("Enter your choice: ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (bankAccount.login()) {
                        System.out.println("\n******************** WELCOME BACK, " + bankAccount.getName() + " ********************");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            System.out.println("Enter your choice: ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    bankAccount.withdraw();
                                    break;
                                case 2:
                                    bankAccount.deposit();
                                    break;
                                case 3:
                                    bankAccount.transfer();
                                    break;
                                case 4:
                                    bankAccount.checkBalance();
                                    break;
                                case 5:
                                    bankAccount.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                                default:
                                    System.out.println("Invalid Choice");
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}
