package wniemiec.mobilang.asc.models.behavior;


/**
 * Responsible for representing behavior variables.
 */
public class Variable {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private String id;
    private String type;
    private String content;
    
    
    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    /**
     * Behavior variable.
     * 
     * @param       id Variable identifier
     * @param       type Variable type
     * @param       content Variable content
     */
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
