package fr.nico.sqript.compiling;

import fr.nico.sqript.ScriptManager;
import fr.nico.sqript.structures.ITypeParser;
import fr.nico.sqript.structures.ScriptElement;
import fr.nico.sqript.structures.ScriptOperator;
import fr.nico.sqript.structures.TypeParserDefinition;
import fr.nico.sqript.types.primitive.TypeBoolean;

import java.util.Arrays;

public class NodeOperation extends Node {

    ScriptOperator operator;

    public NodeOperation(Node[] children, ScriptOperator operator) {
        super(children);
        this.operator = operator;
    }

    public NodeOperation(ScriptOperator operator) {
        this.operator = operator;
    }

    public ScriptOperator getOperator() {
        return operator;
    }

    public void setOperator(ScriptOperator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator.toString()+super.toString();
    }

    @Override
    public Class<? extends ScriptElement> getReturnType() {
        //System.out.println("Getting type for : "+toString());
        if(getChildren().length == 1){
            if(operator == ScriptOperator.NOT)
                return TypeBoolean.class;
            return ScriptManager.getUnaryOperation(getChildren()[0].getReturnType(), operator).getReturnType();
        }else if(getChildren().length == 2){
            //System.out.println((getChildren()[0] == null) +" "+getChildren()[0].getReturnType());
            //System.out.println((getChildren()[1] == null) +" "+getChildren()[1].getReturnType());
            //System.out.println("Operation : "+(ScriptManager.getBinaryOperation(getChildren()[0].getReturnType(), getChildren()[1].getReturnType(), operator)==null));
            //System.out.println("Children : "+ Arrays.toString(getChildren()));
            //System.out.println(ScriptManager.getBinaryOperation(getChildren()[0].getReturnType(), getChildren()[1].getReturnType(), operator));
            //System.out.println(ScriptManager.getBinaryOperation(getChildren()[1].getReturnType(), getChildren()[0].getReturnType(), operator));
            if(getChildren()[1] == null ||  getChildren()[0] == null)
                return null;
            if(ScriptManager.getBinaryOperation(getChildren()[0].getReturnType(), getChildren()[1].getReturnType(), operator) != null)
                return ScriptManager.getBinaryOperation(getChildren()[0].getReturnType(), getChildren()[1].getReturnType(), operator).getReturnType();
            else if(ScriptManager.getBinaryOperation(getChildren()[1].getReturnType(), getChildren()[0].getReturnType(), operator) != null)
                return ScriptManager.getBinaryOperation(getChildren()[1].getReturnType(), getChildren()[0].getReturnType(), operator).getReturnType();
            else if (operator == ScriptOperator.MTE ||operator == ScriptOperator.MT ||operator == ScriptOperator.LTE ||operator == ScriptOperator.LT || operator == ScriptOperator.OR || operator == ScriptOperator.AND || operator == ScriptOperator.NOT || operator == ScriptOperator.EQUAL || operator == ScriptOperator.NOT_EQUAL)
                return TypeBoolean.class;
            else {

            }
        }
        return null;
    }
}
