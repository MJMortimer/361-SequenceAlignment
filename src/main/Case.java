package main;

public class Case {


	private int c;
	public int getC() {
		return c;
	}

	public int getP() {
		return p;
	}

	private int p;

	public Case(int c, int p){
		this.c = c;
		this.p = p;
	}

	public static Case max(Case c1, Case c2){
		return c1.p > c2.p ? c1 : c2;
	}
}
