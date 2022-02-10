package wniemiec.mobilang.asc.models;

import java.util.List;

import wniemiec.mobilang.asc.models.behavior.Variable;


/**
 * Responsible for representing data from screen tag.
 */
public class ScreenData {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String name;
    private final List<String> imports;
    private final List<Variable> stateDeclarations;
    private final List<String> stateBody;
    private final List<Variable> declarations;
    private final List<String> body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private ScreenData(
        String name, 
        List<String> imports, 
        List<Variable> stateDeclarations, 
        List<String> stateBody,
        List<Variable> declarations, 
        List<String> body
    ) {
        this.name = name;
        this.imports = imports;
        this.stateDeclarations = stateDeclarations;
        this.stateBody = stateBody;
        this.declarations = declarations;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Builder
    //-------------------------------------------------------------------------
    public static class Builder {

        private String name;
        private List<String> imports;
        private List<Variable> stateDeclarations;
        private List<String> stateBody;
        private List<Variable> declarations;
        private List<String> body;

        public Builder name(String name) {
            this.name = name;

            return this;
        }

        public Builder imports(List<String> imports) {
            this.imports = imports;

            return this;
        }

        public Builder stateDeclarations(List<Variable> stateDeclarations) {
            this.stateDeclarations = stateDeclarations;

            return this;
        }

        public Builder stateBody(List<String> stateBody) {
            this.stateBody = stateBody;

            return this;
        }

        public Builder declarations(List<Variable> declarations) {
            this.declarations = declarations;

            return this;
        }

        public Builder body(List<String> body) {
            this.body = body;

            return this;
        }

        public ScreenData build() {
            validateFields();

            return new ScreenData(
                name, 
                imports, 
                stateDeclarations, 
                stateBody, 
                declarations, 
                body
            );
        }

        private void validateFields() {
            validateName();
            validateImports();
            validateStateDeclarations();
            validateStateBody();
            validateDeclarations();
            validateBody();
        }

        private void validateName() {
            if (name == null) {
                throw new IllegalStateException("Name cannot be null");
            }
        }

        private void validateImports() {
            if (imports == null) {
                throw new IllegalStateException("imports cannot be null");
            }
        }

        private void validateStateDeclarations() {
            if (stateDeclarations == null) {
                throw new IllegalStateException("stateDeclarations cannot be null");
            }
        }

        private void validateStateBody() {
            if (stateBody == null) {
                throw new IllegalStateException("stateBody cannot be null");
            }
        }

        private void validateDeclarations() {
            if (declarations == null) {
                throw new IllegalStateException("declarations cannot be null");
            }
        }

        private void validateBody() {
            if (body == null) {
                throw new IllegalStateException("body cannot be null");
            }
        }
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public List<String> getImports() {
        return imports;
    }

    public List<Variable> getStateDeclarations() {
        return stateDeclarations;
    }
    
    public List<String> getStateBody() {
        return stateBody;
    }
    
    public List<Variable> getDeclarations() {
        return declarations;
    }
    
    public List<String> getBody() {
        return body;
    }
}
