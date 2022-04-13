package wniemiec.mobilang.ama.models.behavior;


/**
 * Responsible for representing a variable from behavior code.
 */
public class Variable {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String id;
    private final String type;
    private final String content;
    
    
    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public Variable(String id, String type, String content) {
        this.id = id;
        this.type = type;
        this.content = content;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Variable ["
                + "id=" + id 
                + ", type=" + type 
                + ", content=" + content.replace("\\n", "") 
            + "]";
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        
        return prime * result + ((id == null) ? 0 : id.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Variable other = (Variable) obj;
        
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } 
        else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }


    //-------------------------------------------------------------------------
    //		Getters
    //-------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public String getKind() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
