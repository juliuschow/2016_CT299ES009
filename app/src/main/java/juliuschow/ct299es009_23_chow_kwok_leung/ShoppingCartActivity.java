package juliuschow.ct299es009_23_chow_kwok_leung;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by user on 2016/08/19.
 */
public class ShoppingCartActivity  extends AppCompatActivity {
    //屬性
    TextView txtOutput1;
    TextView txtOutput2;
    TextView txtOutput3;
    TextView txtOutput4;
    TextView txtOutput5;
    Button button;
    EditText edtAddress;

    //商品介紹用
    private String goods = "";
    private String price = "";
    private String place = "";

    //短訊用
    private String dateOutput = "";
    private String timeOutput = "";

    // 取得Intent物件
    Intent intent = getIntent();

    //import android.support.v7.widget.Toolbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //Toolbar版面元件掛勾
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //設定Toolbar標題及正副標題
        toolbar.setBackgroundColor(Color.DKGRAY);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(goods);
        //更改ActionBar為Toolbar
        setSupportActionBar(toolbar);

        //txtOutput版面元件掛勾
        txtOutput1 = (TextView) findViewById(R.id.txtOutput1);
        txtOutput2 = (TextView) findViewById(R.id.txtOutput2);
        txtOutput3 = (TextView) findViewById(R.id.txtOutput3);
        txtOutput4 = (TextView) findViewById(R.id.txtOutput4);
        txtOutput5 = (TextView) findViewById(R.id.txtOutput5);
        edtAddress = (EditText)findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();

        try{
            goods =bundle.getString("GOODS");
            price =bundle.getString("PRICE");
            place =bundle.getString("PLACE");
        }catch(Exception e) {
            goods =getString(R.string.unselected);
            price =getString(R.string.unselected);
            place =getString(R.string.unselected);
        }
        if(goods !=getString(R.string.unselected)){
            button.setVisibility(View.VISIBLE);
        }

        txtOutput1.setText(getString(R.string.name)+":"+goods);
        txtOutput2.setText(getString(R.string.price)+":"+price);
        txtOutput3.setText(getString(R.string.place)+":"+ place);

    }

    public void btnDate(View v) {
        /*日歷*//////////////////////////////

        // 實體化Calendar類別為c身件
        Calendar c = Calendar.getInstance();
        // 實體化DatePickerDialog類別為dDialog物件為即時時間
        // 設定觸發時使用DateListener作回應
        DatePickerDialog dDialog = new DatePickerDialog(this, DateListener,
                c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        // 設定dDislog物件的圖示
        dDialog.setIcon(android.R.drawable.presence_away);
        // 顯示dDislog
        dDialog.show();
    }

    // 設定Date監視器
    DatePickerDialog.OnDateSetListener DateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker v, int y, int m, int d) {
            // 顯示DatePicker被設定的日期
            // 留意：月份由0開始
            dateOutput = (y + "年" + (m + 1) + "月" + d + "日");
            txtOutput4.setText(dateOutput);
        }
    };

    public void btnTime(View v) {
        /*時鐘*//////////////////////////////

        // 實體化Calendar類別為c身件
        Calendar c = Calendar.getInstance();
        // 實體化TimePickerDialog類別為tDialog物件為即時時間
        // 使用HOUR_OF_DAY可顯示AM/PM
        TimePickerDialog tDialog = new TimePickerDialog(ShoppingCartActivity.this, TimeListener,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), false);
        // 設定圖示
        tDialog.setIcon(android.R.drawable.presence_away);
        // 顯不tDialog物件
        tDialog.show();
    }
    // 設定TimeListener監視器
    TimePickerDialog.OnTimeSetListener TimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker v, int h, int m) {
            timeOutput = (h + "時" + m + "分");
            txtOutput5.setText(timeOutput);
        }
    };

    // 回應imgBtnEmailClick的事件
    public void btnBuy(View v) {
        /*發送*//////////////////////////////
        String strAddresst = edtAddress.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, "seasonalandfresh@gmail.com");
        intent.putExtra(Intent.CATEGORY_APP_EMAIL, "seasonalandfresh@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "購買"+goods+"的訂單");
        intent.putExtra(Intent.EXTRA_TEXT, "購買"+goods+",價錢:$"+price+"\n送貨日"+dateOutput+"\n"+timeOutput+"\n地址:"+strAddresst);
        intent.setType("text/plain");
        //startActivity(Intent.createChooser(intent, "Choose Email Client"));
        startActivity(intent);
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Toolbar掛入Menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void menu_home_icon_click(MenuItem m){
        Intent intent = new Intent().setClass(this, MainActivity.class);
        startActivity(intent);
    }
    public void menu_aboutMe_icon_click(MenuItem m){
        // 建立啟動另一個Activity元件需要的Intent物件
        // 建構式的第一個參數：「this」
        // 建構式的第二個參數：「Activity元件類別名稱.class」
        Intent intent = new Intent(this, AboutMeActivity.class);
        // 呼叫「startActivity」，參數為一個建立好的Intent物件
        // 這行敘述執行以後，如果沒有任何錯誤，就會啟動指定的元件
        startActivity(intent);
    }
    public void menu_catalogue_icon_click(MenuItem m){
        Intent intent = new Intent().setClass(this, CatalogueActivity.class);
        startActivity(intent);
    }
    public void menu_shoppingCart_icon_click(MenuItem m){
        Intent intent = new Intent().setClass(this, ShoppingCartActivity.class);
        startActivity(intent);
    }
    public void menu_fb_icon_click(MenuItem m){
        doIbtnFb();
    }
    public void menu_mapmode_icon_click(MenuItem m){
        doIbtnMap();
    }
    public void menu_menu_call_icon_click(MenuItem m){
        doIbtnCall();
    }
    public void menu_mapmode2_icon_click(MenuItem m){
        doIbtnMap2();
    }
    public void doIbtnFb(){
        //需要android.permission.INTERNET權限
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.facebook.com/seasonalandfresh/"));
        startActivity(intent);
    }
    public void doIbtnCall(){
        //需要android.permission.CALL_PHONE權限
        //Intent intent = new Intent(Intent.ACTION_DIAL); //直接打出
        Intent intent = new Intent(Intent.ACTION_VIEW); //呼叫電話程式
        intent.setData(Uri.parse("tel:+12345678"));
        startActivity(intent);
    }
    public void doIbtnMap(){
        //需要android.permission.INTERNET權限
        // 模擬器不支援，請使用實機作測試
        Uri uri = Uri.parse("geo:22.3122742,114.1691385,20.25z?q=九龍水果批發市場");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");

        //偵測是否有app可接收此intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void doIbtnMap2(){
        //需要android.permission.INTERNET權限
        // 模擬器不支援，請使用實機作測試
        Uri uri = Uri.parse("google.streetview:cbll=22.3122742,114.1691385,20");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");

        //偵測是否有app可接收此intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
