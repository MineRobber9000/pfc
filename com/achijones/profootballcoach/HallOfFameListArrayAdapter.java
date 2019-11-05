package com.achijones.profootballcoach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HallOfFameListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final String[] values;
  
  public HallOfFameListArrayAdapter(Context paramContext, String[] paramArrayOfString)
  {
    super(paramContext, 2130968635, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968635, paramViewGroup, false);
    Object localObject = (TextView)paramView.findViewById(2131558637);
    paramViewGroup = (TextView)paramView.findViewById(2131558638);
    TextView localTextView = (TextView)paramView.findViewById(2131558639);
    String[] arrayOfString1 = values[paramInt].split("&");
    if (arrayOfString1.length > 1)
    {
      ((TextView)localObject).setText(arrayOfString1[0]);
      localObject = new StringBuilder();
      StringBuilder localStringBuilder = new StringBuilder();
      paramInt = 1;
      while (paramInt < arrayOfString1.length)
      {
        String[] arrayOfString2 = arrayOfString1[paramInt].split(">");
        ((StringBuilder)localObject).append(arrayOfString2[0]);
        if (paramInt != arrayOfString1.length - 1) {
          ((StringBuilder)localObject).append("\n");
        }
        if (arrayOfString2.length > 1)
        {
          localStringBuilder.append(arrayOfString2[1]);
          if (paramInt != arrayOfString1.length - 1) {
            localStringBuilder.append("\n");
          }
        }
        paramInt += 1;
      }
      paramViewGroup.setText(((StringBuilder)localObject).toString());
      localTextView.setText(localStringBuilder.toString());
    }
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.HallOfFameListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */