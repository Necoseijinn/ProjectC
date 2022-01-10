package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.ProxyDemo.CglibProxy.ForTest;

/***
 * 此类不包含任何方法
 * 只用于说明Cglib原理
 */
public class CglibExplain {

/***
 *
 * Cglib动态生成的代理对象类有三个，这三个只有一个是动态代理对象，另外两个是方法索引，要结合MethodProxy类来看。
 * 下面为三个类的清单
 *
 *    被代理对象的FastClass类
 *    代理对象的FastClass类
 *    代理对象
 *
 * FastClass是Cglib的MethodProxy类里的方法生成的一种类，用于索引方法。
 * 可以理解为FastClass就是一个地图。
 * 被代理对象有自己的FastClass地图
 * 代理对象也有自己的FastClass地图
 *
 *
 * MethodProxy是Cglib一个很重要的类，在动态生成的代理对象类中，Cglib为每一个被代理对象的方法都生成一个MethodProxy对象。
 * 可以参考 webapp/ProxyClass/CglibProxy/ProductService$$EnhancerByCGLIB$$f51541e9.class
 * 下面为截取代码片断
 * 类：代理对象
 *
 *       //静态代码块，调用了 CGLIB$STATICHOOK1();
 *       static {
 *         CGLIB$STATICHOOK1();
 *       }
 *       // 被静态代码块调用，相当于初始化方法。
 *　　　　static void CGLIB$STATICHOOK1() {
 *
 *         CGLIB$THREAD_CALLBACKS = new ThreadLocal();
 *         CGLIB$emptyArgs = new Object[0];
 *
 *         //var0是代理对象
 *         Class var0 = Class.forName("ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.CglibProxy.Service.ProductService$$EnhancerByCGLIB$$ea3958f9");
 *         //var1是被代理对象
 *         Class var1;
 *
 *         Method[] var10000 = ReflectUtils.findMethods(new String[]{"equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"}, (var1 = Class.forName("java.lang.Object")).getDeclaredMethods());
 *         CGLIB$equals$1$Method = var10000[0];
 *         CGLIB$equals$1$Proxy = MethodProxy.create(var1, var0, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$1");
 *         CGLIB$toString$2$Method = var10000[1];
 *         CGLIB$toString$2$Proxy = MethodProxy.create(var1, var0, "()Ljava/lang/String;", "toString", "CGLIB$toString$2");
 *         CGLIB$hashCode$3$Method = var10000[2];
 *         CGLIB$hashCode$3$Proxy = MethodProxy.create(var1, var0, "()I", "hashCode", "CGLIB$hashCode$3");
 *         CGLIB$clone$4$Method = var10000[3];
 *         CGLIB$clone$4$Proxy = MethodProxy.create(var1, var0, "()Ljava/lang/Object;", "clone", "CGLIB$clone$4");
 *
 *         //add方法相关
 *         CGLIB$add$0$Method = ReflectUtils.findMethods(new String[]{"add", "(Ljava/lang/String;)V"}, (var1 = Class.forName("ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.CglibProxy.Service.ProductService")).getDeclaredMethods())[0];
 *         CGLIB$add$0$Proxy = MethodProxy.create(var1, var0, "(Ljava/lang/String;)V", "add", "CGLIB$add$0");
 *     }
 *
 *     //核心方法调用
 *     final void CGLIB$add$0(String var1) {
 *         super.add(var1);
 *     }
 *
 *     //代理方法
 *     public final void add(String var1) {
 *
 *         //下面这一小块是为了获取控制器
 *         MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
 *         if (var10000 == null) {
 *             CGLIB$BIND_CALLBACKS(this);
 *             var10000 = this.CGLIB$CALLBACK_0;
 *         }
 *
 *         //判断是否存在控制器
 *         if (var10000 != null) {
 *
 *             //如果存在控制器就调用控制器的回调方法
 *             var10000.intercept(this, CGLIB$add$0$Method, new Object[]{var1}, CGLIB$add$0$Proxy);
 *
 *         } else {
 *
 *             //如果不存在控制器就直接调用父类
 *             super.add(var1);
 *
 *         }
 *     }
 *
 * 由上可知，Cglib为被代理对象的每个方法都创建了一个MethodProxy对象
 * 利用 MethodProxy.create(Class c1,Class c2,String desc,@NotNull String name1,@NotNull String name2)
 * 下面我们来看看 create 方法的源码
 * 类：MethodProxy
 *
 *     //c1是被代理对象的class类，c2是代理对象的class类，desc是方法相关信息的字符串
 *     //name1是被代理对象的方法的名字，用于代理对象调用方法后转到控制器的回调方法（add）
 *     //name2是被代理对象的方法的名字稍加修改，内容就是 super.name1(..)。也就是最终要执行的核心代码。（CGLIB$add$0）
 *     public static MethodProxy create(Class c1, Class c2, String desc, String name1, String name2) {
 *
 *         //新建一个MethodProxy对象
 *         MethodProxy proxy = new MethodProxy();
 *
 *         //设定MethodProxy对象的sig1属性
 *         //该属性对应一个Signature对象
 *         //这个Signature类的构造方法传入方法名，以及方法信息，并保存这些信息．
 *         proxy.sig1 = new Signature(name1, desc);//add
 *         proxy.sig2 = new Signature(name2, desc);//CGLIB$add$0
 *
 *         //把c1和c2传给MethodProxy的一个内部类，并保存在这个内部类中
 *         proxy.createInfo = new CreateInfo(c1, c2);
 *         //返回创建好的MethodProxy对象
 *         return proxy;
 *     }
 *
 * 下面我们来看看当代理对象调用 add 方法时，会先到代理对象的 add 方法中获取并判断是否存在控制器
 * 如果存在就回调控制器的 intercept 方法。
 * 如果不存在就直接调用父类的 add 方法。（super.add(var1)）
 * 那么我们就看看当调用了或者回调了控制器的 intercept 方法，会发生什么。
 * 下面为部分截取代码
 * 类：控制器
 *
 *     //obj是代理对象，method是被代理对象的方法 add ，args是调用方法时候传入的参数，proxy就是上面的MethodProxy对象。
 *     public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
 *
 *         ...
 *
 *         //关键点，执行MethodProxy对象的invokeSuper方法，并最终执行被代理对象的核心方法。
 *         Object object = proxy.invokeSuper(obj, args);
 *
 *         ...
 *
 *     }
 *
 * 下面来看看 invokeSuper 方法的源码
 * 类：MethodProxy
 *
 *     //obj就是代理对象，args就是方法参数
 *     public Object invokeSuper(Object obj, Object[] args) throws Throwable {
 *         try {
 *
 *             //init参考下面的方法，主要进行初始化。
 *             init();
 *
 *             //获取该MethodProxy对象的fastClassInfo（内部类实例）
 *             FastClassInfo fci = fastClassInfo;
 *
 *             //fci内部类实例的f1属性实例是代理类的对象
 *             //结合下面的 init 方法的源码思考
 *             //这里调用了 f2 的 invoke 方法
 *             //f2对应着代理对象类的FastClass实例（里面都是FastClass类的方法的索引）
 *             //参数的 i2 是一个int类型的数值，充当在FastClass类中的索引值。
 *             return fci.f2.invoke(fci.i2, obj, args);
 *         } catch (InvocationTargetException e) {
 *             throw e.getTargetException();
 *         }
 *     }
 *
 *     private void init(){
 *
 *         if (fastClassInfo == null)
 *         {
 *             synchronized (initLock)
 *             {
 *                 if (fastClassInfo == null)
 *                 {
 *                     CreateInfo ci = createInfo;
 *
 *                     //new一个内部类FastClassInfo的对象
 *                     FastClassInfo fci = new FastClassInfo();
 *
 *                     //通过传入对应class（c1为被代理对象类，c2为代理对象类）
 *                     //f1与f2对应两个FastClass的实例（也就是被代理对象类的FastClass跟代理对象类的FastClass）
 *                     fci.f1 = helper(ci, ci.c1);
 *                     fci.f2 = helper(ci, ci.c2);
 *
 *                     //获取对应包装了方法的Signature实例对应的索引
 *                     //说白了就是获取对应方法在FastClass中的索引值
 *                     //i1对应着 add 方法的索引值
 *                     //i2对应着 CGLIB$add$0 方法的索引值
 *                     fci.i1 = fci.f1.getIndex(sig1);
 *                     fci.i2 = fci.f2.getIndex(sig2);
 *
 *                     fastClassInfo = fci;
 *                     createInfo = null;
 *                 }
 *             }
 *         }
 *     }
 *
 * 最后我们就到代理对象类的FastClass类中看看，他的 invoke 方法做了什么事。
 * 类：ProductService$$EnhancerByCGLIB$$ea3958f9$$FastClassByCGLIB$$7273c01f
 *
 *     //var1就是传进来的方法索引值，var2就是代理对象，var3就是方法参数
 *     //我们传进来的 fci.i2 对应着 CGLIB$add$0 方法的索引值，索引值的获取在这个FastClass类的 getIndex 方法中，可以参考下面的getIndex方法。
 *     //参考下面的getIndex方法可知道 var1 的值为 14
 *     public Object invoke(int var1, Object var2, Object[] var3) throws InvocationTargetException {
 *         ea3958f9 var10000 = (ea3958f9)var2;
 *         int var10001 = var1;
 *
 *         try {
 *             switch(var10001) {
 *             case 0:
 *                 var10000.add((String)var3[0]);
 *                 return null;
 *             case 1:
 *                 return new Boolean(var10000.equals(var3[0]));
 *             case 2:
 *                 return var10000.toString();
 *             case 3:
 *                 return new Integer(var10000.hashCode());
 *             case 4:
 *                 return var10000.clone();
 *             case 5:
 *                 return var10000.newInstance((Callback)var3[0]);
 *             case 6:
 *                 return var10000.newInstance((Callback[])var3[0]);
 *             case 7:
 *                 return var10000.newInstance((Class[])var3[0], (Object[])var3[1], (Callback[])var3[2]);
 *             case 8:
 *                 var10000.setCallback(((Number)var3[0]).intValue(), (Callback)var3[1]);
 *                 return null;
 *             case 9:
 *                 var10000.setCallbacks((Callback[])var3[0]);
 *                 return null;
 *             case 10:
 *                 return var10000.getCallback(((Number)var3[0]).intValue());
 *             case 11:
 *                 return var10000.getCallbacks();
 *             case 12:
 *                 ea3958f9.CGLIB$SET_STATIC_CALLBACKS((Callback[])var3[0]);
 *                 return null;
 *             case 13:
 *                 ea3958f9.CGLIB$SET_THREAD_CALLBACKS((Callback[])var3[0]);
 *                 return null;
 *
 *             //目标 start--------------------
 *             //var10000对应着代理对象
 *             //此处相当于调用了代理对象的CGLIB$add$0方法（传入var3作为参数）
 *             case 14:
 *                 var10000.CGLIB$add$0((String)var3[0]);
 *                 return null;
 *             //目标 end----------------------
 *
 *             case 15:
 *                 return new Boolean(var10000.CGLIB$equals$1(var3[0]));
 *             case 16:
 *                 return var10000.CGLIB$toString$2();
 *             case 17:
 *                 return new Integer(var10000.CGLIB$hashCode$3());
 *             case 18:
 *                 return var10000.CGLIB$clone$4();
 *             case 19:
 *                 ea3958f9.CGLIB$STATICHOOK1();
 *                 return null;
 *             case 20:
 *                 return ea3958f9.CGLIB$findMethodProxy((Signature)var3[0]);
 *             }
 *         } catch (Throwable var4) {
 *             throw new InvocationTargetException(var4);
 *         }
 *
 *         throw new IllegalArgumentException("Cannot find matching method/constructor");
 *     }
 *
 *     //getIndex方法，用于获取对应方法的索引值
 *     //寻找目标为 CGLIB$add$0
 *     public int getIndex(Signature var1) {
 *         String var10000 = var1.toString();
 *         switch(var10000.hashCode()) {
 *         case -2055565910:
 *             if (var10000.equals("CGLIB$SET_THREAD_CALLBACKS([Lnet/sf/cglib/proxy/Callback;)V")) {
 *                 return 13;
 *             }
 *             break;
 *         case -1882565338:
 *             if (var10000.equals("CGLIB$equals$1(Ljava/lang/Object;)Z")) {
 *                 return 15;
 *             }
 *             break;
 *         case -1457535688:
 *             if (var10000.equals("CGLIB$STATICHOOK1()V")) {
 *                 return 19;
 *             }
 *             break;
 *
 *         //寻找目标 start------------
 *         case -1422377419:
 *             if (var10000.equals("CGLIB$add$0(Ljava/lang/String;)V")) {
 *                 return 14;
 *             }
 *             break;
 *         //寻找目标 end--------------
 *
 *         case -1411842725:
 *             if (var10000.equals("CGLIB$hashCode$3()I")) {
 *                 return 17;
 *             }
 *             break;
 *         case -1358456834:
 *             if (var10000.equals("add(Ljava/lang/String;)V")) {
 *                 return 0;
 *             }
 *             break;
 *         case -894172689:
 *             if (var10000.equals("newInstance(Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;")) {
 *                 return 5;
 *             }
 *             break;
 *         case -623122092:
 *             if (var10000.equals("CGLIB$findMethodProxy(Lnet/sf/cglib/core/Signature;)Lnet/sf/cglib/proxy/MethodProxy;")) {
 *                 return 20;
 *             }
 *             break;
 *         case -508378822:
 *             if (var10000.equals("clone()Ljava/lang/Object;")) {
 *                 return 4;
 *             }
 *             break;
 *         case -419626537:
 *             if (var10000.equals("setCallbacks([Lnet/sf/cglib/proxy/Callback;)V")) {
 *                 return 9;
 *             }
 *             break;
 *         case 560567118:
 *             if (var10000.equals("setCallback(ILnet/sf/cglib/proxy/Callback;)V")) {
 *                 return 8;
 *             }
 *             break;
 *         case 811063227:
 *             if (var10000.equals("newInstance([Ljava/lang/Class;[Ljava/lang/Object;[Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;")) {
 *                 return 7;
 *             }
 *             break;
 *         case 973717575:
 *             if (var10000.equals("getCallbacks()[Lnet/sf/cglib/proxy/Callback;")) {
 *                 return 11;
 *             }
 *             break;
 *         case 1221173700:
 *             if (var10000.equals("newInstance([Lnet/sf/cglib/proxy/Callback;)Ljava/lang/Object;")) {
 *                 return 6;
 *             }
 *             break;
 *         case 1230699260:
 *             if (var10000.equals("getCallback(I)Lnet/sf/cglib/proxy/Callback;")) {
 *                 return 10;
 *             }
 *             break;
 *         case 1306468936:
 *             if (var10000.equals("CGLIB$toString$2()Ljava/lang/String;")) {
 *                 return 16;
 *             }
 *             break;
 *         case 1584330438:
 *             if (var10000.equals("CGLIB$SET_STATIC_CALLBACKS([Lnet/sf/cglib/proxy/Callback;)V")) {
 *                 return 12;
 *             }
 *             break;
 *         case 1800494055:
 *             if (var10000.equals("CGLIB$clone$4()Ljava/lang/Object;")) {
 *                 return 18;
 *             }
 *             break;
 *         case 1826985398:
 *             if (var10000.equals("equals(Ljava/lang/Object;)Z")) {
 *                 return 1;
 *             }
 *             break;
 *         case 1913648695:
 *             if (var10000.equals("toString()Ljava/lang/String;")) {
 *                 return 2;
 *             }
 *             break;
 *         case 1984935277:
 *             if (var10000.equals("hashCode()I")) {
 *                 return 3;
 *             }
 *         }
 *
 *         return -1;
 *     }
 *
 * 接着就是回到代理对象类了
 * 调用了代理对象的 CGLIB$add$0 方法
 * 类；ProductService$$EnhancerByCGLIB$$ea3958f9
 *
 *     //无需多言，这里就调用了父类也就是被代理对象的 add 方法
 *     final void CGLIB$add$0(String var1) {
 *         super.add(var1);
 *     }
 *
 * 完结
 *
 */
}