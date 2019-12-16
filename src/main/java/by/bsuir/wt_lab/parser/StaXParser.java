package by.bsuir.wt_lab.parser;

import by.bsuir.wt_lab.entity.Extra;
import by.bsuir.wt_lab.tag.ExtraTag;
import by.bsuir.wt_lab.tag.EntityTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.util.ArrayList;
import java.util.List;

public class StaXParser {

    private static final Logger logger = LogManager.getLogger();
    private static EntityTag entityTag = EntityTag.NONE;

    public static List<Extra> processExtras(XMLStreamReader reader) throws XMLStreamException {
        List<Extra> extras = new ArrayList<>();
        Extra extra = null;
        ExtraTag elementName = ExtraTag.NONE;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    try {
                        switch (entityTag) {
                            case NONE:
                                entityTag = EntityTag.valueOf(reader.getLocalName().toUpperCase());
                                extra = new Extra();
                                int id = Integer.parseInt(reader.getAttributeValue(null, "id"));
                                extra.setId(id);
                                break;
                            case EXTRA:
                                elementName = ExtraTag.valueOf(reader.getLocalName().toUpperCase());
                                break;
                        }
                    } catch (EnumConstantNotPresentException | IllegalArgumentException e) {
                        logger.error(e.getMessage());
                        continue;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();

                    if (text.isEmpty()) {
                        break;
                    }
                    try {
                        switch (elementName) {
                            case NAME:
                                extra.setName(text);
                                break;
                            case PRICE:
                                extra.setPrice(Double.parseDouble(text));
                                break;
                        }
                    } catch (NullPointerException | IllegalArgumentException e) {
                        logger.error(e.getMessage());
                        continue;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    try {
                        entityTag = EntityTag.valueOf(reader.getLocalName().toUpperCase());
                    } catch (EnumConstantNotPresentException | IllegalArgumentException e) {
                        continue;
                    }
                    if (entityTag == EntityTag.EXTRA) {
                        extras.add(extra);
                        entityTag = EntityTag.NONE;
                    }
                    break;
            }
        }
        return extras;
    }
}
