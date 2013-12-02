package simpl.interpreter.pcf;

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

public class succ extends FunValue {

	private static Symbol succ = Symbol.symbol("succ");
	
	public succ() {
        // TODO
    	super(Env.empty, Symbol.symbol("x"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return TypeResult.of(((ArrowType) (E.get(succ))).t2);
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                IntValue i = (IntValue)s.E.get(Symbol.symbol("x"));
                return new IntValue(i.n + 1);
            }
        });
        //super(null, null, null);
    }
}

