package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class LessEq extends RelExpr {

    public LessEq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " <= " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        Value vl = l.eval(s);
        Value vr = r.eval(s);
        
        if(!(vl instanceof IntValue) || !(vr instanceof IntValue)) throw new RuntimeError("runtime error: lesseq");
        
        IntValue ivl = (IntValue)vl;
        IntValue ivr = (IntValue)vr;
        if(ivl.n <= ivr.n) {
        	return new BoolValue(true);
        } else {
        	return new BoolValue(false);
        }
    }
}
