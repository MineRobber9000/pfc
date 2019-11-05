package com.achijones.profootballcoach;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class PlayerStatsListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final String[] values;
  
  public PlayerStatsListArrayAdapter(Context paramContext, String[] paramArrayOfString)
  {
    super(paramContext, 2130968609, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
  }
  
  private void colorizeRatings(TextView paramTextView, String paramString)
  {
    String[] arrayOfString = paramString.split(" ");
    if ((arrayOfString.length > 0) && (paramString.split(",").length == 1))
    {
      paramString = arrayOfString[(arrayOfString.length - 1)];
      if ((!paramString.equals("A")) && (!paramString.equals("A+"))) {
        break label58;
      }
      paramTextView.setTextColor(Color.parseColor("#006600"));
    }
    label58:
    do
    {
      return;
      if ((paramString.equals("B")) || (paramString.equals("B+")))
      {
        paramTextView.setTextColor(Color.parseColor("#00b300"));
        return;
      }
      if ((paramString.equals("C")) || (paramString.equals("C+")))
      {
        paramTextView.setTextColor(Color.parseColor("#e68a00"));
        return;
      }
      if ((paramString.equals("D")) || (paramString.equals("D+")))
      {
        paramTextView.setTextColor(Color.parseColor("#cc3300"));
        return;
      }
    } while ((!paramString.equals("F")) && (!paramString.equals("F+")));
    paramTextView.setTextColor(Color.parseColor("#990000"));
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968609, paramViewGroup, false);
    paramViewGroup = values[paramInt].split(">");
    if (paramViewGroup.length == 2)
    {
      TextView localTextView1 = (TextView)paramView.findViewById(2131558552);
      TextView localTextView2 = (TextView)paramView.findViewById(2131558553);
      if (values[paramInt].substring(0, 3).equals("[I]"))
      {
        localTextView1.setText(paramViewGroup[0].substring(3));
        localTextView2.setText(paramViewGroup[1]);
        localTextView1.setTextColor(-65536);
        localTextView2.setTextColor(-65536);
      }
      for (;;)
      {
        ((TextView)paramView.findViewById(2131558554)).setText("");
        ((Button)paramView.findViewById(2131558555)).setVisibility(8);
        ((Button)paramView.findViewById(2131558556)).setVisibility(8);
        return paramView;
        localTextView1.setText(paramViewGroup[0]);
        localTextView2.setText(paramViewGroup[1]);
        colorizeRatings(localTextView1, paramViewGroup[0]);
        colorizeRatings(localTextView2, paramViewGroup[1]);
      }
    }
    paramViewGroup = (TextView)paramView.findViewById(2131558554);
    if (values[paramInt].substring(0, 3).equals("[B]"))
    {
      paramViewGroup.setText(values[paramInt].substring(3));
      paramViewGroup.setTypeface(null, 1);
    }
    for (;;)
    {
      ((TextView)paramView.findViewById(2131558552)).setVisibility(8);
      ((TextView)paramView.findViewById(2131558553)).setVisibility(8);
      break;
      if (values[paramInt].substring(0, 3).equals("[I]"))
      {
        paramViewGroup.setText(values[paramInt].substring(3));
        paramViewGroup.setTextColor(-65536);
      }
      else
      {
        paramViewGroup.setText(values[paramInt]);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.PlayerStatsListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */