package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult trl = l.typecheck(E);
    	TypeResult trr = r.typecheck(trl.s.compose(E));
    	
    	//System.err.println("App: " + trl.t);
    	//System.err.println("App: " + trr.t);
    	
    	TypeVar t1 = new TypeVar(true);
    	Type t2 = trr.t;
    	ArrowType ta = new ArrowType(t2, t1);
    	
    	//System.err.println("App: " + ta);
    	
    	Substitution sofar = trr.s.compose(trl.s);
    	sofar = sofar.apply(trl.t).unify(ta).compose(sofar);
    	
    	//System.err.println("App: " + sofar.apply(trl.t));
    	
    	return TypeResult.of(sofar, sofar.apply(t1));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value vl = l.eval(s);
    	Value vr = r.eval(s);
    	
    	// System.err.println("App E: " + s.E);
    	// System.err.println("App vl: " + vl);
    	// System.err.println("App vr: " + vr);
    	
    	
    	FunValue fl = null;
    	if(!(vl instanceof FunValue)) throw new RuntimeError("runtime error app");
    	else {
    		fl = (FunValue)vl;
    	}
    	
    	// System.err.println("App fl: " + fl.x);
    	
    	Env enew = new Env(fl.E, fl.x, vr);
    	State snew = State.of(enew, s.M, s.p);
    	Value ret = fl.e.eval(snew);
        return ret;
    }
}
