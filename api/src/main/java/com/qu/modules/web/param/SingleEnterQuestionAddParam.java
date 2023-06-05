package com.qu.modules.web.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description: 录入表单表
 * @Author: jeecg-boot
 * @Date:   2023-05-24
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SingleEnterQuestionAddParam", description="SingleEnterQuestionAddParam")
public class SingleEnterQuestionAddParam {

	@NotNull
    @ApiModelProperty(value = "查检表id")
	private Integer questionId;

	@ApiModelProperty(value = "展示列的题目id集合")
	private List<Integer> columnId;

	@ApiModelProperty(value = "填报题目id集合")
	private List<Integer> subjectId;

	@ApiModelProperty(value = "录入表单名称")
	private String name;

	@ApiModelProperty(value = "筛选题目id")
	private Integer selectSubjectId;

}
