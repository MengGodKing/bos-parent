package com.itheima.bos.service;


import com.itheima.bos.domain.Staff;
import com.itheima.bos.utils.PageBean;

public interface IStaffService {

    public void add(Staff staff);
    public void pageQuery(PageBean pageBean);

}
