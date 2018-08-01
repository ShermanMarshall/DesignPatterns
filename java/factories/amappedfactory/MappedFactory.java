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
    private static final Map<Class<?>, Constructor<?>> factory;
    private static final int NUM_TYPES = 1;
    
    /**
     * Boilerplate to initialize factory, but happens prior to application startup,
     * and should be faster than other schemes (switches/String hash-code/etc)
     */
    static {
         factory = new HashMap(NUM_TYPES);
         try {
            //Constructor<ConcreteType> type = ConcreteType.class.getConstructor(ConcreteType.class);
	    Constructor<?> type = (Constructor<?>) ConcreteType.class.getConstructor();

            factory.put(ConcreteType.class, (Constructor<?>) type);
         } catch (NoSuchMethodException nsme) {
             System.out.println(nsme);
         }
    }
    
    public AbstractType create(Class<?> clazz) {
        AbstractType instance = null;
        
        try {
            instance = (AbstractType) factory.get(clazz).newInstance();
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
	MappedFactory mappedFactory = new MappedFactory();
	ConcreteType ct = (ConcreteType) mappedFactory.create(ConcreteType.class);
	
	ct.implement();
	ct.commonLogic();
    }

}
