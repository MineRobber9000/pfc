package com.achijones.profootballcoach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class SaveFilesListArrayAdapter
  extends ArrayAdapter<String>
{
  private final Context context;
  private final String[] values;
  
  public SaveFilesListArrayAdapter(Context paramContext, String[] paramArrayOfString)
  {
    super(paramContext, 2130968609, paramArrayOfString);
    context = paramContext;
    values = paramArrayOfString;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968609, paramViewGroup, false);
    ((Button)paramView.findViewById(2131558555)).setVisibility(8);
    ((Button)paramView.findViewById(2131558556)).setVisibility(8);
    paramViewGroup = values[paramInt].split(">");
    TextView localTextView = (TextView)paramView.findViewById(2131558552);
    localTextView.setPadding(5, 0, 5, 0);
    localTextView.setText(paramViewGroup[0]);
    localTextView.setClickable(false);
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.SaveFilesListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */