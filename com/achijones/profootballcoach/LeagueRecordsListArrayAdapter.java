package com.achijones.profootballcoach;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LeagueRecordsListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final String userTeamAbbr;
  private final String[] values;
  
  public LeagueRecordsListArrayAdapter(Context paramContext, String[] paramArrayOfString, String paramString)
  {
    super(paramContext, 2130968638, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
    userTeamAbbr = paramString;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968638, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558645);
    TextView localTextView1 = (TextView)paramView.findViewById(2131558646);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558647);
    String[] arrayOfString = values[paramInt].split(",");
    if (arrayOfString[1].equals("-1"))
    {
      paramViewGroup.setText("");
      localTextView1.setText(arrayOfString[0]);
      localTextView1.setTextSize(2, 18.0F);
      localTextView2.setText("");
    }
    do
    {
      do
      {
        return paramView;
      } while (arrayOfString[2].equals("XXX"));
      paramViewGroup.setText(arrayOfString[1]);
      localTextView1.setText(arrayOfString[0]);
      localTextView2.setText(arrayOfString[2] + "\n" + arrayOfString[3]);
    } while (!arrayOfString[2].split(" ")[0].equals(userTeamAbbr));
    localTextView2.setTextColor(Color.parseColor("#1A75FF"));
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.LeagueRecordsListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */