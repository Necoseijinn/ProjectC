package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank;

public class RichGuyBankAccount {
    private String accountName;
    private String accountPassword;
    private double balance;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public RichGuyBankAccount(String userName, String userPassword){
        this.accountName=userName;
        this.accountPassword=userPassword;
    }

    public double addBalance(double money){
        this.balance += money;
        return this.balance;
    }

    public double getMoney(double money){
        this.balance-=money;
        return money;
    }

    private boolean varifyPassword(String password) {
        return accountPassword.equals(password);
    }

}
