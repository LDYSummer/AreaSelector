package com.example.areaselectorlibrary;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Pcas{

    private PcasModel model;
    private List<AreaBean> provinceList = new ArrayList<>();
    private List<AreaBean> cityList = new ArrayList<>();
    private List<AreaBean> areaList = new ArrayList<>();
    private List<AreaBean> streetList = new ArrayList<>();
    private int provinceIndex;
    private int cityIndex;

    private String getJson(Context context){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open("pcas.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public List<AreaBean> getProvinceList(Context context){
        if (provinceList.isEmpty()){
            model = new Gson().fromJson(getJson(context),PcasModel.class);
            for (PcasModel.ProvinceListBean bean: model.getProvinceList()){
                provinceList.add(new AreaBean(bean.getCode(),bean.getName()));
            }
        }
        return provinceList;
    }

    public List<AreaBean> getCityList(int position){
        if (!cityList.isEmpty()){
            cityList.clear();
        }
        for (PcasModel.ProvinceListBean.CityBean bean : model.getProvinceList().get(position).getChildren()){
            cityList.add(new AreaBean(bean.getCode(),bean.getName()));
        }
        provinceIndex = position;
        return cityList;
    }

    public List<AreaBean> getAreaList(int position){
        if (!areaList.isEmpty()){
            areaList.clear();
        }
        for (PcasModel.ProvinceListBean.CityBean.AreaBean bean :
                model.getProvinceList().get(provinceIndex).getChildren().get(position).getChildren()){
            areaList.add(new AreaBean(bean.getCode(),bean.getName()));
        }
        cityIndex = position;
        return areaList;
    }

    public List<AreaBean> getStreetList(int position){
        if (!streetList.isEmpty()){
            streetList.clear();
        }
        for (PcasModel.ProvinceListBean.CityBean.AreaBean.StreetBean bean :
                model.getProvinceList().get(provinceIndex).getChildren().get(cityIndex).getChildren().get(position).getChildren()){
            streetList.add(new AreaBean(bean.getCode(),bean.getName()));
        }
        return streetList;
    }

}
