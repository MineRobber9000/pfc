// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public abstract class Player extends TradePiece
{
    protected static final String[] letterGrades;
    private static final Random rando;
    protected int age;
    protected int careerAllPro;
    protected int careerChamps;
    protected int careerGamesPlayed;
    protected int careerMVP;
    protected int careerWins;
    protected Contract contract;
    protected int draftPickNum;
    protected int gamesPlayed;
    protected Injury injury;
    protected boolean isInjured;
    public boolean isStarter;
    protected String name;
    protected String position;
    protected int ratDur;
    protected int ratFootIQ;
    protected int ratImprovement;
    protected int ratOvr;
    protected int ratPot;
    protected int rookieYear;
    protected int statsWins;
    protected Team team;
    protected ArrayList<String> teamsPlayedFor;
    protected boolean wonAllPro;
    protected boolean wonMVP;
    
    static {
        rando = new Random();
        letterGrades = new String[] { "F", "F+", "D", "D+", "C", "C+", "B", "B+", "A", "A+" };
    }
    
    public Player(final Team team, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = array[i].trim();
        }
        this.team = team;
        this.gamesPlayed = 0;
        this.statsWins = 0;
        this.wonAllPro = false;
        this.wonMVP = false;
        this.careerGamesPlayed = 0;
        this.careerWins = 0;
        this.careerAllPro = 0;
        this.careerMVP = 0;
        this.careerChamps = 0;
        this.setInjury(null);
        this.draftPickNum = -1;
        this.teamsPlayedFor = new ArrayList<String>();
        this.addTeamPlayedFor(team.abbr, 2016);
        if (array.length == 12) {
            this.position = array[0];
            this.name = array[1];
            this.age = Integer.parseInt(array[2]);
            this.rookieYear = Integer.parseInt(array[3]);
            this.ratPot = Integer.parseInt(array[4]);
            this.ratFootIQ = Integer.parseInt(array[5]);
            this.ratDur = Integer.parseInt(array[6]);
            this.setRatings(new int[] { Integer.parseInt(array[7]), Integer.parseInt(array[8]), Integer.parseInt(array[9]) });
            this.contract = new Contract(this, Integer.parseInt(array[11]), Double.parseDouble(array[10]));
        }
        else if (array.length == 9) {
            this.position = array[0];
            this.name = array[1];
            this.age = Integer.parseInt(array[2]);
            this.ratPot = Integer.parseInt(array[3]);
            this.ratFootIQ = Integer.parseInt(array[4]);
            this.ratDur = Integer.parseInt(array[5]);
            this.setRatings(new int[] { Integer.parseInt(array[6]), Integer.parseInt(array[7]), Integer.parseInt(array[8]) });
            this.rookieYear = 2016;
            this.contract = new Contract(this, 1, 0.5);
        }
    }
    
    public Player(final String name, final Team team, final String position, final boolean b) {
        this.team = team;
        this.name = name;
        this.position = position;
        this.gamesPlayed = 0;
        this.statsWins = 0;
        this.wonAllPro = false;
        this.wonMVP = false;
        this.careerGamesPlayed = 0;
        this.careerWins = 0;
        this.careerAllPro = 0;
        this.careerMVP = 0;
        this.careerChamps = 0;
        this.setInjury(null);
        this.draftPickNum = -1;
        this.teamsPlayedFor = new ArrayList<String>();
        if (b) {
            this.ratPot = (int)(Math.random() * 50.0 + 50.0);
            this.ratFootIQ = (int)(Math.random() * 30.0 + 55.0);
            this.ratDur = (int)(Math.random() * 50.0 + 50.0);
            this.setRatingsRookie();
            this.age = 22;
            this.gamesPlayed = 10;
            for (int n = (int)(Math.random() * 12.0), i = 0; i < n; ++i) {
                this.advanceSeasonRatingsAge();
            }
            this.gamesPlayed = 0;
            this.rookieYear = team.league.getYear() - (this.age - 22);
            (this.contract = Contract.getContractFA(this)).setYearsLeft((int)(Math.random() * 4.0) + 1);
            this.addTeamPlayedFor(team.abbr, team.league.getYear());
        }
        this.checkMinimums();
    }
    
    public Player(final String name, final String position, final int rookieYear) {
        this.team = null;
        this.name = name;
        this.gamesPlayed = 0;
        this.statsWins = 0;
        this.wonAllPro = false;
        this.wonMVP = false;
        this.careerGamesPlayed = 0;
        this.careerWins = 0;
        this.careerAllPro = 0;
        this.careerMVP = 0;
        this.careerChamps = 0;
        this.setInjury(null);
        this.draftPickNum = -1;
        this.teamsPlayedFor = new ArrayList<String>();
        this.age = (int)(Math.random() * 3.0 + 21.0);
        this.rookieYear = rookieYear;
        this.ratPot = (int)(Math.random() * 50.0 + 50.0);
        this.ratFootIQ = (int)(Math.random() * 30.0 + 55.0);
        this.ratDur = (int)(Math.random() * 50.0 + 50.0);
        this.ratImprovement = 0;
        this.setRatingsRookie();
        this.contract = null;
        this.position = position;
        this.checkMinimums();
    }
    
    private void checkMinimums() {
        if (this.ratPot < 50) {
            this.ratPot = 50;
        }
        if (this.ratPot > 99) {
            this.ratPot = 99;
        }
        if (this.ratDur < 50) {
            this.ratDur = 50;
        }
        if (this.ratFootIQ < 50) {
            this.ratFootIQ = 50;
        }
    }
    
    private int[] getImprovementsArray() {
        final String position = this.position;
        switch (position) {
            default: {
                return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
            }
            case "QB": {
                return new int[] { 2, 2, 2, 1, 1, 1, 0, 0, 0, 0, 0, -2, -4, -5 };
            }
            case "RB": {
                return new int[] { 3, 3, 3, 3, 2, 2, 0, 0, -1, -2, -4, -5, -8, -9 };
            }
            case "WR": {
                return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
            }
            case "OL": {
                return new int[] { 3, 3, 2, 2, 1, 1, 0, 0, 0, -1, -2, -4, -6, -8 };
            }
            case "K": {
                return new int[] { 3, 3, 2, 1, 1, 1, 0, 0, -1, -1, -1, -2, -4, -8 };
            }
            case "S": {
                return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
            }
            case "CB": {
                return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
            }
            case "DL": {
                return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
            }
            case "LB": {
                return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
            }
        }
    }
    
    public static String getLetterGrade(int n) {
        if ((n = (n - 50) / 5) > 9) {
            n = 9;
        }
        int n2;
        if ((n2 = n) < 0) {
            n2 = 0;
        }
        return Player.letterGrades[n2];
    }
    
    public static String getLetterGrade(final String s) {
        int n;
        if ((n = (Integer.parseInt(s) - 50) / 5) > 9) {
            n = 9;
        }
        int n2;
        if ((n2 = n) < 0) {
            n2 = 0;
        }
        return Player.letterGrades[n2];
    }
    
    public static int getPosNumber(final String s) {
        int n = 0;
        switch (s) {
            default: {
                n = 9;
                return n;
            }
            case "QB": {
                return n;
            }
            case "RB": {
                return 1;
            }
            case "WR": {
                return 2;
            }
            case "OL": {
                return 3;
            }
            case "K": {
                return 4;
            }
            case "S": {
                return 5;
            }
            case "CB": {
                return 6;
            }
            case "DL": {
                return 7;
            }
            case "LB": {
                return 8;
            }
        }
    }
    
    public static double getPosValue(final String s) {
        double n = 3.0;
        switch (s) {
            default: {
                n = 1.0;
                return n;
            }
            case "RB":
            case "WR":
            case "S": {
                return n;
            }
            case "QB": {
                return 6.0;
            }
            case "OL": {
                return 2.0;
            }
            case "K": {
                return 0.5;
            }
            case "CB": {
                return 2.0;
            }
            case "DL": {
                return 1.0;
            }
            case "LB": {
                return 1.0;
            }
        }
    }
    
    public void addTeamPlayedFor(final String s, final int n) {
        if (this.teamsPlayedFor == null) {
            this.teamsPlayedFor = new ArrayList<String>();
        }
        if (!this.teamsPlayedFor.isEmpty() && !this.teamsPlayedFor.get(this.teamsPlayedFor.size() - 1).split(":")[0].equals(s)) {
            this.teamsPlayedFor.add(s + ":" + n);
        }
        else if (this.teamsPlayedFor.isEmpty()) {
            this.teamsPlayedFor.add(s + ":" + n);
        }
    }
    
    public void advanceSeason() {
        final int ratOvr = this.ratOvr;
        this.advanceSeasonRatingsAge();
        this.ratImprovement = this.ratOvr - ratOvr;
        this.careerGamesPlayed += this.gamesPlayed;
        this.careerWins += this.statsWins;
        if (this.wonMVP) {
            ++this.careerMVP;
        }
        if (this.wonAllPro) {
            ++this.careerAllPro;
        }
        if (this.team.natChampWL.equals("CBW")) {
            System.out.println(this.getPosNameYrOvr_Str() + " added champ, his team " + this.team.abbr + " won the Champ Bowl");
            ++this.careerChamps;
        }
        this.contract.advanceSeason();
        this.gamesPlayed = 0;
        this.statsWins = 0;
        this.wonAllPro = false;
        this.wonMVP = false;
        this.checkMinimums();
    }
    
    public void advanceSeasonRatingsAge() {
        final int[] ratings = this.getRatings();
        final int[] improvementsArray = this.getImprovementsArray();
        double n;
        if ((n = (this.ratPot - 70 + (double)(this.gamesPlayed / 3 - 3)) / 10.0) > 4.0) {
            n = 4.0;
        }
        int n2;
        if ((n2 = this.age - 20) > 13) {
            n2 = 13;
        }
        final double n3 = n + improvementsArray[n2];
        this.ratFootIQ += (int)(Math.random() * n) + 1;
        ratings[0] += (int)(Player.rando.nextGaussian() * 2.0 + n3 - Math.random() * n3);
        ratings[1] += (int)(Player.rando.nextGaussian() * 2.0 + n3 - Math.random() * n3);
        ratings[2] += (int)(Player.rando.nextGaussian() * 2.0 + n3 - Math.random() * n3);
        this.ratDur += (int)(Player.rando.nextGaussian() * 3.0);
        this.ratPot += (int)(Player.rando.nextGaussian() * 3.0);
        this.checkMinimums();
        this.setRatings(ratings);
        ++this.age;
    }
    
    public void fudgePotentialRookie() {
        this.ratPot += (int)(30.0 * Math.random()) - 15;
        if (this.ratPot < 50) {
            this.ratPot = 50;
        }
        if (this.ratPot > 100) {
            this.ratPot = 100;
        }
    }
    
    public int getAge() {
        return this.age;
    }
    
    public String getAgeOvrPot_Str() {
        return "Age: " + this.age + ", Ovr: " + this.ratOvr + ", Pot: " + getLetterGrade(this.ratPot);
    }
    
    public String getAwards() {
        final int n = 1;
        final ArrayList<String> list = new ArrayList<String>();
        final int careerMVP = this.careerMVP;
        int n2;
        if (this.wonMVP) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        final int n3 = careerMVP + n2;
        if (n3 > 0) {
            list.add(n3 + "x MVP");
        }
        final int careerAllPro = this.careerAllPro;
        int n4;
        if (this.wonAllPro) {
            n4 = 1;
        }
        else {
            n4 = 0;
        }
        final int n5 = careerAllPro + n4;
        if (n5 > 0) {
            list.add(n5 + "x All-Pro");
        }
        final int careerChamps = this.careerChamps;
        int n6;
        if (this.team.natChampWL.equals("CBW")) {
            n6 = n;
        }
        else {
            n6 = 0;
        }
        final int n7 = careerChamps + n6;
        if (n7 > 0) {
            list.add(n7 + "x Champ");
        }
        String s2;
        if (list.size() > 0) {
            String s = "";
            int n8 = 0;
            while (true) {
                s2 = s;
                if (n8 >= list.size()) {
                    break;
                }
                final String s3 = s += list.get(n8);
                if (n8 != list.size() - 1) {
                    s = s3 + ", ";
                }
                ++n8;
            }
        }
        else {
            s2 = "None";
        }
        return s2;
    }
    
    public String getCSV() {
        final int n = 1;
        String csv;
        if (this.injury != null) {
            csv = this.injury.getCSV();
        }
        else {
            csv = "-1,-1";
        }
        final StringBuilder append = new StringBuilder().append(this.position).append(",").append(this.name).append(",").append(this.age).append(",").append(this.rookieYear).append(",").append(this.ratPot).append(",").append(this.ratFootIQ).append(",").append(this.ratDur).append(",").append(this.ratImprovement).append(",").append(this.getRatings()[0]).append(",").append(this.getRatings()[1]).append(",").append(this.getRatings()[2]).append(",").append(this.gamesPlayed).append(",").append(this.statsWins).append(",");
        int n2;
        if (this.wonAllPro) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        final StringBuilder append2 = append.append(n2).append(",");
        int n3;
        if (this.wonMVP) {
            n3 = n;
        }
        else {
            n3 = 0;
        }
        return append2.append(n3).append(",").append(this.careerGamesPlayed).append(",").append(this.careerWins).append(",").append(this.careerAllPro).append(",").append(this.careerMVP).append(",").append(this.careerChamps).append(",").append(this.contract.getYearsLeft()).append(",").append((int)(this.contract.getMoneyPerYear() * 10.0)).append(",").append(csv).append(",").append(this.draftPickNum).append(">").append(this.getTeamsPlayedForCSV()).toString();
    }
    
    public String getCSVRosterFile() {
        final String[] array = { this.position, this.name, this.age + "", this.rookieYear + "", this.ratPot + "", this.ratFootIQ + "", this.ratDur + "", this.getRatings()[0] + "", this.getRatings()[1] + "", this.getRatings()[2] + "", this.contract.getMoneyPerYear() + "", this.contract.getYearsLeft() + "" };
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; ++i) {
            sb.append(array[i]).append(",");
        }
        sb.append(array[11]);
        return sb.toString();
    }
    
    public String getCSV_Online() {
        return this.position + "," + this.name + "," + this.age + "," + this.ratPot + "," + this.ratFootIQ + "," + this.ratDur + "," + this.getRatings()[0] + "," + this.getRatings()[1] + "," + this.getRatings()[2];
    }
    
    public int getCareerAllPro() {
        return this.careerAllPro;
    }
    
    public int getCareerGamesPlayed() {
        return this.careerGamesPlayed;
    }
    
    public int getCareerMVP() {
        return this.careerMVP;
    }
    
    public ArrayList<String> getCareerStatsList() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + (this.gamesPlayed + this.careerGamesPlayed) + " (" + (this.statsWins + this.careerWins) + "-" + (this.gamesPlayed + this.careerGamesPlayed - (this.statsWins + this.careerWins)) + ")>" + this.getDraftPickStr());
        list.add("Teams: " + this.getTeamsPlayedForStr());
        list.add("Awards: " + this.getAwards());
        return list;
    }
    
    public int getCareerWins() {
        return this.careerWins;
    }
    
    public Contract getContract() {
        return this.contract;
    }
    
    public ArrayList<String> getDetailAllStatsList(final int n) {
        return null;
    }
    
    public ArrayList<String> getDetailStatsList(final int n) {
        return null;
    }
    
    public ArrayList<String> getDetailStatsOffseason() {
        return null;
    }
    
    public String getDraftPickStr() {
        if (this.draftPickNum < 0) {
            return "Undrafted, " + this.rookieYear;
        }
        return "Drafted " + this.getPickStr(this.draftPickNum) + ", " + this.rookieYear;
    }
    
    public int getGamesPlayed() {
        if (this.gamesPlayed == 0) {
            return 1;
        }
        return this.gamesPlayed;
    }
    
    public String getInfoForLineup() {
        return null;
    }
    
    public String getInfoLineupInjury() {
        if (this.injury != null) {
            return this.getInitialName() + " [" + this.age + "] " + this.injury.toString();
        }
        return this.getInitialName() + " [" + this.age + "] Ovr: " + this.ratOvr + ", Pot: " + getLetterGrade(this.ratPot);
    }
    
    public String getInitialName() {
        final String[] split = this.name.split(" ");
        return split[0].substring(0, 1) + ". " + split[1];
    }
    
    public Injury getInjury() {
        return this.injury;
    }
    
    public int getMVPScore() {
        int gamesPlayed;
        if ((gamesPlayed = this.gamesPlayed) > 10) {
            gamesPlayed = 10;
        }
        return this.ratOvr * gamesPlayed;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getOvrPotDurFootIQ_Str() {
        return "Ovr: " + this.ratOvr + ", Pot: " + getLetterGrade(this.ratPot) + ", Dur: " + getLetterGrade(this.ratDur) + ", Football IQ: " + getLetterGrade(this.ratFootIQ);
    }
    
    public String getPickStr(final int n) {
        if (n == 11) {
            return "11th";
        }
        if (n == 12) {
            return "12th";
        }
        if (n == 13) {
            return "13th";
        }
        if (n % 10 == 1) {
            return n + "st";
        }
        if (n % 10 == 2) {
            return n + "nd";
        }
        if (n % 10 == 3) {
            return n + "rd";
        }
        return n + "th";
    }
    
    public String getPosInitialNameYrOvr_Str() {
        return this.position + " " + this.getInitialName() + " [" + this.age + "] Ovr: " + this.ratOvr;
    }
    
    public String getPosNameAge_Str() {
        return this.position + " " + this.name + " [" + this.age + "]";
    }
    
    public String getPosNameYrOvrPotImprove_Str() {
        String s;
        if (this.ratImprovement > 0) {
            s = " (+" + this.ratImprovement + ")";
        }
        else if (this.ratImprovement < 0) {
            s = " (" + this.ratImprovement + ")";
        }
        else {
            s = "";
        }
        return this.position + " " + this.getInitialName() + " [" + this.age + "]>" + this.ratOvr + s + ", Pot: " + getLetterGrade(this.ratPot);
    }
    
    public String getPosNameYrOvrPot_Compact() {
        return this.position + " " + this.getInitialName() + " [" + this.age + "] " + this.ratOvr + " / " + getLetterGrade(this.ratPot);
    }
    
    public String getPosNameYrOvrPot_NoInjury() {
        return this.position + " " + this.getInitialName() + " [" + this.age + "] Ovr: " + this.ratOvr + ", Pot: " + getLetterGrade(this.ratPot);
    }
    
    public String getPosNameYrOvrPot_OneLine() {
        if (this.injury != null) {
            return this.position + " " + this.getInitialName() + " [" + this.age + "] Ovr: " + this.ratOvr + " " + this.injury.toString();
        }
        return this.position + " " + this.getInitialName() + " [" + this.age + "] Ovr: " + this.ratOvr + ", Pot: " + getLetterGrade(this.ratPot);
    }
    
    public String getPosNameYrOvrPot_Split() {
        return this.position + " " + this.getInitialName() + " [" + this.age + "]>" + this.ratOvr + ", Pot: " + getLetterGrade(this.ratPot);
    }
    
    public String getPosNameYrOvrPot_Str() {
        if (this.injury != null) {
            return "[I]" + this.position + " " + this.getInitialName() + " [" + this.age + "] Ovr: " + this.ratOvr + ">" + this.injury.toString();
        }
        return this.position + " " + this.name + " [" + this.age + "]>" + this.ratOvr + ", Pot: " + getLetterGrade(this.ratPot);
    }
    
    public String getPosNameYrOvr_Str() {
        return this.position + " " + this.name + " [" + this.age + "] Ovr: " + this.ratOvr;
    }
    
    public String getPosition() {
        return this.position;
    }
    
    public int getRatDur() {
        return this.ratDur;
    }
    
    public int getRatFootIQ() {
        return this.ratFootIQ;
    }
    
    public int getRatImprovement() {
        return this.ratImprovement;
    }
    
    public int getRatOvr() {
        return this.ratOvr;
    }
    
    public int getRatPot() {
        return this.ratPot;
    }
    
    public abstract int[] getRatings();
    
    public int getRookieYear() {
        return this.rookieYear;
    }
    
    public int getStatsWins() {
        return this.statsWins;
    }
    
    public Team getTeam() {
        return this.team;
    }
    
    public String getTeamsPlayedForCSV() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.teamsPlayedFor.size(); ++i) {
            sb.append(this.teamsPlayedFor.get(i));
            if (i < this.teamsPlayedFor.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    public String getTeamsPlayedForStr() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.teamsPlayedFor.size(); ++i) {
            final String[] split = this.teamsPlayedFor.get(i).split(":");
            sb.append(split[0] + " '" + split[1].substring(2));
            if (i < this.teamsPlayedFor.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    @Override
    public String getTradePieceInfo() {
        String string = "";
        if (this.isInjured) {
            string = "\n\t\tInjured: " + this.injury.toString();
        }
        return this.getPosNameYrOvrPot_Compact() + string + "\n\t\tContract: " + this.contract.toString();
    }
    
    @Override
    public int getTradeValue() {
        if (this.ratOvr > 65) {
            final double posValue = getPosValue(this.position);
            final double n = (this.ratDur + 200) / 300.0;
            final double n2 = (65 - this.age) / 40.0;
            final double n3 = (this.ratPot + 200 - this.age * 4) / 200.0;
            double n4 = 1.0;
            if (this.isInjured()) {
                n4 = n4;
                if (this.team.userControlled) {
                    n4 = 0.3;
                }
            }
            double pow;
            final double n5 = pow = 1.0;
            if (!this.team.userControlled) {
                pow = n5;
                if (this.ratOvr > 90) {
                    pow = n5;
                    if (!this.position.equals("K")) {
                        pow = Math.pow(this.ratOvr / 85.0, 3.0);
                    }
                }
            }
            return (int)(Math.pow(this.ratOvr - 60, 2.0) * n3 * n * n2 * n4 * pow * posValue);
        }
        if (this.age < 25) {
            return this.ratPot;
        }
        return 0;
    }
    
    public String getYearsPlayed() {
        return this.rookieYear + "-" + this.team.league.getYear();
    }
    
    public boolean hasWonAllPro() {
        return this.wonAllPro;
    }
    
    public boolean hasWonMVP() {
        return this.wonMVP;
    }
    
    public boolean isInjured() {
        return this.isInjured;
    }
    
    public boolean randomJumpRookie(final int n) {
        if (0.1 + n / 896 > Math.random()) {
            System.out.print("Player of pick #" + n + " got a jump from " + this.ratOvr);
            final int[] ratings = this.getRatings();
            for (int i = 0; i < 3; ++i) {
                ratings[i] += (int)(Math.random() * (n / 32 + 4)) + 3;
            }
            this.setRatings(ratings);
            System.out.println(" to " + this.ratOvr);
            return true;
        }
        return false;
    }
    
    public void setContract(final Contract contract) {
        this.contract = contract;
    }
    
    public void setDraftPickNum(final int draftPickNum) {
        this.draftPickNum = draftPickNum;
    }
    
    public void setInjury(final Injury injury) {
        this.injury = injury;
        if (injury == null) {
            this.isInjured = false;
            return;
        }
        this.isInjured = true;
    }
    
    abstract void setOvr();
    
    abstract void setRatings(final int[] p0);
    
    public void setRatingsRookie() {
        final int[] ratings = new int[3];
        for (int i = 0; i < 3; ++i) {
            ratings[i] = (int)Math.abs(Player.rando.nextGaussian() * 15.0) + 55;
            if (ratings[i] > 95) {
                ratings[i] = 95;
            }
        }
        this.setRatings(ratings);
    }
    
    public void setRatingsUDFA() {
        final int[] ratings = new int[3];
        for (int i = 0; i < 3; ++i) {
            ratings[i] = (int)(Math.random() * 15.0) + 55;
        }
        this.setRatings(ratings);
    }
    
    public void setTeam(final Team team) {
        this.team = team;
    }
    
    public void setTeamsPlayedForCSV(final String s) {
        this.teamsPlayedFor.addAll(Arrays.asList(s.split(",")));
    }
    
    protected void setVariables(final int[] array, final String teamsPlayedForCSV) {
        final boolean b = true;
        this.age = array[0];
        this.rookieYear = array[1];
        this.ratPot = array[2];
        this.ratFootIQ = array[3];
        this.ratDur = array[4];
        this.ratImprovement = array[5];
        this.setRatings(Arrays.copyOfRange(array, 6, 9));
        this.gamesPlayed = array[9];
        this.statsWins = array[10];
        this.wonAllPro = (array[11] == 1);
        this.wonMVP = (array[12] == 1 && b);
        this.careerGamesPlayed = array[13];
        this.careerWins = array[14];
        this.careerAllPro = array[15];
        this.careerMVP = array[16];
        this.careerChamps = array[17];
        this.contract = new Contract(this, array[18], array[19] / 10.0);
        if (array[20] != -1) {
            this.injury = new Injury(this, array[20], array[21]);
        }
        this.checkMinimums();
        this.draftPickNum = array[22];
        this.setTeamsPlayedForCSV(teamsPlayedForCSV);
    }
    
    public void setWonAllPro(final boolean wonAllPro) {
        this.wonAllPro = wonAllPro;
    }
    
    public void setWonMVP(final boolean wonMVP) {
        this.wonMVP = wonMVP;
    }
}
