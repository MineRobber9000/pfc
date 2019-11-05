package com.achijones.profootballcoach;

import PFCpack.Team;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import org.json.JSONObject;

class OnlineActivity$GetLeaderboard
  extends AsyncTask<String, String, String>
{
  JSONObject leaderboardJSON;
  boolean success;
  
  OnlineActivity$GetLeaderboard(OnlineActivity paramOnlineActivity) {}
  
  /* Error */
  protected String doInBackground(String... paramVarArgs)
  {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: aaload
    //   3: astore 8
    //   5: aconst_null
    //   6: astore 5
    //   8: aconst_null
    //   9: astore_3
    //   10: aconst_null
    //   11: astore 6
    //   13: aconst_null
    //   14: astore 7
    //   16: aload 6
    //   18: astore 4
    //   20: new 37	java/net/URL
    //   23: dup
    //   24: ldc 39
    //   26: invokespecial 42	java/net/URL:<init>	(Ljava/lang/String;)V
    //   29: invokevirtual 46	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   32: checkcast 48	java/net/HttpURLConnection
    //   35: astore_1
    //   36: aload 6
    //   38: astore 4
    //   40: aload_1
    //   41: astore_3
    //   42: aload_1
    //   43: astore 5
    //   45: aload_1
    //   46: iconst_1
    //   47: invokevirtual 52	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   50: aload 6
    //   52: astore 4
    //   54: aload_1
    //   55: astore_3
    //   56: aload_1
    //   57: astore 5
    //   59: aload_1
    //   60: ldc 54
    //   62: invokevirtual 57	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   65: aload 6
    //   67: astore 4
    //   69: aload_1
    //   70: astore_3
    //   71: aload_1
    //   72: astore 5
    //   74: aload_1
    //   75: ldc 59
    //   77: ldc 61
    //   79: invokevirtual 65	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: aload 6
    //   84: astore 4
    //   86: aload_1
    //   87: astore_3
    //   88: aload_1
    //   89: astore 5
    //   91: aload_1
    //   92: ldc 67
    //   94: ldc 61
    //   96: invokevirtual 65	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   99: aload 6
    //   101: astore 4
    //   103: aload_1
    //   104: astore_3
    //   105: aload_1
    //   106: astore 5
    //   108: new 69	java/io/BufferedWriter
    //   111: dup
    //   112: new 71	java/io/OutputStreamWriter
    //   115: dup
    //   116: aload_1
    //   117: invokevirtual 75	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   120: ldc 77
    //   122: invokespecial 80	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   125: invokespecial 83	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   128: astore 9
    //   130: aload 6
    //   132: astore 4
    //   134: aload_1
    //   135: astore_3
    //   136: aload_1
    //   137: astore 5
    //   139: aload 9
    //   141: aload 8
    //   143: invokevirtual 88	java/io/Writer:write	(Ljava/lang/String;)V
    //   146: aload 6
    //   148: astore 4
    //   150: aload_1
    //   151: astore_3
    //   152: aload_1
    //   153: astore 5
    //   155: aload 9
    //   157: invokevirtual 91	java/io/Writer:flush	()V
    //   160: aload 6
    //   162: astore 4
    //   164: aload_1
    //   165: astore_3
    //   166: aload_1
    //   167: astore 5
    //   169: aload 9
    //   171: invokevirtual 94	java/io/Writer:close	()V
    //   174: aload 6
    //   176: astore 4
    //   178: aload_1
    //   179: astore_3
    //   180: aload_1
    //   181: astore 5
    //   183: aload_1
    //   184: invokevirtual 98	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   187: astore 9
    //   189: aload 6
    //   191: astore 4
    //   193: aload_1
    //   194: astore_3
    //   195: aload_1
    //   196: astore 5
    //   198: new 100	java/lang/StringBuffer
    //   201: dup
    //   202: invokespecial 101	java/lang/StringBuffer:<init>	()V
    //   205: astore 8
    //   207: aload 9
    //   209: ifnonnull +32 -> 241
    //   212: aload_1
    //   213: ifnull +7 -> 220
    //   216: aload_1
    //   217: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
    //   220: iconst_0
    //   221: ifeq +11 -> 232
    //   224: new 106	java/lang/NullPointerException
    //   227: dup
    //   228: invokespecial 107	java/lang/NullPointerException:<init>	()V
    //   231: athrow
    //   232: aconst_null
    //   233: areturn
    //   234: astore_1
    //   235: aload_1
    //   236: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   239: aconst_null
    //   240: areturn
    //   241: aload 6
    //   243: astore 4
    //   245: aload_1
    //   246: astore_3
    //   247: aload_1
    //   248: astore 5
    //   250: new 112	java/io/BufferedReader
    //   253: dup
    //   254: new 114	java/io/InputStreamReader
    //   257: dup
    //   258: aload 9
    //   260: invokespecial 117	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   263: invokespecial 120	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   266: astore 6
    //   268: aload 6
    //   270: invokevirtual 124	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   273: astore_3
    //   274: aload_3
    //   275: ifnull +72 -> 347
    //   278: aload 8
    //   280: new 126	java/lang/StringBuilder
    //   283: dup
    //   284: invokespecial 127	java/lang/StringBuilder:<init>	()V
    //   287: aload_3
    //   288: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: ldc -123
    //   293: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: invokevirtual 136	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   299: invokevirtual 139	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   302: pop
    //   303: goto -35 -> 268
    //   306: astore_3
    //   307: aload_1
    //   308: astore 5
    //   310: aload 6
    //   312: astore_1
    //   313: aload_3
    //   314: astore 6
    //   316: aload_1
    //   317: astore 4
    //   319: aload 5
    //   321: astore_3
    //   322: aload 6
    //   324: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   327: aload 5
    //   329: ifnull +8 -> 337
    //   332: aload 5
    //   334: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
    //   337: aload_1
    //   338: ifnull +7 -> 345
    //   341: aload_1
    //   342: invokevirtual 140	java/io/BufferedReader:close	()V
    //   345: aconst_null
    //   346: areturn
    //   347: aload 8
    //   349: invokevirtual 144	java/lang/StringBuffer:length	()I
    //   352: istore_2
    //   353: iload_2
    //   354: ifne +31 -> 385
    //   357: aload_1
    //   358: ifnull +7 -> 365
    //   361: aload_1
    //   362: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
    //   365: aload 6
    //   367: ifnull +8 -> 375
    //   370: aload 6
    //   372: invokevirtual 140	java/io/BufferedReader:close	()V
    //   375: aconst_null
    //   376: areturn
    //   377: astore_1
    //   378: aload_1
    //   379: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   382: goto -7 -> 375
    //   385: aload 8
    //   387: invokevirtual 145	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   390: astore_3
    //   391: getstatic 151	java/lang/System:out	Ljava/io/PrintStream;
    //   394: new 126	java/lang/StringBuilder
    //   397: dup
    //   398: invokespecial 127	java/lang/StringBuilder:<init>	()V
    //   401: ldc -103
    //   403: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: aload_3
    //   407: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: invokevirtual 136	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   413: invokevirtual 158	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   416: new 160	org/json/JSONObject
    //   419: dup
    //   420: aload_3
    //   421: invokespecial 161	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   424: astore 4
    //   426: aload 4
    //   428: ldc -94
    //   430: invokevirtual 166	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   433: checkcast 168	java/lang/Integer
    //   436: invokevirtual 171	java/lang/Integer:intValue	()I
    //   439: ifne +36 -> 475
    //   442: getstatic 151	java/lang/System:out	Ljava/io/PrintStream;
    //   445: ldc -83
    //   447: invokevirtual 158	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   450: aload_0
    //   451: iconst_0
    //   452: putfield 175	com/achijones/profootballcoach/OnlineActivity$GetLeaderboard:success	Z
    //   455: aload_1
    //   456: ifnull +7 -> 463
    //   459: aload_1
    //   460: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
    //   463: aload 6
    //   465: ifnull +8 -> 473
    //   468: aload 6
    //   470: invokevirtual 140	java/io/BufferedReader:close	()V
    //   473: aload_3
    //   474: areturn
    //   475: aload_0
    //   476: iconst_1
    //   477: putfield 175	com/achijones/profootballcoach/OnlineActivity$GetLeaderboard:success	Z
    //   480: aload_0
    //   481: aload 4
    //   483: putfield 177	com/achijones/profootballcoach/OnlineActivity$GetLeaderboard:leaderboardJSON	Lorg/json/JSONObject;
    //   486: goto -31 -> 455
    //   489: astore 4
    //   491: aload 4
    //   493: invokevirtual 178	java/lang/Exception:printStackTrace	()V
    //   496: goto -41 -> 455
    //   499: astore_3
    //   500: aload 6
    //   502: astore 4
    //   504: aload_1
    //   505: ifnull +7 -> 512
    //   508: aload_1
    //   509: invokevirtual 104	java/net/HttpURLConnection:disconnect	()V
    //   512: aload 4
    //   514: ifnull +8 -> 522
    //   517: aload 4
    //   519: invokevirtual 140	java/io/BufferedReader:close	()V
    //   522: aload_3
    //   523: athrow
    //   524: astore_1
    //   525: aload_1
    //   526: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   529: goto -56 -> 473
    //   532: astore_1
    //   533: aload_1
    //   534: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   537: goto -192 -> 345
    //   540: astore_1
    //   541: aload_1
    //   542: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   545: goto -23 -> 522
    //   548: astore 5
    //   550: aload_3
    //   551: astore_1
    //   552: aload 5
    //   554: astore_3
    //   555: goto -51 -> 504
    //   558: astore 6
    //   560: aload 7
    //   562: astore_1
    //   563: goto -247 -> 316
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	566	0	this	GetLeaderboard
    //   0	566	1	paramVarArgs	String[]
    //   352	2	2	i	int
    //   9	279	3	localObject1	Object
    //   306	8	3	localIOException1	java.io.IOException
    //   321	153	3	localObject2	Object
    //   499	52	3	localObject3	Object
    //   554	1	3	localObject4	Object
    //   18	464	4	localObject5	Object
    //   489	3	4	localException	Exception
    //   502	16	4	localObject6	Object
    //   6	327	5	arrayOfString	String[]
    //   548	5	5	localObject7	Object
    //   11	490	6	localObject8	Object
    //   558	1	6	localIOException2	java.io.IOException
    //   14	547	7	localObject9	Object
    //   3	383	8	localObject10	Object
    //   128	131	9	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   224	232	234	java/io/IOException
    //   268	274	306	java/io/IOException
    //   278	303	306	java/io/IOException
    //   347	353	306	java/io/IOException
    //   385	416	306	java/io/IOException
    //   416	455	306	java/io/IOException
    //   475	486	306	java/io/IOException
    //   491	496	306	java/io/IOException
    //   370	375	377	java/io/IOException
    //   416	455	489	java/lang/Exception
    //   475	486	489	java/lang/Exception
    //   268	274	499	finally
    //   278	303	499	finally
    //   347	353	499	finally
    //   385	416	499	finally
    //   416	455	499	finally
    //   475	486	499	finally
    //   491	496	499	finally
    //   468	473	524	java/io/IOException
    //   341	345	532	java/io/IOException
    //   517	522	540	java/io/IOException
    //   20	36	548	finally
    //   45	50	548	finally
    //   59	65	548	finally
    //   74	82	548	finally
    //   91	99	548	finally
    //   108	130	548	finally
    //   139	146	548	finally
    //   155	160	548	finally
    //   169	174	548	finally
    //   183	189	548	finally
    //   198	207	548	finally
    //   250	268	548	finally
    //   322	327	548	finally
    //   20	36	558	java/io/IOException
    //   45	50	558	java/io/IOException
    //   59	65	558	java/io/IOException
    //   74	82	558	java/io/IOException
    //   91	99	558	java/io/IOException
    //   108	130	558	java/io/IOException
    //   139	146	558	java/io/IOException
    //   155	160	558	java/io/IOException
    //   169	174	558	java/io/IOException
    //   183	189	558	java/io/IOException
    //   198	207	558	java/io/IOException
    //   250	268	558	java/io/IOException
  }
  
  protected void onPostExecute(String paramString)
  {
    if (!success)
    {
      Toast.makeText(this$0, "Something went wrong.", 0).show();
      return;
    }
    paramString = new AlertDialog.Builder(this$0);
    paramString.setTitle("Online ELO Leaderboard").setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).setView(this$0.getLayoutInflater().inflate(2130968660, null));
    paramString = paramString.create();
    paramString.show();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i < 50) {
        try
        {
          JSONObject localJSONObject = new JSONObject(leaderboardJSON.get("" + i).toString());
          localArrayList.add(i + 1 + "," + localJSONObject.get("name") + "," + localJSONObject.get("elo"));
          i += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
      }
    }
    ((ListView)paramString.findViewById(2131558679)).setAdapter(new TeamRankingsListArrayAdapter(this$0, localArrayList, this$0.userTeam.name));
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.OnlineActivity.GetLeaderboard
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */