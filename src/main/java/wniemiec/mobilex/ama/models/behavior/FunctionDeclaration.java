package wniemiec.mobilex.ama.models.behavior;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Responsible for representing a function declaration from behavior code.
 */
public class FunctionDeclaration implements Instruction {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final String name;
    private final boolean async; 
    private final List<Expression> params;
    private final Instruction body;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public FunctionDeclaration(
        String name, 
        boolean async, 
        List<Expression> params,
        Instruction body
    ) {
        this.name = name;
        this.async = async;
        this.params = params;
        this.body = body;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    @Override
    public String toCode() {
        StringBuilder code = new StringBuilder();

        code.append(async ? "async " : "");
        code.append("function ");
        code.append(name);
        code.append('(');
        code.append(paramsToCode());
        code.append(") " );
        code.append(body.toCode());

        return code.toString();
    }

    private String paramsToCode() {
        StringBuilder code = new StringBuilder();
        List<String> paramsAsCode = getParamsAsCode();

        for (int i = 0; i < paramsAsCode.size(); i++) {
            code.append(paramsAsCode.get(i));
            code.append(',');
        }

        if (code.length() > 0) {
            code.deleteCharAt(code.length()-1);
        }

        return code.toString();
    }

    private List<String> getParamsAsCode() {
        return params
            .stream()
            .map(Expression::toCode)
            .collect(Collectors.toList());
    }
}
