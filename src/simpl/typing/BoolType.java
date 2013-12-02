package simpl.typing;

final class BoolType extends Type {

    protected BoolType() {
    }

    @Override
    public boolean isEqualityType() {
        // TODO - ed
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO - ed
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        if (t instanceof BoolType) {
            return Substitution.IDENTITY;
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO - ed
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO - ed
        return Type.BOOL;
    }

    public String toString() {
        return "bool";
    }
}
