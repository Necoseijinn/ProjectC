package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Watcher;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank.SecuritySystem;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank.Terminal;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Hacker.HackerJerry;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Person.PasswordGenerator;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Person.RichGuy;

import java.util.Date;

public class TimeLine {
    public static void main(String[] args) {
        SecuritySystem ss = new SecuritySystem();
        RichGuy kuke = new RichGuy("kuke");
        Terminal terminal = new Terminal(ss);
        kuke.openAccount(terminal);
        kuke.saveMoney(1000000000);
        HackerJerry jerry = new HackerJerry(terminal);
        long l1=System.currentTimeMillis();
        jerry.setTarget("kuke");
        jerry.start(5);
        long l2=System.currentTimeMillis();
        System.out.println("kuke的密码 "+kuke.getBankPassword());
        System.out.println("破解的密码 "+jerry.getTargetPassword());
        System.out.println("用时: "+(l2-l1)/1000+"秒");

    }
}
