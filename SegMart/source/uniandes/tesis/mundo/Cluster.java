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
	ArrayList<Double> avgGen;
	/**
	 * 
	 */
	ArrayList<String> namProb;
	/**
	 * 
	 */
	ArrayList<Double> avgProb;

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
			ArrayList<String> namGen, ArrayList<Double> avgGen, ArrayList<String> namProb, ArrayList<Double> avgProb) {
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

	public ArrayList<Double> getAvgGen() {
		return avgGen;
	}

	public ArrayList<String> getNamProb() {
		return namProb;
	}

	public ArrayList<Double> getAvgProb() {
		return avgProb;
	}

	

}
