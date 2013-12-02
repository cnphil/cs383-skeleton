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

public class Not extends UnaryExpr {

    public Not(Expr e) {
        super(e);
    }

    public String toString() {
        return "(not " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult tre = e.typecheck(E);
    	
    	Substitution sofar = tre.t.unify(Type.BOOL).compose(tre.s);
    	
        return TypeResult.of(sofar, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
    	Value myv = e.eval(s);
    	if(!(myv instanceof BoolValue)) throw new RuntimeError("runtime error: not");
    	
        return new BoolValue(!(((BoolValue)myv).b));
    }
}
