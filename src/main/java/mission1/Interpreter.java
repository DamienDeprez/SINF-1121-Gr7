package mission1;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Interpreter implements InterpreterInterface {

    private MyStack<Object> memory = new MyStack<Object>(); // pile stockant les nombres à traiter
    private Map<String, Object> def = new HashMap(); //Map stockant les définitions => def.get(Key) retourne la valeur associé à la clé
    private final static String [] keyword = {"pstack", "add", "sub", "mul", "div", "dup","exch", "eq", "ne", "def", "pop"};
    private String display="";

    @Override
    public String interpret(String instructions) {
        display="";
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
            if(Arrays.asList(keyword).contains(str[i]))
            {
                try
                {
                    m = c.getMethod(str[i]);
                    m.invoke(this);
                }
                catch(IllegalAccessException | NoSuchMethodException | SecurityException f)
                {
                    f.printStackTrace();
                }
                catch(InvocationTargetException e)
                {
                    if(e.getCause() instanceof ArithmeticException)
                    {
                        throw new ArithmeticException(e.getCause().getMessage());
                    }
                    else if(e.getCause() instanceof EmptyStackException)
                    {
                        throw new EmptyStackException();
                    }
                }
            }
            else
            {
            	mem(str[i]);
            }
        }
        if(display.length()-1>0 && display.charAt(display.length()-1)==' ')
        {
            display = display.substring(0,display.length()-1);
        }
        return this.display;
    }

    /**
     * met en mémoire l'argument a sous forme d'un Integer si a représente un int,
     * d'un Double si a représente un double et d'un String sinon
     * @param a la string a analyser
     */
    private void mem(String a)
    {
        Object var;
        try
        {
            var = Integer.parseInt(a);
        }
        catch (NumberFormatException e)
        {
            try
            {
                var = Double.parseDouble(a);
            }
            catch(NumberFormatException f)
            {
                var = a;
            }
        }
        memory.push(var);

    }

    /**
     * Converti la string en nombre et si ce n'est pas un nombre, regarde si elle existe dans les definitions
     * @return les arguments s
     * @throw lance une exception si arg n'est pas un double et ne se trouve pas dans la liste de defintion.
     */
    private void convertType(int operation)
    {
        Object var1 = memory.pop();
        Object var2 = memory.pop();

        //----Check si défintion----
        if(var1 instanceof String && def.containsKey(var1))
        {
            var1=def.get(var1);
        }
        if(var2 instanceof String && def.containsKey(var2))
        {
            var2 = def.get(var2);
        }

        //----Convertion de type (2 int -> int dès qu'il y a un double -> double)
        if(var1 instanceof Integer && var2 instanceof Integer)
        {
            int a = (int) var1;
            int b = (int) var2;
            ComputeInt(operation,b,a);
        }
        else
        {
            double a,b;
            if(var1 instanceof Integer)
            {
                a = (double) ((int) var1);
            }
            else
            {
                a =(double) var1;
            }
            if(var2 instanceof Integer)
            {
                b = (double) ((int) var2);
            }
            else
            {
                b = (double) var2;
            }
            ComputeDouble(operation,b,a);
        }
    }

    private void ComputeInt(int operation,int a, int b)
    {
        switch (operation)
        {
            case 0 :
                memory.push(a+b);
                break;
            case 1 :
                memory.push(a-b);
                break;
            case 2:
                memory.push(a*b);
                break;
            case 3:
                memory.push(a/b);
                break;
            default:
                break;
        }
    }

    private void ComputeDouble (int operation, double a, double b)
    {
        switch (operation)
        {
            case 0 :
                memory.push(a+b);
                break;
            case 1 :
                memory.push(a-b);
                break;
            case 2:
                memory.push(a*b);
                break;
            case 3:
                memory.push(a/b);
                break;
            default:
                break;
        }
    }

    /**
     * Imprime le contenu de la stack dans la string display
     */
    public void pstack() {
        MyStack<Object> memory_copy = new MyStack<>();
        while (!memory.empty()) {
            String toDisplay = memory.pop().toString();
            memory_copy.push(toDisplay);
            if (def.containsKey(toDisplay))
            {
                    display = display + def.get(toDisplay)+" ";
            }
            else {
                display = display+toDisplay+" ";
            }
        }
        while(!memory_copy.empty())
        {
            memory.push(memory_copy.pop());
        }
    }

    /**
     * effectue l'addition entre les deux éléments du sommet de la pile et ajoute ce résultat à la pile
     */
    public void add()
    {
        convertType(0);
    }

    /**
     * effectue la soustraction entre les deux éléments du sommet de la pile et ajoute ce résultat à la pile
     */
    public void sub() {
        convertType(1);
    }

    /**
     * effectue la multiplication entre les deux éléments du sommet de la pile et ajoute ce résultat à la pile
     */
    public void mul() {
        convertType(2);
    }

    /**
     * effectue la entre les deux éléments du sommet de la pile et ajoute ce résultat à la pile
     * @throws ArithmeticException si le prmier élément de la pile est 0
     */
    public void div() throws ArithmeticException
    {
        convertType(3);
    }

    /**
     * duplique l'élément se trouvant au sommet de la pile
     */
    public void dup() {
        memory.push(memory.peek());

    }

    /**
     * échange de place les deux premiers éléments du sommet de la pile
     */
    public void exch() {
        Object m1 = memory.pop();
        Object m2 = memory.pop();
        memory.push(m1);
        memory.push(m2);
	}

    /**
     * vérifie l'égalité des deux éléments du sommet de la pile
     * @return true si leur représentation sous forme de String son identique false sinon
     */
	public boolean eq() { //TODO!!! utiliser la définition de la fonction pas la changer
        Object var1 = memory.pop();
        Object var2 = memory.pop();

        if(var1 instanceof String && def.containsKey(var1))
        {
            var1=def.get(var1);
        }
        if(var2 instanceof String && def.containsKey(var2))
        {
            var2 = def.get(var2);
        }

        if(var1 instanceof Integer && var2 instanceof Integer)
        {
            Boolean eq = var1==var2;
            memory.push(eq.toString());
            return eq;
        }
        else if(var1 instanceof Double && var2 instanceof Double)
        {
            Boolean eq = var1==var2;
            memory.push(eq.toString());
            return eq;
        }
        else
        {
            memory.push("false");
            return false;
        }

	}

    /**
     * vérifie l'inégalité des deux éléments du sommet de la plie
     * @return false si leur représentation sous forme de String son identique true sinon
     */
    public boolean ne() {
        boolean bool = eq();
        memory.pop();
        if(bool) {
            memory.push("false");
            return false;
        }
        else{
            memory.push("true");
            return true;
        }
    }

    /**
     * crée une définition pour un nombre dans la table des définitions
     * @return true si la définition est correcte, false sinon
     */
    public boolean def() {

        //si la clé est un mot clé utilisé par le programme, retourne une erreur
    	Object value = memory.pop();
    	Object key = memory.pop();
        if(Arrays.asList(keyword).contains(key))
        {
            throw new IllegalArgumentException("the key is a keywoord");
        }
        else
        {
            key = key.toString().substring(1);// retire le caractère "\" de la string
            def.put(key.toString(),value);
            return true;
            
        }
    }

    /**
     * retire l'élément du sommet de la pile
     */
    public void pop() {
        memory.pop();
    }

    class MyStack<E>{

        private Node head;

        public MyStack()
        {
            this.head=null;
        }


        public boolean empty() {
            return head==null;
        }


        public E peek() throws EmptyStackException {
            // TODO Auto-generated method stub
            if(empty())
            {
                throw new EmptyStackException();
            }
            return this.head.data;
        }


        public E pop() throws EmptyStackException {
            // TODO Auto-generated method stub
            if(empty())
            {
                throw new EmptyStackException();
            }
            Node rm = this.head;
            E data = rm.data;
            this.head=rm.next;
            rm.next=null;
            return data;
        }


        public E push(E item) {
            // TODO Auto-generated method stub
            Node add = new Node();
            add.data=item;
            add.next=this.head;
            this.head=add;
            return add.data;
        }

        class Node
        {
            public Node next;
            public E data;

            public Node()
            {
                this.data=null;
                this.next=null;
            }
        }
    }
}
