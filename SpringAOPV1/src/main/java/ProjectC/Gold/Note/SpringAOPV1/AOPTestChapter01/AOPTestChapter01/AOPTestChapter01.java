package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.AOPTestChapter01;

import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.Core.MainMethod;
import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.Provider.ProxyProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(value = "classpath:aopTestChapter01.xml")
@RunWith(value = SpringJUnit4ClassRunner.class)
public class AOPTestChapter01 {

    @Test
    public void test01(){
        MainMethod mainMethod = new MainMethod();
        MainMethod mainMethod01 =(MainMethod) ProxyProvider.getProxy(false,true,true,false,false,mainMethod.getClass());
        MainMethod mainMethod02 = (MainMethod) ProxyProvider.getProxy(false,true,true,false,false,mainMethod01.getClass());
        mainMethod02.run();
    }
}
