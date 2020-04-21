import java.time.LocalDate;

// Austin Rathje

public class Contract {
	// class attributes
	private int contractId;
	private LocalDate launchDate;
	private String rocket;
	private String launchProvider;
	private String beneficiary;
	private double cost;
	
	/**
	 * default no arguments constructor
	 * @param id
	 */
	public Contract(int id) {
		this.contractId = id;
	}

	/**
	 * @param rocket
	 * @param launchProvider
	 * @param beneficiary
	 * @param cost
	 */
	public Contract(String rocket, String launchProvider, String beneficiary, double cost) {
		this.rocket = rocket;
		this.launchProvider = launchProvider;
		this.beneficiary = beneficiary;
		this.cost = cost;
	}
	
	/**
	 * @param id
	 * @param rocket
	 * @param launchProvider
	 * @param beneficiary
	 * @param cost
	 */
	public Contract(int id, String rocket, String launchProvider, String beneficiary, double cost) {
		this.contractId = id;
		this.rocket = rocket;
		this.launchProvider = launchProvider;
		this.beneficiary = beneficiary;
		this.cost = cost;
	}
	
	/**
	 * @param launchDate
	 * @param rocket
	 * @param launchProvider
	 * @param beneficiary
	 * @param cost
	 */
	public Contract(LocalDate launchDate, String rocket, String launchProvider, String beneficiary,
			double cost) {
		this.launchDate = launchDate;
		this.rocket = rocket;
		this.launchProvider = launchProvider;
		this.beneficiary = beneficiary;
		this.cost = cost;
	}
	
	/**
	 * @param contractId
	 * @param launchDate
	 * @param rocket
	 * @param launchProvider
	 * @param beneficiary
	 * @param cost
	 */
	public Contract(int contractId, LocalDate launchDate, String rocket, String launchProvider, String beneficiary,
			double cost) {
		this.contractId = contractId;
		this.launchDate = launchDate;
		this.rocket = rocket;
		this.launchProvider = launchProvider;
		this.beneficiary = beneficiary;
		this.cost = cost;
	}

	/**
	 * @return the launchDate
	 */
	public LocalDate getLaunchDate() {
		return launchDate;
	}

	/**
	 * @param launchDate the launchDate to set
	 */
	public void setLaunchDate(LocalDate launchDate) {
		this.launchDate = launchDate;
	}

	/**
	 * @return the rocket
	 */
	public String getRocket() {
		return rocket;
	}

	/**
	 * @param rocket the rocket to set
	 */
	public void setRocket(String rocket) {
		this.rocket = rocket;
	}

	/**
	 * @return the launchProvider
	 */
	public String getLaunchProvider() {
		return launchProvider;
	}

	/**
	 * @param launchProvider the launchProvider to set
	 */
	public void setLaunchProvider(String launchProvider) {
		this.launchProvider = launchProvider;
	}

	/**
	 * @return the beneficiary
	 */
	public String getBeneficiary() {
		return beneficiary;
	}

	/**
	 * @param beneficiary the beneficiary to set
	 */
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	
	/**
	 * @return the contractId
	 */
	public int getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	@Override
	public String toString() {
		return contractId + " | " + launchDate + " | " + rocket + " | " + launchProvider
				+ " | " + beneficiary + " | $" + cost;
	}

}
