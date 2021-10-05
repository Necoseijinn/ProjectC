package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Person;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank.RichGuyBankAccount;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank.Terminal;

public class RichGuy {

    private Terminal terminal;
    private double balance;
    private String name;
    private String bankPassword;
    private RichGuyBankAccount myBankAccount;

    public RichGuy(String name) {
        this.name = name;
        this.bankPassword = PasswordGenerator.generatePassword();
    }


    public String getName() {
        return name;
    }

    public void openAccount(Terminal terminal) {
        this.terminal = terminal;
        terminal.openAccount(name, bankPassword);
    }

    public boolean saveMoney(double money) {
        if (!terminal.varifyPassword(name, bankPassword)) {
            return false;
        }
        terminal.addBalance(money);
        terminal.logOut();
        return true;
    }

    public String getBankPassword() {
        return bankPassword;
    }
}
