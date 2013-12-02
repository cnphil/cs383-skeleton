package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Eq extends EqExpr {

    public Eq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " = " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value lvalue = this.l.eval(s);
    	Value rvalue = this.r.eval(s);
    	// if(!(lvalue.isEqual) || !(rvalue instanceof IntValue)) throw new RuntimeError("runtime error eq");
    	boolean retbool = lvalue.equals(rvalue);
    	BoolValue retvalue = new BoolValue(retbool);
    	
        return retvalue;
    }
}
