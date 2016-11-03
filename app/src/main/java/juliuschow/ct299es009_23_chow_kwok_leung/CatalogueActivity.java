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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 2016/08/12.
 */
public class CatalogueActivity extends AppCompatActivity {
    //屬性
    private ListView item_list;
    Button button;

    private ArrayList<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    //商品介紹用
    private String goods = "";
    private String price = "";
    private String place = "";

    //import android.support.v7.widget.Toolbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);

        item_list = (ListView)findViewById(R.id.item_list);
        button = (Button) findViewById(R.id.buttonBack);
        //Toolbar版面元件掛勾
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //設定Toolbar標題及正副標題
        toolbar.setBackgroundColor(Color.DKGRAY);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(R.string.catalogue);
        //更改ActionBar為Toolbar
        setSupportActionBar(toolbar);

        // 加入範例資料
        data.add(getString(R.string.momo));
        data.add(getString(R.string.durian));
        data.add(getString(R.string.apple));
        data.add(getString(R.string.grape));
        data.add(getString(R.string.cherry));

        int layoutId = android.R.layout.simple_list_item_1;
        adapter = new ArrayAdapter<String>(this, layoutId, data);
        item_list.setAdapter(adapter);

        // 建立選單項目長按監聽物件
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            // 第一個參數是使用者操作的ListView物件
            // 第二個參數是使用者選擇的項目
            // 第三個參數是使用者選擇的項目編號，第一個是0
            // 第四個參數在這裡沒有用途
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                switch (position) {
                    case 0:
                        doMomo();
                        break;
                    case 1:
                        doDurian();
                        break;
                    case 2:
                        doApple();
                        break;
                    case 3:
                        doGrape();
                        break;
                    case 4:
                        doCherry();
                        break;
                }
                doCatalogueGoods();
            }

        };
        // 註冊選單項目點擊監聽物件
        item_list.setOnItemClickListener(itemListener);
    } //onCreate()

    public void doMomo(){
        goods =getString(R.string.momo);
        price =getString(R.string.momo_price);
        place =getString(R.string.momo_place);
    }
    public void doDurian(){
        goods =getString(R.string.durian);
        price =getString(R.string.durian_price);
        place =getString(R.string.durian_place);
    }
    public void doApple(){
        goods =getString(R.string.apple);
        price =getString(R.string.apple_price);
        place =getString(R.string.apple_place);
    }
    public void doGrape(){
        goods =getString(R.string.grape);
        price =getString(R.string.grape_price);
        place =getString(R.string.grape_place);
    }
    public void doCherry(){
        goods =getString(R.string.cherry);
        price =getString(R.string.cherry_price);
        place =getString(R.string.cherry_place);
    }

    public void doCatalogueGoods(){
        Intent intent = new Intent(this, CatalogueGoodsActivity.class);
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
