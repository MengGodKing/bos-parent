package com.itheima.bos.web.action;

import com.itheima.bos.domain.Decidedzone;
import com.itheima.bos.service.DecidedzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends IBaseAction<Decidedzone> {

    private  String[] subareaid;
    @Autowired
    private DecidedzoneService decidedzoneService;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    public String add(){
        decidedzoneService.save(model,subareaid);
        return LIST;
    }

    public String pageQuery(){

        decidedzoneService.pageQuery(pageBean);
        this.java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});
        return NONE;
    }


}
