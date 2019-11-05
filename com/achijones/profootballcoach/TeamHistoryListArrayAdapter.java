package com.achijones.profootballcoach;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TeamHistoryListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final String[] values;
  
  public TeamHistoryListArrayAdapter(Context paramContext, String[] paramArrayOfString)
  {
    super(paramContext, 2130968662, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968662, paramViewGroup, false);
    paramView = (TextView)localView.findViewById(2131558680);
    TextView localTextView = (TextView)localView.findViewById(2131558681);
    String[] arrayOfString = values[paramInt].split(">");
    int k;
    if (arrayOfString.length > 1)
    {
      paramView.setText(arrayOfString[0]);
      paramViewGroup = arrayOfString[0].split(" ");
      i = 0;
      j = 0;
      m = paramViewGroup.length;
      paramInt = 0;
      if (paramInt < m)
      {
        Object localObject = paramViewGroup[paramInt];
        if (((String)localObject).equals("CBW")) {
          k = 1;
        }
        for (;;)
        {
          paramInt += 1;
          i = k;
          break;
          k = i;
          if (((String)localObject).equals("DW"))
          {
            j = 1;
            k = i;
          }
        }
      }
      if (i != 0) {
        paramView.setTextColor(Color.parseColor("#FF9933"));
      }
      for (;;)
      {
        paramView = "";
        paramInt = 1;
        while (paramInt < arrayOfString.length)
        {
          paramViewGroup = paramView + arrayOfString[paramInt];
          paramView = paramViewGroup;
          if (paramInt != arrayOfString.length - 1) {
            paramView = paramViewGroup + "\n";
          }
          paramInt += 1;
        }
        if (0 != 0) {
          paramView.setTextColor(Color.parseColor("#00B300"));
        } else if (j != 0) {
          paramView.setTextColor(Color.parseColor("#1A75FF"));
        }
      }
      localTextView.setText(paramView);
      return localView;
    }
    paramView.setText(values[paramInt]);
    paramViewGroup = values[paramInt].split(" ");
    int i = 0;
    int j = 0;
    int m = paramViewGroup.length;
    paramInt = 0;
    if (paramInt < m)
    {
      arrayOfString = paramViewGroup[paramInt];
      if (arrayOfString.equals("CBW")) {
        k = 1;
      }
      for (;;)
      {
        paramInt += 1;
        i = k;
        break;
        k = i;
        if (arrayOfString.equals("DW"))
        {
          j = 1;
          k = i;
        }
      }
    }
    if (i != 0) {
      paramView.setTextColor(Color.parseColor("#FF9933"));
    }
    for (;;)
    {
      localTextView.setVisibility(8);
      return localView;
      if (0 != 0) {
        paramView.setTextColor(Color.parseColor("#00B300"));
      } else if (j != 0) {
        paramView.setTextColor(Color.parseColor("#1A75FF"));
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.TeamHistoryListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */