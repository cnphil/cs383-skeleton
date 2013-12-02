package simpl.typing;

public final class ArrowType extends Type {

    public Type t1, t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO - ed
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO - ed
    	if(t instanceof TypeVar) {
    		return t.unify(this);
    	}
    	if(t instanceof ArrowType) {
    		Substitution s1 = this.t1.unify(((ArrowType)t).t1);
    		
    		//System.err.println("ArrowType: " + s1.apply(this.t2));
    		//System.err.println("ArrowType: " + s1.apply(((ArrowType)t).t2));
    		
    		Substitution s2 = s1.apply(this.t2).unify(s1.apply(((ArrowType)t).t2));
    		return s1.compose(s2);
    	}
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO - ed
        return this.t1.contains(tv) || this.t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO - ed
        return new ArrowType(this.t1.replace(a, t), this.t2.replace(a, t));
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
