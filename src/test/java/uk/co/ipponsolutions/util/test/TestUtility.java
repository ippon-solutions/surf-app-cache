package uk.co.ipponsolutions.util.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.co.ipponsolutions.surfapp.exceptions.CacheException;


public class TestUtility {

	public static Object loadSpringManagedObject(String objectName, String confFileName){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(confFileName);
		return ctx.getBean(objectName);
	}
	
	public static String loadResourceAsString(String resourceName) throws IOException {
		return IOUtils.toString(TestUtility.class.getResourceAsStream(resourceName));
	}
	
	
	public static InputStream loadResourceAsStream(String resourceName) throws IOException {
		return TestUtility.class.getResourceAsStream(resourceName);
	}
	
	
	@Test
	public void testDummy() throws CacheException {
		assertTrue(true);	
	}
	
}
