package com.qu.modules.web.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.List;

@Data
@ApiModel(value="题目新增入参", description="题目新增入参")
public class SubjectParam {
    /**问卷id*/
    @Excel(name = "问卷id", width = 15)
    @ApiModelProperty(value = "问卷id")
    private java.lang.Integer quId;
    /**题目名称*/
    @Excel(name = "题目名称", width = 15)
    @ApiModelProperty(value = "题目名称")
    private java.lang.String subName;

    @ApiModelProperty(value = "题目类型 1.单选 2.多选 3.日期 4.时间 5.下拉框 6.单行文本 7.多行文本 8.分组框 9.提示标题")
    private java.lang.String subType;
    /**是否为必填 0:非必填 1:必填*/
    @Excel(name = "是否为必填 0:非必填 1:必填", width = 15)
    @ApiModelProperty(value = "是否为必填 0:非必填 1:必填")
    private java.lang.Integer required;
    /**默认是否显示 0:不显示 1:显示*/
    @Excel(name = "默认是否显示 0:不显示 1:显示", width = 15)
    @ApiModelProperty(value = "默认是否显示 0:不显示 1:显示")
    private java.lang.Integer display;
    /**最多字数限制*/
    @Excel(name = "最多字数限制", width = 15)
    @ApiModelProperty(value = "最多字数限制")
    private java.lang.Integer limitWords;
    /**文本框校验 num:数字 email:电子邮箱 ch:中文 en:英文 idcard:身份证号码 qqno:QQ号 phone:手机号码(仅支持大陆地区) tel:电话号码*/
    @Excel(name = "文本框校验 num:数字 email:电子邮箱 ch:中文 en:英文 idcard:身份证号码 qqno:QQ号 phone:手机号码(仅支持大陆地区) tel:电话号码", width = 15)
    @ApiModelProperty(value = "文本框校验 num:数字 email:电子邮箱 ch:中文 en:英文 idcard:身份证号码 qqno:QQ号 phone:手机号码(仅支持大陆地区) tel:电话号码")
    private java.lang.String textCheck;
    /**多行文本框高度*/
    @Excel(name = "多行文本框高度", width = 15)
    @ApiModelProperty(value = "多行文本框高度")
    private java.lang.Integer textHight;
    /**时间是否为区间 0:非区间 1:区间*/
    @Excel(name = "时间是否为区间 0:非区间 1:区间", width = 15)
    @ApiModelProperty(value = "时间是否为区间 0:非区间 1:区间")
    private java.lang.Integer section;
    /**默认值*/
    @Excel(name = "默认值", width = 15)
    @ApiModelProperty(value = "默认值")
    private java.lang.String defValue;
    /**时间显示方式 0:时分 1:时分秒*/
    @Excel(name = "时间显示方式 0:时分 1:时分秒", width = 15)
    @ApiModelProperty(value = "时间显示方式 0:时分 1:时分秒")
    private java.lang.Integer defDisplay;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
    /**跳题逻辑*/
    @Excel(name = "跳题逻辑", width = 15)
    @ApiModelProperty(value = "跳题逻辑")
    private java.lang.String jumpLogic;

    @ApiModelProperty(value = "分组题包含题号")
    private String[] groupIds;

    /**数据库列名*/
    @Excel(name = "数据库列名", width = 15)
    @ApiModelProperty(value = "数据库列名")
    private java.lang.String columnName;

    @ApiModelProperty(value = "选项")
    private List<QoptionParam>  optionParamList;
}
