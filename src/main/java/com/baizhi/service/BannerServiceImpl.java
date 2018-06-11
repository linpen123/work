package com.baizhi.service;

import com.baizhi.controller.LoggerAnnotation;
import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDAO bannerDAO;
    @Override
    //分页展示轮播图
    public List<Banner> selAll(int begin,int end) {
        return bannerDAO.queryAll(begin,end);
    }
    @Override
    //添加轮播图
    public void regiser(Banner banner) {
        bannerDAO.save(banner);
    }
    @Override
    //删除轮播图
    @LoggerAnnotation(name="删除一份轮播图")
    public boolean delBanner(int id) {
        try {
            bannerDAO.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
//更改轮播图
    @Override
    public boolean updateBanner(Banner banner) {
        try {
            bannerDAO.update(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Banner selById(int id) {
        return bannerDAO.queryById(id);
    }
}
