package simpl.interpreter.pcf;

import simpl.interpreter.BoolValue;
import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.ArrowType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class iszero extends FunValue {

	private static Symbol iszero = Symbol.symbol("iszero");
	
	
    public iszero() {
        // TODO
    	super(Env.empty, Symbol.symbol("x"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return TypeResult.of(((ArrowType) (E.get(iszero))).t2);
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                IntValue i = (IntValue)s.E.get(Symbol.symbol("x"));
                if (i.n == 0) return new BoolValue(true);
                return new BoolValue(false);
            }
        });
        //super(null, null, null);
    }
}
