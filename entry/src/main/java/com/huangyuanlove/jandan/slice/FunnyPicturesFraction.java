package com.huangyuanlove.jandan.slice;

import com.huangyuanlove.jandan.MyApplication;
import com.huangyuanlove.jandan.ResourceTable;
import com.huangyuanlove.jandan.data.NewsVO;
import com.huangyuanlove.jandan.data.PicsVO;
import com.huangyuanlove.jandan.data.RequestResultBean;
import com.huangyuanlove.jandan.net.PicsInterface;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FunnyPicturesFraction extends Fraction {

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
      Component component=  scatter.parse(ResourceTable.Layout_fraction_funny_picture,container,false);
        Text text = (Text) component.findComponentById(ResourceTable.Id_info);
        PicsInterface picsService =    MyApplication.retrofit.create(PicsInterface.class);
        Call<RequestResultBean<PicsVO>> call =   picsService.getPics(1);
        call.enqueue(new Callback<RequestResultBean<PicsVO>>() {
            @Override
            public void onResponse(Call<RequestResultBean<PicsVO>> call, Response<RequestResultBean<PicsVO>> response) {
                if(response.body()!=null &&"ok".equals(response.body().getStatus()) ){
                    text.setText(response.body().getComments().toString());
                }
            }

            @Override
            public void onFailure(Call<RequestResultBean<PicsVO>> call, Throwable throwable) {
                text.setText("请求出错");
            }
        });

        return component;
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
    }
}