package com.github.novicezk.midjourney.dto;

import com.github.novicezk.midjourney.enums.TaskAction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("变化任务提交参数")
@EqualsAndHashCode(callSuper = true)
public class SubmitModifyDTO extends BaseSubmitDTO {

  @ApiModelProperty(value = "任务ID", required = true, example = "\"1320098173412546\"")
  private String taskId;

  @ApiModelProperty(
      value = "ZOOM(重新生成)",
      required = true,
      allowableValues = "ZOOM",
      example = "ZOOM")
  private TaskAction action;

  @ApiModelProperty(value = "序号(1~2)", allowableValues = "range[1, 2]", example = "1")
  private Integer index;
}
