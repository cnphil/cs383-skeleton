package simpl.interpreter;

import simpl.parser.Symbol;

public class Env {

    private final Env E;
    private final Symbol x;
    private final Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        // TODO - ed
    	if(x == y) return v;
    	else return this.E.get(y);
    }

    public Env clone() {
        // TODO - ed
        return new Env(this.E.clone(), x, v);
    }
}
