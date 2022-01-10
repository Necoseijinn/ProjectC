package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByCglib.Proxy;

import net.sf.cglib.proxy.Enhancer;

import java.util.ArrayList;
import java.util.List;

public class ProxyFactory {

    /** 私有化构造函数 **/
    private ProxyFactory(){
        super();
    }

    /** 提供该类对象的获取方法 **/
    public static ProxyFactory getProxyFactory(){
        return new ProxyFactory();
    }

    /***
     *
     * 获取动态代理对象
     * 此处可以设置得更复杂点
     * 思路是只返回一个动态代理对象
     * 该代理对象包含了多个切面，并构成一个切面链。
     *
     * @param target
     * @param layer
     * @return
     */
    public Object getProxy(Object target,int layer){

        /** Cglib生成动态代理对象核心类 **/
        final Enhancer enhancer = new Enhancer();
        /** 切面链 **/
        List<Point> list = new ArrayList<Point>();

        /***
         * 可拓展位置
         * 可以改成获取各个切面的设置并生成对应切面类
         */
        for (int i =layer;i>=1;i--){
            list.add(new Point(i));
        }

        /** 生成一个切面链对象 **/
        Chain chain = new Chain(list,target);
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MyInterceptor(chain));
        /** 返回生成的动态代理对象 **/
        return enhancer.create();
    }
}
