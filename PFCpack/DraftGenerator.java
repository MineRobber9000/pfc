package PFCpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DraftGenerator
{
  public static ArrayList<Player> generateLeaguePlayers(League paramLeague)
  {
    ArrayList localArrayList = new ArrayList();
    int k = '☖' / 46;
    int i = 0;
    int m;
    Object localObject;
    int j;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerQB(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerQB)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    k = '㋈' / 46;
    i = 0;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerRB(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerRB)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    k = '䰬' / 46;
    i = 0;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerWR(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerWR)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    k = '维' / 46;
    i = 0;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerOL(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerOL)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    k = 'ᥤ' / 46;
    i = 0;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerK(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerK)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    k = 'ᥤ' / 46;
    i = 0;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerS(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerS)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    k = '䰬' / 46;
    i = 0;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerCB(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerCB)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    k = '斐' / 46;
    i = 0;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerDL(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerDL)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    k = '䰬' / 46;
    i = 0;
    while (i < k)
    {
      m = (int)(15.0D * Math.random());
      localObject = new PlayerLB(paramLeague.getRandName(), 2016 - m);
      gamesPlayed = 10;
      j = 0;
      while (j < m)
      {
        ((PlayerLB)localObject).advanceSeasonRatingsAge();
        j += 1;
      }
      gamesPlayed = 0;
      localArrayList.add(localObject);
      i += 1;
    }
    Collections.sort(localArrayList, new PlayerComparator());
    return localArrayList;
  }
  
  public static ArrayList<ArrayList<Player>> generateRookies(League paramLeague)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < 10)
    {
      localArrayList.add(new ArrayList());
      i += 1;
    }
    int j = 'ʼ' / 46;
    i = 0;
    Object localObject;
    while (i < j)
    {
      localObject = new PlayerQB(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerQB)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(1)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    j = 'ո' / 46;
    i = 0;
    while (i < j)
    {
      localObject = new PlayerRB(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerRB)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(2)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    j = '࠴' / 46;
    i = 0;
    while (i < j)
    {
      localObject = new PlayerWR(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerWR)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(3)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    j = 'ඬ' / 46;
    i = 0;
    while (i < j)
    {
      localObject = new PlayerOL(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerOL)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(4)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    j = 'ʼ' / 46;
    i = 0;
    while (i < j)
    {
      localObject = new PlayerK(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerK)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(5)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    j = 'ʼ' / 46;
    i = 0;
    while (i < j)
    {
      localObject = new PlayerS(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerS)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(6)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    j = '࠴' / 46;
    i = 0;
    while (i < j)
    {
      localObject = new PlayerCB(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerCB)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(7)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    j = '૰' / 46;
    i = 0;
    while (i < j)
    {
      localObject = new PlayerDL(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerDL)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(8)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    j = '࠴' / 46;
    i = 0;
    while (i < j)
    {
      localObject = new PlayerLB(paramLeague.getRandName(), paramLeague.getYear());
      ((PlayerLB)localObject).setRatingsRookie();
      ((ArrayList)localArrayList.get(9)).add(localObject);
      ((ArrayList)localArrayList.get(0)).add(localObject);
      i += 1;
    }
    paramLeague = localArrayList.iterator();
    while (paramLeague.hasNext()) {
      Collections.sort((ArrayList)paramLeague.next(), new PlayerComparator());
    }
    return localArrayList;
  }
}

/* Location:
 * Qualified Name:     PFCpack.DraftGenerator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */