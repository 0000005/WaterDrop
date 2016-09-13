<#assign myParentDir="service">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;
import java.util.List;

import ${basepackage}.entity.${className};
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.BaseService;


<#include "/copyright_class.include" >
public interface ${className}Service extends BaseService<${className}>{
	
	/**
	 * 分页查找
	 * 
	 * @param ${classNameLower}
	 * @param pagination
	 * @return
	 */
	List<${className}> listPage(${className} ${classNameLower}, Pagination pagination);
}
