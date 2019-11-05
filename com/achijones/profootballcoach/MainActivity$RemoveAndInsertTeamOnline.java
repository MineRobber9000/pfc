package com.achijones.profootballcoach;

import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONObject;

class MainActivity$RemoveAndInsertTeamOnline
  extends AsyncTask<String, String, String>
{
  boolean success = false;
  
  MainActivity$RemoveAndInsertTeamOnline(MainActivity paramMainActivity) {}
  
  /* Error */
  protected String doInBackground(String... paramVarArgs)
  {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: aaload
    //   3: astore 8
    //   5: aconst_null
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 4
    //   11: aconst_null
    //   12: astore 7
    //   14: aconst_null
    //   15: astore 9
    //   17: aload 7
    //   19: astore 5
    //   21: new 35	java/net/URL
    //   24: dup
    //   25: ldc 37
    //   27: invokespecial 40	java/net/URL:<init>	(Ljava/lang/String;)V
    //   30: invokevirtual 44	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   33: checkcast 46	java/net/HttpURLConnection
    //   36: astore_1
    //   37: aload 7
    //   39: astore 5
    //   41: aload_1
    //   42: astore 4
    //   44: aload_1
    //   45: astore 6
    //   47: aload_1
    //   48: iconst_1
    //   49: invokevirtual 50	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   52: aload 7
    //   54: astore 5
    //   56: aload_1
    //   57: astore 4
    //   59: aload_1
    //   60: astore 6
    //   62: aload_1
    //   63: ldc 52
    //   65: invokevirtual 55	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   68: aload 7
    //   70: astore 5
    //   72: aload_1
    //   73: astore 4
    //   75: aload_1
    //   76: astore 6
    //   78: aload_1
    //   79: ldc 57
    //   81: ldc 59
    //   83: invokevirtual 63	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: aload 7
    //   88: astore 5
    //   90: aload_1
    //   91: astore 4
    //   93: aload_1
    //   94: astore 6
    //   96: aload_1
    //   97: ldc 65
    //   99: ldc 59
    //   101: invokevirtual 63	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   104: aload 7
    //   106: astore 5
    //   108: aload_1
    //   109: astore 4
    //   111: aload_1
    //   112: astore 6
    //   114: new 67	java/io/BufferedWriter
    //   117: dup
    //   118: new 69	java/io/OutputStreamWriter
    //   121: dup
    //   122: aload_1
    //   123: invokevirtual 73	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   126: ldc 75
    //   128: invokespecial 78	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   131: invokespecial 81	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   134: astore 10
    //   136: aload 7
    //   138: astore 5
    //   140: aload_1
    //   141: astore 4
    //   143: aload_1
    //   144: astore 6
    //   146: aload 10
    //   148: aload 8
    //   150: invokevirtual 86	java/io/Writer:write	(Ljava/lang/String;)V
    //   153: aload 7
    //   155: astore 5
    //   157: aload_1
    //   158: astore 4
    //   160: aload_1
    //   161: astore 6
    //   163: aload 10
    //   165: invokevirtual 89	java/io/Writer:flush	()V
    //   168: aload 7
    //   170: astore 5
    //   172: aload_1
    //   173: astore 4
    //   175: aload_1
    //   176: astore 6
    //   178: aload 10
    //   180: invokevirtual 92	java/io/Writer:close	()V
    //   183: aload 7
    //   185: astore 5
    //   187: aload_1
    //   188: astore 4
    //   190: aload_1
    //   191: astore 6
    //   193: aload_1
    //   194: invokevirtual 96	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   197: astore 10
    //   199: aload 7
    //   201: astore 5
    //   203: aload_1
    //   204: astore 4
    //   206: aload_1
    //   207: astore 6
    //   209: new 98	java/lang/StringBuffer
    //   212: dup
    //   213: invokespecial 99	java/lang/StringBuffer:<init>	()V
    //   216: astore 8
    //   218: aload 10
    //   220: ifnonnull +32 -> 252
    //   223: aload_1
    //   224: ifnull +7 -> 231
    //   227: aload_1
    //   228: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   231: iconst_0
    //   232: ifeq +11 -> 243
    //   235: new 104	java/lang/NullPointerException
    //   238: dup
    //   239: invokespecial 105	java/lang/NullPointerException:<init>	()V
    //   242: athrow
    //   243: aconst_null
    //   244: areturn
    //   245: astore_1
    //   246: aload_1
    //   247: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   250: aconst_null
    //   251: areturn
    //   252: aload 7
    //   254: astore 5
    //   256: aload_1
    //   257: astore 4
    //   259: aload_1
    //   260: astore 6
    //   262: new 110	java/io/BufferedReader
    //   265: dup
    //   266: new 112	java/io/InputStreamReader
    //   269: dup
    //   270: aload 10
    //   272: invokespecial 115	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   275: invokespecial 118	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   278: astore 7
    //   280: aload 7
    //   282: invokevirtual 122	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   285: astore 4
    //   287: aload 4
    //   289: ifnull +66 -> 355
    //   292: aload 8
    //   294: new 124	java/lang/StringBuilder
    //   297: dup
    //   298: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   301: aload 4
    //   303: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: ldc -125
    //   308: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   314: invokevirtual 137	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   317: pop
    //   318: goto -38 -> 280
    //   321: astore 8
    //   323: aload 7
    //   325: astore 5
    //   327: aload_1
    //   328: astore 4
    //   330: aload 8
    //   332: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   335: aload_1
    //   336: ifnull +7 -> 343
    //   339: aload_1
    //   340: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   343: aload 7
    //   345: ifnull +8 -> 353
    //   348: aload 7
    //   350: invokevirtual 138	java/io/BufferedReader:close	()V
    //   353: aconst_null
    //   354: areturn
    //   355: aload 8
    //   357: invokevirtual 142	java/lang/StringBuffer:length	()I
    //   360: istore_2
    //   361: iload_2
    //   362: ifne +31 -> 393
    //   365: aload_1
    //   366: ifnull +7 -> 373
    //   369: aload_1
    //   370: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   373: aload 7
    //   375: ifnull +8 -> 383
    //   378: aload 7
    //   380: invokevirtual 138	java/io/BufferedReader:close	()V
    //   383: aconst_null
    //   384: areturn
    //   385: astore_1
    //   386: aload_1
    //   387: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   390: goto -7 -> 383
    //   393: aload 8
    //   395: invokevirtual 143	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   398: astore 4
    //   400: getstatic 149	java/lang/System:out	Ljava/io/PrintStream;
    //   403: new 124	java/lang/StringBuilder
    //   406: dup
    //   407: invokespecial 125	java/lang/StringBuilder:<init>	()V
    //   410: ldc -105
    //   412: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload 4
    //   417: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: invokevirtual 134	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   423: invokevirtual 156	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   426: new 158	org/json/JSONObject
    //   429: dup
    //   430: aload 4
    //   432: invokespecial 159	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   435: ldc -96
    //   437: invokevirtual 164	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   440: checkcast 166	java/lang/Integer
    //   443: invokevirtual 169	java/lang/Integer:intValue	()I
    //   446: iconst_1
    //   447: if_icmpne +31 -> 478
    //   450: iconst_1
    //   451: istore_3
    //   452: aload_0
    //   453: iload_3
    //   454: putfield 21	com/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline:success	Z
    //   457: aload_1
    //   458: ifnull +7 -> 465
    //   461: aload_1
    //   462: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   465: aload 7
    //   467: ifnull +8 -> 475
    //   470: aload 7
    //   472: invokevirtual 138	java/io/BufferedReader:close	()V
    //   475: aload 4
    //   477: areturn
    //   478: iconst_0
    //   479: istore_3
    //   480: goto -28 -> 452
    //   483: astore 5
    //   485: aload 5
    //   487: invokevirtual 170	java/lang/Exception:printStackTrace	()V
    //   490: goto -33 -> 457
    //   493: astore 4
    //   495: aload 7
    //   497: astore 5
    //   499: aload_1
    //   500: ifnull +7 -> 507
    //   503: aload_1
    //   504: invokevirtual 102	java/net/HttpURLConnection:disconnect	()V
    //   507: aload 5
    //   509: ifnull +8 -> 517
    //   512: aload 5
    //   514: invokevirtual 138	java/io/BufferedReader:close	()V
    //   517: aload 4
    //   519: athrow
    //   520: astore_1
    //   521: aload_1
    //   522: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   525: goto -50 -> 475
    //   528: astore_1
    //   529: aload_1
    //   530: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   533: goto -180 -> 353
    //   536: astore_1
    //   537: aload_1
    //   538: invokevirtual 108	java/io/IOException:printStackTrace	()V
    //   541: goto -24 -> 517
    //   544: astore 6
    //   546: aload 4
    //   548: astore_1
    //   549: aload 6
    //   551: astore 4
    //   553: goto -54 -> 499
    //   556: astore 8
    //   558: aload 9
    //   560: astore 7
    //   562: aload 6
    //   564: astore_1
    //   565: goto -242 -> 323
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	568	0	this	RemoveAndInsertTeamOnline
    //   0	568	1	paramVarArgs	String[]
    //   360	2	2	i	int
    //   451	29	3	bool	boolean
    //   9	467	4	localObject1	Object
    //   493	54	4	localObject2	Object
    //   551	1	4	localObject3	Object
    //   19	307	5	localObject4	Object
    //   483	3	5	localException	Exception
    //   497	16	5	localObject5	Object
    //   6	255	6	arrayOfString	String[]
    //   544	19	6	localObject6	Object
    //   12	549	7	localObject7	Object
    //   3	290	8	localObject8	Object
    //   321	73	8	localIOException1	java.io.IOException
    //   556	1	8	localIOException2	java.io.IOException
    //   15	544	9	localObject9	Object
    //   134	137	10	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   235	243	245	java/io/IOException
    //   280	287	321	java/io/IOException
    //   292	318	321	java/io/IOException
    //   355	361	321	java/io/IOException
    //   393	426	321	java/io/IOException
    //   426	450	321	java/io/IOException
    //   452	457	321	java/io/IOException
    //   485	490	321	java/io/IOException
    //   378	383	385	java/io/IOException
    //   426	450	483	java/lang/Exception
    //   452	457	483	java/lang/Exception
    //   280	287	493	finally
    //   292	318	493	finally
    //   355	361	493	finally
    //   393	426	493	finally
    //   426	450	493	finally
    //   452	457	493	finally
    //   485	490	493	finally
    //   470	475	520	java/io/IOException
    //   348	353	528	java/io/IOException
    //   512	517	536	java/io/IOException
    //   21	37	544	finally
    //   47	52	544	finally
    //   62	68	544	finally
    //   78	86	544	finally
    //   96	104	544	finally
    //   114	136	544	finally
    //   146	153	544	finally
    //   163	168	544	finally
    //   178	183	544	finally
    //   193	199	544	finally
    //   209	218	544	finally
    //   262	280	544	finally
    //   330	335	544	finally
    //   21	37	556	java/io/IOException
    //   47	52	556	java/io/IOException
    //   62	68	556	java/io/IOException
    //   78	86	556	java/io/IOException
    //   96	104	556	java/io/IOException
    //   114	136	556	java/io/IOException
    //   146	153	556	java/io/IOException
    //   163	168	556	java/io/IOException
    //   178	183	556	java/io/IOException
    //   193	199	556	java/io/IOException
    //   209	218	556	java/io/IOException
    //   262	280	556	java/io/IOException
  }
  
  protected void onPostExecute(String paramString)
  {
    if (success)
    {
      new MainActivity.InsertTeamOnline(this$0).execute(new String[] { this$0.onlineTeamDict.toString() });
      return;
    }
    Toast.makeText(this$0, "Something went wrong when replacing a team.", 0).show();
    new MainActivity.InsertTeamOnline(this$0).execute(new String[] { this$0.onlineTeamDict.toString() });
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.MainActivity.RemoveAndInsertTeamOnline
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */