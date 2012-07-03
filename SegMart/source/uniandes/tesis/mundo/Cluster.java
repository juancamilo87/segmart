package uniandes.tesis.mundo;

/**
 * @author Cami
 *
 */
public class Cluster {
	
	/**
	 * 
	 */
	String[] carSig;
	/**
	 * 
	 */
	double[] avgCar;
	/**
	 * 
	 */
	double[] dispCar;
	/**
	 * 
	 */
	String[] namGen;
	/**
	 * 
	 */
	double[] avgGen;
	/**
	 * 
	 */
	String[] namProb;
	/**
	 * 
	 */
	double[] avgProb;
	
	/**
	 * @param carSig
	 * @param avgCar
	 * @param dispCar
	 * @param namGen
	 * @param avgGen
	 * @param namProb
	 * @param avgProb
	 */
	public Cluster(String[] carSig, double[] avgCar, double[] dispCar,
			String[] namGen, double[] avgGen, String[] namProb, double[] avgProb) {
		super();
		this.carSig = carSig;
		this.avgCar = avgCar;
		this.dispCar = dispCar;
		this.namGen = namGen;
		this.avgGen = avgGen;
		this.namProb = namProb;
		this.avgProb = avgProb;
	}

	public String[] getCarSig() {
		return carSig;
	}

	public double[] getAvgCar() {
		return avgCar;
	}

	public double[] getDispCar() {
		return dispCar;
	}

	public String[] getNamGen() {
		return namGen;
	}

	public double[] getAvgGen() {
		return avgGen;
	}

	public String[] getNamProb() {
		return namProb;
	}

	public double[] getAvgProb() {
		return avgProb;
	}
	
	

}
