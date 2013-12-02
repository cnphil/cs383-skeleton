package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public int count() {
    	if(new NilValue().equals(v2)) {
    		return 1;
    	} else if(v2 instanceof ConsValue) {
    		ConsValue newv2 = (ConsValue)v2;
    		return 1 + newv2.count();
    	} else return 2;
    }
    
    public String toString() {
        // TODO
        return "list@" + this.count();
    }

    @Override
    public boolean equals(Object other) {
        // TODO - ed
    	if (other instanceof ConsValue)
    		if ((v1.equals(((ConsValue)other).v1))&&(v2.equals(((ConsValue)other).v2))) return true;
        return false;
    }
}
