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

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
        TypeResult tl = l.typecheck(E);
        TypeResult tr = r.typecheck(tl.s.compose(E));
        
        RefType myref = new RefType(tr.t);
        
        Substitution sofar = tr.s.compose(tl.s);
        sofar = sofar.apply(tl.t).unify(myref).compose(sofar);
    	return TypeResult.of(sofar, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        Value v1 = l.eval(s);
        if(!(v1 instanceof RefValue)) throw new RuntimeError("runtime error: assign");
        Value v2 = r.eval(s);
        
        RefValue rv = (RefValue)v1;
        s.M.put(rv.p, v2);
        return Value.UNIT;
    }
}
