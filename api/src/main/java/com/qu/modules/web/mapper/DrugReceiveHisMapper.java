package com.qu.modules.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.qu.modules.web.entity.DrugReceiveHis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 药品规则接收his数据表
 * @Author: jeecg-boot
 * @Date:   2021-09-10
 * @Version: V1.0
 */
public interface DrugReceiveHisMapper extends BaseMapper<DrugReceiveHis> {

    //根据输入内容搜索用药目的
    List<DrugReceiveHis> queryPurposeByInput(String name);

    //根据输入内容搜索物理作用
    List<DrugReceiveHis> queryActionByInput(String name);

    //根据用药目的id获取药品物理作用
    List<DrugReceiveHis> queryByPid(Integer id);

    //根据id获取purposeOrActionId
    Integer queryPurposeOrActionIdById(Integer id);

}
