package PFCpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Division
{
  public ArrayList<Player> allDivPlayers;
  public String divName;
  public ArrayList<Team> divTeams;
  public boolean evenYear;
  public League league;
  public int robinWeek;
  public int week;
  
  public Division(String paramString, League paramLeague)
  {
    divName = paramString;
    divTeams = new ArrayList();
    league = paramLeague;
    week = 0;
    robinWeek = 0;
    allDivPlayers = new ArrayList();
  }
  
  public void generateTeams()
  {
    if (divName.equals("AMNOR"))
    {
      divTeams.add(new Team("CIN Tigers", "CIN", divName, league));
      divTeams.add(new Team("BAL Blackbirds", "BAL", divName, league));
      divTeams.add(new Team("PIT Irondogs", "PIT", divName, league));
      divTeams.add(new Team("CLE Mud", "CLE", divName, league));
    }
    do
    {
      return;
      if (divName.equals("AMEAS"))
      {
        divTeams.add(new Team("NYJ Juice", "NYJ", divName, league));
        divTeams.add(new Team("BUF Bison", "BUF", divName, league));
        divTeams.add(new Team("NE Redcoats", "NE", divName, league));
        divTeams.add(new Team("MIA Pitbulls", "MIA", divName, league));
        return;
      }
      if (divName.equals("AMSOU"))
      {
        divTeams.add(new Team("TEN Monsters", "TEN", divName, league));
        divTeams.add(new Team("JAX Cheetahs", "JAX", divName, league));
        divTeams.add(new Team("HOU Herders", "HOU", divName, league));
        divTeams.add(new Team("IND Ponies", "IND", divName, league));
        return;
      }
      if (divName.equals("AMWES"))
      {
        divTeams.add(new Team("DEN Stallions", "DEN", divName, league));
        divTeams.add(new Team("KC Cherokee", "KC", divName, league));
        divTeams.add(new Team("SD Lightning", "SD", divName, league));
        divTeams.add(new Team("OAK Pillagers", "OAK", divName, league));
        return;
      }
      if (divName.equals("NANOR"))
      {
        divTeams.add(new Team("GB Boxers", "GB", divName, league));
        divTeams.add(new Team("DET Mustangs", "DET", divName, league));
        divTeams.add(new Team("MIN Voyagers", "MIN", divName, league));
        divTeams.add(new Team("CHI Wind", "CHI", divName, league));
        return;
      }
      if (divName.equals("NAEAS"))
      {
        divTeams.add(new Team("DAL Ranchers", "DAL", divName, league));
        divTeams.add(new Team("WAS Senators", "WAS", divName, league));
        divTeams.add(new Team("PHI Liberty", "PHI", divName, league));
        divTeams.add(new Team("NYG Goliaths", "NYG", divName, league));
        return;
      }
      if (divName.equals("NASOU"))
      {
        divTeams.add(new Team("ATL Birds", "ATL", divName, league));
        divTeams.add(new Team("CAR Cougars", "CAR", divName, league));
        divTeams.add(new Team("TB Pirates", "TB", divName, league));
        divTeams.add(new Team("NO Jazz", "NO", divName, league));
        return;
      }
    } while (!divName.equals("NAWES"));
    divTeams.add(new Team("ARI Phoenixes", "ARI", divName, league));
    divTeams.add(new Team("SAN Miners", "SAN", divName, league));
    divTeams.add(new Team("SEA Baristas", "SEA", divName, league));
    divTeams.add(new Team("LA Stars", "LA", divName, league));
  }
  
  public ArrayList<Player> getAllDivPlayers()
  {
    if (allDivPlayers.isEmpty())
    {
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      ArrayList localArrayList3 = new ArrayList();
      ArrayList localArrayList4 = new ArrayList();
      ArrayList localArrayList5 = new ArrayList();
      ArrayList localArrayList6 = new ArrayList();
      ArrayList localArrayList7 = new ArrayList();
      ArrayList localArrayList8 = new ArrayList();
      ArrayList localArrayList9 = new ArrayList();
      Iterator localIterator = divTeams.iterator();
      while (localIterator.hasNext())
      {
        Team localTeam = (Team)localIterator.next();
        localArrayList1.addAll(teamQBs);
        localArrayList2.addAll(teamRBs);
        localArrayList3.addAll(teamWRs);
        localArrayList4.addAll(teamOLs);
        localArrayList5.addAll(teamKs);
        localArrayList6.addAll(teamSs);
        localArrayList7.addAll(teamCBs);
        localArrayList8.addAll(teamDLs);
        localArrayList9.addAll(teamLBs);
      }
      Collections.sort(localArrayList1, new PlayerMVPComp());
      Collections.sort(localArrayList2, new PlayerMVPComp());
      Collections.sort(localArrayList3, new PlayerMVPComp());
      Collections.sort(localArrayList4, new PlayerMVPComp());
      Collections.sort(localArrayList5, new PlayerMVPComp());
      Collections.sort(localArrayList6, new PlayerMVPComp());
      Collections.sort(localArrayList7, new PlayerMVPComp());
      Collections.sort(localArrayList8, new PlayerMVPComp());
      Collections.sort(localArrayList9, new PlayerMVPComp());
      allDivPlayers.add(localArrayList1.get(0));
      allDivPlayers.add(localArrayList2.get(0));
      allDivPlayers.add(localArrayList2.get(1));
      int i = 0;
      while (i < 3)
      {
        allDivPlayers.add(localArrayList3.get(i));
        i += 1;
      }
      i = 0;
      while (i < 5)
      {
        allDivPlayers.add(localArrayList4.get(i));
        i += 1;
      }
      allDivPlayers.add(localArrayList5.get(0));
      allDivPlayers.add(localArrayList6.get(0));
      i = 0;
      while (i < 3)
      {
        allDivPlayers.add(localArrayList7.get(i));
        i += 1;
      }
      i = 0;
      while (i < 4)
      {
        allDivPlayers.add(localArrayList8.get(i));
        i += 1;
      }
      i = 0;
      while (i < 3)
      {
        allDivPlayers.add(localArrayList9.get(i));
        i += 1;
      }
    }
    return allDivPlayers;
  }
}

/* Location:
 * Qualified Name:     PFCpack.Division
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */