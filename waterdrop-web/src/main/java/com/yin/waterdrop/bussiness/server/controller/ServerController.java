package  com.yin.waterdrop.bussiness.server.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yin.waterdrop.bussiness.server.entity.Server;
import com.yin.waterdrop.bussiness.server.service.ServerService;
import com.yin.waterdrop.bussiness.system.entity.Account;
import com.yin.waterdrop.bussiness.system.entity.Dict;
import com.yin.waterdrop.common.filter.WebContext;
import com.yin.waterdrop.common.utils.Const;
import com.yin.waterdrop.frame.pagePlugin.Pagination;


/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.Server.Servers
 */
@Controller
@RequestMapping(value="/servers")
public class ServerController {
	@Resource
	private ServerService serversService;
	
	 /**
	 * 服务器列表主页面
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("server:manage:view")
	@RequestMapping("/list")
	public String list() 
	{
		return "/server/manage/list";
	}
	
	/**
	 * 获取服务器数据
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	@RequiresPermissions("server:manage:view")
	@ResponseBody
	@RequestMapping("/list.json")
	public Object list(Server server, Pagination pagination) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List<Server> list = serversService.listPage(server, pagination);
		map.put("list", list);
		map.put(Const.PAGINATION, pagination);
		return map;
	}
	
	/**
	 * 添加服务器页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("server:manage:add")
	@RequestMapping("/add")
	public String add(String id) {
		if (id != null) 
		{
			WebContext.setAttribute("id", id);
			Server server = serversService.selectByPrimaryKey(id);
			WebContext.setAttribute("server", server);
		}
		return "/server/manage/add";
	}
	
	/**
	 * 保存服务器信息
	 * @param server
	 * @return
	 */
	@RequiresPermissions("server:manage:add")
	@RequestMapping("/save")
	@ResponseBody
	public Object save(Server server) 
	{
		return serversService.saveServer(server);
	}
	
	/**
	 * 进入单个服务器管理页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("server:manage:view")
	@RequestMapping("/manage/{id}")
	public String serverIndex(@PathVariable String id) 
	{
		Server server = serversService.selectByPrimaryKey(id);
		if(server==null)
		{
			WebContext.setAttribute("msg", "没有找到这个服务器（id:"+id+"）");
			return "/common/500";
		}
		WebContext.setAttribute("server", server);
		return "/server/manage/serverIndex";
	}
	
	/**
	 * 进入服务器信息页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("server:manage:view")
	@RequestMapping("/manage/{id}/serverInfo")
	public String serverInfo(@PathVariable String id) 
	{
		Server server = serversService.selectByPrimaryKey(id);
		if(server==null)
		{
			WebContext.setAttribute("msg", "没有找到这个服务器（id:"+id+"）");
			return "/common/500";
		}
		WebContext.setAttribute("server", server);
		return "/server/manage/serverInfo";
	}
	
	
}
