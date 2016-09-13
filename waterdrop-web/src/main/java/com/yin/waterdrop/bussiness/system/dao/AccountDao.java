package com.yin.waterdrop.bussiness.system.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.yin.waterdrop.bussiness.system.entity.Account;
import com.yin.waterdrop.frame.dao.MyMapper;
import com.yin.waterdrop.frame.pagePlugin.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-04-15 13:44:42
 * @see com.yin.waterdrop.bussiness.system.entity.Account
 */
public interface AccountDao extends MyMapper<Account> {

	/**
	 * 根据用户名和用户类型查找用户
	 * 
	 * @param username
	 * @param userType
	 * @return
	 */
	Account selectByUsername(String username);

	/**
	 * 分页查找
	 * 
	 * @param account
	 * @param pagination
	 * @return
	 */
	List<Account> listPage(@Param("user") Account account,
			@Param("pagination") Pagination pagination);

	/**
	 * 获取用户权限标识
	 * 
	 * @param username
	 * @param type
	 * @return
	 */
	Set<String> findPermissions(String username);
}
