package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO - ed
    	TypeResult trl = l.typecheck(E);
    	TypeResult trr = r.typecheck(trl.s.compose(E));
    	
    	Substitution sofar = trr.s.compose(trl.s);
    	
    	sofar = sofar.apply(trl.t).unify(trr.t).compose(sofar);
        return TypeResult.of(sofar, Type.BOOL);
    }
}
