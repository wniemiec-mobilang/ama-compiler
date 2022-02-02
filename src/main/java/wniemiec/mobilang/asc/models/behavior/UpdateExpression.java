package wniemiec.mobilang.asc.models.behavior;

public class UpdateExpression extends Expression {

    private String operator;
    private boolean prefix; 
    private Expression argument;

    public UpdateExpression(String operator, boolean prefix, Expression argument) {
        this.operator = operator;
        this.prefix = prefix;
        this.argument = argument;
    }

    @Override
    public String toCode() {
        if (prefix) {
            return operator + argument.toCode();    
        }

        return argument.toCode() + operator;
    }

}
