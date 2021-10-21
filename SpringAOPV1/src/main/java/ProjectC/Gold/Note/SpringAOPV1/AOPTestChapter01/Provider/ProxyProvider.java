package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.Provider;

import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.Interceptor.MultipleInterceptor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class ProxyProvider {

    private static int i =1;

    public static Object getProxy(boolean before, boolean after, boolean afterRunning, boolean afterThrowing, boolean around,Class clz){

        MethodInterceptor methodInterceptor=new MultipleInterceptor(before,after,afterRunning,afterThrowing,around,"第"+i+"层");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(methodInterceptor);
        i++;
        return enhancer.create();
    }
}
