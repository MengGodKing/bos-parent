package com.itheima.bos.service;

import com.itheima.bos.domain.Region;

import java.util.List;

public interface RegionService {

    void saveBatch(List<Region> regionList);
}
