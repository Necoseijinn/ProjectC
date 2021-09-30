package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.breakPassword.Bank;

public class Terminal {
    private RichGuyBankAccount rba;
    private SecuritySystem ss;
    private Boolean varifyResult;
    public Terminal(SecuritySystem ss){
        this.ss = ss;
    }
    public void openAccount(String name,String password){
        rba =new RichGuyBankAccount(name,password);
        ss.addBankAccout(rba);
    }
    public double getMoney(double money){
        if(varifyResult){
            return rba.getMoney(money);
        }else {
            return -1;
        }
    }
    public boolean addBalance(double money){
        if (varifyResult){
            rba.addBalance(money);
            return true;
        }
        return false;
    }
    public boolean varifyPassword(String name,String password){
        rba=ss.varifyPassword(name,password);
        if(rba!=null){
            this.varifyResult=true;
        }
        return varifyResult;
    }

    public void logOut() {
        this.rba=null;
        this.varifyResult=false;
    }
}
