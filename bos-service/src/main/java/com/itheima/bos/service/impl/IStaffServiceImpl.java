package com.itheima.bos.service.impl;

import com.itheima.bos.dao.IStaffDao;
import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IStaffServiceImpl implements IStaffService {
    @Autowired
    private IStaffDao staffDao;
    @Override
    public void add(Staff staff) {
        staffDao.save(staff);
    }
}
