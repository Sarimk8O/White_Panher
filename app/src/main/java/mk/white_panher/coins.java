package mk.white_panher;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by abbott on 26-Dec-17.
 */

public class coins {


    LineChart lineChart;
    TextView textView9;
    TextView textView10;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView13;

    EditText editText,editText1;


    public coins(LineChart lineChart, TextView textView9, TextView textView10, TextView textView1, TextView textView2
            , TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView13
    ,EditText editText,EditText editText1) {
        this.lineChart = lineChart;
        this.textView9 = textView9;
        this.textView10 = textView10;
        this.textView1 = textView1;
        this.textView2 = textView2;
        this.textView3 = textView3;
        this.textView4 = textView4;
        this.textView5 = textView5;
        this.textView6 = textView6;
        this.textView7 = textView7;
        this.textView8 = textView8;
        this.textView13 = textView13;
        this.editText=editText;
        this.editText1=editText1;
    }

    void Graph(String currencyName, final Context context, String currency) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();


//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graphView.animate();
//        graphView.getGridLabelRenderer().setGridColor(R.color.background);
//        graphView.getGridLabelRenderer().setHorizontalLabelsVisible(true);
//        graphView.getGridLabelRenderer().setVerticalLabelsVisible(false);
//        graphView.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
//        series.setDrawDataPoints(true);
//        graphView.setTitle(currencyName);
//        graphView.setTitleTextSize(40);
//        series.setDataPointsRadius(10);
//        series.setThickness(8);
//        series.setColor(Color.rgb(245, 0, 87));
//        series.setDrawBackground(true);
//        series.setBackgroundColor(Color.argb(20, 245, 0, 87));
        //final List<CandleEntry> candleEntries=new ArrayList<>();
        final List<Entry> entries = new ArrayList<>();
        final List<Entry> entries1 = new ArrayList<>();
        final List<Entry> entries2 = new ArrayList<>();
        final List<Entry> entries3 = new ArrayList<>();

        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        RequestQueue requestQueue1 = Volley.newRequestQueue(context);

        String URL = "https://www.cryptocompare.com/api/data/coinsnapshot/?fsym=" + currency + "&tsym=USD";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONObject data = response.getJSONObject("Data");

                    JSONObject AggregatedData = data.getJSONObject("AggregatedData");

                    try {
                        String totalMines = data.getString("TotalCoinsMined");
                        textView9.setText(String.valueOf(Math.round(Float.parseFloat(totalMines))));

                    } catch (Error e) {
                        textView9.setText("null");

                    }
                    try {
                        String lastId = AggregatedData.getString("LASTTRADEID");
                        textView10.setText(String.valueOf(Math.round(Float.parseFloat(lastId))));

                    } catch (Error e) {
                        textView10.setText("null");
                    }
                    try {
                        String price = AggregatedData.getString("PRICE");
                        textView1.setText("$ " + String.valueOf(Math.round(Float.parseFloat(price))));

                    } catch (Error e) {
                        textView1.setText("null");

                    }


                    try {
                        Log.d("MainSarim", "In oPEN 24 HOUR");
                        String open_24 = AggregatedData.getString("OPEN24HOUR");
                        textView5.setText(String.valueOf(Math.round(Float.parseFloat(open_24))));

                    } catch (Error e) {
                        textView5.setText("nul");

                    }
                    try {
                        String close_24 = AggregatedData.getString("LOW24HOUR");
                        textView6.setText(String.valueOf(Math.round(Float.parseFloat(close_24))));

                    } catch (Error e) {
                        textView6.setText("null");
                    }
                    try {
                        String high_24 = AggregatedData.getString("HIGH24HOUR");
                        textView7.setText(String.valueOf(Math.round(Float.parseFloat(high_24))));

                    } catch (Error e) {
                        textView7.setText("null");

                    }


                    if (AggregatedData.has("VOLUMEDAY")) {
                        String vloume = AggregatedData.getString("VOLUMEDAY");
                        textView8.setText(String.valueOf(Math.round(Float.parseFloat(vloume))));

                    } else {
                        textView8.setText("null");
                    }


                    try {
                        String lastUpdate = AggregatedData.getString("LASTUPDATE");
                        String convert = UNIX_time(Long.valueOf(lastUpdate));
                        textView13.setText(convert);

                    } catch (Error e) {
                        textView13.setText("null");

                    }

                    if (AggregatedData.has("OPENDAY")) {
                        String open = AggregatedData.getString("OPENDAY");
                        textView2.setText(String.valueOf(Math.round(Float.parseFloat(open))));
                    } else {
                        textView2.setText("null");
                    }


                    if (AggregatedData.has("HIGHDAY")) {
                        String high = AggregatedData.getString("HIGHDAY");
                        textView3.setText(String.valueOf(Math.round(Float.parseFloat(high))));

                    } else {
                        textView3.setText("null");
                    }


                    if (AggregatedData.has("LOWDAY")) {
                        String low = AggregatedData.getString("LOWDAY");
                        textView4.setText(String.valueOf(Math.round(Float.parseFloat(low))));
                    } else {
                        textView4.setText("null");
                    }


                } catch (JSONException e) {

                    progressDialog.dismiss();

                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

//                  Snackbar.make(, "Replace with your own action", Snackbar.LENGTH_LONG)
//                          .setAction("Action", null).show();
//

            }
        });


      /*
      * Bitfinex
      * Api
      * Response
      *
      * */

        String Candle_URL = "https://min-api.cryptocompare.com/data/histohour?fsym=" + currency + "&tsym=USD&limit=10&aggregate=1&e=Bitfinex";


        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, Candle_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray jsonArray = response.getJSONArray("Data");
                    int _open[] = new int[jsonArray.length()];
                    int _high[] = new int[jsonArray.length()];
                    int _low[] = new int[jsonArray.length()];
                    int _close[] = new int[jsonArray.length()];
                    String _date[] = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String time = jsonObject.getString("time");
                        //String formated_time=UNIX_time(Integer.valueOf(time));
                        int open = jsonObject.getInt("open");
                        int high = jsonObject.getInt("high");
                        int low = jsonObject.getInt("low");
                        int close = jsonObject.getInt("close");
                        _date[i] = jsonObject.getString("time");
                        _open[i] = jsonObject.getInt("open");
                        _high[i] = jsonObject.getInt("high");
                        _low[i] = jsonObject.getInt("low");
                        _close[i] = jsonObject.getInt("close");

                        // candleEntries.add(new CandleEntry(Float.valueOf(_date[i]),_low[i],_high[i],_open[i],_close[i]));
                        entries.add(new Entry(Float.valueOf(_date[i]), _high[i]));
                        entries1.add(new Entry(Float.valueOf(_date[i]), _low[i]));
                        entries2.add(new Entry(Float.valueOf(_date[i]), _open[i]));
                        entries3.add(new Entry(Float.valueOf(_date[i]), _close[i]));



                    }
                    progressDialog.dismiss();
                    Display_Graph(entries, entries1, entries2, entries3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();

            }
        });


        requestQueue.add(jsonObjectRequest);
        requestQueue1.add(jsonObjectRequest1);


    }


    public String UNIX_time(long timestamp) {


// convert seconds to milliseconds
        Date date = new Date(timestamp * 1000L);
// the format of your date
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
// give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(TimeZone.getDefault());
        String formattedDate = sdf.format(date);

        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
        Date _24HourDt = null;
        try {
            _24HourDt = _24HourSDF.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(_24HourDt);
//        System.out.println(_12HourSDF.format(_24HourDt));

        return _12HourSDF.format(_24HourDt);
    }


    public void Display_Graph(List<Entry> entries, List<Entry> entries1, List<Entry> entries2, List<Entry> entries3) {

        LineDataSet lineDataSet = new LineDataSet(entries, "High");
        LineDataSet lineDataSet1 = new LineDataSet(entries1, "Low");
        LineDataSet lineDataSet2 = new LineDataSet(entries2, "Open");
        LineDataSet lineDataSet3 = new LineDataSet(entries3, "Close");

        LineData lineData = new LineData(lineDataSet, lineDataSet1, lineDataSet2, lineDataSet3);

        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCircleColor(Color.BLUE);


        lineDataSet1.setColor(Color.CYAN);
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet1.setCircleColor(Color.CYAN);


        lineDataSet2.setColor(Color.GREEN);
        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet2.setCircleColor(Color.GREEN);


        lineDataSet3.setColor(Color.rgb(245,0,87));
        lineDataSet3.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet3.setCircleColor(Color.rgb(245,0,87));



//        lineData.setDecreasingColor(Color.RED);
//        lineData.setDecreasingPaintStyle(Paint.Style.FILL);
//        lineData.setIncreasingColor(Color.rgb(122, 242, 84));
//        lineData.setIncreasingPaintStyle(Paint.Style.FILL);
//        lineData.setNeutralColor(Color.CYAN);
//        lineData.setShadowColor(Color.DKGRAY);
//        lineData.setShadowWidth(0.4f);
        //candleDataSet.setHighlightEnabled(true);
        //candleDataSet.setDrawHighlightIndicators(true);
        //candleDataSet.setDrawHorizontalHighlightIndicator(true);
        //candleDataSet.setDrawVerticalHighlightIndicator(true);
        lineChart.getDescription().setText("Time: ");
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                lineChart.getDescription().setText("Time:  "+UNIX_time((long) e.getX()));

            }

            @Override
            public void onNothingSelected() {

            }
        });


        lineChart.setData(lineData);
        lineChart.setDrawGridBackground(false);
        lineChart.setBackgroundColor(Color.TRANSPARENT);
        lineChart.setDrawBorders(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.setDrawMarkers(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setEnabled(true);
        lineChart.getDescription().setTextSize(15);
        lineChart.getLegend().setEnabled(true);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDoubleTapToZoomEnabled(true);
        YAxis yAxis1 = lineChart.getAxisRight();
        YAxis yAxis=lineChart.getAxisLeft();
        lineChart.setHovered(true);
        lineChart.setHighlightPerDragEnabled(true);
        lineChart.setHighlightPerTapEnabled(true);
        lineChart.setTouchEnabled(true);
        lineChart.animateX(2000);
        yAxis.setEnabled(false);
        yAxis1.setEnabled(false);


        lineChart.invalidate();
//        candleStickChart.setVisibleXRangeMinimum(4);
//        candleStickChart.setVisibleXRangeMaximum(4);
        //lineChart.zoom(2f,0,3.5f,0);
        lineChart.moveViewToX(Integer.MAX_VALUE);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return UNIX_time((long) value);
            }
        });

    }



    public void Data(Context context,String currency){
        String URL="https://min-api.cryptocompare.com/data/price?fsym="+currency+"&tsyms=USD";

        RequestQueue requestQueue=Volley.newRequestQueue(context);


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int Currency=response.getInt("USD");
                    String number=editText.getText().toString();

                    float Result=Math.round(Float.valueOf(number)*Currency);

                    editText1.setText(String.valueOf(Result));
                    Log.d("Main",String.valueOf(Currency));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }


}


