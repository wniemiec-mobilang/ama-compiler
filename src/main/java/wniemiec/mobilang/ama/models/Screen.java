package wniemiec.mobilang.ama.models;

import wniemiec.mobilang.ama.models.behavior.Behavior;
import wniemiec.mobilang.ama.models.tag.Tag;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for representing data from screen tag.
 */
public class Screen {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String rawName;
    private final String name;
    private final Tag structure;
    private final Style style;
    private final Behavior behavior;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private Screen(
        String name, 
        Tag structure,
        Style style,
        Behavior behavior
    ) {
        this.rawName = name;
        this.name = normalize(name);
        this.structure = structure;
        this.style = (style == null) ? new Style() : style;
        this.behavior = (behavior == null) ? new Behavior() : behavior;
    }


    //-------------------------------------------------------------------------
    //		Builder
    //-------------------------------------------------------------------------
    public static class Builder {

        private String name;
        private Tag structure;
        private Style style;
        private Behavior behavior;

        public Builder name(String name) {
            this.name = name;

            return this;
        }

        public Builder structure(Tag structure) {
            this.structure = structure;

            return this;
        }

        public Builder style(Style style) {
            this.style = style;

            return this;
        }

        public Builder behavior(Behavior behavior) {
            this.behavior = behavior;

            return this;
        }

        public Screen build() {
            validateFields();

            return new Screen(
                name, 
                structure,
                style,
                behavior
            );
        }

        private void validateFields() {
            validateName();
            validateStructure();
        }

        private void validateName() {
            if (name == null) {
                throw new IllegalStateException("Name cannot be null");
            }
        }

        private void validateStructure() {
            if (structure == null) {
                throw new IllegalStateException("structure cannot be null");
            }
        }
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private String normalize(String name) {
        StringBuilder normalizedName = new StringBuilder();
        
        for (String term : name.split("-")) {
            normalizedName.append(StringUtils.capitalize(term));
        }

        return normalizedName.toString();
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public String getRawName() {
        return rawName;
    }

    public String getName() {
        return name;
    }
    
    public Tag getStructure() {
        return structure;
    }

    public Style getStyle() {
        return style;
    }

    public Behavior getBehavior() {
        return behavior;
    }
}
