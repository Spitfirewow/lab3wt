package by.bsuir.wt_lab.parser;

import by.bsuir.wt_lab.entity.Coffee;
import by.bsuir.wt_lab.tag.CoffeeTag;
import by.bsuir.wt_lab.tag.EntityTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

public class DomParser {

    private static Document document = null;
    private static DomParser instance = new DomParser();
    private static final Logger logger = LogManager.getLogger();

    public static DomParser getInstance() {
        return instance;
    }

    private static void parseXmlFile(File xmlFile) throws IOException, SAXException, ParserConfigurationException {
        if (document == null) {
            logger.info("Creating document for dom parser from file - " + xmlFile);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
            document.normalize();
        }
    }

    public List<Coffee> getCoffees(String filepath) {
        File xmlFile = new File(filepath);
        List<Coffee> coffees = new ArrayList<>();
        logger.info("A list of Coffees was created.");
        try{
            DomParser.parseXmlFile(xmlFile);
            NodeList coffeeNodes = document.getDocumentElement().getElementsByTagName(EntityTag.COFFEE.toString().toLowerCase());
            for (int i = 0; i < coffeeNodes.getLength(); i++) {
                Element coffeeElement = (Element) coffeeNodes.item(i);
                if (coffeeElement.getNodeType() != Node.TEXT_NODE) {
                    Coffee coffee = getCoffeeFromNode(coffeeElement);
                    coffee.setId(Integer.parseInt(coffeeElement.getAttribute("id")));
                    coffees.add(coffee);
                    logger.info("New coffee was added to the list.");
                }
            }
            logger.info("All coffees were got by parser (" + coffees.size() + ")");
        } catch (SAXException | IOException | ParserConfigurationException e){
            logger.error("File " + xmlFile.getName() + " not found or is incorrect.");
        }
        return coffees;
    }

    private Coffee getCoffeeFromNode(Node orderNode) {
        Coffee coffee = new Coffee();
        logger.info("New coffee created.");
        NodeList coffeeProps = orderNode.getChildNodes();
        CoffeeTag orderTagName;
        String str = null;
        for (int j = 0; j < coffeeProps.getLength(); j++) {

            if (coffeeProps.item(j).getNodeType() != Node.TEXT_NODE) {

                try {
                    str = coffeeProps.item(j).getNodeName();
                    orderTagName = CoffeeTag.valueOf(str.toUpperCase().replace("-", "_"));
                    switch (orderTagName) {
                        case NAME:
                            coffee.setName(coffeeProps.item(j).getTextContent());
                            break;
                        case WEIGHTL:
                            coffee.setWeightL(Integer.parseInt(coffeeProps.item(j).getTextContent()));
                            break;
                        case WEIGHTM:
                            coffee.setWeightM(Integer.parseInt(coffeeProps.item(j).getTextContent()));
                            break;
                        case WEIGHTS:
                            coffee.setWeightS(Integer.parseInt(coffeeProps.item(j).getTextContent()));
                            break;
                    }
                } catch (Exception e) {
                    logger.warn("Tag " + str + " was ignored.");
                }
            }
        }
        return coffee;
    }

}
