package com.achijones.profootballcoach;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LeagueHistoryListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private String userTeamAbbr;
  private final String[] values;
  
  public LeagueHistoryListArrayAdapter(Context paramContext, String[] paramArrayOfString, String paramString)
  {
    super(paramContext, 2130968637, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
    userTeamAbbr = paramString;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968637, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558642);
    Object localObject = (TextView)paramView.findViewById(2131558643);
    TextView localTextView = (TextView)paramView.findViewById(2131558644);
    String[] arrayOfString = values[paramInt].split("\n");
    if (arrayOfString.length == 3)
    {
      paramViewGroup.setText(arrayOfString[0]);
      ((TextView)localObject).setText(arrayOfString[1]);
      localTextView.setText(arrayOfString[2]);
      if ((arrayOfString[1].split(" ").length > 1) && (arrayOfString[1].split(" ")[1].equals(userTeamAbbr))) {
        ((TextView)localObject).setTextColor(Color.parseColor("#1A75FF"));
      }
      if ((arrayOfString[2].split(" ").length > 5) && (arrayOfString[2].split(" ")[5].equals(userTeamAbbr))) {
        localTextView.setTextColor(Color.parseColor("#1A75FF"));
      }
      localObject = arrayOfString[0].split(" ");
      if ((localObject.length > 2) && ((localObject[0].equals(userTeamAbbr)) || (localObject[1].equals(userTeamAbbr)))) {
        paramViewGroup.setTextColor(Color.parseColor("#1A75FF"));
      }
    }
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.LeagueHistoryListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */