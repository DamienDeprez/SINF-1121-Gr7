package mission1;

import testmission1.InterpreterTests;

public class Interpreter extends InterpreterTests{
	public String interpret(String instructions){
		String stringretour =null;
		String[] str = instructions.split(" ");
		MyStack<Integer> mystack1 = new MyStack<Integer>();
		int i;
		for(int i = 0; i < str.length; i++){
			if(isanint(str[i])){
				
			}
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
}
