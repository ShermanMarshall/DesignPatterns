package factories.amappedfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @class:       MappedFactory
 * @description: Design Pattern utilizing a map to access and swap for the appropriate 
 *               concrete type to construct based on an input
 * @author:      Sherman
 */
public class MappedFactory {
    private static final Map<String, Constructor<?>> factory;
    private static final int NUM_TYPES = 1;

    //If the application uses a naming scheme between frontend/backend, it may be possible to
    //create objects dynamically using the same method, avoiding multiple switch statements, or
    //hiding objects (inner/ private classes). Handling arguments is additional complexity
    private static final String CONCRETE_TYPE = "type1";
    
    /**
     * Boilerplate to initialize factory, but happens prior to application startup,
     * and should be faster than other schemes (switches/String hash-code/etc)
     */
    static {
         factory = new HashMap(NUM_TYPES);
         try {
            //Constructor<ConcreteType> type = ConcreteType.class.getConstructor(ConcreteType.class);
	    Constructor<?> type = (Constructor<?>) ConcreteType.class.getConstructor();

            factory.put(CONCRETE_TYPE, (Constructor<?>) type);
         } catch (NoSuchMethodException nsme) {
             System.out.println(nsme);
         }
    }
    
    public static AbstractType create(String stringOrEnum) {
        AbstractType instance = null;
        
        try {
            instance = (AbstractType) factory.get(stringOrEnum).newInstance();
        } catch (InvocationTargetException ite) {
            System.out.println(ite);
        } catch (InstantiationException ie) {
            System.out.println(ie);
        } catch (IllegalAccessException iae) {
            System.out.println(iae);
        } 
        
        return instance;
    }

    public static void main(String...args) {
	ConcreteType ct = (ConcreteType) MappedFactory.create(CONCRETE_TYPE);
	
	ct.implement();
	ct.commonLogic();
    }

}
