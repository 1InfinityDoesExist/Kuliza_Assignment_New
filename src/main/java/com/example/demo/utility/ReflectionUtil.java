package com.example.demo.utility;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

	private static ReflectionUtil refObj = null;

	private ReflectionUtil() {

	}

	public static ReflectionUtil getInstance() {
		logger.info("Inside getInstance Method");
		if (refObj == null) {
			refObj = new ReflectionUtil();
			setUpMethod();
		}
		return refObj;
	}

	private static HashMap<String, String> objectBeanMap = new HashMap<String, String>() {
		{
			put("", "");
		}
	};

	private static HashMap<String, HashMap> objSetterPropsMap = new HashMap<String, HashMap>();
	private static HashMap<String, HashMap> objGetterPropsMap = new HashMap<String, HashMap>();

	public static void setUpMethod() {

		logger.info("Inside setUpMethod of ReflectionUtil");

		for (Iterator iterator = objectBeanMap.keySet().iterator(); iterator.hasNext();) {
			String objectName = (String) iterator.next();

			HashMap<String, Method> propGetMethodMap = new HashMap<String, Method>();
			HashMap<String, Method> propSetMethodMap = new HashMap<String, Method>();

			try {

				Class<?> cls = Class.forName(objectBeanMap.get(objectName));
				for (PropertyDescriptor pd : Introspector.getBeanInfo(cls).getPropertyDescriptors()) {
					if (!"class".equals(pd.getName())) {
						if (pd.getReadMethod() != null) {
							propGetMethodMap.put(pd.getName(), pd.getReadMethod());
						}
						if (pd.getWriteMethod() != null) {
							propSetMethodMap.put(pd.getName(), pd.getWriteMethod());
						}
					}
				}
			} catch (ClassNotFoundException | IntrospectionException ex) {
				logger.error(ex.getMessage());
			}
			objSetterPropsMap.put(objectName, propGetMethodMap);
			objGetterPropsMap.put(objectName, propSetMethodMap);
		}

	}

	public HashMap<String, HashMap> getObjSetterPropsMap() {
		return objSetterPropsMap;
	}

	public HashMap<String, HashMap> getObjGetterPropsMap() {
		return objGetterPropsMap;
	}

	public Method getSetterMethod(String objectName, String propName) {
		HashMap propMethod = getObjSetterPropsMap().get(objectName);
		return (Method) propMethod.get(propName);
	}

	public Method getGetterMethod(String objectName, String propName) {
		HashMap propMethod = getObjGetterPropsMap().get(objectName);
		return (Method) propMethod.get(propName);

	}

}
