package uk.co.ipponsolutions.surfapp.loaders;


import org.w3c.dom.Document;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;

import uk.co.ipponsolutions.surfapp.exceptions.CacheException;

public class XMLParserHelper {


	/**
	 * Compile and evaluate xpath expression against the xml document.
	 * @param xpath
	 * @param document
	 * @param expression
	 * @param returnType
	 * @return object
	 * @throws CacheException
	 */
	public static Object getExpressionValue(XPath xPath, Document domDocument, String expression, QName returnType) throws CacheException {
		try {
			XPathExpression xPathExpression = xPath.compile(expression);
			return xPathExpression.evaluate(domDocument, returnType);
		} catch (XPathExpressionException e) {
			throw new CacheException(e.getMessage(), e);
		}
	}
	
	/**
	 * Compile and evaluate xpath expression against the xml document.
	 * @param element
	 * @param expression
	 * @param returnType
	 * @return object
	 * @throws CacheException
	 */
	public static Object getExpressionValueForXmlSnippet(Element element, String expression, QName returnType) throws CacheException {
		try {
			XPathExpression xPathExpression = XPathFactory.newInstance().newXPath().compile(expression); 
			return xPathExpression.evaluate(element, returnType);
		} catch (XPathExpressionException e) {
			throw new CacheException(e.getMessage(), e);
		}
	}
}
