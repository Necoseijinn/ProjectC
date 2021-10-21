package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.Interceptor;

import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.Common.CommonUtils;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MultipleInterceptor implements MethodInterceptor {

    private boolean before;
    private boolean after;
    private boolean afterRunning;
    private boolean afterThrowing;
    private boolean around;
    private CommonUtils commonUtils;

    public MultipleInterceptor(boolean before, boolean after, boolean afterRunning, boolean afterThrowing, boolean around,String name) {
        this.before = before;
        this.after = after;
        this.afterRunning = afterRunning;
        this.afterThrowing = afterThrowing;
        this.around = around;
        this.commonUtils = new CommonUtils(name);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result = null;
        try {

            if(before){
                commonUtils.before();
            }

            if(around){
                result=commonUtils.around(obj,args,proxy);
            }else{
                result = proxy.invokeSuper(obj,args);
            }

            if (after){
                commonUtils.after();
            }

        } catch (Throwable throwable) {
            commonUtils.afterThrowing();
        } finally {

            if (afterRunning){
                commonUtils.afterRunning();
            }

        }
        return result;
    }
}
