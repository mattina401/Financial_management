package cs.android.ddtc.mvp.Iviews;

public interface ISpendingView {
	
	void advance();
	
	void advanceToGraph();
	
	void advanceToUpdate(double f, double gro, double s, double gas, double u, double t);

}

