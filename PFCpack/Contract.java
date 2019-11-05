// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

public class Contract
{
    public static final double[] MAX_CONTRACTS;
    public static final int MAX_CONTRACT_OVR = 90;
    public static final double SALARY_CAP = 150.0;
    private double moneyPerYear;
    private Player player;
    private int yearsLeft;
    
    static {
        MAX_CONTRACTS = new double[] { 22.0, 12.0, 10.0, 8.0, 5.0, 10.0, 8.0, 8.0, 8.0 };
    }
    
    public Contract(final Player player) {
        this.yearsLeft = (int)(Math.random() * 5.0 + 1.0);
        this.moneyPerYear = (player.ratOvr - 50) / 10;
    }
    
    public Contract(final Player player, final int yearsLeft, final double moneyPerYear) {
        this.player = player;
        this.yearsLeft = yearsLeft;
        this.moneyPerYear = moneyPerYear;
    }
    
    public static Contract getContractDraft(final Player player, final int n) {
        int n2;
        if ((n2 = (224 - n) / 64 + 1) < 2) {
            n2 = 2;
        }
        double n3;
        if ((n3 = (int)(10.0 * (5.6 - Math.pow(1.0 + n / 125.0, 3.25))) / 10.0) < 0.5) {
            n3 = 0.5;
        }
        return new Contract(player, n2, n3);
    }
    
    public static Contract getContractFA(final Player player) {
        int n;
        if ((n = (player.ratOvr - 40) / 10) < 2) {
            n = 2;
        }
        int n2;
        if ((n2 = n) > 5) {
            n2 = 5;
        }
        double n3 = Contract.MAX_CONTRACTS[Player.getPosNumber(player.position)];
        final int n4 = player.ratOvr * (220 - player.age) / 200;
        if (n4 < 90) {
            if (n4 >= 80) {
                final double n5 = n3 - Math.pow(0.2857142857142857, 0.65) * n3;
                if ((n3 = (int)(10.0 * (n5 + (n3 - n5) * Math.pow((n4 - 80) / 10.0, 0.65))) / 10.0) < 0.5) {
                    n3 = 0.5;
                }
            }
            else if ((n3 = (int)(10.0 * (n3 - Math.pow((90 - n4) / 35.0, 0.65) * n3)) / 10.0) < 0.5) {
                n3 = 0.5;
            }
        }
        return new Contract(player, n2, n3);
    }
    
    public boolean advanceSeason() {
        --this.yearsLeft;
        return this.yearsLeft <= 0;
    }
    
    public void decreaseMoneyAndYears() {
        --this.moneyPerYear;
        this.moneyPerYear = (int)(this.moneyPerYear * 10.0) / 10.0;
        --this.yearsLeft;
        if (this.moneyPerYear < 0.5) {
            this.moneyPerYear = 0.5;
        }
        if (this.yearsLeft < 2) {
            this.yearsLeft = 2;
        }
    }
    
    public double getMoneyPerYear() {
        return this.moneyPerYear;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public int getYearsLeft() {
        return this.yearsLeft;
    }
    
    public void setMoneyPerYear(final double moneyPerYear) {
        this.moneyPerYear = moneyPerYear;
        if ((int)(10.0 * moneyPerYear) / 10.0 < 0.5) {}
    }
    
    public void setYearsLeft(final int yearsLeft) {
        this.yearsLeft = yearsLeft;
    }
    
    @Override
    public String toString() {
        return this.yearsLeft + "yrs, $" + this.moneyPerYear + "mil/yr";
    }
}
