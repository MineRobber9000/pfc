package PFCpack;

public class Contract
{
  public static final double[] MAX_CONTRACTS = { 22.0D, 12.0D, 10.0D, 8.0D, 5.0D, 10.0D, 8.0D, 8.0D, 8.0D };
  public static final int MAX_CONTRACT_OVR = 90;
  public static final double SALARY_CAP = 150.0D;
  private double moneyPerYear;
  private Player player;
  private int yearsLeft;
  
  public Contract(Player paramPlayer)
  {
    yearsLeft = ((int)(Math.random() * 5.0D + 1.0D));
    moneyPerYear = ((ratOvr - 50) / 10);
  }
  
  public Contract(Player paramPlayer, int paramInt, double paramDouble)
  {
    player = paramPlayer;
    yearsLeft = paramInt;
    moneyPerYear = paramDouble;
  }
  
  public static Contract getContractDraft(Player paramPlayer, int paramInt)
  {
    int j = (224 - paramInt) / 64 + 1;
    int i = j;
    if (j < 2) {
      i = 2;
    }
    double d2 = (int)(10.0D * (5.6D - Math.pow(1.0D + paramInt / 125.0D, 3.25D))) / 10.0D;
    double d1 = d2;
    if (d2 < 0.5D) {
      d1 = 0.5D;
    }
    return new Contract(paramPlayer, i, d1);
  }
  
  public static Contract getContractFA(Player paramPlayer)
  {
    int j = (ratOvr - 40) / 10;
    int i = j;
    if (j < 2) {
      i = 2;
    }
    j = i;
    if (i > 5) {
      j = 5;
    }
    double d1 = MAX_CONTRACTS[Player.getPosNumber(position)];
    i = ratOvr * (220 - age) / 200;
    if (i >= 90) {}
    for (;;)
    {
      return new Contract(paramPlayer, j, d1);
      double d2;
      if (i >= 80)
      {
        d2 = d1 - Math.pow(0.2857142857142857D, 0.65D) * d1;
        d2 = (int)(10.0D * (d2 + (d1 - d2) * Math.pow((i - 80) / 10.0D, 0.65D))) / 10.0D;
        d1 = d2;
        if (d2 < 0.5D) {
          d1 = 0.5D;
        }
      }
      else
      {
        d2 = (int)(10.0D * (d1 - Math.pow((90 - i) / 35.0D, 0.65D) * d1)) / 10.0D;
        d1 = d2;
        if (d2 < 0.5D) {
          d1 = 0.5D;
        }
      }
    }
  }
  
  public boolean advanceSeason()
  {
    yearsLeft -= 1;
    return yearsLeft <= 0;
  }
  
  public void decreaseMoneyAndYears()
  {
    moneyPerYear -= 1.0D;
    moneyPerYear = ((int)(moneyPerYear * 10.0D) / 10.0D);
    yearsLeft -= 1;
    if (moneyPerYear < 0.5D) {
      moneyPerYear = 0.5D;
    }
    if (yearsLeft < 2) {
      yearsLeft = 2;
    }
  }
  
  public double getMoneyPerYear()
  {
    return moneyPerYear;
  }
  
  public Player getPlayer()
  {
    return player;
  }
  
  public int getYearsLeft()
  {
    return yearsLeft;
  }
  
  public void setMoneyPerYear(double paramDouble)
  {
    moneyPerYear = paramDouble;
    if ((int)(10.0D * paramDouble) / 10.0D < 0.5D) {}
  }
  
  public void setYearsLeft(int paramInt)
  {
    yearsLeft = paramInt;
  }
  
  public String toString()
  {
    return yearsLeft + "yrs, $" + moneyPerYear + "mil/yr";
  }
}

/* Location:
 * Qualified Name:     PFCpack.Contract
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
