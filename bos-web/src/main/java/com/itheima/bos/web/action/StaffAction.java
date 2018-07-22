package com.itheima.bos.web.action;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class StaffAction extends IBaseAction<Staff> {
    @Autowired
    private IStaffService staffService;
    public String add(){
        staffService.add(model);
        return LIST;
    }
}
