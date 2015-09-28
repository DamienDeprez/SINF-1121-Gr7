package mission1;

import testmission1.InterpreterTests;

public class Interpreter extends InterpreterTests{
	public String interpret(String instructions){
		String stringretour =null;
		String[] str = instructions.split(" ");
		MyStack<Integer> mystack1 = new MyStack<Integer>();
		int i;
		for(i = 0; i < str.length; i++){
			if(isanint(str[i])){
				mystack1.push(Integer.parseInt(str[i]));
			}
			
			else if(str[i].compareTo("pstack")){}
			
			else if(str[i].compareTo("add")){}

			else if(str[i].compareTo("sub")){}
			
			else if(str[i].compareTo("mul")){}
			
			else if(str[i].compareTo("div")){}
			
			else if(str[i].compareTo("dup")){}
			
			else if(str[i].compareTo("exch")){}
			
			else if(str[i].compareTo("eq")){}
			
			else if(str[i].compareTo("ne")){}
			
			else if(str[i].compareTo("def")){}
		
			else if(str[i].compareTo("pop")){}			
		}
		
		return stringretour;
		
	}
	private boolean  isanint(String string) {
		try {
			Integer.parseInt(string);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	private void pstack(Stack<Integer> mystack){
		
	}
	
	private int add(int i, int j){
		
	}
	
	private int sub(int i, int j){
		
	}
	
	private int mul(int i, int j){
		
	}
	
	private int div(int i, int j){
		
	}
	
	private void dup(Stack<Integer> mystack){
		
	}
	
	private void exch(Stack<Integer> mystack){
		
	}
	
	private boolean eq(int i, int j){
		
	}
	
	private boolean ne(int i, int j) {
		
	}
	
	private void def(){
		
	}
	
	private void pop(Stack<Integer> mystack){
		mystack.pop();
	}
}
