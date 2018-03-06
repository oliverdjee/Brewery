package Grain;

public class Malt {

	private String Name;
	private Double Contribution;
	private double MaxPercentage;
	private Double Qty;
	private double OGperKg;
	private Boolean OverMaxPercent;
	private Cereal Type;
	public Malt(Cereal GrainType)
	{
		Contribution = null; 
		MaxPercentage = GrainType.MaxPercentage;
		Qty = null;
		OGperKg = GrainType.OGperKg;
		setName(GrainType.Name);
		OverMaxPercent = null;
	}
	public double getContribution() {
		Contribution = ComputeContribution();
		return Contribution;
	}

	public double getMaxPercentage() {
		return MaxPercentage;
	}

	public double getQuantity() {
		return Qty;
	}

	public String getGrainType() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getOGperKg() {
		return OGperKg;
	}

	public Cereal getType() {
		return Type;
	}
	public void setType(Cereal type) {
		Type = type;
	}
	public String getName() {
		return Name;
	}
	private void setName(String name) {
		Name = name;
	}
	public double ComputeContribution() {
		return 1+(Qty*(OGperKg-1));
	}

	public void setQuantity(double QtyKg) {
		Qty = QtyKg;
	}

	public boolean isOverMax() {
		return OverMaxPercent;
	}
	
	public void setOverMax(boolean OverMax) {
		OverMaxPercent = OverMax;
	}

}
