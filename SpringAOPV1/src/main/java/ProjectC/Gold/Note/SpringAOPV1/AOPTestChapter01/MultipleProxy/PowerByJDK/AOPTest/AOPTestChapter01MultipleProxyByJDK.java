package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByJDK.AOPTest;

import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByJDK.Core.BaseInterface;
import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByJDK.Core.Target;
import ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.PowerByJDK.Proxy.JDKProxyFactory;
import org.junit.Test;

public class AOPTestChapter01MultipleProxyByJDK {

    @Test
    public void test01(){
        Target target = new Target();
        JDKProxyFactory factory= new JDKProxyFactory();
        BaseInterface proxy01 = (BaseInterface) factory.getProxy(target);
        BaseInterface proxy02 = (BaseInterface) factory.getProxy(proxy01);
        proxy02.show();
    }
}
