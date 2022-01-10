package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByJDK.Proxy;

import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.Common.CommonOutPut;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerDemo<T> implements InvocationHandler {

    private T t;
    private int layer;

    public InvocationHandlerDemo(){
        super();
    }

    public InvocationHandlerDemo(T t,int layer){
        this.t=t;
        this.layer=layer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;

        try {
            CommonOutPut.before(layer);
            result=method.invoke(t,args);
            CommonOutPut.afterReturn(layer);
        } catch (Exception e) {
            CommonOutPut.afterThrowing(layer);
            e.printStackTrace();
        }finally {
            CommonOutPut.after(layer);
        }

        return result;
    }
}
