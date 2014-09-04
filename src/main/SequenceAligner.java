package main;

public class SequenceAligner {

	private String x;
	private String y;

	private int gCost = -2;
	private int mCost = 1;
	private int bCost = -1;
	private Case[][] matchings;

	private int totalPen;
	private String res1;
	public int getTotalPen() {
		return totalPen;
	}


	public String getRes1() {
		return res1;
	}


	public String getRes2() {
		return res2;
	}


	private String res2;

	public int alpha(char x,char y){
		return x == y ? mCost : bCost;
	}


	public void align(){

		matchings = initializeMatchingArray(x, y);

		for(int j = 1; j < x.length()+1; j++){
			for(int i = 1; i < y.length()+1; i++){
				char a = x.charAt(j-1);
				char b = y.charAt(i-1);
				Case optimal = Case.max(Case.max(
						new Case(1, alpha(a,b) + matchings[j-1][i-1].getP()),
						new Case(2, gCost + matchings[j-1][i].getP())),
						new Case(3, gCost + matchings[j][i-1].getP()));
				matchings[j][i] = optimal;
			}
		}

		this.totalPen = matchings[x.length()][y.length()].getP();

		gatherAlignment(x, y);

	}

	private void gatherAlignment(String x, String y){
		int i = x.length();
		int j = y.length();

		StringBuilder strx = new StringBuilder();
		StringBuilder stry = new StringBuilder();

		while(i != 0 && j !=0){
			Case c = matchings[i][j];
			if(c.getC() == 1){
				strx.append(x.charAt(i-1));
				stry.append(y.charAt(j-1));
				i--;
				j--;
			}else if(c.getC() == 2){
				strx.append(x.charAt(i-1));
				stry.append("_");
				i--;
			}else if(c.getC() == 3){
				strx.append("_");
				stry.append(y.charAt(j-1));
				j--;
			}
		}

		while(i!=0){
			strx.append(x.charAt(i-1));
			stry.append("_");
			i--;
		}

		while(j!=0){
			strx.append(y.charAt(j-1));
			stry.append("_");
			j--;
		}

		this.res1 = strx.reverse().toString();
		this.res2 = stry.reverse().toString();


	}


	private Case[][] initializeMatchingArray(String x, String y) {
		Case[][] matchings = new Case[x.length()+1][y.length()+1];
		for(int i = 0; i < x.length()+1; i++){
			matchings[i][0] = new Case(-1, i*gCost);
		}
		for(int i = 0; i < y.length()+1; i++){
			matchings[0][i] = new Case(-1, i*gCost);
		}

		return matchings;
	}



	public static void main(String[] args){
		SequenceAligner sa = new SequenceAligner();
		sa.setStrings("THISISASTRINGGOINGFORWARDSLEL", "LELSDRAWROFGNIOGGNIRTSASISIHT");
		sa.align();
		System.out.println(sa.res1);
		System.out.println(sa.res2);
		System.out.println(sa.totalPen);

	}


	public void setStrings(String string1, String string2) {
		this.x = string1;
		this.y = string2;
	}
}
