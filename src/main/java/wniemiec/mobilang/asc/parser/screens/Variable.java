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

    
}
