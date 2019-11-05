package com.achijones.profootballcoach;

import PFCpack.Player;
import PFCpack.Team;
import PFCpack.Trade;
import PFCpack.TradePiece;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TradeListAdapter
  extends ArrayAdapter<TradePiece>
{
  private final Context context;
  private final MainActivity mainAct;
  private final TradePiece[] pieces;
  private final Trade trade;
  
  public TradeListAdapter(Context paramContext, MainActivity paramMainActivity, TradePiece[] paramArrayOfTradePiece, Trade paramTrade)
  {
    super(paramContext, 2130968671, paramArrayOfTradePiece);
    context = paramContext;
    mainAct = paramMainActivity;
    pieces = paramArrayOfTradePiece;
    trade = paramTrade;
  }
  
  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(2130968671, paramViewGroup, false);
    paramViewGroup = (TextView)paramView.findViewById(2131558707);
    TextView localTextView = (TextView)paramView.findViewById(2131558708);
    Button localButton = (Button)paramView.findViewById(2131558709);
    if (pieces[paramInt] != null)
    {
      if ((pieces[paramInt] instanceof Player)) {
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            mainAct.examinePlayer((Player)pieces[paramInt]);
          }
        });
      }
      for (;;)
      {
        paramViewGroup.setText(pieces[paramInt].getTradePieceInfo());
        localTextView.setText("");
        return paramView;
        localButton.setVisibility(8);
      }
    }
    if (paramInt == 0)
    {
      paramViewGroup.setText("");
      localTextView.setText(trade.getATeam().getStrAbbrWL() + " offer:");
      localButton.setVisibility(8);
      return paramView;
    }
    paramViewGroup.setText("");
    localTextView.setText(trade.getBTeam().getStrAbbrWL() + " offer:");
    localButton.setVisibility(8);
    return paramView;
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.TradeListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */