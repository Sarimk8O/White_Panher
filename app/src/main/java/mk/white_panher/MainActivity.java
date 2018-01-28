package mk.white_panher;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Toolbar toolbar;
    LineChart lineChart;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10
            ,textView11,textView12,textView13;

    TextView textView14,textView15,textView16,textView17,textView18,textView19,textView20,textView21,textView22,textView23,textView24;



    EditText editText1,editText2;

    ImageView imageView;

    Button button;

    coins _coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lineChart=(LineChart) findViewById(R.id.LineChart);

        textView1=(TextView)findViewById(R.id.Price);
        textView2= (TextView) findViewById(R.id.open);
        textView3=(TextView) findViewById(R.id.high);
        textView4=(TextView) findViewById(R.id.low);
        textView5=(TextView) findViewById(R.id.open_24);
        textView6=(TextView) findViewById(R.id.close_24);
        textView7=(TextView) findViewById(R.id.high_24);
        textView8=(TextView) findViewById(R.id.volume);
        textView9=(TextView) findViewById(R.id.total_mined);
        textView10=(TextView) findViewById(R.id.last_id);
        textView11=(TextView) findViewById(R.id.currency);
        textView12=(TextView) findViewById(R.id.usd);
        textView13=(TextView) findViewById(R.id.lastupdate);

        editText1=(EditText) findViewById(R.id.number_currency);
        editText2=(EditText) findViewById(R.id.usd_currency);

        textView14=(TextView) findViewById(R.id.price_font);
        textView15=(TextView) findViewById(R.id.open_font);
        textView16=(TextView) findViewById(R.id.high_font);
        textView17=(TextView) findViewById(R.id.low_font);
        textView18=(TextView) findViewById(R.id.open_24_font);
        textView19=(TextView) findViewById(R.id.close_24_font);
        textView20=(TextView) findViewById(R.id.high_24_font);
        textView21=(TextView) findViewById(R.id.volume_font);
        textView22=(TextView) findViewById(R.id.total_mined_font);
        textView23=(TextView) findViewById(R.id.last_id_font);
        textView24=(TextView) findViewById(R.id.last_update_font);

        button=(Button) findViewById(R.id.send_data);

        imageView=(ImageView) findViewById(R.id.refresh);


        Typeface typeface1=Typeface.createFromAsset(getAssets(),"segoeui.ttf");
        textView2.setTypeface(typeface1);
        textView3.setTypeface(typeface1);
        textView4.setTypeface(typeface1);
        textView5.setTypeface(typeface1);
        textView6.setTypeface(typeface1);
        textView7.setTypeface(typeface1);
        textView8.setTypeface(typeface1);
        textView9.setTypeface(typeface1);
        textView10.setTypeface(typeface1);
        textView11.setTypeface(typeface1);
        textView12.setTypeface(typeface1);
        editText1.setTypeface(typeface1);
        editText2.setTypeface(typeface1);
        textView13.setTypeface(typeface1);
        textView14.setTypeface(typeface1);
        textView15.setTypeface(typeface1);
        textView16.setTypeface(typeface1);
        textView17.setTypeface(typeface1);
        textView18.setTypeface(typeface1);
        textView19.setTypeface(typeface1);
        textView20.setTypeface(typeface1);
        textView21.setTypeface(typeface1);
        textView22.setTypeface(typeface1);
        textView23.setTypeface(typeface1);
        textView24.setTypeface(typeface1);

        Typeface typeface=Typeface.createFromAsset(getAssets(), "bold_font.ttf");
        textView1.setTypeface(typeface);



        drawerLayout = (DrawerLayout) findViewById(R.id.nav_bar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        navigationView = (NavigationView) findViewById(R.id.navigation);


//        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

//        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_menu, this.getTheme());
//        actionBarDrawerToggle.setHomeAsUpIndicator(drawable);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

        _coin=new coins(lineChart,textView9,textView10,textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView13
        ,editText1,editText2);

        _coin.Graph("BitCoin",MainActivity.this,"BTC");

        _coin.Data(MainActivity.this,"BTC");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _coin.Data(MainActivity.this, String.valueOf(textView11.getText()));
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        if(id == R.id.bitcoin){
//            Toast.makeText(MainActivity.this,"In Bitcoin",Toast.LENGTH_LONG).show();
//        }


        switch(item.getItemId()){
            case R.id.bitcoin:{

                _coin.Graph("BitCoin",MainActivity.this,"BTC");
                textView11.setText("BTC");
                _coin.Data(MainActivity.this,"BTC");
                setTitle(item.getTitle());

                break;
            }

            case  R.id.litecoin:{

                _coin.Graph("Lite Coin",MainActivity.this,"LTC");
                textView11.setText("LTC");
                _coin.Data(MainActivity.this,"LTC");
                setTitle(item.getTitle());
                break;
            }

            case R.id.Bitcoincash:{

                _coin.Graph("BitCoin Cash",MainActivity.this,"BCH");
                textView11.setText("BCH");
                _coin.Data(MainActivity.this,"BCH");
                setTitle(item.getTitle());
                break;
            }
            case R.id.Monero:{

                _coin.Graph("Monero",MainActivity.this,"XMR");
                textView11.setText("XMR");
                _coin.Data(MainActivity.this,"XMR");
                setTitle(item.getTitle());
                break;
            }
            case R.id.Dashcoin:{

                _coin.Graph("DashCoin",MainActivity.this,"DASH");
                textView11.setText("DASH");
                _coin.Data(MainActivity.this,"DASH");
                setTitle(item.getTitle());
                break;
            }
            case R.id.Ethereum:{

                _coin.Graph("Ethereum",MainActivity.this,"ETH");
                textView11.setText("ETH");
                _coin.Data(MainActivity.this,"ETH");
                setTitle(item.getTitle());
                break;
            }
            case R.id.BitcoinGold:{

                _coin.Graph("BitCoin Gold",MainActivity.this,"BTG");
                textView11.setText("BTG");
                _coin.Data(MainActivity.this,"BTG");
                setTitle(item.getTitle());
                break;
            }
            case R.id.neo:{

                _coin.Graph("NEO",MainActivity.this,"NEO");
                textView11.setText("NEO");
                _coin.Data(MainActivity.this,"NEO");
                setTitle(item.getTitle());
                break;
            }
            case R.id.Zcash:{

                _coin.Graph("Zcash",MainActivity.this,"ZEC");
                textView11.setText("ZEC");
                _coin.Data(MainActivity.this,"ZEC");
                setTitle(item.getTitle());
                break;
            }
            case R.id.iota:{

                _coin.Graph("IOTA",MainActivity.this,"IOT");
                textView11.setText("IOT");
                _coin.Data(MainActivity.this,"IOT");
                setTitle(item.getTitle());
                break;
            }


        }

        DrawerLayout drawerLayout=(DrawerLayout) findViewById(R.id.nav_bar);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }






}
