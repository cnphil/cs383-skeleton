package simpl.interpreter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;

public class Interpreter {

    public void run(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;
            program.typecheck(new DefaultTypeEnv());
            System.out.println(program.eval(new InitialState()));
        }
        catch (SyntaxError e) {
            System.out.println("syntax error");
        }
        catch (TypeError e) {
            System.out.println("type error");
        }
        catch (RuntimeError e) {
            System.out.println("runtime error");
        	// System.out.println(e);
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void interpret(String filename) {
        Interpreter i = new Interpreter();
        // System.out.println(filename);
        i.run(filename);
    }

    public static void main(String[] args) {
    	
    	/*System.setErr(new PrintStream(new OutputStream() {
    	    public void write(int b) {
    	    }
    	}));*/
    	
    	interpret(args[0]);
    	
        /*
        interpret("doc/examples/myexample.spl");
        
       
        interpret("doc/examples/plus.spl");
        interpret("doc/examples/factorial.spl");
        interpret("doc/examples/gcd1.spl");
        interpret("doc/examples/gcd2.spl");
        interpret("doc/examples/max.spl");
        interpret("doc/examples/sum.spl");
        interpret("doc/examples/map.spl");
        interpret("doc/examples/pcf.sum.spl");
        interpret("doc/examples/pcf.even.spl");
        interpret("doc/examples/pcf.minus.spl");
        interpret("doc/examples/pcf.factorial.spl");
        interpret("doc/examples/pcf.fibonacci.spl");*/
        
        
        // interpret("doc/examples/pcf.twice.spl");
        // interpret("doc/examples/pcf.lists.spl");
    }
}
