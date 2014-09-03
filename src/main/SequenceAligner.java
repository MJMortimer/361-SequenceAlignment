package main;

public class SequenceAligner {

	private int gCost = -2;
	private int mCost = 1;
	private int bCost = -1;


	public void alignment(String x, String y){

		int[][] matchings = initializeMatchingArray(x, y);


	}



	private int[][] initializeMatchingArray(String x, String y) {
		int[][] matchings = new int[x.length()][y.length()];
		for(int i = 0; i < x.length(); i++){
			matchings[i][0] = gCost;
		}
		for(int i = 0; i < y.length(); i++){
			matchings[0][i] = gCost;
		}

		return matchings;
	}



	public static void main(String[] args){
		SequenceAligner sa = new SequenceAligner();
		sa.alignment("good", "bad");

	}
}
