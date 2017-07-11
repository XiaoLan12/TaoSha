package com.yizhisha.taosha.ui.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.json.AreaInfo;
import com.yizhisha.taosha.ui.me.fragment.AreaFragment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AreaSelectActivity extends BaseActivity implements AreaFragment.OnFragmentInteractionListener{
    private Fragment oneFragment;
    private Fragment twoFragment;


    private Map map = new HashMap();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_area_select;
    }
    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        oneFragment = AreaFragment.newInstance("");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content,oneFragment).commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount()>0){
                    fragmentManager.popBackStack();
                }else{
                    finish();
                }
                break;
        }
        return true;
    }
    /**
     * 处理交互，hide前一个fragment，并且调用addToBackStack让Fragment可以点击back的时候显示前一个fragment
     * 如果是第三级地区则直接返回地区选择数据给上个Activity
     * @param areaInfo 被点击的地区信息
     */
    @Override
    public void onFragmentInteraction(AreaInfo areaInfo) {
        if (areaInfo==null){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int level = areaInfo.getLevel();
        switch (level){
            case 1:
                map.put("provId",areaInfo.getId());
                map.put("provName",areaInfo.getAreaName());
                if (areaInfo.isLeaf()){
                    Intent intent = new Intent();
                    intent.putExtra("addressInfo", (Serializable) map);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    transaction.hide(oneFragment);
                    transaction.add(R.id.content,twoFragment=AreaFragment.newInstance(areaInfo.getAreaCode()+"")).addToBackStack(null).commit();
                }
                break;
            case 2:
                map.put("cityId",areaInfo.getId());
                map.put("cityName",areaInfo.getAreaName());
                if (areaInfo.isLeaf()){
                    Intent intent = new Intent();
                    intent.putExtra("addressInfo", (Serializable) map);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    transaction.hide(twoFragment);
                    transaction.add (R.id.content, AreaFragment.newInstance(areaInfo.getAreaCode()+"")).addToBackStack(null).commit();
                }
                break;
            case 3:
                map.put("districtId",areaInfo.getId());
                map.put("districtName",areaInfo.getAreaName());
                Intent intent = new Intent();
                intent.putExtra("addressInfo", (Serializable) map);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
