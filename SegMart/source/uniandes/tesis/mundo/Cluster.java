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
	ArrayList<String> avgCar;
	/**
	 * 
	 */
	ArrayList<String> dispCar;
	/**
	 * 
	 */
	ArrayList<String> namGen;
	/**
	 * 
	 */ 
	ArrayList<String> avgGen;
	/**
	 * 
	 */
	ArrayList<String> namProb;
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
	public Cluster(ArrayList<String> carSig, ArrayList<String> avgCar, ArrayList<String> dispCar,
			ArrayList<String> namGen, ArrayList<String> avgGen, ArrayList<String> namProb, String[] avgProb) {
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

	public ArrayList<String> getAvgCar() {
		return avgCar;
	}

	public ArrayList<String> getDispCar() {
		return dispCar;
	}

	public ArrayList<String> getNamGen() {
		return namGen;
	}

	public ArrayList<String> getAvgGen() {
		return avgGen;
	}

	public ArrayList<String> getNamProb() {
		return namProb;
	}

	public String[] getAvgProb() {
		return avgProb;
	}

	

}
