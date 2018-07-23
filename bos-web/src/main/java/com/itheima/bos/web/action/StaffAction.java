package com.itheima.bos.web.action;

import com.itheima.bos.domain.Staff;
import com.itheima.bos.service.IStaffService;
import com.itheima.bos.utils.PageBean;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class StaffAction extends IBaseAction<Staff> {

    //属性驱动，接收页面提交的分页参数
    private int page;
    private int rows;
    private String ids;

    @Autowired
    private IStaffService staffService;
    public String add(){
        staffService.add(model);
        return LIST;
    }

    /**
     * 分页查询方法
     * @throws IOException
     */
    public String pageQuery() throws IOException{
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        //创建离线提交查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        staffService.pageQuery(pageBean);

        //使用json-lib将PageBean对象转为json，通过输出流写回页面中
        //JSONObject---将单一对象转为json
        //JSONArray----将数组或者集合对象转为json
        JsonConfig jsonConfig = new JsonConfig();
        //指定哪些属性不需要转json
        jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    /**
     * 作废
     * @return
     */
    public String delete(){
        staffService.delete(ids);
        return LIST;
    }


    public int getPage() {
        return page;
    }

    public int getRows() {
        return rows;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
