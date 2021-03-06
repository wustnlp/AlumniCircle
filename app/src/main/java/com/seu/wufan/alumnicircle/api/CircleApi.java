package com.seu.wufan.alumnicircle.api;

import com.seu.wufan.alumnicircle.api.entity.DynamicListRes;
import com.seu.wufan.alumnicircle.api.entity.DynamicRes;
import com.seu.wufan.alumnicircle.api.entity.PublishDynamicReq;
import com.seu.wufan.alumnicircle.api.entity.QnReq;
import com.seu.wufan.alumnicircle.api.entity.QnRes;
import com.seu.wufan.alumnicircle.api.entity.TopicDynamicRes;
import com.seu.wufan.alumnicircle.api.entity.TopicRes;
import com.seu.wufan.alumnicircle.api.entity.item.DynamicItem;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author wufan
 * @date 2016/4/24
 */
public interface CircleApi {

    @POST("news/")
    Observable<Void> publishDynamic(@Body PublishDynamicReq req);

    //获取最新动态
    @GET("news/timeline/{page}")
    Observable<List<DynamicItem>> getNewDynamic(@Path("page")String page);

    //获取某用户时间线:打开朋友圈时
    @GET("news/timeline/{page}/{user_id}")
    Observable<DynamicListRes> getUserDynamic(@Path("page")String page,@Path("user_id")String user_id);

    //查看动态
    @GET("news/{news_id}")
    Observable<DynamicRes> getDynamic(@Path("news_id")String news_id);

    //获取话题
    @GET("topic/view/")
    Observable<TopicRes> getTopic();

    @GET("topic/timeline/{topic_id}")
    Observable<TopicDynamicRes> getTopicDynamic(@Path("topic_id")String topic_id);

    //创建七牛上传凭证
    @POST("static/token/")
    Observable<List<QnRes>> createQiNiuToken(@Body QnReq req);

}
