<#assign myParentDir="dao">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;

import ${basepackage}.entity.${className};
import com.yin.waterdrop.frame.dao.MyMapper;
import com.yin.waterdrop.frame.pagePlugin.Pagination;

<#include "/copyright_class.include" >
public interface ${className}Dao extends MyMapper<${className}> {
	
	/**
	 * 分页查找
	 * 
	 * @param ${classNameLower}
	 * @param pagination
	 * @return
	 */
	List<${className}> listPage(@Param("${classNameLower}") ${className} ${classNameLower},
			@Param("pagination") Pagination pagination);
}
