package simpl.typing;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        // TODO - ed
        return t.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO - ed
    	if(t instanceof TypeVar) {
    		return t.unify(this);
    	}
    	if(t instanceof RefType) {
    		return this.t.unify(((RefType)t).t);
    	}
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO - ed
        return this.t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO - ed
        return new RefType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " ref";
    }
}
