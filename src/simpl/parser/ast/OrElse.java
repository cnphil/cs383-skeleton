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

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
    	TypeResult tr1 = l.typecheck(E);
    	TypeResult tr2 = r.typecheck(tr1.s.compose(E));
    	
    	Substitution sofar = tr2.s.compose(tr1.s);
    	
    	sofar = sofar.apply(tr1.t).unify(Type.BOOL).compose(sofar);
    	
    	sofar = sofar.apply(tr2.t).unify(Type.BOOL).compose(sofar);
    	
    	return TypeResult.of(sofar, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        Value vl = l.eval(s);
        if(!(vl instanceof BoolValue)) throw new RuntimeError("runtime error: orelse");
        BoolValue bvl = (BoolValue)vl;
        
        if(bvl.b) {
        	return new BoolValue(true);
        } else {
        	Value ret = r.eval(s);
        	return ret;
        }
    }
}
