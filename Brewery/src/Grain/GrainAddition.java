package Grain;

import java.util.ArrayList;

public class GrainAddition {
	private Double TargetOG;
	private ArrayList<Malt> GrainBill;
	private double TotalQty;
	private ArrayList<Boolean> Warnings;
	private double CurrentOG;
	private boolean OverTarget;
	private Double BatchVolume;
	
	public GrainAddition(double volume)
	{
		setTargetOG(1.056);
		setGrainBill(new ArrayList<Malt>());
		setTotalQty(0.0);
		setWarnings(new ArrayList<Boolean>());
		setCurrentOG(1.000);
		OverTarget = false;
		setBatchVolume(volume);
	}

	public Double getTargetOG() {
		return TargetOG;
	}

	public void setTargetOG(Double targetOG) {
		TargetOG = targetOG;
	}

	public ArrayList<Malt> getGrainBill() {
		return GrainBill;
	}

	public void setGrainBill(ArrayList<Malt> grainBill) {
		GrainBill = grainBill;
	}

	public double getTotalQty() {
		return TotalQty;
	}

	public void setTotalQty(double totalQty) {
		TotalQty = totalQty;
	}

	public ArrayList<Boolean> getWarnings() {
		return Warnings;
	}

	public void setWarnings(ArrayList<Boolean> warnings) {
		Warnings = warnings;
	}
	
	public double getCurrentOG() {
		return CurrentOG;
	}

	public void setCurrentOG(double currentOG) {
		CurrentOG = currentOG;
	}

	public boolean isOverTarget() {
		return OverTarget;
	}

	public void setOverTarget(boolean overTarget) {
		OverTarget = overTarget;
	}

	public Double getBatchVolume() {
		return BatchVolume;
	}

	public void setBatchVolume(Double batchVolume) {
		BatchVolume = batchVolume;
		ComputeBill();
	}

	public void AddMalt(Malt malt)
	{
		GrainBill.add(malt);
		double ogperkg = (malt.getOGperKg()-1)*20/BatchVolume;
		double target = this.TargetOG-1;
		double current = this.CurrentOG-1;
		double missing = target - current;
		double kg = missing/ogperkg;
		if(kg < 0)
		{
			kg = 0;
		}
		malt.setQuantity(kg);
		ComputeBill();
	}
	
	public void setQty(Malt malt, double QtyKg)
	{
		malt.setQuantity(QtyKg);
		ComputeBill();
	}
	
	private void ComputeBill() 
	{
		this.TotalQty = 0;
		CurrentOG = 0;
		for(Malt grain:GrainBill)
		{
			TotalQty += grain.getQuantity();
			CurrentOG += (grain.ComputeContribution() - 1)*20/BatchVolume;
		}
		CurrentOG += 1;
		VerifyMaltPercentages();
		VerifyCurrentOG();
	}

	private void VerifyCurrentOG() {
		if(CurrentOG>TargetOG)
		{
			OverTarget = true;
		}
		else
		{
			OverTarget = false;
		}
	}

	private void VerifyMaltPercentages() {
		for(Malt grain:GrainBill)
		{
			if(grain.getQuantity() > TotalQty*grain.getMaxPercentage()/100)
			{
				grain.setOverMax(true);
			}
			else
			{
				grain.setOverMax(false);
			}
		}
	}

	public void RemoveMalt(Malt malt)
	{
		if(GrainBill.contains(malt))
		{
			GrainBill.remove(malt);
			ComputeBill();
		}
	}
}
