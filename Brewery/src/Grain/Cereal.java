package Grain;

public enum Cereal 
{
	WheatMalt("Wheat Malt",100,1.0146,20),
	DarkWheatMalt("Dark Wheat Malt",100,1.0146,20),
	OatMalt("Oat Malt",30,1.0148,20),
	Rye("Rye",30,1.0146,20),
	Oats("Oats",30,1.0094,20),
	CaraPils("Cara-Pils",15,1.0127,20),
	PaleCaramel("Pale Caramel",15,1.0125,20),
	MediumCaramel("Medium Caramel",15,1.0125,20),
	ExtraDarkCaramel("Extra Dark Caramel",15,1.0108,20),
	ChocolateMalt("Chocolate Malt",20,1.0116,20),
	Rostmalz("Rostmalz",20,1.0116,20),
	CarafaIIIDehuskedMalt("Carafa III Dehusked Malt",10,1.0116,20),
	BlackMalt("Black Malt",10,1.0116,20),
	MunichMalt("Munich Malt",100,1.0133,20),
	AmberMalt("Amber Malt",30,1.0139,20),
	MelanoidinMalt("Melanoidin Malt",30,1.0133,20),
	HoneyMalt("Honey Malt",25,1.0133,20),
	BrownMalt("Brown Malt",80,1.0125,20),
	SixRowPilsnerMalt("Six-Row Pilsner Malt",100,1.0129,20),
	TwoRowPilsnerMalt("Two-Row Pilsner Malt",100,1.0135,20),
	TwoRowPaleMalt("Two-Row Pale Malt",100,1.0135,20),
	MarisOtter("Maris Otter",100,1.0135,20),
	ViennaMalt("Vienna Malt",100,1.0135,20),
	MildAleMalt("Mild Ale Malt",100,1.0133,20),
	PaleAleMalt("Pale Ale Malt",100,1.0139,20),
	VictoryMalt("Victory Malt",30,1.0139,20),
	AcidulatedMalt("Acidulated Malt",30,1.0139,20),
	BeechWoodSmokedMalt("BeechWood Smoked Malt",30,1.0139,20);

	public final String Name;
	public final double MaxPercentage;
	public final double OGperKg;
	public final double Volume;
	
	private Cereal(String Name, double MaxPercent, double OGperKg, double Volume) {
		this.Name = Name;
		this.MaxPercentage = MaxPercent;
		this.OGperKg = OGperKg;
		this.Volume = Volume;
	}
	
	public String getName()
	{
		return this.Name;
	}
	public double getMaxPercentage()
	{
		return this.MaxPercentage;
	}
	public double getOGperKg20L()
	{
		return this.OGperKg;
	}
	public double getVolume()
	{
		return this.Volume;
	}
}
