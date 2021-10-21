package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.Common;

import net.sf.cglib.proxy.MethodProxy;

import java.util.Date;

public class CommonUtils {

    private String layer;

    public CommonUtils(String layer){
        this.layer=layer;
    }

    public void before(){
        System.out.println(new Date().toString()+" before方法被执行，层级："+layer);
    }

    public void after(){
        System.out.println(new Date().toString()+" after方法被执行，层级："+layer);
    }

    public void afterRunning(){
        System.out.println(new Date().toString()+" afterRunning方法被执行，层级："+layer);
    }

    public void afterThrowing(){
        System.out.println(new Date().toString()+" afterThrowing方法被执行，层级："+layer);
    }

    public Object around(Object obj, Object[] args, MethodProxy proxy){
        Object result=null;
        System.out.println(new Date().toString()+" aroundBefore方法被执行，层级："+layer);
        try {
            result = proxy.invokeSuper(obj, args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
        }
        System.out.println(new Date().toString()+" aroundAfter方法被执行，层级："+layer);
        return result;
    }

}
