package juliuschow.ct299es009_23_chow_kwok_leung;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 2016/08/12.
 */
public class CatalogueGoodsActivity extends AppCompatActivity {
    //屬性
    TextView txtOutput1;
    TextView txtOutput2;
    TextView txtOutput3;
    ImageView imageView;
    //商品介紹用
    private String goods = "";
    private String price = "";
    private String place = "";

    // 取得Intent物件
    Intent intent = getIntent();

    //import android.support.v7.widget.Toolbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue_goods);

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
        imageView =(ImageView)findViewById(R.id.imageView);

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();

        goods =bundle.getString("GOODS");
        price =bundle.getString("PRICE");
        place =bundle.getString("PLACE");

        txtOutput1.setText(getString(R.string.name)+":"+goods);
        txtOutput2.setText(getString(R.string.price)+":"+price);
        txtOutput3.setText(getString(R.string.place)+":"+ place);

        switch (goods) {
            case "桃":
                imageView.setImageResource(R.drawable.momo);
                break;
            case "榴槤":
                imageView.setImageResource(R.drawable.durian);
                break;
            case "蘋果":
                imageView.setImageResource(R.drawable.apple);
                break;
            case "提子":
                imageView.setImageResource(R.drawable.grape);
                break;
            case "車厘子":
                imageView.setImageResource(R.drawable.cherry);
                break;
        }
    }

    // 回應imgBtnEmailClick的事件
    public void btnBack(View v) {
        // 呼叫這個方法結束Activity元件
        finish();
    }

    // 回應imgBtnEmailClick的事件
    public void btnBuy(View v) {
        Intent intent = new Intent().setClass(this, ShoppingCartActivity.class);
        //設定bundle物件
        Bundle bundle = new Bundle();
        bundle.putString("GOODS",goods);
        bundle.putString("PRICE",price);
        bundle.putString("PLACE",place);
        //將Bundle物件加於Intent物件之上
        intent.putExtras(bundle);
        //啟動intent物件呼叫其他Activity
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
