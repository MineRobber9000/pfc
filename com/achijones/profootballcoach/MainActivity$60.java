package com.achijones.profootballcoach;

import PFCpack.Team;
import PFCpack.Trade;
import PFCpack.TradePiece;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import java.util.ArrayList;

class MainActivity$60
  implements AdapterView.OnItemSelectedListener
{
  MainActivity$60(MainActivity paramMainActivity, ArrayList paramArrayList, ListView paramListView) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (this$0.currentTeam.userControlled) {}
    for (paramAdapterView = (Trade)val$tradeOffers.get(paramInt);; paramAdapterView = Trade.getTradeOfferFromUserTeam(this$0.userTeam, this$0.currentTeam, this$0.currentTeam.tradingBlock))
    {
      paramView = new TradePiece[paramAdapterView.getAOffer().size() + 2 + paramAdapterView.getBOffer().size()];
      paramView[0] = null;
      paramInt = 0;
      while (paramInt < paramAdapterView.getAOffer().size())
      {
        paramView[(paramInt + 1)] = ((TradePiece)paramAdapterView.getAOffer().get(paramInt));
        paramInt += 1;
      }
    }
    paramView[(paramAdapterView.getAOffer().size() + 1)] = null;
    paramInt = 0;
    while (paramInt < paramAdapterView.getBOffer().size())
    {
      paramView[(paramAdapterView.getAOffer().size() + paramInt + 2)] = ((TradePiece)paramAdapterView.getBOffer().get(paramInt));
      paramInt += 1;
    }
    paramView = new TradeListAdapter(this$0, this$0, paramView, paramAdapterView);
    val$tradeOfferList.setAdapter(paramView);
    this$0.currentTrade = paramAdapterView;
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.60
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */