package mission1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Interpreter implements InterpreterInterface {

    private Stack<String> memory = new MyStack<>(); // pile stockant les nombres à traiter
    private Map<String, Double> def = new HashMap(); //Map stockant les définitions => def.get(Key) retourne la valeur associé à la clé
    private final static String [] keyword = {"pstack", "add", "sub", "mul", "div", "dup","exch", "eq", "ne", "def", "pop"};

    @Override
    public String interpret(String instructions) {
        String stringretour = "";
        instructions = instructions.toLowerCase(); //met toutes les instructions en minuscule
        instructions = instructions.replace("\n", " "); // retire les retours à la ligne des instructions
        String[] str = instructions.split(" ");
        int i;
        for (i = 0; i < str.length; i++)
        {
            if (isDouble(str[i]))
            {
                memory.push(str[i]);
            }
            else if (str[i].equals("pstack"))
            {
                pstack();
            }
            else if (str[i].equals("add"))
            {
                simpleCalculate(1);
            }
            else if (str[i].equals("sub"))
            {
                simpleCalculate(2);
            }
            else if (str[i].equals("mul"))
            {
                simpleCalculate(3);
            }
            else if (str[i].equals("div"))
            {
                simpleCalculate(4);
            }
            else if (str[i].equals("dup"))
            {
                memory.push(memory.peek());
            }
            else if (str[i].equals("exch"))
            {
                String num1 = memory.pop();
                String num2 = memory.pop();
                memory.push(num1);
                memory.push(num2);
            }
            else if (str[i].equals("eq"))
            {
                simpleCalculate(5);
            }
            else if (str[i].equals("ne"))
            {
                simpleCalculate(6);
            }
            else if (str[i].equals("def"))
            {
                def(str[i-2]); // TODO vérifier que l'on ne sort pas du tableau
            }
            else if (str[i].equals("pop"))
            {
                pop();
            }
            else if(def.containsKey(str[i])) //Si la clé existe, alors on met sa valeur sur la pile
            {
                memory.push(def.get(str[i]).toString());
            }
        }

        return stringretour;

    }

    /*
     * Extrait de la mémoire les arguments pour les fonctions de calcul
     * et effectue le calcul
     */
    private void simpleCalculate(int operation)
    {
        String num1 = memory.pop();
        String num2 = memory.pop();
        Double d1 = 0.0,d2 = 0.0;

                /*
                 * Essaye si on sait transformer num1 et num2 en double. Si on ne sait pas le faire,
                 * On envoi un message d'erreur sur le terminal
                 */
        try
        {
            d1 = Double.parseDouble(num1);
            d2 = Double.parseDouble(num2);
        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
        }
        switch (operation)
        {
            case 1: add(d1,d2);
                break;
            case 2: sub(d1,d2);
                break;
            case 3: mul(d1,d2);
                break;
            case 4: div(d1,d2);
                break;
            case 5: eq(d1,d2);
                break;
            case 6: ne(d1,d2);
                break;
            default: System.err.println("Error, invalid operator");
                break;
        }
    }

    private boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

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

    private double add(double i, double j) {
        return 0;
    }

    private double sub(double i, double j) {
        return 0;
    }

    private double mul(double i, double j) {
        return 0;
    }

    private double div(double i, double j) {
        return 0;
    }

    private void dup() {

    }

    private void exch() {

	}

	private boolean eq(double i, double j) { //TODO!!! utiliser la définition de la fonction pas la changer
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

    private boolean ne(double i, double j) {
        return false;
    }

    private boolean def(String key) {

        //si la clé est un mot clé utilisé par le programme, retourne une erreur
        if(Arrays.asList(keyword).contains(key))
        {
            return false;
        }
        else
        {
            key = key.substring(1);// retire le caractère "\" de la string
            Double value = Double.parseDouble(memory.pop());
            def.put(key,value);
            return true;
        }
    }

    private void pop() {
        memory.pop();
    }
}
