package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByCglib.Proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Chain {

    /***
     * list
     * 所有的AOP拦截切面都保存在该list中
     */
    private List<Point> list;

    /***
     * index
     * 记录目前切面拦截的位置
     */
    private int index = -1;

    /***
     * target
     * 被代理对象
     */
    private Object target;

    public Chain(){
        super();
    }

    /***
     *
     * 构造函数
     * 用于初始化 list 和 target
     *
     * @param list
     * @param target
     */
    public Chain(List<Point> list,Object target){
        this.list=list;
        this.target=target;
    }

    /***
     *
     * 重点方法
     * 每次调用先判断 index 是否已经到达拦截链的尾部
     * 如果没到达就执行对应位置的AOP拦截面方法也就是 list 中保存的 Point 对象的 proceed 方法
     * 如果到达尾部了就执行被代理对象的核心方法，执行完后重制index位置。
     *
     * @param method
     * @param args
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object proceed(Method method,Object[] args) throws InvocationTargetException, IllegalAccessException {

        /** 返回值 **/
        Object result;

        /** 当前切面链位置判断 **/
        if (++index == list.size()){
            result = method.invoke(target,args);
            index = -1;
        }else {
            /** 如果还没到达切面链的尾部就继续执行切面对象的方法 **/
            Point point = list.get(index);
            result = point.proceed(this,method,args);
        }

        /***
         * 由于调用链的关系
         * result的赋值是在到达切面链末端实际调用了 result = method.invoke(target,args); 后才赋值的
         * 然后就是往前面的调用链返回该 result
         */
        return result;
    }
}
