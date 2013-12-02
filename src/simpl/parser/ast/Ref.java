package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult tre = e.typecheck(E);
    	RefType myref = new RefType(tre.t);
    	
        return TypeResult.of(tre.s, myref);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	int thisp = s.p.get();
        s.p.set(thisp + 1);
        Value v = e.eval(s);
        s.M.put(thisp, v);
        RefValue ret = new RefValue(thisp);
        return ret;
    }
}
