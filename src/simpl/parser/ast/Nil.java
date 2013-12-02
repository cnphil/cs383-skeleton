package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeVar alpha = new TypeVar(true);
    	ListType ans = new ListType(alpha);
    	
    	return TypeResult.of(ans);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO - ed
        return Value.NIL;
    }
}
