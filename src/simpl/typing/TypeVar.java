package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        // TODO - ed
        if(t != this && t.contains(this)) {
        	throw new TypeCircularityError();
        }
        //System.err.println("Substitution: " + this.name + " : " + t);
        return Substitution.of(this, t);
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO - ed
        return (this.name == tv.name);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO - ed
        if(this.name == a.name) {
        	return t;
        } else {
        	return this;
        }
    }
}
