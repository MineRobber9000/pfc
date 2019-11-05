// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;

public class Division
{
    public ArrayList<Player> allDivPlayers;
    public String divName;
    public ArrayList<Team> divTeams;
    public boolean evenYear;
    public League league;
    public int robinWeek;
    public int week;
    
    public Division(final String divName, final League league) {
        this.divName = divName;
        this.divTeams = new ArrayList<Team>();
        this.league = league;
        this.week = 0;
        this.robinWeek = 0;
        this.allDivPlayers = new ArrayList<Player>();
    }
    
    public void generateTeams() {
        if (this.divName.equals("AMNOR")) {
            this.divTeams.add(new Team("CIN Tigers", "CIN", this.divName, this.league));
            this.divTeams.add(new Team("BAL Blackbirds", "BAL", this.divName, this.league));
            this.divTeams.add(new Team("PIT Irondogs", "PIT", this.divName, this.league));
            this.divTeams.add(new Team("CLE Mud", "CLE", this.divName, this.league));
        }
        else {
            if (this.divName.equals("AMEAS")) {
                this.divTeams.add(new Team("NYJ Juice", "NYJ", this.divName, this.league));
                this.divTeams.add(new Team("BUF Bison", "BUF", this.divName, this.league));
                this.divTeams.add(new Team("NE Redcoats", "NE", this.divName, this.league));
                this.divTeams.add(new Team("MIA Pitbulls", "MIA", this.divName, this.league));
                return;
            }
            if (this.divName.equals("AMSOU")) {
                this.divTeams.add(new Team("TEN Monsters", "TEN", this.divName, this.league));
                this.divTeams.add(new Team("JAX Cheetahs", "JAX", this.divName, this.league));
                this.divTeams.add(new Team("HOU Herders", "HOU", this.divName, this.league));
                this.divTeams.add(new Team("IND Ponies", "IND", this.divName, this.league));
                return;
            }
            if (this.divName.equals("AMWES")) {
                this.divTeams.add(new Team("DEN Stallions", "DEN", this.divName, this.league));
                this.divTeams.add(new Team("KC Cherokee", "KC", this.divName, this.league));
                this.divTeams.add(new Team("SD Lightning", "SD", this.divName, this.league));
                this.divTeams.add(new Team("OAK Pillagers", "OAK", this.divName, this.league));
                return;
            }
            if (this.divName.equals("NANOR")) {
                this.divTeams.add(new Team("GB Boxers", "GB", this.divName, this.league));
                this.divTeams.add(new Team("DET Mustangs", "DET", this.divName, this.league));
                this.divTeams.add(new Team("MIN Voyagers", "MIN", this.divName, this.league));
                this.divTeams.add(new Team("CHI Wind", "CHI", this.divName, this.league));
                return;
            }
            if (this.divName.equals("NAEAS")) {
                this.divTeams.add(new Team("DAL Ranchers", "DAL", this.divName, this.league));
                this.divTeams.add(new Team("WAS Senators", "WAS", this.divName, this.league));
                this.divTeams.add(new Team("PHI Liberty", "PHI", this.divName, this.league));
                this.divTeams.add(new Team("NYG Goliaths", "NYG", this.divName, this.league));
                return;
            }
            if (this.divName.equals("NASOU")) {
                this.divTeams.add(new Team("ATL Birds", "ATL", this.divName, this.league));
                this.divTeams.add(new Team("CAR Cougars", "CAR", this.divName, this.league));
                this.divTeams.add(new Team("TB Pirates", "TB", this.divName, this.league));
                this.divTeams.add(new Team("NO Jazz", "NO", this.divName, this.league));
                return;
            }
            if (this.divName.equals("NAWES")) {
                this.divTeams.add(new Team("ARI Phoenixes", "ARI", this.divName, this.league));
                this.divTeams.add(new Team("SAN Miners", "SAN", this.divName, this.league));
                this.divTeams.add(new Team("SEA Baristas", "SEA", this.divName, this.league));
                this.divTeams.add(new Team("LA Stars", "LA", this.divName, this.league));
            }
        }
    }
    
    public ArrayList<Player> getAllDivPlayers() {
        if (this.allDivPlayers.isEmpty()) {
            final ArrayList<Player> list = (ArrayList<Player>)new ArrayList<Object>();
            final ArrayList<Player> list2 = (ArrayList<Player>)new ArrayList<Object>();
            final ArrayList<Player> list3 = (ArrayList<Player>)new ArrayList<Object>();
            final ArrayList<Player> list4 = (ArrayList<Player>)new ArrayList<Object>();
            final ArrayList<Player> list5 = (ArrayList<Player>)new ArrayList<Object>();
            final ArrayList<Player> list6 = (ArrayList<Player>)new ArrayList<Object>();
            final ArrayList<Player> list7 = (ArrayList<Player>)new ArrayList<Object>();
            final ArrayList<Player> list8 = (ArrayList<Player>)new ArrayList<Object>();
            final ArrayList<Player> list9 = (ArrayList<Player>)new ArrayList<Object>();
            for (final Team team : this.divTeams) {
                list.addAll((Collection<?>)team.teamQBs);
                list2.addAll((Collection<?>)team.teamRBs);
                list3.addAll((Collection<?>)team.teamWRs);
                list4.addAll((Collection<?>)team.teamOLs);
                list5.addAll((Collection<?>)team.teamKs);
                list6.addAll((Collection<?>)team.teamSs);
                list7.addAll((Collection<?>)team.teamCBs);
                list8.addAll((Collection<?>)team.teamDLs);
                list9.addAll((Collection<?>)team.teamLBs);
            }
            Collections.sort((List<Object>)list, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort((List<Object>)list2, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort((List<Object>)list3, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort((List<Object>)list4, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort((List<Object>)list5, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort((List<Object>)list6, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort((List<Object>)list7, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort((List<Object>)list8, (Comparator<? super Object>)new PlayerMVPComp());
            Collections.sort((List<Object>)list9, (Comparator<? super Object>)new PlayerMVPComp());
            this.allDivPlayers.add(list.get(0));
            this.allDivPlayers.add(list2.get(0));
            this.allDivPlayers.add(list2.get(1));
            for (int i = 0; i < 3; ++i) {
                this.allDivPlayers.add(list3.get(i));
            }
            for (int j = 0; j < 5; ++j) {
                this.allDivPlayers.add(list4.get(j));
            }
            this.allDivPlayers.add(list5.get(0));
            this.allDivPlayers.add(list6.get(0));
            for (int k = 0; k < 3; ++k) {
                this.allDivPlayers.add(list7.get(k));
            }
            for (int l = 0; l < 4; ++l) {
                this.allDivPlayers.add(list8.get(l));
            }
            for (int n = 0; n < 3; ++n) {
                this.allDivPlayers.add(list9.get(n));
            }
        }
        return this.allDivPlayers;
    }
}
