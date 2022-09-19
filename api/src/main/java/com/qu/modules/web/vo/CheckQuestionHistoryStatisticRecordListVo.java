package com.qu.modules.web.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CheckQuestionHistoryStatisticRecordListVo", description = "CheckQuestionHistoryStatisticRecordListVo")
public class CheckQuestionHistoryStatisticRecordListVo {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "问卷id")
    private Integer quId;

    @ApiModelProperty(value = "问卷名称(检查项目)")
    private String quName;

    @ApiModelProperty(value = "被检查科室id")
    private String checkedDept;

    @ApiModelProperty(value = "检查月份")
    private String checkMonth;

    @ApiModelProperty(value = "被检查科室名称")
    private String checkedDeptName;


    @ApiModelProperty(value = "检查科室id")
    private String createrDeptId;

    @ApiModelProperty(value = "检查科室名称")
    private String createrDeptName;

//    @ApiModelProperty(value = "答题人id")
//    private String creater;

    @ApiModelProperty(value = "检查填报人姓名")
    private String createrName;

    /**答题时间-填报时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "答题时间-填报时间")
    private Date answerTime;


}
