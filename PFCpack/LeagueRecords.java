// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

public class LeagueRecords
{
    private HashMap<String, Record> records;
    public final String[] recordsList;
    
    public LeagueRecords() {
        this.recordsList = new String[] { "TEAM", "Team PPG", "Team Opp PPG", "Team YPG", "Team Opp YPG", "Team TO Diff", "SEASON", "Pass Yards", "Pass TDs", "Interceptions", "Comp Percent", "Rush Yards", "Rush TDs", "Rush Fumbles", "Rec Yards", "Rec TDs", "Catch Percent", "CAREER", "Career Pass Yards", "Career Pass TDs", "Career Interceptions", "Career Rush Yards", "Career Rush TDs", "Career Rush Fumbles", "Career Rec Yards", "Career Rec TDs" };
        (this.records = new HashMap<String, Record>()).put("TEAM", null);
        this.records.put("Team PPG", new Record(0, "XXX", 0));
        this.records.put("Team Opp PPG", new Record(1000, "XXX", 0));
        this.records.put("Team YPG", new Record(0, "XXX", 0));
        this.records.put("Team Opp YPG", new Record(1000, "XXX", 0));
        this.records.put("Team TO Diff", new Record(0, "XXX", 0));
        this.records.put("SEASON", null);
        this.records.put("Pass Yards", new Record(0, "XXX", 0));
        this.records.put("Pass TDs", new Record(0, "XXX", 0));
        this.records.put("Interceptions", new Record(0, "XXX", 0));
        this.records.put("Comp Percent", new Record(0, "XXX", 0));
        this.records.put("Rush Yards", new Record(0, "XXX", 0));
        this.records.put("Rush TDs", new Record(0, "XXX", 0));
        this.records.put("Rush Fumbles", new Record(0, "XXX", 0));
        this.records.put("Rec Yards", new Record(0, "XXX", 0));
        this.records.put("Rec TDs", new Record(0, "XXX", 0));
        this.records.put("Catch Percent", new Record(0, "XXX", 0));
        this.records.put("CAREER", null);
        this.records.put("Career Pass Yards", new Record(0, "XXX", 0));
        this.records.put("Career Pass TDs", new Record(0, "XXX", 0));
        this.records.put("Career Interceptions", new Record(0, "XXX", 0));
        this.records.put("Career Rush Yards", new Record(0, "XXX", 0));
        this.records.put("Career Rush TDs", new Record(0, "XXX", 0));
        this.records.put("Career Rush Fumbles", new Record(0, "XXX", 0));
        this.records.put("Career Rec Yards", new Record(0, "XXX", 0));
        this.records.put("Career Rec TDs", new Record(0, "XXX", 0));
    }
    
    public LeagueRecords(final ArrayList<String> list) {
        this.recordsList = new String[] { "TEAM", "Team PPG", "Team Opp PPG", "Team YPG", "Team Opp YPG", "Team TO Diff", "SEASON", "Pass Yards", "Pass TDs", "Interceptions", "Comp Percent", "Rush Yards", "Rush TDs", "Rush Fumbles", "Rec Yards", "Rec TDs", "Catch Percent", "CAREER", "Career Pass Yards", "Career Pass TDs", "Career Interceptions", "Career Rush Yards", "Career Rush TDs", "Career Rush Fumbles", "Career Rec Yards", "Career Rec TDs" };
        this.records = new HashMap<String, Record>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String[] split = iterator.next().split(",");
            this.records.put(split[0], new Record(Integer.parseInt(split[1]), split[2], Integer.parseInt(split[3])));
        }
    }
    
    private String recordStrCSV(final String s) {
        if (!this.records.containsKey(s)) {
            return "ERROR,ERROR,ERROR,ERROR";
        }
        final Record record = this.records.get(s);
        if (record == null) {
            return s + ",-1,-1,-1";
        }
        return s + "," + record.getNumber() + "," + record.getHolder() + "," + record.getYear();
    }
    
    public String brokenRecordsStr(final int n, final String s) {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, Record> entry : this.records.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getHolder().split(" ")[0].equals(s) && entry.getValue().getYear() == n && !entry.getKey().split(" ")[0].equals("Career")) {
                sb.append(entry.getValue().getHolder() + " broke the record for " + entry.getKey() + " with " + entry.getValue().getNumber() + "!\n");
            }
        }
        return sb.toString();
    }
    
    public void changeAbbrRecords(final String s, final String s2) {
        final String[] recordsList = this.recordsList;
        for (int length = recordsList.length, i = 0; i < length; ++i) {
            final Record record = this.records.get(recordsList[i]);
            if (record != null && record.getHolder().split(" ")[0].equals(s)) {
                record.changeAbbr(s2);
            }
        }
    }
    
    public void checkRecord(final String s, final int n, final String s2, final int n2) {
        if (s.equals("Team Opp PPG") || s.equals("Team Opp YPG")) {
            if (this.records.containsKey(s) && n < this.records.get(s).getNumber()) {
                this.records.remove(s);
                this.records.put(s, new Record(n, s2, n2));
            }
            else if (!this.records.containsKey(s)) {
                this.records.put(s, new Record(n, s2, n2));
            }
        }
        else {
            if (this.records.containsKey(s) && n > this.records.get(s).getNumber()) {
                this.records.remove(s);
                this.records.put(s, new Record(n, s2, n2));
                return;
            }
            if (!this.records.containsKey(s)) {
                this.records.put(s, new Record(n, s2, n2));
            }
        }
    }
    
    public String getRecordsStr() {
        final StringBuilder sb = new StringBuilder();
        final String[] recordsList = this.recordsList;
        for (int length = recordsList.length, i = 0; i < length; ++i) {
            sb.append(this.recordStrCSV(recordsList[i]) + "\n");
        }
        return sb.toString();
    }
    
    public class Record
    {
        private String holder;
        private int number;
        private int year;
        
        public Record(final int number, final String holder, final int year) {
            this.number = number;
            this.holder = holder;
            this.year = year;
        }
        
        private void changeAbbr(final String holder) {
            final String[] split = this.holder.split(" ");
            this.holder = holder;
            for (int i = 1; i < split.length; ++i) {
                this.holder = this.holder + " " + split[i];
            }
        }
        
        public String getHolder() {
            return this.holder;
        }
        
        public int getNumber() {
            return this.number;
        }
        
        public int getYear() {
            return this.year;
        }
    }
}
