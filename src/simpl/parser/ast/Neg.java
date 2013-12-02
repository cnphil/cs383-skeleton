package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult tre = e.typecheck(E);
    	Substitution sofar = tre.t.unify(Type.INT).compose(tre.s);
    	
        return TypeResult.of(sofar, Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value myv = e.eval(s);
    	if(!(myv instanceof IntValue)) throw new RuntimeError("runtime error: neg");
    	
    	IntValue intv = (IntValue)myv;
    	IntValue ret = new IntValue(-intv.n);
        return ret;
    }
}
