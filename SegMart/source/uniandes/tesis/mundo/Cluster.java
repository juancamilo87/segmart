package uniandes.tesis.mundo;

import java.util.ArrayList;

/**
 * @author Cami
 * 
 */
public class Cluster {
	

	/**
	 * 
	 */
	ArrayList<String> carSig;
	/**
	 * 
	 */
	String[] avgCar;
	/**
	 * 
	 */
	ArrayList<String> dispCar;
	/**
	 * 
	 */
	String[] namGen;
	/**
	 * 
	 */ 
	String[] avgGen;
	/**
	 * 
	 */
	String[] namProb;
	/**
	 * 
	 */
	String[] avgProb;

	/**
	 * @param carSig
	 * @param avgCar
	 * @param dispCar
	 * @param namGen
	 * @param avgGen
	 * @param namProb
	 * @param avgProb
	 */
	public Cluster(ArrayList<String> carSig, String[] avgCar, ArrayList<String> dispCar,
			String[] namGen, String[] avgGen, String[] namProb, String[] avgProb) {
		super();
		this.carSig = carSig;
		this.avgCar = avgCar;
		this.dispCar = dispCar;
		this.namGen = namGen;
		this.avgGen = avgGen;
		this.namProb = namProb;
		this.avgProb = avgProb;
	}

	public ArrayList<String> getCarSig() {
		return carSig;
	}

	public String[] getAvgCar() {
		return avgCar;
	}

	public ArrayList<String> getDispCar() {
		return dispCar;
	}

	public String[] getNamGen() {
		return namGen;
	}

	public String[] getAvgGen() {
		return avgGen;
	}

	public String[] getNamProb() {
		return namProb;
	}

	public String[] getAvgProb() {
		return avgProb;
	}

	

}
