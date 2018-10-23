package com.xmgps.service;

import com.xmgps.dao.DaoSupport;
import com.xmgps.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public PageData checkUser(PageData pd) throws Exception {
        return (PageData)dao.selectOne("UserMapper.checkUser",pd);
    }

    public List<Map<String,Object>> queryMenuAuthByUserName(String userName) throws Exception {
        return (List<Map<String,Object>>)dao.findForList("UserMapper.queryMenuAuthByUserName",userName);
    }
}
