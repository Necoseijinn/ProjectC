package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Hacker;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank.Terminal;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Person.PasswordGenerator;

public class HackerJerry {
    private PasswordBreaker passwordBreaker;
    private Terminal terminal;
    private String targetPassword;

    public String getTargetPassword() {
        return targetPassword;
    }

    public HackerJerry(Terminal terminal){
        this.terminal=terminal;
    }
    public void setTarget(String name){
        passwordBreaker=new PasswordBreaker(terminal,name);
    }

    public Boolean start(int len){
        targetPassword=passwordBreaker.breakPassword(len);
        if(targetPassword!=null){
            return true;
        }
        return false;
    }
}
