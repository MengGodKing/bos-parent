package com.itheima.bos.service;

import com.itheima.bos.domain.Region;
import com.itheima.bos.utils.PageBean;

import java.util.List;

public interface RegionService {

    public void saveBatch(List<Region> regionList);

    public void pageQuery(PageBean pageBean);
}
