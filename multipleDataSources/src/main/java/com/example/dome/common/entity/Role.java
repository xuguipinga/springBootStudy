package com.example.dome.common.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Time 2019/8/27 17:45
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain
 */
@Data
@TableName(value = "dj_line")
public class Role {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String line;
    private String lineName;
    private String remarks;
    private String createTime;
    private String operator;
    private String enable;
    private String area;


}
