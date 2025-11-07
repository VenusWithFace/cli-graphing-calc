import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class parse {
	static {
  System.setProperty("nashorn.args","--no-deprecation-warning");
	}
	public static void parse(String arg) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		
		StringBuilder str = new StringBuilder(arg);
		str.insert(0, '(');
		str.insert(str.length(),')');
		for (byte i = 0;i<str.length();i++) {
			if (str.charAt(i)=='s' && str.charAt(i+1)=='i' && str.charAt(i+2)=='n') {
				str.insert(i,"Math.");
				//System.out.println("lalala");
				i+=5;
			};
			if (str.charAt(i)=='c' && str.charAt(i+1)=='o' && str.charAt(i+2)=='s') {
				str.insert(i,"Math.");
				//System.out.println("lalala");
				i+=5;
			};
			if (str.charAt(i)=='t' && str.charAt(i+1)=='a' && str.charAt(i+2)=='n') {
				str.insert(i,"Math.");
				//System.out.println("lalala");
				i+=5;
			};
			//System.out.println(str);
		};
		try {
			System.out.println(engine.eval(str.toString()));
		} catch (ScriptException var) {
			System.out.println(var);
		};
	};
};