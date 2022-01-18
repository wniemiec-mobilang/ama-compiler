package wniemiec.mobilang.asc.parser.screens;

public class Variable {
    private String id;
    private String kind;
    private String content;
    
    
    public Variable(String id, String kind, String content) {
        this.id = id;
        this.kind = kind;
        this.content = content;
    }


    @Override
    public String toString() {
        return "Variable [id=" + id + ", kind=" + kind + ", content=" + content.replaceAll("\\n", "") + "]";
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Variable other = (Variable) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    public String getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }


    public String getContent() {
        return content;
    }

    
}
