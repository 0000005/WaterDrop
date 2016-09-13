package  com.yin.waterdrop.bussiness.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.yin.waterdrop.bussiness.server.entity.Servers;
import com.yin.waterdrop.bussiness.server.service.ServersService;


/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.servers.Servers
 */
@Controller
@RequestMapping(value="/servers")
public class ServersController {
	 @Autowired
	private ServersService serversService;
	
	
}
