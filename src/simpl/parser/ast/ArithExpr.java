package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult tr1 = l.typecheck(E);
    	TypeResult tr2 = r.typecheck(tr1.s.compose(E));
    	
    	Substitution sofar = tr2.s.compose(tr1.s);
    	
    	sofar = sofar.apply(tr1.t).unify(Type.INT).compose(sofar);
    	
    	sofar = sofar.apply(tr2.t).unify(Type.INT).compose(sofar);
    	
    	return TypeResult.of(sofar, Type.INT);
    }
}
