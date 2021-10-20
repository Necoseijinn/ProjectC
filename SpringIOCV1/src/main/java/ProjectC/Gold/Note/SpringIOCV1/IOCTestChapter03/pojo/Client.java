package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;

public class Client implements InitializingBean, DisposableBean {
    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(new Date().toString() + " 通过实现InitializingBean接口的afterPropertiesSet方法来实现自定义Bean的初始化方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(new Date().toString() + " 通过实现DisposableBean接口的destroy方法来实现自定义Bean的销毁方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }
}
