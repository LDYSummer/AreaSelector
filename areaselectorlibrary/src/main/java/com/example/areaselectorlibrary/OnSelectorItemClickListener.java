package com.example.areaselectorlibrary;

import java.util.List;

public interface OnSelectorItemClickListener {
     List<AreaBean> setProvinceList();
     List<AreaBean> setOnProvinceSelected(AreaBean areaBean, int position);
     List<AreaBean> setOnCitySelected(AreaBean areaBean, int position);
     List<AreaBean> setOnAreaSelected(AreaBean areaBean, int position);
     void setOnStreetSelected(AreaBean areaBean, int position);
}
