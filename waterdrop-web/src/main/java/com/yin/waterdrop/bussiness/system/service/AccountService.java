package com.yin.waterdrop.bussiness.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yin.waterdrop.bussiness.system.entity.Account;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.BaseService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yin.waterdrop.bussiness.system.entity.Account
 */
public interface AccountService extends BaseService<Account>{

	Account selectByUsername(String username);

	List<Account> listPage(Account account, Pagination pagination);

	/**
	 * 获取用户权限标识
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	Set<String> findPermissions(String username);
	
	public Map<String,Object> saveAccount(Account account);

}
