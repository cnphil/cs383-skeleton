package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	
        return TypeResult.of(E.get(x));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value vx = s.E.get(x);
    	if(vx == null) throw new RuntimeError("runtime error name: " + x);
    	
    	if(vx instanceof RecValue) {
    		// TODO - ed
    		RecValue rv = (RecValue)vx;
    		Rec recExpr = new Rec(rv.x, rv.e);
    		
    		Value ret = recExpr.eval(State.of(rv.E, s.M, s.p));
    		return ret;
    	}
    	
        return vx;
    }
}
