<#assign myParentDir="service.impl">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;

import java.util.List;
import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.impl.BaseServiceImpl;


import ${basepackage}.dao.${className}Dao;



<#include "/copyright_class.include" >
@Service("${classNameLower}Service")
public class ${className}ServiceImpl  extends BaseServiceImpl<${className}> implements ${className}Service {
	@Autowired
    private ${className}Dao ${classNameLower}Dao;
   
    public List<${className}> listPage(${className} ${classNameLower}, Pagination pagination){
		return ${classNameLower}Dao.listPage(${classNameLower},pagination);
	}
}
