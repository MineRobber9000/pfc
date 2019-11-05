package com.achijones.profootballcoach;

import PFCpack.Team;
import PFCpack.TradePiece;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TradingBlockListArrayAdapter
  extends ArrayAdapter<TradePiece>
{
  private final Context context;
  private final MainActivity mainAct;
  private final TradePiece[] pieces;
  private final Team tradeTeam;
  private final Team userTeam;
  
  public TradingBlockListArrayAdapter(Context paramContext, MainActivity paramMainActivity, Team paramTeam1, Team paramTeam2, TradePiece[] paramArrayOfTradePiece)
  {
    super(paramContext, 2130968670, paramArrayOfTradePiece);
    context = paramContext;
    mainAct = paramMainActivity;
    pieces = paramArrayOfTradePiece;
    userTeam = paramTeam1;
    tradeTeam = paramTeam2;
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968670, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558704);
    Button localButton1 = (Button)paramView.findViewById(2131558706);
    Button localButton2 = (Button)paramView.findViewById(2131558705);
    if (pieces[paramInt] != null)
    {
      localButton1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          mainAct.removeTradePiece(pieces[paramInt]);
          notifyDataSetChanged();
        }
      });
      localButton2.setVisibility(8);
      paramViewGroup.setText(pieces[paramInt].getTradePieceInfo());
      return paramView;
    }
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        mainAct.tradeOffersDialog();
      }
    });
    localButton1.setVisibility(8);
    paramViewGroup.setVisibility(8);
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.TradingBlockListArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */