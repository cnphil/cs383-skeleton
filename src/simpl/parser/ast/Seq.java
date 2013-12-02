package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult trl = l.typecheck(E);
    	TypeResult trr = r.typecheck(trl.s.compose(E));
    	
        return TypeResult.of(trr.s.compose(trl.s), trr.t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        Value vl = l.eval(s);
        Value vr = r.eval(s);
        
        return vr;
    }
}
