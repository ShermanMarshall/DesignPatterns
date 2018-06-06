package java.factory.mappedfactory;

/**
 * @class:       ConcreteType
 * @description: 
 * @author:      Sherman
 */
public class ConcreteType extends AbstractType {    
    
    public void implement() {
        System.out.println("I must be implemented to satisfy abstract contract");
    }
    
    @Override
    public void commonLogic() {
        System.out.println("I must also be implemented or overridden to satisfy interface contract");
    }
    
}
