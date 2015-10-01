package testmission1;

import org.junit.Test;

import mission1.Interpreter;

public class InterpreterTests {

	@Test
	public void Test1()
	{
		Interpreter i = new Interpreter();
		i.interpret("/var 1 def var pstack");
	}
}
