package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Hacker;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank.Terminal;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Person.PasswordGenerator;

public class PasswordBreaker {

    private Terminal terminal;
    private String username;
    private String password;
    private byte[] bytes;
    private Boolean breakResult=false;
    private int currentLevel=0;
    private int length=0;

    public PasswordBreaker(Terminal terminal,String username){
        this.terminal=terminal;
        this.username=username;
    }

    public String breakPassword(int len){
        this.length=len;
        this.currentLevel=0;
        bytes=new byte[len];
        breakDown();
        return password;
    }

    private void breakDown(){
        if (breakResult){
            return;
        }
        if(currentLevel<length){
            for(int i=33;i<123;i++){
                bytes[currentLevel]=(byte) i;
                if (currentLevel<length){
                    currentLevel++;
                    breakDown();
                }
                if (breakResult){
                    return;
                }
            }
            currentLevel--;
        }else{
            password=new String(bytes);
            if(terminal.varifyPassword(username,password)){
                breakResult=true;
                currentLevel--;
            }else{
                currentLevel--;
            }
        }
    }
}
