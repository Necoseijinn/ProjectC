package ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy;

import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.Bean.Product;
import ProjectC.Gold.Note.PersonalTest.PCTest.JavaOnly.Proxy.DAO.DAOImpl.ProductDAO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestProxy {

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        Class<?>[] interfaces = productDAO.getClass().getInterfaces();
        Class<?> anInterface = interfaces[0];

        Method[] methods = anInterface.getMethods();

        Method[] methods1 = productDAO.getClass().getMethods();

        System.out.println(methods[0]==methods1[0]);
        System.out.println(methods[0].getName());
        System.out.println(methods1[0].getName());
        System.out.println(methods[0]);
        System.out.println(methods1[0]);

        System.out.println("=====================");

        try {
            methods[0].invoke(productDAO, new Product());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }




    }
}
