package PFCpack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class LeagueRecords
{
  private HashMap<String, Record> records = new HashMap();
  public final String[] recordsList = { "TEAM", "Team PPG", "Team Opp PPG", "Team YPG", "Team Opp YPG", "Team TO Diff", "SEASON", "Pass Yards", "Pass TDs", "Interceptions", "Comp Percent", "Rush Yards", "Rush TDs", "Rush Fumbles", "Rec Yards", "Rec TDs", "Catch Percent", "CAREER", "Career Pass Yards", "Career Pass TDs", "Career Interceptions", "Career Rush Yards", "Career Rush TDs", "Career Rush Fumbles", "Career Rec Yards", "Career Rec TDs" };
  
  public LeagueRecords()
  {
    records.put("TEAM", null);
    records.put("Team PPG", new Record(0, "XXX", 0));
    records.put("Team Opp PPG", new Record(1000, "XXX", 0));
    records.put("Team YPG", new Record(0, "XXX", 0));
    records.put("Team Opp YPG", new Record(1000, "XXX", 0));
    records.put("Team TO Diff", new Record(0, "XXX", 0));
    records.put("SEASON", null);
    records.put("Pass Yards", new Record(0, "XXX", 0));
    records.put("Pass TDs", new Record(0, "XXX", 0));
    records.put("Interceptions", new Record(0, "XXX", 0));
    records.put("Comp Percent", new Record(0, "XXX", 0));
    records.put("Rush Yards", new Record(0, "XXX", 0));
    records.put("Rush TDs", new Record(0, "XXX", 0));
    records.put("Rush Fumbles", new Record(0, "XXX", 0));
    records.put("Rec Yards", new Record(0, "XXX", 0));
    records.put("Rec TDs", new Record(0, "XXX", 0));
    records.put("Catch Percent", new Record(0, "XXX", 0));
    records.put("CAREER", null);
    records.put("Career Pass Yards", new Record(0, "XXX", 0));
    records.put("Career Pass TDs", new Record(0, "XXX", 0));
    records.put("Career Interceptions", new Record(0, "XXX", 0));
    records.put("Career Rush Yards", new Record(0, "XXX", 0));
    records.put("Career Rush TDs", new Record(0, "XXX", 0));
    records.put("Career Rush Fumbles", new Record(0, "XXX", 0));
    records.put("Career Rec Yards", new Record(0, "XXX", 0));
    records.put("Career Rec TDs", new Record(0, "XXX", 0));
  }
  
  public LeagueRecords(ArrayList<String> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      String[] arrayOfString = ((String)paramArrayList.next()).split(",");
      records.put(arrayOfString[0], new Record(Integer.parseInt(arrayOfString[1]), arrayOfString[2], Integer.parseInt(arrayOfString[3])));
    }
  }
  
  private String recordStrCSV(String paramString)
  {
    if (records.containsKey(paramString))
    {
      Record localRecord = (Record)records.get(paramString);
      if (localRecord == null) {
        return paramString + ",-1,-1,-1";
      }
      return paramString + "," + localRecord.getNumber() + "," + localRecord.getHolder() + "," + localRecord.getYear();
    }
    return "ERROR,ERROR,ERROR,ERROR";
  }
  
  public String brokenRecordsStr(int paramInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = records.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((localEntry.getValue() != null) && (((Record)localEntry.getValue()).getHolder().split(" ")[0].equals(paramString)) && (((Record)localEntry.getValue()).getYear() == paramInt) && (!((String)localEntry.getKey()).split(" ")[0].equals("Career"))) {
        localStringBuilder.append(((Record)localEntry.getValue()).getHolder() + " broke the record for " + (String)localEntry.getKey() + " with " + ((Record)localEntry.getValue()).getNumber() + "!\n");
      }
    }
    return localStringBuilder.toString();
  }
  
  public void changeAbbrRecords(String paramString1, String paramString2)
  {
    String[] arrayOfString = recordsList;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfString[i];
      localObject = (Record)records.get(localObject);
      if ((localObject != null) && (localObject.getHolder().split(" ")[0].equals(paramString1))) {
        ((Record)localObject).changeAbbr(paramString2);
      }
      i += 1;
    }
  }
  
  public void checkRecord(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    if ((paramString1.equals("Team Opp PPG")) || (paramString1.equals("Team Opp YPG"))) {
      if ((records.containsKey(paramString1)) && (paramInt1 < ((Record)records.get(paramString1)).getNumber())) {
        records.remove(paramString1);
      }
    }
    do
    {
      records.put(paramString1, new Record(paramInt1, paramString2, paramInt2));
      do
      {
        return;
      } while (records.containsKey(paramString1));
      records.put(paramString1, new Record(paramInt1, paramString2, paramInt2));
      return;
      if ((records.containsKey(paramString1)) && (paramInt1 > ((Record)records.get(paramString1)).getNumber()))
      {
        records.remove(paramString1);
        records.put(paramString1, new Record(paramInt1, paramString2, paramInt2));
        return;
      }
    } while (records.containsKey(paramString1));
    records.put(paramString1, new Record(paramInt1, paramString2, paramInt2));
  }
  
  public String getRecordsStr()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = recordsList;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      localStringBuilder.append(recordStrCSV(str) + "\n");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public class Record
  {
    private String holder;
    private int number;
    private int year;
    
    public Record(int paramInt1, String paramString, int paramInt2)
    {
      number = paramInt1;
      holder = paramString;
      year = paramInt2;
    }
    
    private void changeAbbr(String paramString)
    {
      String[] arrayOfString = holder.split(" ");
      holder = paramString;
      int i = 1;
      while (i < arrayOfString.length)
      {
        holder = (holder + " " + arrayOfString[i]);
        i += 1;
      }
    }
    
    public String getHolder()
    {
      return holder;
    }
    
    public int getNumber()
    {
      return number;
    }
    
    public int getYear()
    {
      return year;
    }
  }
}

/* Location:
 * Qualified Name:     PFCpack.LeagueRecords
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */