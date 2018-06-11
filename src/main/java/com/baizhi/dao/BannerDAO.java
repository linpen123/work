package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {
 //分页展示所有
    public List<Banner> queryAll(@Param("begin")int begin, @Param("end")int end);
//添加轮播图
    public void save(Banner banner);
//删除轮播图
    public void delete(int id);
//更改轮播图信息
    public void update(Banner banner);
    public Banner queryById(int id);

}
