package simpl.interpreter;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public boolean equals(Object other) {
        // TODO - ed
    	if (other instanceof BoolValue)
    		if (this.b == ((BoolValue)other).b) return true;
        return false;
    }
}
