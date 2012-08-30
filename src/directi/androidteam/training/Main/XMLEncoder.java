package directi.androidteam.training.Main;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created with IntelliJ IDEA.
 * User: ssumit
 * Date: 8/30/12
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLEncoder implements IEncoder {
    Document doc;
    public XMLEncoder() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.doc = docBuilder.newDocument();
    }


}
