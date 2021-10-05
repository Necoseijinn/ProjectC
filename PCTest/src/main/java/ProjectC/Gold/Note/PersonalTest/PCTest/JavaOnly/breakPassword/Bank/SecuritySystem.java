package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Person.RichGuy;

import java.util.HashMap;
import java.util.Map;

public class SecuritySystem {
    private Map<String, RichGuyBankAccount> accounts = new HashMap<String, RichGuyBankAccount>();

    public RichGuyBankAccount varifyPassword(String name, String password) {
        RichGuyBankAccount rba;
        if (accounts.containsKey(name)) {
            rba = accounts.get(name);
        } else {
            return null;
        }
        if (rba.getAccountPassword().equals(password)) {
            return rba;
        } else {
            return null;
        }
    }

    public void addBankAccout(RichGuyBankAccount richGuyBankAccount) {
        accounts.put(richGuyBankAccount.getAccountName(), richGuyBankAccount);
    }
}
