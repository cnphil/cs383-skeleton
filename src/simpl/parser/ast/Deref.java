package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult tre = e.typecheck(E);
    	
        TypeVar t = new TypeVar(true);
        
        RefType myref = new RefType(t);
        
        Substitution sofar = tre.s;
        
        sofar = tre.t.unify(myref).compose(sofar);
        
        return TypeResult.of(sofar, sofar.apply(t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        Value v = e.eval(s);
        if(!(v instanceof RefValue)) throw new RuntimeError("runtime error deref1");
        RefValue rv = (RefValue)v;
        if(s.M.containsKey(rv.p)) {
        	return s.M.get(rv.p);
        } else {
        	throw new RuntimeError("runtime error deref2");
        }
    }
}
