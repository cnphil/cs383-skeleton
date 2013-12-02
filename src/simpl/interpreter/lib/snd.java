package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.ArrowType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class snd extends FunValue {

private static Symbol snd = Symbol.symbol("snd");
	
	
    public snd() {
        // TODO
    	super(Env.empty, Symbol.symbol("x"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return TypeResult.of(((ArrowType) (E.get(snd))).t2);
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                return ((PairValue) s.E.get(Symbol.symbol("x"))).v2;
            }
        });
        //super(null, null, null);
    }
    
}
