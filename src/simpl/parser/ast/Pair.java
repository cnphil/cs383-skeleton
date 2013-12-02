package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.PairType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult trl = l.typecheck(E);
    	TypeResult trr = r.typecheck(trl.s.compose(E));
    	
    	Substitution sofar = trr.s.compose(trl.s);
    	PairType mypair = new PairType(sofar.apply(trl.t), trr.t);
    	
        return TypeResult.of(sofar, mypair);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value vl = l.eval(s);
    	Value vr = r.eval(s);
    	
        return new PairValue(vl, vr);
    }
}
