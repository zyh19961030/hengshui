package com.qu.modules.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.api.vo.ResultBetter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qu.constant.AnswerCheckConstant;
import com.qu.constant.Constant;
import com.qu.modules.web.entity.AnswerCheck;
import com.qu.modules.web.param.AnswerCheckAddParam;
import com.qu.modules.web.param.AnswerCheckDeleteParam;
import com.qu.modules.web.param.AnswerCheckDetailListExportParam;
import com.qu.modules.web.param.AnswerCheckDetailListParam;
import com.qu.modules.web.param.AnswerCheckListParam;
import com.qu.modules.web.param.CheckQuestionHistoryStatisticDeptDetailListExportParam;
import com.qu.modules.web.param.CheckQuestionHistoryStatisticDeptDetailListParam;
import com.qu.modules.web.param.CheckQuestionHistoryStatisticDeptRecordListParam;
import com.qu.modules.web.param.CheckQuestionHistoryStatisticDetailListExportParam;
import com.qu.modules.web.param.CheckQuestionHistoryStatisticDetailListParam;
import com.qu.modules.web.param.CheckQuestionHistoryStatisticRecordListParam;
import com.qu.modules.web.pojo.Data;
import com.qu.modules.web.request.AnswerCheckListRequest;
import com.qu.modules.web.request.CheckQuestionHistoryStatisticDetailListExportRequest;
import com.qu.modules.web.request.CheckQuestionHistoryStatisticDetailListRequest;
import com.qu.modules.web.request.CheckQuestionHistoryStatisticRecordListRequest;
import com.qu.modules.web.service.IAnswerCheckService;
import com.qu.modules.web.vo.AnswerCheckDetailListVo;
import com.qu.modules.web.vo.AnswerCheckPageVo;
import com.qu.modules.web.vo.AnswerCheckVo;
import com.qu.modules.web.vo.CheckQuestionHistoryStatisticRecordListVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 检查表问卷总表
 * @Author: jeecg-boot
 * @Date:   2022-07-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags="检查表问卷总表")
@RestController
@RequestMapping("/business/answerCheck")
public class AnswerCheckController {

	@Autowired
	private IAnswerCheckService answerCheckService;

	 @ApiOperation(value = "检查表问卷_填报中分页列表", notes = "检查表问卷_填报中分页列表")
	 @GetMapping(value = "/checkQuestionFillInList")
	 public Result<AnswerCheckPageVo> checkQuestionFillInList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
			 HttpServletRequest request) {
		 Result<AnswerCheckPageVo> result = new Result<AnswerCheckPageVo>();
         AnswerCheckListRequest listRequest = new AnswerCheckListRequest();
		 Data data = (Data) request.getSession().getAttribute(Constant.SESSION_USER);
		 String deptId = data.getDeps().get(0).getId();
         listRequest.setUserDeptId(deptId);
		 IPage<AnswerCheckVo> answerPageVo = answerCheckService.checkQuestionFillInList(listRequest, pageNo, pageSize, AnswerCheckConstant.ANSWER_STATUS_DRAFT);
		 result.setSuccess(true);
		 result.setResult(answerPageVo);
		 return result;
	 }

	 @ApiOperation(value = "检查表问卷_填报记录(已完成的)分页列表_改为全院检查记录", notes = "检查表问卷_填报记录(已完成的)分页列表_改为全院检查记录")
	 @GetMapping(value = "/checkQuestionRecordList")
	 public Result<AnswerCheckPageVo> checkQuestionRecordList(AnswerCheckListParam answerCheckListParam,
															  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
															  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
															  HttpServletRequest request) {
		 Result<AnswerCheckPageVo> result = new Result<AnswerCheckPageVo>();
         AnswerCheckListRequest listRequest = new AnswerCheckListRequest();
         BeanUtils.copyProperties(answerCheckListParam,listRequest);
		 IPage<AnswerCheckVo> answerPageVo = answerCheckService.checkQuestionFillInList(listRequest,pageNo, pageSize, AnswerCheckConstant.ANSWER_STATUS_RELEASE);
		 result.setSuccess(true);
		 result.setResult(answerPageVo);
		 return result;
	 }


    @ApiOperation(value = "检查表问卷_填报记录_我的填报记录", notes = "检查表问卷_填报记录_我的填报记录")
    @GetMapping(value = "/myCheckQuestionRecordList")
    public Result<AnswerCheckPageVo> myCheckQuestionRecordList(AnswerCheckListParam answerCheckListParam,
                                                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                             HttpServletRequest request) {
        Result<AnswerCheckPageVo> result = new Result<AnswerCheckPageVo>();
        AnswerCheckListRequest listRequest = new AnswerCheckListRequest();
        Data data = (Data) request.getSession().getAttribute(Constant.SESSION_USER);
        String userId = data.getTbUser().getId();
        BeanUtils.copyProperties(answerCheckListParam,listRequest);
        listRequest.setUserId(userId);
        IPage<AnswerCheckVo> answerPageVo = answerCheckService.checkQuestionFillInList(listRequest,pageNo, pageSize, AnswerCheckConstant.ANSWER_STATUS_RELEASE);
        result.setSuccess(true);
        result.setResult(answerPageVo);
        return result;
    }

	@ApiOperation(value = "检查表管理_历史统计_上级督查_填报记录分页列表(职能科室和临床科室同一个接口)", notes = "检查表管理_历史统计_上级督查_填报记录分页列表(职能科室和临床科室同一个接口)")
	@GetMapping(value = "/checkQuestionHistoryStatisticRecordList")
	public ResultBetter<IPage<CheckQuestionHistoryStatisticRecordListVo>> checkQuestionHistoryStatisticRecordList(@Valid CheckQuestionHistoryStatisticRecordListParam recordListParam,
                                                                                                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                                                               HttpServletRequest request) {
		ResultBetter<IPage<CheckQuestionHistoryStatisticRecordListVo>> result = new ResultBetter<IPage<CheckQuestionHistoryStatisticRecordListVo>>();
		CheckQuestionHistoryStatisticRecordListRequest listRequest = new CheckQuestionHistoryStatisticRecordListRequest();
		BeanUtils.copyProperties(recordListParam,listRequest);
		IPage<CheckQuestionHistoryStatisticRecordListVo> answerPageVo = answerCheckService.checkQuestionHistoryStatisticRecordList(listRequest,pageNo, pageSize);
		result.setSuccess(true);
		result.setResult(answerPageVo);
		return result;
	}

	@ApiOperation(value = "检查表管理_历史统计_科室自查_填报记录分页列表(职能科室和临床科室同一个接口)", notes = "检查表管理_历史统计_科室自查_填报记录分页列表(职能科室和临床科室同一个接口)")
	@GetMapping(value = "/checkQuestionHistoryStatisticDeptRecordList")
	public ResultBetter<IPage<CheckQuestionHistoryStatisticRecordListVo>> checkQuestionHistoryStatisticDeptRecordList(@Valid CheckQuestionHistoryStatisticDeptRecordListParam deptRecordListParam,
																										 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
																										 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
																										 HttpServletRequest request) {
		ResultBetter<IPage<CheckQuestionHistoryStatisticRecordListVo>> result = new ResultBetter<IPage<CheckQuestionHistoryStatisticRecordListVo>>();
		CheckQuestionHistoryStatisticRecordListRequest listRequest = new CheckQuestionHistoryStatisticRecordListRequest();
		BeanUtils.copyProperties(deptRecordListParam,listRequest);
		IPage<CheckQuestionHistoryStatisticRecordListVo> answerPageVo = answerCheckService.checkQuestionHistoryStatisticRecordList(listRequest,pageNo, pageSize);
		result.setSuccess(true);
		result.setResult(answerPageVo);
		return result;
	}

	@ApiOperation(value = "检查表管理_历史统计_上级督查/科室自查_填报记录分页列表_删除接口", notes = "检查表管理_历史统计_科室自查_填报记录分页列表(职能科室和临床科室同一个接口)")
	@GetMapping(value = "/checkQuestionRecordDelete")
	public ResultBetter checkQuestionRecordDelete(@Valid AnswerCheckDeleteParam param,
												  HttpServletRequest request) {
		Data data = (Data) request.getSession().getAttribute(Constant.SESSION_USER);
		return answerCheckService.checkQuestionRecordDelete(param, data.getTbUser().getId());
	}

	 @ApiOperation(value = "答题", notes = "答题")
	 @PostMapping(value = "/answer")
	 public Result answer(@RequestBody AnswerCheckAddParam answerCheckAddParam, HttpServletRequest request) {
		 String token = request.getHeader("token");
		 String cookie = "JSESSIONID=" + token;
		 log.info("-----------answerCheckAddParam={}", JSON.toJSONString(answerCheckAddParam));
		 return answerCheckService.answer(cookie, answerCheckAddParam);
	 }


	 @ApiOperation(value = "回显_通过id查询", notes = "回显_通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<AnswerCheck> queryById(@RequestParam(name = "id", required = true) String id) {
		 Result<AnswerCheck> result = new Result<AnswerCheck>();
		 AnswerCheck answerCheck = answerCheckService.queryById(id);
		 if (answerCheck == null) {
			 result.error500("未找到对应实体");
		 } else {
			 result.setResult(answerCheck);
			 result.setSuccess(true);
		 }
		 return result;
	 }

	 @ApiOperation(value = "检查表_检查明细记录", notes = "检查表_检查明细记录",response = AnswerCheckDetailListVo.class)
	 @GetMapping(value = "/detailList")
	 public Result<AnswerCheckDetailListVo> detailList(@Valid AnswerCheckDetailListParam answerCheckDetailListParam,
													   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
													   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
													   HttpServletRequest request) {
		 Result<AnswerCheckDetailListVo> result = new Result<>();
		 Data data = (Data) request.getSession().getAttribute(Constant.SESSION_USER);
		 AnswerCheckDetailListVo vo =  answerCheckService.detailList(answerCheckDetailListParam, data, pageNo, pageSize);
		 result.setSuccess(true);
		 result.setResult(vo);
		 return result;
	 }

	@ApiOperation(value = "检查管理_历史统计_上级督查_明细表格分页列表(职能科室和临床科室同一个接口)", notes = "检查管理_历史统计_上级督查_明细表格分页列表(职能科室和临床科室同一个接口)",response = AnswerCheckDetailListVo.class)
	@GetMapping(value = "/checkQuestionHistoryStatisticDetailList")
	public ResultBetter<AnswerCheckDetailListVo> checkQuestionHistoryStatisticDetailList(@Valid CheckQuestionHistoryStatisticDetailListParam detailListParam,
													  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
													  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
													  HttpServletRequest request) {
		ResultBetter<AnswerCheckDetailListVo> result = new ResultBetter<>();
		Data data = (Data) request.getSession().getAttribute(Constant.SESSION_USER);
		CheckQuestionHistoryStatisticDetailListRequest listRequest = new CheckQuestionHistoryStatisticDetailListRequest();
		BeanUtils.copyProperties(detailListParam,listRequest);
		AnswerCheckDetailListVo vo =  answerCheckService.checkQuestionHistoryStatisticDetailList(listRequest, data, pageNo, pageSize);
		result.setSuccess(true);
		result.setResult(vo);
		return result;
	}

	@ApiOperation(value = "检查管理_历史统计_科室自查_明细表格分页列表(职能科室和临床科室同一个接口)", notes = "检查管理_历史统计_科室自查_明细表格分页列表(职能科室和临床科室同一个接口)",response = AnswerCheckDetailListVo.class)
	@GetMapping(value = "/checkQuestionHistoryStatisticDeptDetailList")
	public ResultBetter<AnswerCheckDetailListVo> checkQuestionHistoryStatisticDeptDetailList(@Valid CheckQuestionHistoryStatisticDeptDetailListParam deptDetailListParam,
																				   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
																				   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
																				   HttpServletRequest request) {
		ResultBetter<AnswerCheckDetailListVo> result = new ResultBetter<>();
		Data data = (Data) request.getSession().getAttribute(Constant.SESSION_USER);
		CheckQuestionHistoryStatisticDetailListRequest listRequest = new CheckQuestionHistoryStatisticDetailListRequest();
		BeanUtils.copyProperties(deptDetailListParam,listRequest);
		AnswerCheckDetailListVo vo =  answerCheckService.checkQuestionHistoryStatisticDetailList(listRequest, data, pageNo, pageSize);
		result.setSuccess(true);
		result.setResult(vo);
		return result;
	}



      /**
       * 检查表_检查明细记录_导出excel
       *
       * @param response
       */
      @GetMapping(value = "/exportXlsDetailList")
      @ApiOperation(value = "检查表_检查明细记录_导出", notes = "检查表_检查明细记录_导出")
      public void exportXlsDetailList(@Valid AnswerCheckDetailListExportParam answerCheckDetailListExportParam,HttpServletResponse response) {
          answerCheckService.exportXlsDetailList(answerCheckDetailListExportParam, response);
      }


	/**
	 * 检查管理_历史统计_上级督查_明细表格分页列表_导出(职能科室和临床科室同一个接口)
	 *
	 * @param response
	 */
	@GetMapping(value = "/exportXlsCheckQuestionHistoryStatisticDetailList")
	@ApiOperation(value = "检查管理_历史统计_上级督查_明细表格分页列表_导出(职能科室和临床科室同一个接口)", notes = "检查管理_历史统计_上级督查_明细表格分页列表_导出(职能科室和临床科室同一个接口)")
	public void exportXlsCheckQuestionHistoryStatisticDetailList(@Valid CheckQuestionHistoryStatisticDetailListExportParam detailListExportParam,HttpServletResponse response) {
		CheckQuestionHistoryStatisticDetailListExportRequest exportRequest = new CheckQuestionHistoryStatisticDetailListExportRequest();
		BeanUtils.copyProperties(detailListExportParam,exportRequest);
		answerCheckService.exportXlsCheckQuestionHistoryStatisticDetailList(exportRequest, response);
	}

	/**
	 * 检查管理_历史统计_科室自查_明细表格分页列表_导出(职能科室和临床科室同一个接口)
	 *
	 * @param response
	 */
	@GetMapping(value = "/exportXlsCheckQuestionHistoryStatisticDeptDetailList")
	@ApiOperation(value = "检查管理_历史统计_科室自查_明细表格分页列表_导出(职能科室和临床科室同一个接口)", notes = "检查管理_历史统计_科室自查_明细表格分页列表_导出(职能科室和临床科室同一个接口)")
	public void exportXlsCheckQuestionHistoryStatisticDeptDetailList(@Valid CheckQuestionHistoryStatisticDeptDetailListExportParam deptDetailListExportParam,HttpServletResponse response) {
		CheckQuestionHistoryStatisticDetailListExportRequest exportRequest = new CheckQuestionHistoryStatisticDetailListExportRequest();
		BeanUtils.copyProperties(deptDetailListExportParam,exportRequest);
		answerCheckService.exportXlsCheckQuestionHistoryStatisticDetailList(exportRequest, response);
	}




    //	/**
//	  * 分页列表查询
//	 * @param answerCheck
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "检查表问卷总表-分页列表查询")
//	@ApiOperation(value="检查表问卷总表-分页列表查询", notes="检查表问卷总表-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<IPage<AnswerCheck>> queryPageList(AnswerCheck answerCheck,
//									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//									  HttpServletRequest req) {
//		Result<IPage<AnswerCheck>> result = new Result<IPage<AnswerCheck>>();
//		QueryWrapper<AnswerCheck> queryWrapper = QueryGenerator.initQueryWrapper(answerCheck, req.getParameterMap());
//		Page<AnswerCheck> page = new Page<AnswerCheck>(pageNo, pageSize);
//		IPage<AnswerCheck> pageList = answerCheckService.page(page, queryWrapper);
//		result.setSuccess(true);
//		result.setResult(pageList);
//		return result;
//	}
//
//	/**
//	  *   添加
//	 * @param answerCheck
//	 * @return
//	 */
//	@AutoLog(value = "检查表问卷总表-添加")
//	@ApiOperation(value="检查表问卷总表-添加", notes="检查表问卷总表-添加")
//	@PostMapping(value = "/add")
//	public Result<AnswerCheck> add(@RequestBody AnswerCheck answerCheck) {
//		Result<AnswerCheck> result = new Result<AnswerCheck>();
//		try {
//			answerCheckService.save(answerCheck);
//			result.success("添加成功！");
//		} catch (Exception e) {
//			log.error(e.getMessage(),e);
//			result.error500("操作失败");
//		}
//		return result;
//	}
//
//	/**
//	  *  编辑
//	 * @param answerCheck
//	 * @return
//	 */
//	@AutoLog(value = "检查表问卷总表-编辑")
//	@ApiOperation(value="检查表问卷总表-编辑", notes="检查表问卷总表-编辑")
//	@PutMapping(value = "/edit")
//	public Result<AnswerCheck> edit(@RequestBody AnswerCheck answerCheck) {
//		Result<AnswerCheck> result = new Result<AnswerCheck>();
//		AnswerCheck answerCheckEntity = answerCheckService.getById(answerCheck.getId());
//		if(answerCheckEntity==null) {
//			result.error500("未找到对应实体");
//		}else {
//			boolean ok = answerCheckService.updateById(answerCheck);
//			//TODO 返回false说明什么？
//			if(ok) {
//				result.success("修改成功!");
//			}
//		}
//
//		return result;
//	}
//
//	/**
//	  *   通过id删除
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "检查表问卷总表-通过id删除")
//	@ApiOperation(value="检查表问卷总表-通过id删除", notes="检查表问卷总表-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<AnswerCheck> delete(@RequestParam(name="id",required=true) String id) {
//		Result<AnswerCheck> result = new Result<AnswerCheck>();
//		AnswerCheck answerCheck = answerCheckService.getById(id);
//		if(answerCheck==null) {
//			result.error500("未找到对应实体");
//		}else {
//			boolean ok = answerCheckService.removeById(id);
//			if(ok) {
//				result.success("删除成功!");
//			}
//		}
//
//		return result;
//	}
//
//	/**
//	  *  批量删除
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "检查表问卷总表-批量删除")
//	@ApiOperation(value="检查表问卷总表-批量删除", notes="检查表问卷总表-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<AnswerCheck> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		Result<AnswerCheck> result = new Result<AnswerCheck>();
//		if(ids==null || "".equals(ids.trim())) {
//			result.error500("参数不识别！");
//		}else {
//			this.answerCheckService.removeByIds(Arrays.asList(ids.split(",")));
//			result.success("删除成功!");
//		}
//		return result;
//	}
//
//	/**
//	  * 通过id查询
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "检查表问卷总表-通过id查询")
//	@ApiOperation(value="检查表问卷总表-通过id查询", notes="检查表问卷总表-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<AnswerCheck> queryById(@RequestParam(name="id",required=true) String id) {
//		Result<AnswerCheck> result = new Result<AnswerCheck>();
//		AnswerCheck answerCheck = answerCheckService.getById(id);
//		if(answerCheck==null) {
//			result.error500("未找到对应实体");
//		}else {
//			result.setResult(answerCheck);
//			result.setSuccess(true);
//		}
//		return result;
//	}
//
//  /**
//      * 导出excel
//   *
//   * @param request
//   * @param response
//   */
//  @RequestMapping(value = "/exportXls")
//  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
//      // Step.1 组装查询条件
//      QueryWrapper<AnswerCheck> queryWrapper = null;
//      try {
//          String paramsStr = request.getParameter("paramsStr");
//          if (oConvertUtils.isNotEmpty(paramsStr)) {
//              String deString = URLDecoder.decode(paramsStr, "UTF-8");
//              AnswerCheck answerCheck = JSON.parseObject(deString, AnswerCheck.class);
//              queryWrapper = QueryGenerator.initQueryWrapper(answerCheck, request.getParameterMap());
//          }
//      } catch (UnsupportedEncodingException e) {
//          e.printStackTrace();
//      }
//
//      //Step.2 AutoPoi 导出Excel
//      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//      List<AnswerCheck> pageList = answerCheckService.list(queryWrapper);
//      //导出文件名称
//      mv.addObject(NormalExcelConstants.FILE_NAME, "检查表问卷总表列表");
//      mv.addObject(NormalExcelConstants.CLASS, AnswerCheck.class);
//      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("检查表问卷总表列表数据", "导出人:Jeecg", "导出信息"));
//      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
//      return mv;
//  }
//
//  /**
//      * 通过excel导入数据
//   *
//   * @param request
//   * @param response
//   * @return
//   */
//  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//          MultipartFile file = entity.getValue();// 获取上传文件对象
//          ImportParams params = new ImportParams();
//          params.setTitleRows(2);
//          params.setHeadRows(1);
//          params.setNeedSave(true);
//          try {
//              List<AnswerCheck> listAnswerChecks = ExcelImportUtil.importExcel(file.getInputStream(), AnswerCheck.class, params);
//              for (AnswerCheck answerCheckExcel : listAnswerChecks) {
//                  answerCheckService.save(answerCheckExcel);
//              }
//              return Result.ok("文件导入成功！数据行数:" + listAnswerChecks.size());
//          } catch (Exception e) {
//              log.error(e.getMessage(),e);
//              return Result.error("文件导入失败:"+e.getMessage());
//          } finally {
//              try {
//                  file.getInputStream().close();
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }
//          }
//      }
//      return Result.ok("文件导入失败！");
//  }

}
