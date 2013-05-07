
package com.laytonsmith.abstraction;

import com.laytonsmith.PureUtilities.ClassDiscovery;
import com.laytonsmith.PureUtilities.ReflectionUtils;
import com.laytonsmith.annotations.WrappedItem;
import com.sk89q.util.ReflectionUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class provides various utilities for managing the abstraction layer
 * in a decoupled fashion.
 */
public class AbstractionUtils {
	
	private AbstractionUtils(){}
	
	/**
	 * This maps from the wrapped type to the wrapper
	 */
	private static Map<Class, Class<? extends AbstractionObject>> abstractionTypes = null;
	
	static {
		init();
	}
	/**
	 * Finds the closest match for an object in the abstraction layer, and
	 * returns that object instead. If {@code item} is null, null is simply returned.
	 * @param <T>
	 * @param item
	 * @throws AbstractionException Thrown if this object doesn't match ANY of the object wrappers
	 * in the abstraction layer.
	 * @return 
	 */
	public static <T extends AbstractionObject> T wrap(Object item) throws AbstractionException {
		if(item == null){
			return null;
		}
		return instantiate(doLookup(item.getClass()), item);
	}
	
	/**
	 * Given a class type, returns the abstraction layer wrapper for it. It may not exist, in which
	 * case it will throw an AbstractionException.
	 * @param clazz
	 * @return
	 * @throws com.laytonsmith.abstraction.AbstractionUtils.AbstractionException 
	 */
	public static Class<? extends AbstractionObject> doLookup(Class<?> clazz) throws AbstractionException {
		if(abstractionTypes.containsKey(clazz)){
			return abstractionTypes.get(clazz);
		} else {
			Class found = null;
			//It's not directly included. This may not be a problem though, we need to walk through the superclasses.
			if(found == null){
				Class c = clazz;
				while((c = c.getSuperclass()) != null){
					if(abstractionTypes.containsKey(c)){
						found = c;
						break;
					}
				}
			}
			if(found == null){
				//Ok, still not found? Now we need to look through the implemented interfaces. Unfortunately, there's
				//no awesome way to do this, because if two interfaces are implemented, it's equally likely we want either,
				//so it's arbitrary which one we return. However, the getSuperInterfaces class will return them in the order
				//from left to right of most likely, so while undefined, which is returned is consistent, and reasonable.
				for(Class c : getAllSuperInterfaces(clazz, new ArrayList<Class>())){
					if(abstractionTypes.containsKey(c)){
						found = c;
						break;
					}
				}
			}
			if(found != null){
				//Cool, it's in here. Let's add this type though, so future lookups are faster
				abstractionTypes.put(found, abstractionTypes.get(found));
				return abstractionTypes.get(found);
			}
		}
		throw new AbstractionException("Could not find a match for " + clazz.getName() + " in the abstraction library.");
	}
	
	private static <T extends AbstractionObject> T instantiate(Class<? extends AbstractionObject> type, Object instance){
		Object wrapper = ReflectionUtils.newInstance(type);
		for(Field f : type.getDeclaredFields()){
			if(f.getAnnotation(WrappedItem.class) != null){
				//This is it
				if(f.getType().isAssignableFrom(instance.getClass())){
					ReflectionUtils.set(wrapper.getClass(), wrapper, f.getName(), instance);
				} else {
					//This is unit tested for, but just in case
					throw new RuntimeException("There is an error in the abstraction layer, with the " + type.getName() + " class. Please report this error to the developers.");
				}
				break;
			}
		}
		return (T) wrapper;
	}
	
	private static void init() {
		if(abstractionTypes == null){
			initialize();
		}
	}
	
	/**
	 * Scans all the classes for abstraction classes, and caches them locally
	 * to speed up future operations.
	 */
	public static void initialize() {
		abstractionTypes = new HashMap<Class, Class<? extends AbstractionObject>>();
		for(Field f : ClassDiscovery.GetFieldsWithAnnotation(WrappedItem.class)){
			//There's a unit test for this to make sure that this cast will actually work correctly at runtime.
			abstractionTypes.put(f.getType(), (Class<? extends AbstractionObject>)f.getDeclaringClass());			
		}
	}
	
	private static List<Class> getAllSuperInterfaces(Class base, List<Class> types){
		if(base.getInterfaces().length == 0){
			types.add(base);
		} else {
			//This isn't, but lets look at the parents
			for(Class s : base.getInterfaces()){
				types.add(s);
				getAllSuperInterfaces(s, types);
			}
		}
		return types;
	}
	
	/**
	 * Thrown to indicate that an appropriate object couldn't be found.
	 */
	public static class AbstractionException extends RuntimeException {

		public AbstractionException(String message) {
			super(message);
		}
		
	}
}
