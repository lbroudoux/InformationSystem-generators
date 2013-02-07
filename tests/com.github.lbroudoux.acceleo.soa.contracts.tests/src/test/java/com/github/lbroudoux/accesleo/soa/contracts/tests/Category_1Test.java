package com.github.lbroudoux.accesleo.soa.contracts.tests;

import static org.junit.Assert.*;

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
 * 
 * @author laurent
 */
public class Category_1Test{

   /** The document corresponding to schema under test. */
   private static Document document = null; 
   /** The Xpath engine for testing the document. */
   private static XpathEngine engine = null;
   
   
   @BeforeClass
   public static void setUp() throws Exception{
      // Parse xsd schema under test.
      InputSource source = new InputSource(new FileInputStream("target/classes/NonRegressionSystem-Category_1-v1.xsd"));
      document = XMLUnit.buildControlDocument(source);
      
      // Prepare a Xpath engine with expected namespaces.
      HashMap<String, String> m = new HashMap<String, String>();
      m.put("xs", "http://www.w3.org/2001/XMLSchema");

      NamespaceContext ctx = new SimpleNamespaceContext(m);
      engine = XMLUnit.newXpathEngine();
      engine.setNamespaceContext(ctx);
   }
   
   @Test
   public void testBasicDTOType(){
      try{
         // Check BasicDTO type is defined.     
         NodeList list = engine.getMatchingNodes("/xs:schema/xs:complexType[@name='BasicDTO']", document);
         assertEquals(1, list.getLength());
         assertEquals(Node.ELEMENT_NODE, list.item(0).getNodeType());
         
         // Check foo attribute is correct.
         list = engine.getMatchingNodes("/xs:schema/xs:complexType[@name='BasicDTO']/xs:sequence/xs:element[@name='foo']",
               document);
         assertEquals(1, list.getLength());
         assertEquals("xs:string", list.item(0).getAttributes().getNamedItem("type").getFirstChild().getNodeValue());
         assertEquals("0", list.item(0).getAttributes().getNamedItem("minOccurs").getFirstChild().getNodeValue());
         
         // Check bar attribute is correct.
         list = engine.getMatchingNodes("/xs:schema/xs:complexType[@name='BasicDTO']/xs:sequence/xs:element[@name='bar']",
               document);
         assertEquals(1, list.getLength());
         assertEquals("xs:int", list.item(0).getAttributes().getNamedItem("type").getFirstChild().getNodeValue());
      }
      catch (Exception e){
         fail("Exception should not be thrown");
      }
   }
   
   @Test
   public void testEntity_1DTOType(){
      try{
         // Check Entity_1DTO type is defined.     
         NodeList list = engine.getMatchingNodes("/xs:schema/xs:complexType[@name='Entity_1DTO']", document);
         assertEquals(1, list.getLength());
         assertEquals(Node.ELEMENT_NODE, list.item(0).getNodeType());
         
         // Check attribute1 attribute is correct.
         list = engine.getMatchingNodes("/xs:schema/xs:complexType[@name='Entity_1DTO']/xs:sequence/xs:element[@name='attribute1']",
               document);
         assertEquals(1, list.getLength());
         assertEquals("xs:string", list.item(0).getAttributes().getNamedItem("type").getFirstChild().getNodeValue());
         assertEquals("0", list.item(0).getAttributes().getNamedItem("minOccurs").getFirstChild().getNodeValue());
         
         // Check attribute2 attribute is correct.
         list = engine.getMatchingNodes("/xs:schema/xs:complexType[@name='Entity_1DTO']/xs:sequence/xs:element[@name='attribute2']",
               document);
         assertEquals(1, list.getLength());
         assertEquals("xs:int", list.item(0).getAttributes().getNamedItem("type").getFirstChild().getNodeValue());
      }
      catch (Exception e){
         fail("Exception should not be thrown");
      }
   }
}
