package com.qu.modules.web.controller;

import com.qu.modules.web.service.ITbFollowVisitTemplateCycleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 /**
 * @Description: 随访模板周期表
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="随访模板周期表")
@RestController
@RequestMapping("/web/tbFollowVisitTemplateCycle")
public class TbFollowVisitTemplateCycleController {
	@Autowired
	private ITbFollowVisitTemplateCycleService tbFollowVisitTemplateCycleService;
	
//	/**
//	  * 分页列表查询
//	 * @param tbFollowVisitTemplateCycle
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "随访模板周期表-分页列表查询")
//	@ApiOperation(value="随访模板周期表-分页列表查询", notes="随访模板周期表-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<IPage<TbFollowVisitTemplateCycle>> queryPageList(TbFollowVisitTemplateCycle tbFollowVisitTemplateCycle,
//									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//									  HttpServletRequest req) {
//		Result<IPage<TbFollowVisitTemplateCycle>> result = new Result<IPage<TbFollowVisitTemplateCycle>>();
//		QueryWrapper<TbFollowVisitTemplateCycle> queryWrapper = QueryGenerator.initQueryWrapper(tbFollowVisitTemplateCycle, req.getParameterMap());
//		Page<TbFollowVisitTemplateCycle> page = new Page<TbFollowVisitTemplateCycle>(pageNo, pageSize);
//		IPage<TbFollowVisitTemplateCycle> pageList = tbFollowVisitTemplateCycleService.page(page, queryWrapper);
//		result.setSuccess(true);
//		result.setResult(pageList);
//		return result;
//	}
//
//	/**
//	  *   添加
//	 * @param tbFollowVisitTemplateCycle
//	 * @return
//	 */
//	@AutoLog(value = "随访模板周期表-添加")
//	@ApiOperation(value="随访模板周期表-添加", notes="随访模板周期表-添加")
//	@PostMapping(value = "/add")
//	public Result<TbFollowVisitTemplateCycle> add(@RequestBody TbFollowVisitTemplateCycle tbFollowVisitTemplateCycle) {
//		Result<TbFollowVisitTemplateCycle> result = new Result<TbFollowVisitTemplateCycle>();
//		try {
//			tbFollowVisitTemplateCycleService.save(tbFollowVisitTemplateCycle);
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
//	 * @param tbFollowVisitTemplateCycle
//	 * @return
//	 */
//	@AutoLog(value = "随访模板周期表-编辑")
//	@ApiOperation(value="随访模板周期表-编辑", notes="随访模板周期表-编辑")
//	@PutMapping(value = "/edit")
//	public Result<TbFollowVisitTemplateCycle> edit(@RequestBody TbFollowVisitTemplateCycle tbFollowVisitTemplateCycle) {
//		Result<TbFollowVisitTemplateCycle> result = new Result<TbFollowVisitTemplateCycle>();
//		TbFollowVisitTemplateCycle tbFollowVisitTemplateCycleEntity = tbFollowVisitTemplateCycleService.getById(tbFollowVisitTemplateCycle.getId());
//		if(tbFollowVisitTemplateCycleEntity==null) {
//			result.error500("未找到对应实体");
//		}else {
//			boolean ok = tbFollowVisitTemplateCycleService.updateById(tbFollowVisitTemplateCycle);
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
//	@AutoLog(value = "随访模板周期表-通过id删除")
//	@ApiOperation(value="随访模板周期表-通过id删除", notes="随访模板周期表-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<TbFollowVisitTemplateCycle> delete(@RequestParam(name="id",required=true) String id) {
//		Result<TbFollowVisitTemplateCycle> result = new Result<TbFollowVisitTemplateCycle>();
//		TbFollowVisitTemplateCycle tbFollowVisitTemplateCycle = tbFollowVisitTemplateCycleService.getById(id);
//		if(tbFollowVisitTemplateCycle==null) {
//			result.error500("未找到对应实体");
//		}else {
//			boolean ok = tbFollowVisitTemplateCycleService.removeById(id);
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
//	@AutoLog(value = "随访模板周期表-批量删除")
//	@ApiOperation(value="随访模板周期表-批量删除", notes="随访模板周期表-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<TbFollowVisitTemplateCycle> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		Result<TbFollowVisitTemplateCycle> result = new Result<TbFollowVisitTemplateCycle>();
//		if(ids==null || "".equals(ids.trim())) {
//			result.error500("参数不识别！");
//		}else {
//			this.tbFollowVisitTemplateCycleService.removeByIds(Arrays.asList(ids.split(",")));
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
//	@AutoLog(value = "随访模板周期表-通过id查询")
//	@ApiOperation(value="随访模板周期表-通过id查询", notes="随访模板周期表-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<TbFollowVisitTemplateCycle> queryById(@RequestParam(name="id",required=true) String id) {
//		Result<TbFollowVisitTemplateCycle> result = new Result<TbFollowVisitTemplateCycle>();
//		TbFollowVisitTemplateCycle tbFollowVisitTemplateCycle = tbFollowVisitTemplateCycleService.getById(id);
//		if(tbFollowVisitTemplateCycle==null) {
//			result.error500("未找到对应实体");
//		}else {
//			result.setResult(tbFollowVisitTemplateCycle);
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
//      QueryWrapper<TbFollowVisitTemplateCycle> queryWrapper = null;
//      try {
//          String paramsStr = request.getParameter("paramsStr");
//          if (oConvertUtils.isNotEmpty(paramsStr)) {
//              String deString = URLDecoder.decode(paramsStr, "UTF-8");
//              TbFollowVisitTemplateCycle tbFollowVisitTemplateCycle = JSON.parseObject(deString, TbFollowVisitTemplateCycle.class);
//              queryWrapper = QueryGenerator.initQueryWrapper(tbFollowVisitTemplateCycle, request.getParameterMap());
//          }
//      } catch (UnsupportedEncodingException e) {
//          e.printStackTrace();
//      }
//
//      //Step.2 AutoPoi 导出Excel
//      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//      List<TbFollowVisitTemplateCycle> pageList = tbFollowVisitTemplateCycleService.list(queryWrapper);
//      //导出文件名称
//      mv.addObject(NormalExcelConstants.FILE_NAME, "随访模板周期表列表");
//      mv.addObject(NormalExcelConstants.CLASS, TbFollowVisitTemplateCycle.class);
//      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("随访模板周期表列表数据", "导出人:Jeecg", "导出信息"));
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
//              List<TbFollowVisitTemplateCycle> listTbFollowVisitTemplateCycles = ExcelImportUtil.importExcel(file.getInputStream(), TbFollowVisitTemplateCycle.class, params);
//              for (TbFollowVisitTemplateCycle tbFollowVisitTemplateCycleExcel : listTbFollowVisitTemplateCycles) {
//                  tbFollowVisitTemplateCycleService.save(tbFollowVisitTemplateCycleExcel);
//              }
//              return Result.ok("文件导入成功！数据行数:" + listTbFollowVisitTemplateCycles.size());
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
