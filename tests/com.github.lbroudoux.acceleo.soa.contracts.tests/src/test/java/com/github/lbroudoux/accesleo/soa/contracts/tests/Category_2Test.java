package com.github.lbroudoux.accesleo.soa.contracts.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.HashMap;

import org.custommonkey.xmlunit.NamespaceContext;
import org.custommonkey.xmlunit.SimpleNamespaceContext;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.XpathEngine;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
/**
 * This is an test case (actually more an integration test case ...) for the
 * NonRegressionSystem-Category_2-v1.xsd file generated by the XSD Acceleo
 * generators for Obeo SOA Designer.
 * @author laurent
 */
public class Category_2Test{

   /** The document corresponding to schema under test. */
   private static Document document = null; 
   /** The Xpath engine for testing the document. */
   private static XpathEngine engine = null;
   
   
   @BeforeClass
   public static void setUp() throws Exception{
      // Parse xsd schema under test.
      InputSource source = new InputSource(new FileInputStream("target/classes/NonRegressionSystem-Category_2-v1.xsd"));
      document = XMLUnit.buildControlDocument(source);
      
      // Prepare a Xpath engine with expected namespaces.
      HashMap<String, String> m = new HashMap<String, String>();
      m.put("xs", "http://www.w3.org/2001/XMLSchema");
      m.put("cat1", "http://www.github.com/Category_1");
      m.put("sub1", "http://www.github.com/Category_1/SubCategory_1");

      NamespaceContext ctx = new SimpleNamespaceContext(m);
      engine = XMLUnit.newXpathEngine();
      engine.setNamespaceContext(ctx);
   }
   
   @Test
   public void testCompositeDTOType(){
      try{
         // Check BasicDTO type is defined.     
         NodeList list = engine.getMatchingNodes("/xs:schema/xs:complexType[@name='CompositeDTO']", document);
         assertEquals(1, list.getLength());
         assertEquals(Node.ELEMENT_NODE, list.item(0).getNodeType());
         
         // Check basicExt attribute is correct.
         list = engine.getMatchingNodes("/xs:schema/xs:complexType[@name='CompositeDTO']/xs:sequence/xs:element[@name='basicExt']",
               document);
         assertEquals(1, list.getLength());
         assertEquals("ns2:BasicExtDTO", list.item(0).getAttributes().getNamedItem("type").getTextContent());
         
      }
      catch (Exception e){
         fail("Exception should not be thrown");
      }
   }
}
