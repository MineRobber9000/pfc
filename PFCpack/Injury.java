package PFCpack;

import java.util.Random;

public class Injury
{
  private static final String[] injuries = { "Knee", "Head", "Shoulder", "Wrist", "Ankle", "Foot", "Arm", "Back", "Brain" };
  private static final Random rando = new Random();
  private String description;
  private int duration;
  private Player player;
  
  public Injury(int paramInt, String paramString, Player paramPlayer)
  {
    duration = paramInt;
    description = paramString;
    player = paramPlayer;
    player.isInjured = true;
  }
  
  public Injury(Player paramPlayer)
  {
    duration = Math.abs((int)(rando.nextGaussian() * 3.0D + 1.0D));
    if (duration == 0) {
      duration = 1;
    }
    if (Math.random() < 0.01D) {
      duration = 100;
    }
    description = injuries[((int)(Math.random() * injuries.length))];
    player = paramPlayer;
    player.isInjured = true;
  }
  
  public Injury(Player paramPlayer, int paramInt1, int paramInt2)
  {
    player = paramPlayer;
    description = injuries[paramInt1];
    duration = paramInt2;
    player.isInjured = true;
  }
  
  public void advanceGame()
  {
    duration -= 1;
    if (duration <= 0)
    {
      player.isInjured = false;
      player.injury = null;
    }
  }
  
  public String getCSV()
  {
    int k = 0;
    int i = 1;
    for (;;)
    {
      int j = k;
      if (i < injuries.length)
      {
        if (description.equals(injuries[i])) {
          j = i;
        }
      }
      else {
        return j + "," + duration;
      }
      i += 1;
    }
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public int getDuration()
  {
    return duration;
  }
  
  public String toString()
  {
    if (duration < 20) {
      return description + " (" + duration + " wk)";
    }
    return description + " (Season)";
  }
}

/* Location:
 * Qualified Name:     PFCpack.Injury
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */