package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.PostProcessor;

import ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo.Boss;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Date;

public class MyBeanPostProcessor implements BeanPostProcessor {

    /***
     *
     * 该方法作为拦截器会在bean的初始化方法被调用之前执行
     * 注意无论bean配置或没配置初始化方法都会执行。
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        /** 只针对类型为Boss的bean **/
        if (bean instanceof Boss) {
            System.out.println(new Date().toString() + " BeanPostProcessor 在[Boss]初始化之前捕捉到了该动作");
        }

        return null;
    }

    /***
     *
     * 该方法作为拦截器会在bean的初始化方法被调用之前执行
     * 注意无论bean配置或没配置初始化方法都会执行。
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        /** 只针对类型为Boss的bean **/
        if (bean instanceof Boss) {
            System.out.println(new Date().toString() + " BeanPostProcessor 在[Boss]初始化之后捕捉到了该动作");
        }

        return null;
    }
}
