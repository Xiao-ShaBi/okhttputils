package com.zhy.http.okhttp.builder;

import android.net.Uri;

import com.zhy.http.okhttp.request.GetRequest;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhy on 15/12/14.
 * 这也是一个请求体，从名字看，是一个get的请求体
 * 将post请求的参数封装进get请求的url之中
 */
public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> implements HasParamsable {
    @Override
    public RequestCall build() {
        if (params != null) {
            url = appendParams(url, params);
        }

        return new GetRequest(url, tag, params, headers, id).build();
    }

    //将请求体放入build中，并转化为字符串
    protected String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }


    /**
     * 添加参数
     *
     * @param params
     * @return
     */
    @Override
    public GetBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    /**
     * 将添加的key value封装为param
     *
     * @param key
     * @param val
     * @return
     */
    @Override
    public GetBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }


}
