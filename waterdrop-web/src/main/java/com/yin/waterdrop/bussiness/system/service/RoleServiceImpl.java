package com.yin.waterdrop.bussiness.system.service;

import com.yin.waterdrop.bussiness.system.dao.RoleDao;
import com.yin.waterdrop.bussiness.system.entity.Role;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:42
 * @see com.yin.waterdrop.bussiness.system.entity.service.impl.Role
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> listPage(Role role, Pagination pagination) {
		return roleDao.listPage(role, pagination);
	}

	@Override
	public Integer delPermBYId(Long id) {
		return roleDao.delPermBYId(id);
	}

	@Override
	public Integer bathSavePerm(Long[] rid, Long id) {
		return roleDao.bathSavePerm(rid, id);
	}

	@Override
	public List<Role> findByAccountId(Long id) {
		return roleDao.findByAccountId(id);
	}

	@Override
	public Integer delRoleBYId(Long id) {
		return roleDao.delRoleBYId(id);
	}

	@Override
	public Boolean bathSaveRole(Long accountId,Long[] roleIds) {
		roleDao.delRoleBYId(accountId);
		if(roleIds!=null){
			roleDao.bathSaveRole(accountId,roleIds);
		}
		return true;
	}


	@Override
	public Boolean updatePremission(Long roleId, Long[] resourcesIds) {
		this.delPermBYId(roleId);
		if(resourcesIds!=null&&resourcesIds.length>0){
			this.bathSavePerm(resourcesIds,roleId);
		}
		return true;
	}

}
