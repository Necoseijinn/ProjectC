package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByCglib.Core;

import java.util.Date;

public class Target {

    /** 被代理对象的核心方法 **/
    public void show(){
        System.out.println(new Date().toString()+" 核心方法被执行");
    }
}
