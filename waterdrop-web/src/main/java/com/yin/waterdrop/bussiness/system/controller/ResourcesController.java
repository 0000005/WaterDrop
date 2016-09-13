package com.yin.waterdrop.bussiness.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yin.waterdrop.bussiness.system.entity.Dict;
import com.yin.waterdrop.bussiness.system.entity.Resources;
import com.yin.waterdrop.bussiness.system.service.DictService;
import com.yin.waterdrop.bussiness.system.service.ResourcesService;
import com.yin.waterdrop.common.filter.WebContext;
import com.yin.waterdrop.common.shiro.ShiroSessionUtils;
import com.yin.waterdrop.common.utils.Const;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yin.waterdrop.bussiness.system.entity.web.Resources
 */
@Controller
@RequestMapping(value = "resources")
public class ResourcesController {
	@Resource
	private ResourcesService resourcesService;
	@Resource
	private DictService dictService;

	@ResponseBody
	@RequestMapping("/currentResources.json")
	public List<Resources> currentResources() {
		Long accountId = ShiroSessionUtils.getLoginAccount().getId();
			return resourcesService.findByRoleId(accountId);
	}

	@RequestMapping("/list")
	public String list() {
		Resources r = new Resources();
		r.setType(0);//只查询目录
		List<Resources> pList = resourcesService.list(r);
		WebContext.setAttribute("pList",pList);
		List<Dict> disabledList = dictService.selectByColumn(Const.DISABLED);
		WebContext.setAttribute("disabledList", disabledList);
		List<Dict> rTypeList = dictService.selectByColumn(Const.RESOURCE_TYPE);
		WebContext.setAttribute("rTypeList",rTypeList);
		return "/system/resources/list";
	}

	@ResponseBody
	@RequestMapping("/list.json")
	public Object list(Resources resource) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Resources> list = resourcesService.list(resource);
		map.put("list", list);
		return map;
	}

	@RequiresPermissions("sys:perm:add")
	@RequestMapping("/add")
	public String add(Integer id) {
		Resources r = new Resources();
		List<Resources> list = resourcesService.list(r);
		WebContext.setAttribute("list", list);
		if(id!=null){
			r=resourcesService.selectByPrimaryKey(id);
			WebContext.setAttribute("r",r);
		}
		return "/system/resources/add";
	}

	@ResponseBody
	@RequestMapping("/getById/{id}")
	public Object getById(@PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Resources r = resourcesService.selectByPrimaryKey(id);
			map.put(Const.STATUS, 1);
			map.put("data", r);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}


	@ResponseBody
	@RequestMapping("/save")
	public Object save(Resources resources) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try {
			Integer i;
			if (resources.getId() != null && resources.getId() != 0) {
				i = resourcesService.updateByPrimaryKey(resources);
			} else {
				i = resourcesService.save(resources);
			}
			if (i!=null&&i > 0) {
				map.put(Const.STATUS, 0);
				map.put(Const.MSG, "保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.MSG, Const.DEFAULT_ERROR);
		}
		return map;
	}
}
