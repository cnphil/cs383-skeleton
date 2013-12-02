package simpl.interpreter.pcf;

import simpl.interpreter.BoolValue;
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

public class pred extends FunValue {
	
	private static Symbol pred = Symbol.symbol("pred");
	
	public pred() {
        // TODO
    	super(Env.empty, Symbol.symbol("x"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return TypeResult.of(((ArrowType) (E.get(pred))).t2);
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                IntValue i = (IntValue)s.E.get(Symbol.symbol("x"));
                if (i.n <= 0) return new IntValue(0);
                return new IntValue(i.n - 1);
            }
        });
        //super(null, null, null);
    }
}
