package simpl.interpreter;

import static simpl.parser.Symbol.symbol;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(Env.empty), new Mem(), new Int(0));
    }

    private static Env initialEnv(Env E) {
        // TODO - ed?
    	Env newe = new Env(E,Symbol.symbol("fst"),new fst());
    	newe = new Env(newe,Symbol.symbol("snd"),new snd());
    	newe = new Env(newe,Symbol.symbol("hd"),new hd());
    	newe = new Env(newe,Symbol.symbol("tl"),new tl());
    	newe = new Env(newe,Symbol.symbol("iszero"),new iszero());
    	newe = new Env(newe,Symbol.symbol("pred"),new pred());
    	newe = new Env(newe,Symbol.symbol("succ"),new succ());
    	return newe;
    }
}
