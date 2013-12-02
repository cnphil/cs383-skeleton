package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Div extends ArithExpr {

    public Div(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " / " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value lvalue = this.l.eval(s);
    	Value rvalue = this.r.eval(s);
    	if(!(lvalue instanceof IntValue) || !(rvalue instanceof IntValue)) throw new RuntimeError("runtime error div");
    	IntValue retvalue = new IntValue(((IntValue)lvalue).n / ((IntValue)rvalue).n);
    	
        return retvalue;
    }
}
