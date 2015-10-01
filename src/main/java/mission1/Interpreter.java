package mission1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

public class Interpreter implements InterpreterInterface {

    private Stack<String> memory = new MyStack<>(); // pile stockant les nombres à traiter
    private Map<String, Double> def = new HashMap(); //Map stockant les définitions => def.get(Key) retourne la valeur associé à la clé
    private final static String [] keyword = {"pstack", "add", "sub", "mul", "div", "dup","exch", "eq", "ne", "def", "pop"};
    private String display="";

    @Override
    public String interpret(String instructions) {
        Class<? extends Interpreter> c = this.getClass(); //récupère la classe actuelle
        Method m;
        instructions = instructions.toLowerCase(); //met toutes les instructions en minuscule
        instructions = instructions.replace("\n", " "); // retire les retours à la ligne des instructions
        String[] str = instructions.split(" ");
        int i;
        for (i = 0; i < str.length; i++)
        {
            
        	/*
        	 * Si c'est un mot clé, on appele la fonction associée
        	 * Sinon on met la string en mémoire, c'est alors un nombre, un boolean ou une definition
        	 */
            if (Arrays.asList(keyword).contains(str[i]))
            {
                try
                {
                	m=c.getMethod(str[i], null); // recupere la methode nomee str[i]
                	m.invoke(this, null); //execute la methode recuperer ci-dessus
				}
                catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
                {
					e.printStackTrace();
				}
            }
            else
            {
            	memory.push(str[i]);
            }
        }
        return this.display;
    }

    /*
     * Converti la string en nombre et si ce n'est pas un nombre, regarde si elle existe dans les definitions
     * @param arg, string a analyser
     * @return les arguments s
     * @throw lance une exception si arg n'est pas un double et ne se trouve pas dans la liste de defintion.
     */
    private Double toDouble(String arg)
    {
    	Double argD = 0.0;
        try
        {
        	Double.parseDouble(arg);
        }
        catch (NumberFormatException e)
        {
        	if(def.containsKey(arg))
        	{
        		argD=def.get(arg);
        	}
        	else
        	{
        		throw new IllegalArgumentException(arg+" is not an number and doesn't exist in def var");
        	}
        }
        return argD;
    }

    private boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //!!! pstack doit écrire dans la string display et pas a l'écran car iterpreteur retourne le contenu de 
    //display !!!
    private void pstack() {
        Stack<String> mystackbis = memory;
        if (mystackbis == null) {
            System.out.print("");
        } else {
            while (!mystackbis.empty()) {
                System.out.print(mystackbis.pop() + " ");
            }
        }
    }

    private double add() {
        return 0;
    }

    private double sub() {
        return 0;
    }

    private double mul() {
        return 0;
    }

    private double div() {
        return 0;
    }

    private void dup() {

    }

    private void exch() {

	}

	private boolean eq() { //TODO!!! utiliser la définition de la fonction pas la changer
		if (memory.pop() == memory.pop()) {
			memory.push("true");
			return true;
		}
		else {
			memory.pop();
			memory.pop();
			memory.push("false");
			return false;
		}
	}

    private boolean ne() {
        return false;
    }

    private boolean def() {

        //si la clé est un mot clé utilisé par le programme, retourne une erreur
    	Double value = toDouble(memory.pop());
    	String key = memory.pop();
        if(Arrays.asList(keyword).contains(key))
        {
            throw new IllegalArgumentException("the key is a keywoord");
        }
        else
        {
            key = key.substring(1);// retire le caractère "\" de la string
            def.put(key,value);
            return true;
            
        }
    }

    private void pop() {
        memory.pop();
    }
}
