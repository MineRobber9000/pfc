package com.achijones.profootballcoach;

import PFCpack.Player;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import java.util.ArrayList;

public class TeamLineupArrayAdapter
  extends ArrayAdapter<Player>
{
  private final Context context;
  public final ArrayList<Player> players;
  public int playersRequired;
  public ArrayList<Player> playersSelected;
  
  public TeamLineupArrayAdapter(Context paramContext, ArrayList<Player> paramArrayList, int paramInt)
  {
    super(paramContext, 2130968664, paramArrayList);
    context = paramContext;
    players = paramArrayList;
    playersRequired = paramInt;
    playersSelected = new ArrayList();
    int i = 0;
    while (i < paramInt)
    {
      playersSelected.add(players.get(i));
      i += 1;
    }
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968664, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558687);
    CheckBox localCheckBox;
    if (((Player)players.get(paramInt)).getInjury() == null)
    {
      paramViewGroup.setText(((Player)players.get(paramInt)).getInfoForLineup());
      localCheckBox = (CheckBox)paramView.findViewById(2131558688);
      if (!playersSelected.contains(players.get(paramInt))) {
        break label137;
      }
      localCheckBox.setChecked(true);
    }
    for (;;)
    {
      localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          if (paramAnonymousBoolean)
          {
            playersSelected.add(players.get(paramInt));
            return;
          }
          playersSelected.remove(players.get(paramInt));
        }
      });
      return paramView;
      paramViewGroup.setText(((Player)players.get(paramInt)).getInfoLineupInjury());
      break;
      label137:
      if (((Player)players.get(paramInt)).isInjured())
      {
        localCheckBox.setEnabled(false);
        paramViewGroup.setTextColor(-65536);
      }
      else
      {
        localCheckBox.setChecked(false);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.TeamLineupArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */