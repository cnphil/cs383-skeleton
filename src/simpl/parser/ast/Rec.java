package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	Type t = E.get(x);
    	
    	if(t == null) {
    		t = new TypeVar(true);
    		E = TypeEnv.of(E, x, t);
    	}
    	
    	TypeResult tre = e.typecheck(E);
    	
    	//System.err.println("Rec: " + tre.s);
    	
    	//System.err.println("Rec: " + tre.s.apply(t));
    	//System.err.println("Rec: " + tre.t);
    	
    	Substitution sofar = tre.t.unify(tre.s.apply(t)).compose(tre.s);
    	
    	//System.err.println("Rec: " + sofar.apply(t));
    	//System.err.println("Rec: " + tre.t);
    	
        return TypeResult.of(sofar, sofar.apply(t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	RecValue myrec = new RecValue(s.E, this.x, this.e);
    	Env enew = new Env(s.E, this.x, myrec);
    	State snew  = State.of(enew, s.M, s.p);
    	
    	Value ret = this.e.eval(snew);
        return ret;
    }
}
