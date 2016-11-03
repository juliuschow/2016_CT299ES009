package juliuschow.ct299es009_23_chow_kwok_leung;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * Created by user on 2016/08/12.
 * 圖源：https://thenounproject.com/
 */

public class MainActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private float lastX;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar版面元件掛勾
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //設定Toolbar標題及正副標題
        toolbar.setBackgroundColor(Color.DKGRAY);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(R.string.title1);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle(R.string.title2);
        //更改ActionBar為Toolbar
        setSupportActionBar(toolbar);

        //ViewFlipper
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        TypedArray typeArray = getResources().obtainTypedArray(R.array.pics);

        for (int i=0;i<4;i++){
            // Get resource id by its index
            // typeArray.getResourceId(1, -1);
            Log.d("imageArray[]--->",String.valueOf(typeArray.getResourceId(i, -1)));
            viewFlipper.addView(getImageView(typeArray.getResourceId(i, -1)));
        }
        // TypeArray每次使用後都需要執行recycle()
        typeArray.recycle();

        // 自動播放
        viewFlipper.setAutoStart(true);
        //設定視圖切換的時間間隔參數為毫秒
        viewFlipper.setFlipInterval(3000);
        // 下一頁由右進入
        viewFlipper.setInAnimation(this, R.anim.right_in);
        // 本頁由左進出
        viewFlipper.setOutAnimation(this, R.anim.left_out);
        // 開始播放
        viewFlipper.startFlipping();
        // 停止播放
        //viewFlipper.stopFlipping();

    } //onCreate()

    //新增view
    private ImageView getImageView(int id){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(id);
        return imageView;
    }
    // Using the following method, we will handle all screen swaps.
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = motionEvent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = motionEvent.getX();

                // Handling left to right screen swap.
                if (lastX < currentX) {

                    // If there aren't any other children, just break.
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // Next screen comes in from left.
                    viewFlipper.setInAnimation(this, R.anim.left_in);
                    // Current screen goes out from right.
                    viewFlipper.setOutAnimation(this, R.anim.right_out);

                    // Display next screen.
                    viewFlipper.showNext();
                }

                // Handling right to left screen swap.
                if (lastX > currentX) {

                    // If there is a child (to the left), kust break.
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;

                    // Next screen comes in from right.
                    viewFlipper.setInAnimation(this, R.anim.right_in);
                    // Current screen goes out from left.
                    viewFlipper.setOutAnimation(this, R.anim.left_out);

                    // Display previous screen.
                    viewFlipper.showPrevious();
                }
                break;
        }
        return false;
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
