package com.qu.modules.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qu.modules.web.vo.AnswerPageVo;
import com.qu.modules.web.vo.QSingleDiseaseTakeVo;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import com.qu.modules.web.entity.QSingleDiseaseTake;
import com.qu.modules.web.service.IQSingleDiseaseTakeService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 单病种总表
 * @Author: jeecg-boot
 * @Date:   2021-04-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="单病种总表")
@RestController
@RequestMapping("/business/qSingleDiseaseTake")
public class QSingleDiseaseTakeController {
	@Autowired
	private IQSingleDiseaseTakeService qSingleDiseaseTakeService;


	 /**
	  * 按单病种填报
	  */
	 @AutoLog(value = "按单病种填报查询")
	 @ApiOperation(value="按单病种填报查询", notes="按单病种填报查询")
	 @GetMapping(value = "/singleDiseaseList")
	 public Result<List<QSingleDiseaseTakeVo>> singleDiseaseList(@RequestParam(name = "name", required = false) String name) {
		 Result<List<QSingleDiseaseTakeVo>> result = new Result<>();
		List<QSingleDiseaseTakeVo> list =  qSingleDiseaseTakeService.singleDiseaseList(name);
		 result.setSuccess(true);
		 result.setResult(list);
	 	return result;
	 }
	/**
	  * 分页列表查询
	 * @param qSingleDiseaseTake
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 *//*
	@AutoLog(value = "单病种总表-分页列表查询")
	@ApiOperation(value="单病种总表-分页列表查询", notes="单病种总表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<QSingleDiseaseTake>> queryPageList(QSingleDiseaseTake qSingleDiseaseTake,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<QSingleDiseaseTake>> result = new Result<IPage<QSingleDiseaseTake>>();
		QueryWrapper<QSingleDiseaseTake> queryWrapper = QueryGenerator.initQueryWrapper(qSingleDiseaseTake, req.getParameterMap());
		Page<QSingleDiseaseTake> page = new Page<QSingleDiseaseTake>(pageNo, pageSize);
		IPage<QSingleDiseaseTake> pageList = qSingleDiseaseTakeService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	*//**
	  *   添加
	 * @param qSingleDiseaseTake
	 * @return
	 *//*
	@AutoLog(value = "单病种总表-添加")
	@ApiOperation(value="单病种总表-添加", notes="单病种总表-添加")
	@PostMapping(value = "/add")
	public Result<QSingleDiseaseTake> add(@RequestBody QSingleDiseaseTake qSingleDiseaseTake) {
		Result<QSingleDiseaseTake> result = new Result<QSingleDiseaseTake>();
		try {
			qSingleDiseaseTakeService.save(qSingleDiseaseTake);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	*//**
	  *  编辑
	 * @param qSingleDiseaseTake
	 * @return
	 *//*
	@AutoLog(value = "单病种总表-编辑")
	@ApiOperation(value="单病种总表-编辑", notes="单病种总表-编辑")
	@PutMapping(value = "/edit")
	public Result<QSingleDiseaseTake> edit(@RequestBody QSingleDiseaseTake qSingleDiseaseTake) {
		Result<QSingleDiseaseTake> result = new Result<QSingleDiseaseTake>();
		QSingleDiseaseTake qSingleDiseaseTakeEntity = qSingleDiseaseTakeService.getById(qSingleDiseaseTake.getId());
		if(qSingleDiseaseTakeEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = qSingleDiseaseTakeService.updateById(qSingleDiseaseTake);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	*//**
	  *   通过id删除
	 * @param id
	 * @return
	 *//*
	@AutoLog(value = "单病种总表-通过id删除")
	@ApiOperation(value="单病种总表-通过id删除", notes="单病种总表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<QSingleDiseaseTake> delete(@RequestParam(name="id",required=true) String id) {
		Result<QSingleDiseaseTake> result = new Result<QSingleDiseaseTake>();
		QSingleDiseaseTake qSingleDiseaseTake = qSingleDiseaseTakeService.getById(id);
		if(qSingleDiseaseTake==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = qSingleDiseaseTakeService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}
	
	*//**
	  *  批量删除
	 * @param ids
	 * @return
	 *//*
	@AutoLog(value = "单病种总表-批量删除")
	@ApiOperation(value="单病种总表-批量删除", notes="单病种总表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<QSingleDiseaseTake> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<QSingleDiseaseTake> result = new Result<QSingleDiseaseTake>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.qSingleDiseaseTakeService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	*//**
	  * 通过id查询
	 * @param id
	 * @return
	 *//*
	@AutoLog(value = "单病种总表-通过id查询")
	@ApiOperation(value="单病种总表-通过id查询", notes="单病种总表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<QSingleDiseaseTake> queryById(@RequestParam(name="id",required=true) String id) {
		Result<QSingleDiseaseTake> result = new Result<QSingleDiseaseTake>();
		QSingleDiseaseTake qSingleDiseaseTake = qSingleDiseaseTakeService.getById(id);
		if(qSingleDiseaseTake==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(qSingleDiseaseTake);
			result.setSuccess(true);
		}
		return result;
	}

  *//**
      * 导出excel
   *
   * @param request
   * @param response
   *//*
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<QSingleDiseaseTake> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              QSingleDiseaseTake qSingleDiseaseTake = JSON.parseObject(deString, QSingleDiseaseTake.class);
              queryWrapper = QueryGenerator.initQueryWrapper(qSingleDiseaseTake, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<QSingleDiseaseTake> pageList = qSingleDiseaseTakeService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "单病种总表列表");
      mv.addObject(NormalExcelConstants.CLASS, QSingleDiseaseTake.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("单病种总表列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  *//**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   *//*
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
              List<QSingleDiseaseTake> listQSingleDiseaseTakes = ExcelImportUtil.importExcel(file.getInputStream(), QSingleDiseaseTake.class, params);
              for (QSingleDiseaseTake qSingleDiseaseTakeExcel : listQSingleDiseaseTakes) {
                  qSingleDiseaseTakeService.save(qSingleDiseaseTakeExcel);
              }
              return Result.ok("文件导入成功！数据行数:" + listQSingleDiseaseTakes.size());
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
  }*/

}
