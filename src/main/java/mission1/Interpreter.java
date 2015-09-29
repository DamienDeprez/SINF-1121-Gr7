package mission1;

import java.util.HashMap;
import java.util.Map;

public class Interpreter implements InterpreterInterface {

    private Stack<Double> memory = new MyStack<>(); // pile stockant les nombres à traiter
    private Map<String, Double> def = new HashMap(); //Map stockant les définitions => def.get(Key) retourne la valeur associé à la clé

    @Override
    public String interpret(String instructions) {
        String stringretour = null;
        String[] str = instructions.split(" ");
        int i;
        for (i = 0; i < str.length; i++) {
            if (isDouble(str[i])) {
                memory.push(Double.parseDouble(str[i]));
            } else if (str[i].equals("pstack")) {
            } else if (str[i].equals("add")) {
            } else if (str[i].equals("sub")) {
            } else if (str[i].equals("mul")) {
            } else if (str[i].equals("div")) {
            } else if (str[i].compareTo("dup") == 0) {
                Double newint = memory.peek();
                memory.push(newint);
            } else if (str[i].compareTo("exch") == 0) {
                Double int1 = memory.pop();
                Double int2 = memory.pop();
                memory.push(int1);
                memory.push(int2);

            } else if (str[i].equals("eq")) {
            } else if (str[i].equals("ne")) {
            } else if (str[i].equals("def")) {
            } else if (str[i].equals("pop")) {
            }
        }

        return stringretour;

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
        Stack<Double> mystackbis = memory;
        if (mystackbis == null) {
            System.out.print("");
        } else {
            while (mystackbis != null) {
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

    private boolean eq(double i, double j) {
        return false;
    }

    private boolean ne(double i, double j) {
        return false;
    }

    private void def() {

    }

    private void pop() {
        memory.pop();
    }
}
