package com.solfashop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.solfashop.fragment.BaseFragment;

/**
 * Created by Ratri on 9/30/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_ORDER = 1;
    public static final int FRAGMENT_PRICELIST = 2;
    public static final int FRAGMENT_LOGIN = 3;
    public static final int FRAGMENT_CHECKOUT= 4;
    public static final int FRAGMENT_TRANSAKSI= 5;
    public static final int FRAGMENT_DETAILTRANSAKSI= 6;
    public static final int FRAGMENT_REGISTER= 7;
    public static final String EXTRA_MODEL = "extra.model";
    public static final String KEY_FRAGMENT = "solfa.fragment";
    protected int currentFragment = FRAGMENT_HOME;
    public static BaseActivity baseActivity;
    public BaseFragment fragment;
    public Toolbar toolbar;
    public NavigationView navigationView;
    public TextView welcome, userName;
    DrawerLayout drawer;
    public boolean isParrentView = true;

    public static final String BASE_URL = "http://solfadev.net/api/vouchershop/";
    public static final String REGISTER_OPERATION = "register";
    public static final String LOGIN_OPERATION = "login";
    public static final String CHANGE_PASSWORD_OPERATION = "chgPass";
    public static final String RESET_PASSWORD_INITIATE = "resPassReq";
    public static final String RESET_PASSWORD_FINISH = "resPass";

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String IS_LOGGED_IN = "isLoggedIn";

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String UNIQUE_ID = "unique_id";

    public static final String TAG = "Learn2Crack";
    public SharedPreferences pref;
    private int hot_number = 0;
    private TextView ui_hot = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    protected void setupNavabar() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        welcome = (TextView) header.findViewById(R.id.welcome);
        userName = (TextView) header.findViewById(R.id.username);
        pref = getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);
        if (!pref.getBoolean(IS_LOGGED_IN, false)) {
            navigationView.inflateMenu(R.menu.login);
            welcome.setText("Kamu belum login");
        } else {
            navigationView.inflateMenu(R.menu.activity_main_login);
            welcome.setText("Selamat Datang ");
            userName.setText(pref.getString(NAME, "")+ pref.getString(UNIQUE_ID,""));
            System.out.println(pref.getString(UNIQUE_ID,""));
        }


    }

    public static BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void startFragment(int TYPE, String judul) {
        Intent i = new Intent(this, ActivityMain.class);
        i.putExtra(KEY_FRAGMENT, TYPE);
        i.putExtra("judul", judul);
        startActivity(i);
    }

    public void startFragment(Intent i) {
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))drawer.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
//        @Override public boolean onCreateOptionsMenu(final Menu menu) {
//            MenuInflater menuInflater = getSupportMenuInflater();
//            menuInflater.inflate(R.menu.menu_actionbar, menu);
            //View menu_cart = menu.findItem(R.id.menu_cart).getActionView();
//            ui_hot = (TextView) menu_cart.findViewById(R.id.hotlist_hot);
//                hot_number=3;
//              updateHotCount(hot_number);
//            new MyMenuItemStuffListener(menu_hotlist, "Show hot message") {
//                @Override
//                public void onClick(View v) {
//                }
//            };

        MenuItem itemCart = menu.findItem(R.id.action_cart);
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        setBadgeCount(this, icon, "9");
            return super.onCreateOptionsMenu(menu);
        }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

// call the updating code on the main thread,
// so we can call this asynchronously
//    public void updateHotCount(final int new_hot_number) {
//        hot_number = new_hot_number;
//        if (ui_hot == null) return;
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (new_hot_number == 0)
//                    ui_hot.setVisibility(View.INVISIBLE);
//                else {
//                    ui_hot.setVisibility(View.VISIBLE);
//                    ui_hot.setText(Integer.toString(new_hot_number));
//                }
//            }
//        });
//    }

//    static abstract class MyMenuItemStuffListener implements View.OnClickListener, View.OnLongClickListener {
//        private String hint;
//        private View view;
//
//        MyMenuItemStuffListener(View view, String hint) {
//            this.view = view;
//            this.hint = hint;
//            view.setOnClickListener(this);
//            view.setOnLongClickListener(this);
//        }
//
//        @Override abstract public void onClick(View v);
//
//        @Override public boolean onLongClick(View v) {
//            final int[] screenPos = new int[2];
//            final Rect displayFrame = new Rect();
//            view.getLocationOnScreen(screenPos);
//            view.getWindowVisibleDisplayFrame(displayFrame);
//            final Context context = view.getContext();
//            final int width = view.getWidth();
//            final int height = view.getHeight();
//            final int midy = screenPos[1] + height / 2;
//            final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
//            Toast cheatSheet = Toast.makeText(context, hint, Toast.LENGTH_SHORT);
//            if (midy < displayFrame.height()) {
//                cheatSheet.setGravity(Gravity.TOP | Gravity.RIGHT,
//                        screenWidth - screenPos[0] - width / 2, height);
//            } else {
//                cheatSheet.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
//            }
//            cheatSheet.show();
//            return true;
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.hotlist_bell) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setBaseFragment(BaseFragment fragment){
        this.fragment = fragment;
    }
}