package simpl.interpreter;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "pair@" + v1 + "@" + v2;
    }

    @Override
    public boolean equals(Object other) {
        // TODO - ed
    	if (other instanceof PairValue) {
    		PairValue now = (PairValue) other;
    		if ((this.v1.equals(now.v1))&&(this.v2.equals(now.v2))) return true;
    	}
        return false;
    }
}
