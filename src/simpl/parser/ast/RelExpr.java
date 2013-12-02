package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult trl = l.typecheck(E);
    	TypeResult trr = r.typecheck(trl.s.compose(E));
    	
    	Substitution sofar = trr.s.compose(trl.s);
    	sofar = sofar.apply(trl.t).unify(Type.INT).compose(sofar);
    	sofar = sofar.apply(trr.t).unify(Type.INT).compose(sofar);
    	
        return TypeResult.of(sofar, Type.BOOL);
    }
}
