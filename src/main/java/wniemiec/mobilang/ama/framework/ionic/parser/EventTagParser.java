package wniemiec.mobilang.ama.framework.ionic.parser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import wniemiec.data.java.Encryptor;
import wniemiec.data.java.Encryptors;
import wniemiec.mobilang.ama.models.tag.Tag;


class EventTagParser {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final Map<String, String> events;
    private static final String ATTRIBUTE_ONCLICK;
    private Tag parsedTag;
    private Encryptor encryptor;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        ATTRIBUTE_ONCLICK = "onclick";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public EventTagParser() {
        events = new HashMap<>();
        parsedTag = Tag.getEmptyInstance();
        encryptor = Encryptors.md5();
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public void parse(Tag tag) {
        parsedTag = tag.clone();
        
        if (tag.hasAttribute(ATTRIBUTE_ONCLICK)) {
            parseTagWithOnClick(parsedTag);
        }
    }

    private void parseTagWithOnClick(Tag tag) {
        String id = generateUniqueIdentifier();
        
        tag.addAttribute("id", id);
        
        events.put(id, "onclick = () => " + tag.getAttribute(ATTRIBUTE_ONCLICK));

        tag.removeAttribute(ATTRIBUTE_ONCLICK);
    }

    private String generateUniqueIdentifier() {
        return ("_" + encryptor.encrypt(String.valueOf(generateRandomNumber())));
    }
	
	private double generateRandomNumber() {
		return (new Date().getTime() + (Math.random() * 9999 + 1));
	}


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public Map<String, String> getEvents() {
        return events;
    }

    public Tag getParsedTag() {
        return parsedTag;
    }
}
