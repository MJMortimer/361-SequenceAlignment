package main;

public class SequenceAligner {

	private int gCost = -2;
	private int mCost = 1;
	private int bCost = -1;

	public int alpha(char x,char y){
		return x == y ? mCost : bCost;
	}


	public void alignment(String x, String y){

		int[][] matchings = initializeMatchingArray(x, y);

		for(int j = 1; j < x.length()+1; j++){
			for(int i = 1; i < y.length()+1; i++){
				char a = x.charAt(j-1);
				char b = y.charAt(i-1);
				int optimal = Math.max(Math.max(alpha(a,b) + matchings[j-1][i-1], gCost + matchings[j-1][i]), gCost + matchings[j][i-1]);
				matchings[j][i] = optimal;
			}
		}

		System.out.println(matchings[x.length()][y.length()]);


	}


	private int[][] initializeMatchingArray(String x, String y) {
		int[][] matchings = new int[x.length()+1][y.length()+1];
		for(int i = 0; i < x.length()+1; i++){
			matchings[i][0] = i*gCost;
		}
		for(int i = 0; i < y.length()+1; i++){
			matchings[0][i] = i*gCost;
		}

		return matchings;
	}



	public static void main(String[] args){
		SequenceAligner sa = new SequenceAligner();
		sa.alignment("GATCGGCAT", "CAATGTGAATC");

	}
}
