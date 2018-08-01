package factories.amappedfactory;

/**
 * @class:       AbstractType
 * @description: 
 * @author:      Sherman
 */
public abstract class AbstractType implements IsOptional {
            
    public void commonMethod() {
        System.out.println("I am shared between all instances. If shared memory isn't a concern, I should be static");
    }
    
    public abstract void implement();
    
    public void commonLogic() {
        System.out.println("I can satisfy interface contract between children types here");
    }

}
