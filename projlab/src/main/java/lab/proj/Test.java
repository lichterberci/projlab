package lab.proj;

public class Test implements ITest{
	@Override
	public void foo() {
		if (Math.random() < 0.5f)
			return;
		foo();
	}
}
