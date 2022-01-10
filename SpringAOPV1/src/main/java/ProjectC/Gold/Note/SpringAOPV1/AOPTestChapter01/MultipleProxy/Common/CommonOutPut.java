package ProjectC.Gold.Note.SpringAOPV1.AOPTestChapter01.MultipleProxy.Common;

public class CommonOutPut {

    public static void before(int layer){
        System.out.println("Layer : "+layer+", before method proceed.");
    }

    public static void afterReturn(int layer){
        System.out.println("Layer : "+layer+", afterReturn method proceed.");
    }

    public static void afterThrowing(int layer){
        System.out.println("Layer : "+layer+", afterThrowing method proceed.");
    }

    public static void after(int layer){
        System.out.println("Layer : "+layer+", after method proceed.");
    }
}
