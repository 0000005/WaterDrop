<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign pkJavaType = table.idColumn.javaType>  
package  ${basepackage}.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;
<#assign myParentDir="${table.classNameFirstLower}">


<#include "/copyright_class.include" >
@Controller
@RequestMapping(value="/${classNameLowerCase}")
public class ${className}Controller {
	 @Autowired
	private ${className}Service ${classNameLower}Service;
	
	
}
