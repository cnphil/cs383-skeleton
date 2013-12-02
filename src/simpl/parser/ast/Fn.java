package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	
    	Type t1 = E.get(x);
    	
    	if(t1 == null) {
    		t1 = new TypeVar(true);
    		E = TypeEnv.of(E, x, t1);
    	}
    	
    	TypeResult tre = e.typecheck(E);
    	
    	ArrowType ans = new ArrowType(tre.s.apply(t1), tre.t);
    	
    	TypeResult ret = TypeResult.of(tre.s, ans);
    	//System.err.println("Fn: " + tre.s);
    	return ret;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        return new FunValue(s.E, x, e);
    }
}
