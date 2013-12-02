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

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult tr1 = e1.typecheck(E);
    	TypeResult tr2 = e2.typecheck(tr1.s.compose(E));
    	
    	Substitution sofar = tr2.s.compose(tr1.s);
    	sofar = sofar.apply(tr1.t).unify(Type.BOOL).compose(sofar);
    	
    	
        return TypeResult.of(sofar, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        Value v1 = e1.eval(s);
        if(!(v1 instanceof BoolValue)) throw new RuntimeError("runtime error: loop");
        
        BoolValue bv = (BoolValue)v1;
        if(bv.b) {
        	Seq torun = new Seq(this.e2, this);
        	Value ret = torun.eval(s);
        	return ret;
        } else {
        	return Value.UNIT;
        }
    }
}
