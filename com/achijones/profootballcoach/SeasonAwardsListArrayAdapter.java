package com.achijones.profootballcoach;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SeasonAwardsListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private String userTeamAbbr;
  private final String[] values;
  
  public SeasonAwardsListArrayAdapter(Context paramContext, String[] paramArrayOfString, String paramString)
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
    TextView localTextView1 = (TextView)paramView.findViewById(2131558643);
    TextView localTextView2 = (TextView)paramView.findViewById(2131558644);
    String[] arrayOfString = values[paramInt].split("\n");
    if (arrayOfString.length == 3)
    {
      paramViewGroup.setText(arrayOfString[0]);
      localTextView1.setText(arrayOfString[1]);
      localTextView2.setText(arrayOfString[2]);
      if (arrayOfString[0].split("\\(")[0].equals(userTeamAbbr)) {
        paramViewGroup.setTextColor(Color.parseColor("#1A75FF"));
      }
    }
    do
    {
      return paramView;
      if (arrayOfString.length != 2) {
        break;
      }
      paramViewGroup.setText(arrayOfString[0]);
      localTextView1.setText(arrayOfString[1]);
      localTextView2.setVisibility(8);
    } while (!arrayOfString[0].split("\\(")[0].equals(userTeamAbbr));
    paramViewGroup.setTextColor(Color.parseColor("#1A75FF"));
    return paramView;
    localTextView1.setText(values[paramInt]);
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.SeasonAwardsListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */