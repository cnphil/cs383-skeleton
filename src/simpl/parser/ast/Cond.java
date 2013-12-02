package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult tr1 = e1.typecheck(E);
    	Substitution sofar = tr1.s;
    	TypeResult tr2 = e2.typecheck(sofar.compose(E));
    	sofar = tr2.s.compose(sofar);
    	TypeResult tr3 = e3.typecheck(sofar.compose(E));
    	sofar = tr3.s.compose(sofar);
    	
    	sofar = sofar.apply(tr1.t).unify(Type.BOOL).compose(sofar);
    	sofar = sofar.apply(tr2.t).unify(sofar.apply(tr3.t)).compose(sofar);
    	
    	TypeResult ret = TypeResult.of(sofar, sofar.apply(tr2.t));
    	//System.err.println("Cond: " + sofar);
    	return ret;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value v1 = e1.eval(s);
    	BoolValue bv;
    	if(!(v1 instanceof BoolValue)) throw new RuntimeError("runtime error: cond");
    	bv = (BoolValue)v1;
    	
    	Value ret = null;
    	
    	if(bv.b) {
    		ret = e2.eval(s);
    	} else {
    		ret = e3.eval(s);
    	}
        return ret;
    }
}
