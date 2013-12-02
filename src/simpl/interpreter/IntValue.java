package simpl.interpreter;

public class IntValue extends Value {

    public final int n;

    public IntValue(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    @Override
    public boolean equals(Object other) {
        // TODO - ed
    	if(other instanceof IntValue) {
    		IntValue snd = (IntValue)other;
    		return this.n == snd.n;
    	}
        return false;
    }
}
