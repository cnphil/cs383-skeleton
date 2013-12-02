package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult tl = l.typecheck(E);
    	TypeResult tr = r.typecheck(tl.s.compose(E));
    	
    	Substitution sofar = tr.s.compose(tl.s);
    	ListType mylist = new ListType(sofar.apply(tl.t));
    	sofar = tr.t.unify(mylist).compose(sofar);
    	
    	
        return TypeResult.of(sofar, sofar.apply(mylist));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value vl = l.eval(s);
    	Value vr = r.eval(s);
    	
        return new ConsValue(vl, vr);
    }
}
