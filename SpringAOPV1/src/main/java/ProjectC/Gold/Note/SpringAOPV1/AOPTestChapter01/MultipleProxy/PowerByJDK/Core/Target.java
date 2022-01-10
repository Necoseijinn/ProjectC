package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByJDK.Core;

import java.util.Date;

public class Target implements BaseInterface {

    public void show(){
        System.out.println(new Date().toString()+" 核心方法被执行");
    }

}
