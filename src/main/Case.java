package main;

public class Case {


	private int c;
	public int getC() {
		return c;
	}

	public int getR() {
		return r;
	}

	private int r;

	public Case(int c, int p){
		this.c = c;
		this.r = p;
	}

	public static Case max(Case c1, Case c2){
		return c1.r >= c2.r ? c1 : c2;
	}
}
