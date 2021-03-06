package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class GreaterEq extends RelExpr {

    public GreaterEq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " >= " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        Value vl = l.eval(s);
        Value vr = r.eval(s);
        
        if(!(vl instanceof IntValue) || !(vr instanceof IntValue)) throw new RuntimeError("runtime error: greatereq");
        
        IntValue ivl = (IntValue)vl;
        IntValue ivr = (IntValue)vr;
        if(ivl.n >= ivr.n) {
        	return new BoolValue(true);
        } else {
        	return new BoolValue(false);
        }
    }
}
