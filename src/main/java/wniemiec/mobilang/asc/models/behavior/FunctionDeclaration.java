package wniemiec.mobilang.asc.models.behavior;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionDeclaration extends Instruction {

    private String name;
    private boolean async; 
    private List<Expression> params;
    private Instruction body;

    public FunctionDeclaration(
        String name, 
        boolean async, 
        List<Expression> params,
        Instruction body
    ) {
        super("FunctionDeclaration");
        this.name = name;
        this.async = async;
        this.params = params;
        this.body = body;
    }

    @Override
    public String toCode() {
        String asyncField = async ? "async " : "";

        return asyncField + "function " + name + "(" + paramsToCode() + ") " + body.toCode();
    }

    private String paramsToCode() {
        StringBuilder sb = new StringBuilder();
        List<String> argumentsAsCode = params.stream().map(a -> a.toCode()).collect(Collectors.toList());

        for (int i = 0; i < argumentsAsCode.size(); i++) {
            sb.append(argumentsAsCode.get(i));
            sb.append(',');
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length()-1);
        }

        return sb.toString();
        //return 
    }
}
