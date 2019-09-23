package com.example.dome.server;

import com.example.dome.common.annotion.DataSource;
import com.example.dome.common.entity.Role;
import com.example.dome.common.mapper.RoleMapper;
import com.example.dome.common.mutidatesource.DSEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;

    public Role selectId(Integer id){
        return roleMapper.selectById(id);
    }

    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public Role selectIdByBiz(Integer id){
        return roleMapper.selectById(id);
    }
}
