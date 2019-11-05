package PFCpack;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PlayerPickerLogic
{
  public static Player draftPlayer(Team paramTeam, ArrayList<Player> paramArrayList, boolean paramBoolean)
  {
    Player localPlayer = (Player)paramArrayList.get(0);
    String str = paramTeam.getNeededPosition(paramBoolean);
    int i = 0;
    while ((i < paramArrayList.size()) && (i < 10))
    {
      localPlayer = (Player)paramArrayList.get(i);
      if ((position.equals(str)) || (i == paramArrayList.size() - 1))
      {
        paramTeam.addPlayer(localPlayer);
        localPlayer.setTeam(paramTeam);
        localPlayer.addTeamPlayedFor(abbr, league.getYear());
        return localPlayer;
      }
      i += 1;
    }
    if (paramBoolean)
    {
      paramTeam.addPlayer((Player)paramArrayList.get(0));
      ((Player)paramArrayList.get(0)).setTeam(paramTeam);
      ((Player)paramArrayList.get(0)).addTeamPlayedFor(abbr, league.getYear());
      return (Player)paramArrayList.get(0);
    }
    i = 0;
    for (;;)
    {
      if (i < paramArrayList.size())
      {
        localPlayer = (Player)paramArrayList.get(i);
        if (position.equals("K")) {}
      }
      else
      {
        paramTeam.addPlayer(localPlayer);
        localPlayer.setTeam(paramTeam);
        localPlayer.addTeamPlayedFor(abbr, league.getYear());
        return localPlayer;
      }
      i += 1;
    }
  }
  
  public static boolean signFreeAgentForTeam(ArrayList<Team> paramArrayList, Player paramPlayer, ArrayList<String> paramArrayList1, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (((paramPlayer.getRatOvr() < paramInt1) && (paramPlayer.getAge() <= 30)) || ((paramPlayer.getRatOvr() < paramInt2) && (paramPlayer.getAge() > 30))) {
      return false;
    }
    Contract localContract = Contract.getContractFA(paramPlayer);
    Object localObject1 = new ArrayList();
    ((ArrayList)localObject1).addAll(paramArrayList);
    Object localObject2;
    Team localTeam;
    if (!paramBoolean)
    {
      localObject2 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localTeam = (Team)((Iterator)localObject2).next();
        if (userControlled) {
          ((ArrayList)localObject1).remove(localTeam);
        }
      }
    }
    paramInt1 = 0;
    for (;;)
    {
      if (paramInt1 != 0) {
        break label265;
      }
      paramInt2 = 0;
      while (paramInt2 < ((ArrayList)localObject1).size()) {
        if ((localContract.getMoneyPerYear() > ((Team)((ArrayList)localObject1).get(paramInt2)).getSalaryCapRoom()) && (localContract.getMoneyPerYear() != 0.5D)) {
          ((ArrayList)localObject1).remove(paramInt2);
        } else {
          paramInt2 += 1;
        }
      }
      if (((ArrayList)localObject1).isEmpty())
      {
        localContract.decreaseMoneyAndYears();
        ((ArrayList)localObject1).clear();
        ((ArrayList)localObject1).addAll(paramArrayList);
        if (paramBoolean) {
          continue;
        }
        localObject2 = ((ArrayList)localObject1).iterator();
        if (!((Iterator)localObject2).hasNext()) {
          continue;
        }
        localTeam = (Team)((Iterator)localObject2).next();
        if (!userControlled) {
          break;
        }
        ((ArrayList)localObject1).remove(localTeam);
        continue;
      }
      paramInt1 = 1;
    }
    label265:
    paramArrayList = new int[((ArrayList)localObject1).size()];
    paramInt1 = 0;
    while (paramInt1 < paramArrayList.length)
    {
      paramArrayList[paramInt1] = ((Team)((ArrayList)localObject1).get(paramInt1)).getValueAdded(paramPlayer);
      paramInt1 += 1;
    }
    Arrays.sort(paramArrayList);
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Team)((Iterator)localObject1).next();
      if (((Team)localObject2).getValueAdded(paramPlayer) == paramArrayList[(paramArrayList.length - 1)])
      {
        System.out.println(abbr + " signs " + paramPlayer.getPosNameYrOvrPot_OneLine() + " for $" + localContract.getMoneyPerYear() + "/yr, valueAdded = " + paramArrayList[(paramArrayList.length - 1)]);
        paramPlayer.setContract(localContract);
        paramPlayer.setTeam((Team)localObject2);
        ((Team)localObject2).addPlayer(paramPlayer);
        paramPlayer.addTeamPlayedFor(abbr, league.getYear());
        if (paramArrayList1 != null) {
          paramArrayList1.add(abbr + " signs " + paramPlayer.getPosNameAge_Str() + "\n\t\t" + paramPlayer.getOvrPotDurFootIQ_Str() + "\n\t\tSigned to a " + localContract.toString() + " contract");
        }
        return true;
      }
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerPickerLogic
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */