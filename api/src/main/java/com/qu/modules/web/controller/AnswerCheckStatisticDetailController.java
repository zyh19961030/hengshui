package com.qu.modules.web.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qu.modules.web.entity.AnswerCheckStatisticDetail;
import com.qu.modules.web.service.IAnswerCheckStatisticDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 查检表的统计明细表
 * @Author: jeecg-boot
 * @Date:   2023-03-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="查检表的统计明细表")
@RestController
@RequestMapping("/web/answerCheckStatisticDetail")
public class AnswerCheckStatisticDetailController {
	@Autowired
	private IAnswerCheckStatisticDetailService answerCheckStatisticDetailService;
	
	/**
	  * 分页列表查询
	 * @param answerCheckStatisticDetail
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "查检表的统计明细表-分页列表查询")
	@ApiOperation(value="查检表的统计明细表-分页列表查询", notes="查检表的统计明细表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AnswerCheckStatisticDetail>> queryPageList(AnswerCheckStatisticDetail answerCheckStatisticDetail,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<AnswerCheckStatisticDetail>> result = new Result<IPage<AnswerCheckStatisticDetail>>();
		QueryWrapper<AnswerCheckStatisticDetail> queryWrapper = QueryGenerator.initQueryWrapper(answerCheckStatisticDetail, req.getParameterMap());
		Page<AnswerCheckStatisticDetail> page = new Page<AnswerCheckStatisticDetail>(pageNo, pageSize);
		IPage<AnswerCheckStatisticDetail> pageList = answerCheckStatisticDetailService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param answerCheckStatisticDetail
	 * @return
	 */
	@AutoLog(value = "查检表的统计明细表-添加")
	@ApiOperation(value="查检表的统计明细表-添加", notes="查检表的统计明细表-添加")
	@PostMapping(value = "/add")
	public Result<AnswerCheckStatisticDetail> add(@RequestBody AnswerCheckStatisticDetail answerCheckStatisticDetail) {
		Result<AnswerCheckStatisticDetail> result = new Result<AnswerCheckStatisticDetail>();
		try {
			answerCheckStatisticDetailService.save(answerCheckStatisticDetail);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param answerCheckStatisticDetail
	 * @return
	 */
	@AutoLog(value = "查检表的统计明细表-编辑")
	@ApiOperation(value="查检表的统计明细表-编辑", notes="查检表的统计明细表-编辑")
	@PutMapping(value = "/edit")
	public Result<AnswerCheckStatisticDetail> edit(@RequestBody AnswerCheckStatisticDetail answerCheckStatisticDetail) {
		Result<AnswerCheckStatisticDetail> result = new Result<AnswerCheckStatisticDetail>();
		AnswerCheckStatisticDetail answerCheckStatisticDetailEntity = answerCheckStatisticDetailService.getById(answerCheckStatisticDetail.getId());
		if(answerCheckStatisticDetailEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = answerCheckStatisticDetailService.updateById(answerCheckStatisticDetail);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "查检表的统计明细表-通过id删除")
	@ApiOperation(value="查检表的统计明细表-通过id删除", notes="查检表的统计明细表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<AnswerCheckStatisticDetail> delete(@RequestParam(name="id",required=true) String id) {
		Result<AnswerCheckStatisticDetail> result = new Result<AnswerCheckStatisticDetail>();
		AnswerCheckStatisticDetail answerCheckStatisticDetail = answerCheckStatisticDetailService.getById(id);
		if(answerCheckStatisticDetail==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = answerCheckStatisticDetailService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "查检表的统计明细表-批量删除")
	@ApiOperation(value="查检表的统计明细表-批量删除", notes="查检表的统计明细表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<AnswerCheckStatisticDetail> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<AnswerCheckStatisticDetail> result = new Result<AnswerCheckStatisticDetail>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.answerCheckStatisticDetailService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "查检表的统计明细表-通过id查询")
	@ApiOperation(value="查检表的统计明细表-通过id查询", notes="查检表的统计明细表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AnswerCheckStatisticDetail> queryById(@RequestParam(name="id",required=true) String id) {
		Result<AnswerCheckStatisticDetail> result = new Result<AnswerCheckStatisticDetail>();
		AnswerCheckStatisticDetail answerCheckStatisticDetail = answerCheckStatisticDetailService.getById(id);
		if(answerCheckStatisticDetail==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(answerCheckStatisticDetail);
			result.setSuccess(true);
		}
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<AnswerCheckStatisticDetail> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              AnswerCheckStatisticDetail answerCheckStatisticDetail = JSON.parseObject(deString, AnswerCheckStatisticDetail.class);
              queryWrapper = QueryGenerator.initQueryWrapper(answerCheckStatisticDetail, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<AnswerCheckStatisticDetail> pageList = answerCheckStatisticDetailService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "查检表的统计明细表列表");
      mv.addObject(NormalExcelConstants.CLASS, AnswerCheckStatisticDetail.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("查检表的统计明细表列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<AnswerCheckStatisticDetail> listAnswerCheckStatisticDetails = ExcelImportUtil.importExcel(file.getInputStream(), AnswerCheckStatisticDetail.class, params);
              for (AnswerCheckStatisticDetail answerCheckStatisticDetailExcel : listAnswerCheckStatisticDetails) {
                  answerCheckStatisticDetailService.save(answerCheckStatisticDetailExcel);
              }
              return Result.ok("文件导入成功！数据行数:" + listAnswerCheckStatisticDetails.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
