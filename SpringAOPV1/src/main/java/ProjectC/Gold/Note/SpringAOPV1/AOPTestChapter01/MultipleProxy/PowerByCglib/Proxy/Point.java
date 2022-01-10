package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByCglib.Proxy;

import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.Common.CommonOutPut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Point {

    /** 代表该切面类对应的层数（多重代理） **/
    private int layer;

    /** 初始化 layer **/
    public Point(int layer){
        this.layer=layer;
    }

    /***
     *
     * 重点方法
     * 用于执行切面链该层的切面方法
     * 往下一切面走的方法（如果已到达切面链尾部就是执行目标方法）：
     *    result=chain.proceed(method,args);
     *
     * @param chain
     * @param method
     * @param args
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object proceed(Chain chain,Method method,Object[] args) throws InvocationTargetException, IllegalAccessException {

        /** 返回值 **/
        Object result = null;

        try {
            /** 目标方法执行前执行 **/
            CommonOutPut.before(layer);
            /** 往切面链下一个切面执行或者执行目标方法 **/
            result=chain.proceed(method,args);
            /** 目标方法执行后执行 **/
            CommonOutPut.afterReturn(layer);
        } catch (Exception e) {
            /** 目标方法抛出异常后执行 **/
            CommonOutPut.afterThrowing(layer);
            e.printStackTrace();
            throw e;
        }finally {
            /** 整个代理方法结束后执行 **/
            CommonOutPut.after(layer);
        }

        return result;
    }
}
