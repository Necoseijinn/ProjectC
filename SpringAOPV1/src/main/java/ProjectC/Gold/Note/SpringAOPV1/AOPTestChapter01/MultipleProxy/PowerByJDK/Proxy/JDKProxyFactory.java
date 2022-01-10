package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByJDK.Proxy;

import java.lang.reflect.Proxy;
public class JDKProxyFactory{

    private int layer=0;

    public JDKProxyFactory(){
        super();

    }

    public <T> Object getProxy(T t){
        Object proxy = Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(),new InvocationHandlerDemo(t,layer));
        layer++;
        return proxy;
    }

}
