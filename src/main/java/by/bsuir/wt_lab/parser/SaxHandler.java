package by.bsuir.wt_lab.parser;

import by.bsuir.wt_lab.entity.*;
import by.bsuir.wt_lab.tag.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private final static String xmlFile = "coffeeData.xml";

    private List<Coffee> coffeeList = new ArrayList<>();
    private Coffee coffee;

    private List<Order> orderList = new ArrayList<>();
    private Order order;

    private List<Extra> extraList = new ArrayList<>();
    private Extra extra;

    private StringBuilder text;
    private EntityTag entityTag = EntityTag.NONE;

    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<Extra> getExtraList() {
        return extraList;
    }

    private static SaxHandler instance = null;

    public static SaxHandler getInstance() {
        if (instance == null) {
            instance = new SaxHandler();
            try {
                XMLReader xmlReader = XMLReaderFactory.createXMLReader();
                xmlReader.setContentHandler(instance);

                xmlReader.parse(new InputSource(instance.getClass().getClassLoader().
                        getResource(xmlFile).toString()));
            } catch (SAXException | IOException | NullPointerException e) {
                logger.error(e.getMessage());
            }
        }
        return instance;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        text = new StringBuilder();
        EntityTag rootTag = EntityTag.NONE;
        try {
            rootTag = EntityTag.valueOf(qName.toUpperCase());
            entityTag = rootTag;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }
        switch (rootTag) {
            case COFFEE:
                coffee = new Coffee();
                coffee.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case ORDER:
                order = new Order();
                order.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case EXTRA:
                extra = new Extra();
                extra.setId(Integer.parseInt(attributes.getValue("id")));
                break;
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String qNameUpperCase = qName.toUpperCase();
        switch (entityTag) {
            case COFFEE: {
                CoffeeTag coffeeTag = CoffeeTag.NONE;
                try {
                    coffeeTag = CoffeeTag.valueOf(qNameUpperCase);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage());
                }
                switch (coffeeTag) {
                    case NAME:
                        coffee.setName(text.toString());
                        break;
                    case WEIGHTL:
                        coffee.setWeightL(Integer.parseInt(text.toString()));
                        break;
                    case WEIGHTM:
                        coffee.setWeightM(Integer.parseInt(text.toString()));
                        break;
                    case WEIGHTS:
                        coffee.setWeightS(Integer.parseInt(text.toString()));
                        break;
                    default:
                        coffeeList.add(coffee);
                        coffee = null;
                        entityTag = EntityTag.NONE;
                        break;
                }
            }
            break;
            case ORDER: {
                OrderTag orderTag = OrderTag.NONE;
                try {
                    orderTag = OrderTag.valueOf(qNameUpperCase);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage());
                }
                switch (orderTag) {
                    case DATETIME:
                        order.setDateTime(text.toString());
                        break;
                    case READY:
                        boolean isReady = text.toString().toUpperCase().equals("TRUE");
                        order.setReady(isReady);
                        break;
                    case USERID:
                        order.setUserId(Integer.parseInt(text.toString()));
                        break;
                    default:
                        orderList.add(order);
                        order = null;
                        entityTag = EntityTag.NONE;
                        break;
                }
            }
            break;
            case EXTRA: {
                ExtraTag extraTag = ExtraTag.NONE;
                try {
                    extraTag = ExtraTag.valueOf(qNameUpperCase);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage());
                }
                switch (extraTag) {
                    case NAME:
                        extra.setName(text.toString());
                        break;
                    case PRICE:
                        extra.setPrice(Double.parseDouble(text.toString()));
                        break;
                    default:
                        extraList.add(extra);
                        extra = null;
                        entityTag = EntityTag.NONE;
                        break;
                }
                break;
            }
        }
    }
}
