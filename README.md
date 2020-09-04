# AreaSelector
四级联动地址选择器
[![](https://jitpack.io/v/LDYSummer/AreaSelector.svg)](https://jitpack.io/#LDYSummer/AreaSelector)

- 默认国家统计局数据
- 一句代码即可使用
- 支持自定义数据

### 添加项目依赖
#### build.gradle
```javascript
  allprojects {
    repositories {
     ...
     maven { url 'https://jitpack.io' }
    }
  }
```

#### dependency
```javascript
  dependencies {
      implementation 'com.github.LDYSummer:AreaSelector:1.0.0'
  }
```
### 使用
#### 默认使用国家统计数据 设置setOnSelectedResultListener 返回省/市/区(县)/街道 实时改变地址text 未选为空字符串
```javascript
    new AreaSelector(this)
            .showSelector()
            .setOnSelectedResultListener(new OnSelectedResultListener() {
                @Override
                public void SelectedData(String province, String city, String area, String street) {
                    tv_address.setText(province + city + area + street);
                }
            });

```

#### 自定义数据及item选中事件
```javascript
    new AreaSelector(this)
            .showSelector()
            .setOnItemClickListener(new OnSelectorItemClickListener() {
                @Override
                public List<AreaBean> setProvinceList() {
                    List<AreaBean> provinceList = new ArrayList<>();
                    provinceList.add(new AreaBean("your province id","your province name"));
                    ..
                    return provinceList;
                }

                @Override
                public List<AreaBean> setOnProvinceSelected(AreaBean areaBean, int position) {
                    ...
                    return cityList;
                }

                @Override
                public List<AreaBean> setOnCitySelected(AreaBean areaBean, int position) {
                    ...
                    return areaList;
                }

                @Override
                public List<AreaBean> setOnAreaSelected(AreaBean areaBean, int position) {
                    ...
                    return streetList;
                }

                @Override
                public void setOnStreetSelected(AreaBean areaBean, int position) {
                    ...
                }
            });
```

#### AreaBean
```javascript
    public String name;   //地区名称
    public String id;     //地区id
```
