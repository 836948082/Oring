package com.runtai.oring;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.runtai.oring.base.BaseActivity;
import com.runtai.oring.fragment.BookFragment;
import com.runtai.oring.fragment.GameFragment;
import com.runtai.oring.fragment.HomeFragment;
import com.runtai.oring.fragment.MusicFragment;
import com.runtai.oring.fragment.TvFragment;
import com.runtai.oring.utils.SharedPreferencesUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<Fragment> fragments;

    /**
     * 颜色
     */
    private int color_0 = R.color.orange;
    private int color_1 = R.color.teal;
    private int color_2 = R.color.blue;
    private int color_3 = R.color.brown;
    private int color_4 = R.color.grey;

    /**
     * 底部导航栏按钮
     */
    BottomNavigationItem BNitem_0;
    BottomNavigationItem BNitem_1;
    BottomNavigationItem BNitem_2;
    BottomNavigationItem BNitem_3;
    BottomNavigationItem BNitem_4;

    /**
     * 底部导航栏文字
     */
    private String text_0 = "Home";
    private String text_1 = "Books";
    private String text_2 = "Music";
    private String text_3 = "Movies & TV";
    private String text_4 = "Games";

    /**
     * 底部导航栏文字(角标\标记)
     */
    BadgeItem numberBadgeItem_0;
    BadgeItem numberBadgeItem_1;
    BadgeItem numberBadgeItem_2;
    BadgeItem numberBadgeItem_3;
    BadgeItem numberBadgeItem_4;



    @Override
    protected void beforeSetContent() {

    }

    @Override
    protected int getView() {
        return R.layout.activity_main;
    }
    LinearLayout linearLayout;
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        setStatusBar(color_0);

        /**
         * 设置切换模式和背景颜色
         */
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);

        /**
         * 设置角标(标记)
         * 不同位置使用的值不同(numberBadgeItem对象)也不能相同
         */
        numberBadgeItem_0 = new BadgeItem().setBorderWidth(4).setBackgroundColor(Color.RED).setText("1").setHideOnSelect(true);
        numberBadgeItem_1 = new BadgeItem().setBorderWidth(4).setBackgroundColor(Color.RED).setText("2").setHideOnSelect(true);
        numberBadgeItem_2 = new BadgeItem().setBorderWidth(4).setBackgroundColor(Color.RED).setText("3").setHideOnSelect(true);
        numberBadgeItem_3 = new BadgeItem().setBorderWidth(4).setBackgroundColor(Color.RED).setText("4").setHideOnSelect(true);
        numberBadgeItem_4 = new BadgeItem().setBorderWidth(4).setBackgroundColor(Color.RED).setText("5").setHideOnSelect(true);

        /*  setBadgeItem(numberBadgeItem_0)  设置角标  若要不显示 ---> 调用 BNitem_2.setBadgeItem(numberBadgeItem_2.hide());   */
        BNitem_0 = new BottomNavigationItem(R.mipmap.ic_home_white_24dp, text_0).setActiveColorResource(color_0).setBadgeItem(numberBadgeItem_0);
        BNitem_1 = new BottomNavigationItem(R.mipmap.ic_book_white_24dp, text_1).setActiveColorResource(color_1);
        BNitem_2 = new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, text_2).setActiveColorResource(color_2).setBadgeItem(numberBadgeItem_2);
        BNitem_3 = new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, text_3).setActiveColorResource(color_3);
        BNitem_4 = new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp, text_4).setActiveColorResource(color_4).setBadgeItem(numberBadgeItem_4);

        bottomNavigationBar
                .addItem(BNitem_0)
                .addItem(BNitem_1)
                .addItem(BNitem_2)
                .addItem(BNitem_3)
                .addItem(BNitem_4)
                .setFirstSelectedPosition(0)
                //                .setInActiveColor("#2196f3")//未被选中时图标和文字的颜色(BACKGROUND_STYLE_RIPPLE、BACKGROUND_STYLE_STATIC(选中时自身的颜色))
                //                .setBarBackgroundColor("#FF0000")//导航栏背景颜色(BACKGROUND_STYLE_RIPPLE模式下表示所有图标的默认颜色、(BACKGROUND_STYLE_STATIC))
                .initialise();

        /**
         * .setInActiveColor("#2196f3")//未被选中时图标和文字的颜色(BACKGROUND_STYLE_RIPPLE、BACKGROUND_STYLE_STATIC(选中时自身的颜色))
         * .setBarBackgroundColor("#FF0000")//导航栏背景颜色(BACKGROUND_STYLE_RIPPLE模式下表示所有图标的默认颜色、(BACKGROUND_STYLE_STATIC))
         * .initialise();
         *
         * bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
         * setInActiveColor("#2196f3")//未被选中时图标和文字的颜色
         * setBarBackgroundColor("#FF0000")//导航栏背景颜色
         * 并携带选中是的自身设置的颜色
         *
         *
         * bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
         * setActiveColorResource(R.color.orange)//导航栏背景颜色
         * setInActiveColorResource(R.color.blue)//未被选中时的文字和图标的颜色
         *
         *
         * bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
         * setActiveColorResource(R.color.orange)//被选中时的文字和图标的颜色
         * setInActiveColorResource(R.color.blue)//未被选中时的文字和图标的颜色
         */

        fragments = getFragments();
        setDefaultFragment();

        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, HomeFragment.newInstance("Home"));
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Home"));
        fragments.add(BookFragment.newInstance("Books"));
        fragments.add(MusicFragment.newInstance("Music"));
        fragments.add(TvFragment.newInstance("Movies & TV"));
        fragments.add(GameFragment.newInstance("Games"));
        return fragments;
    }

    /**
     * 当一个选项卡进入选中状态时调用。
     *
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        Log.e("onTabSelected", "选中" + position);
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.replace(R.id.layFrame, fragment);
                ft.commitAllowingStateLoss();
            }
        }

        /**
         * 动态设置状态栏颜色
         */
        switch (position) {
            case 0:
                setStatusBar(color_0);
                break;
            case 1:
                setStatusBar(color_1);
                break;
            case 2:
                setStatusBar(color_2);
                break;
            case 3:
                setStatusBar(color_3);
                break;
            case 4:
                setStatusBar(color_4);
                break;
            default:
                break;
        }

    }

    /**
     * 当一个选项卡退出选中状态时调用。
     *
     * @param position
     */
    @Override
    public void onTabUnselected(int position) {
        Log.e("onTabUnselected", "退出选中" + position);
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
        setting(position);
    }

    /**
     * 当界面停止选中是的额外的设置
     * 设置角标(若为0，则隐藏不显示)
     * @param position
     */
    public void setting(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                int num = MusicFragment.getNum();
                if (num == 0) {
                    BNitem_2.setBadgeItem(numberBadgeItem_2.hide());
                } else {
                    BNitem_2.setBadgeItem(numberBadgeItem_2.setText(String.valueOf(num)).show());
                }
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    /**
     * 当一个已被选中的标签再次被用户选中时调用
     * 这个页面刷新(页面布局和数据重新构建)
     * @param position
     */
    @Override
    public void onTabReselected(int position) {
        Log.e("onTabReselected", "再次选中" + position);

        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                if (fragments != null) {
                    if (position < fragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment fragment = fragments.get(position);
                        ft.remove(fragment);
                        ft.add(R.id.layFrame, fragment);
                        ft.commitAllowingStateLoss();
                    }
                }
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    /**
     * 获取状态栏高度
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 设置状态栏颜色(沉浸式状态栏)
     */
    public void setStatusBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            /** 透明状态栏 */
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            /** 透明导航栏 */  //如果设置了这里，则导航栏覆盖在页面之上，影响页面操作
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            Log.e("getStatusBarHeight",""+getStatusBarHeight());
            linearLayout.setPadding(0, getStatusBarHeight(), 0, 0);
        }

        /**
         * 如果手机导航栏是虚拟按键并且是Android6.0系统,则设置去掉状态栏深色背景
         */
        if (getNavigationBarHeight(this) == 0) { // 不是虚拟按键
            //6.0版本(设置状态栏和导航栏在6.0系统上全透明)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getColor(color));//设置状态栏在6.0系统上全透明
                Log.e("导航栏高度", "" + getNavigationBarHeight(this));
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Log.e("5.0", "设置颜色" + getNavigationBarHeight(this));
                this.getWindow().setStatusBarColor(color);
            }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Log.e("4.0", "设置颜色" + getNavigationBarHeight(this));
            }
        } else { // 是虚拟按键
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                linearLayout.setPadding(0, getStatusBarHeight(), 0, getNavigationBarHeight(this));
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);//设置状态栏在6.0系统上全透明
                Log.e("导航栏高度", "" + getNavigationBarHeight(this));
            }
        }
    }

    /**
     * 获取导航栏高度
     *
     * @param activity
     * @return
     */
    public static int getNavigationBarHeight(Activity activity) {
        int height = 0;
        if (checkDeviceHasNavigationBar(activity)) {
            Resources resources = activity.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            //获取NavigationBar的高度
            height = resources.getDimensionPixelSize(resourceId);
        }
        return height;
    }

    /**
     * 通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
     *
     * @param activity
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context activity) {
        boolean hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏(虚拟按键)
            return true;
        }
        return false;
    }


    private long exitTime = 0;
    /**
     * // * 菜单、返回键响应 //
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 双击退出程序函数
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            new SharedPreferencesUtils(this).put("EXIT", true);
            finish();
            System.exit(0);
            //清除Token状态
        }
    }

}
