package com.example.areaselectorlibrary;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AreaSelector {

    private final Activity mContext;

    private final Dialog dialog;

    private final TextView tv_province;
    private final TextView tv_city;
    private final TextView tv_area;
    private final TextView tv_street;

    private final ListView lv_province;
    private final ListView lv_city;
    private final ListView lv_area;
    private final ListView lv_street;

    private List<AreaBean> provinceList;
    private final QuickAdapter<AreaBean> provinceAdapter;
    private final QuickAdapter<AreaBean> cityAdapter;
    private final QuickAdapter<AreaBean> areaAdapter;
    private final QuickAdapter<AreaBean> streetAdapter;

    private OnSelectorItemClickListener mItemClickListener;
    private OnSelectedResultListener mResultListener;
    private List<AreaBean> cityList;
    private List<AreaBean> areaList;
    private List<AreaBean> streetList;

    public AreaSelector(Activity context){

        mContext = context;
        provinceList = new ArrayList<>();
        cityList = new ArrayList<>();
        areaList = new ArrayList<>();
        streetList = new ArrayList<>();

        dialog = new Dialog(mContext,R.style.regionsDialog);
        dialog.setContentView(R.layout.selector_dialog);
        dialog.setCanceledOnTouchOutside(true);

        ImageView iv_back = dialog.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tv_province = dialog.findViewById(R.id.tv_province);
        tv_city = dialog.findViewById(R.id.tv_city);
        tv_area = dialog.findViewById(R.id.tv_area);
        tv_street = dialog.findViewById(R.id.tv_street);

        lv_province = dialog.findViewById(R.id.lv_province);
        lv_city = dialog.findViewById(R.id.lv_city);
        lv_area = dialog.findViewById(R.id.lv_area);
        lv_street = dialog.findViewById(R.id.lv_street);

        provinceAdapter = new QuickAdapter<AreaBean>(mContext,R.layout.item_view) {
            @Override
            protected void convert(BaseAdapterHelper helper, AreaBean bean) {
                helper.setText(R.id.tv_item,bean.getName());
            }
        };
        cityAdapter = new QuickAdapter<AreaBean>(mContext,R.layout.item_view) {
            @Override
            protected void convert(BaseAdapterHelper helper, AreaBean bean) {
                helper.setText(R.id.tv_item,bean.getName());
            }
        };
        areaAdapter = new QuickAdapter<AreaBean>(mContext,R.layout.item_view) {
            @Override
            protected void convert(BaseAdapterHelper helper, AreaBean bean) {
                helper.setText(R.id.tv_item,bean.getName());
            }
        };
        streetAdapter = new QuickAdapter<AreaBean>(mContext,R.layout.item_view) {
            @Override
            protected void convert(BaseAdapterHelper helper, AreaBean bean) {
                helper.setText(R.id.tv_item,bean.getName());
            }
        };

        lv_province.setAdapter(provinceAdapter);
        lv_city.setAdapter(cityAdapter);
        lv_area.setAdapter(areaAdapter);
        lv_street.setAdapter(streetAdapter);

        tv_province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_province.setVisibility(View.VISIBLE);
                lv_area.setVisibility(View.GONE);
                lv_city.setVisibility(View.GONE);
                lv_street.setVisibility(View.GONE);
                tv_province.setTextColor(mContext.getResources().getColor(R.color.text_red));
                tv_city.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_area.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_street.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
            }
        });
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_province.setVisibility(View.GONE);
                lv_area.setVisibility(View.GONE);
                lv_city.setVisibility(View.VISIBLE);
                lv_street.setVisibility(View.GONE);
                tv_province.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_city.setTextColor(mContext.getResources().getColor(R.color.text_red));
                tv_area.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_street.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
            }
        });
        tv_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_city.setVisibility(View.GONE);
                lv_area.setVisibility(View.VISIBLE);
                lv_province.setVisibility(View.GONE);
                lv_street.setVisibility(View.GONE);
                tv_province.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_city.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_area.setTextColor(mContext.getResources().getColor(R.color.text_red));
                tv_street.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
            }
        });

        tv_street.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv_city.setVisibility(View.GONE);
                lv_area.setVisibility(View.GONE);
                lv_province.setVisibility(View.GONE);
                lv_street.setVisibility(View.VISIBLE);
                tv_province.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_city.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_area.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                tv_street.setTextColor(mContext.getResources().getColor(R.color.text_red));
            }
        });

        lv_province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                if(mItemClickListener !=null){
                    cityList = mItemClickListener.setOnProvinceSelected(provinceList.get(position),position);
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(cityList==null||cityList.size()==0){
                                Toast.makeText(mContext,"没有获取到该省份的城市数据",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            tv_province.setText(provinceList.get(position).getName());
                            tv_city.setVisibility(View.VISIBLE);

                            tv_province.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                            tv_city.setTextColor(mContext.getResources().getColor(R.color.text_red));
                            tv_area.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                            tv_street.setTextColor(mContext.getResources().getColor(R.color.text_black_high));

                            lv_province.setVisibility(View.GONE);
                            lv_city.setVisibility(View.VISIBLE);
                            lv_area.setVisibility(View.GONE);
                            lv_street.setVisibility(View.GONE);

                            cityAdapter.replaceAll(cityList);
                        }
                    });
                }
            }
        });
        lv_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                if(mItemClickListener !=null){
                    areaList = mItemClickListener.setOnCitySelected(cityList.get(position),position);
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(areaList==null||areaList.size()==0){
                                Toast.makeText(mContext,"没有获取到该城市的区域数据",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            tv_city.setText(cityList.get(position).getName());
                            tv_area.setVisibility(View.VISIBLE);

                            tv_province.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                            tv_city.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                            tv_area.setTextColor(mContext.getResources().getColor(R.color.text_red));
                            tv_street.setTextColor(mContext.getResources().getColor(R.color.text_black_high));

                            lv_province.setVisibility(View.GONE);
                            lv_city.setVisibility(View.GONE);
                            lv_area.setVisibility(View.VISIBLE);
                            lv_street.setVisibility(View.GONE);

                            areaAdapter.replaceAll(areaList);
                        }
                    });
                }
            }
        });
        lv_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                if(mItemClickListener !=null){
                    streetList = mItemClickListener.setOnAreaSelected(areaList.get(position),position);
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(streetList ==null|| streetList.size()==0){
                                dialog.dismiss();
                                return;
                            }
                            tv_area.setText(areaList.get(position).getName());
                            tv_street.setVisibility(View.VISIBLE);

                            tv_province.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                            tv_city.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                            tv_area.setTextColor(mContext.getResources().getColor(R.color.text_black_high));
                            tv_street.setTextColor(mContext.getResources().getColor(R.color.text_red));

                            lv_province.setVisibility(View.GONE);
                            lv_city.setVisibility(View.GONE);
                            lv_area.setVisibility(View.GONE);
                            lv_street.setVisibility(View.VISIBLE);

                            streetAdapter.replaceAll(streetList);
                        }
                    });
                }

            }
        });
        lv_street.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mItemClickListener != null){
                    mItemClickListener.setOnStreetSelected(streetList.get(i), i);
                }
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    private void setProvinceData(List<AreaBean> areaBeanList){
        provinceList = areaBeanList;
        if(provinceList !=null&& provinceList.size()>0){
            provinceAdapter.replaceAll(provinceList);
        }
    }

    private void setDefaultData(){

        final Pcas pcas = new Pcas();

        final String[] province = {""};
        final String[] city = {""};
        final String[] area = {""};
        final String[] street = {""};
        setOnItemClickListener(new OnSelectorItemClickListener() {
            @Override
            public List<AreaBean> setProvinceList() {
                return pcas.getProvinceList(mContext);
            }

            @Override
            public List<AreaBean> setOnProvinceSelected(AreaBean areaBean, int position) {
                province[0] = areaBean.getName();
                mResultListener.SelectedData(province[0],"","","");
                return pcas.getCityList(position);
            }

            @Override
            public List<AreaBean> setOnCitySelected(AreaBean areaBean, int position) {
                city[0] = areaBean.getName();
                mResultListener.SelectedData(province[0],city[0],"","");
                return pcas.getAreaList(position);
            }

            @Override
            public List<AreaBean> setOnAreaSelected(AreaBean areaBean, int position) {
                area[0] = areaBean.getName();
                mResultListener.SelectedData(province[0],city[0],area[0],"");
                return pcas.getStreetList(position);
            }

            @Override
            public void setOnStreetSelected(AreaBean areaBean, int position) {
                street[0] = areaBean.getName();
                mResultListener.SelectedData(province[0],city[0],area[0],street[0]);
            }
        });
    }

    public AreaSelector build(){

        if(mItemClickListener ==null){
            setDefaultData();
        }
        setProvinceData(mItemClickListener.setProvinceList());
        if(provinceList ==null|| provinceList.size()<=0){
            Toast.makeText(mContext,"请先初始化数据",Toast.LENGTH_SHORT).show();
        }

        return this;
    }

    public void showSelector(){
        Window win = dialog.getWindow();
        assert win != null;
        win.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = mContext.getResources().getDisplayMetrics().heightPixels/2;
        win.setWindowAnimations(R.style.dialogBottomInStyle);
        win.setAttributes(lp);
        dialog.show();
    }

    public AreaSelector setOnItemClickListener(OnSelectorItemClickListener itemClickListener){
        mItemClickListener = itemClickListener;
        return this;
    }

    public AreaSelector setOnSelectedResultListener(OnSelectedResultListener resultListener){
        mResultListener = resultListener;
        return this;
    }

}
