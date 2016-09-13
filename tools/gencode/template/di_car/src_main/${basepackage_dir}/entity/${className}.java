<#assign myParentDir="entity">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.commons.lang.StringUtils;
import javax.persistence.Id;
import javax.persistence.Table;



<#include "/copyright_class.include" >
@Table(name = "${table.sqlName}")
public class ${className}  implements Serializable{
	
	//columns START
	<#list table.columns as column>
	/**
	 * ${column.columnAlias}
	 */
	 <#if column.isPk()>
	@Id
	</#if>
	private ${column.simpleJavaType} ${column.camelName};
	</#list>
	//columns END 数据库字段结束
	
	

	//get and set
	<@generateJavaColumns/>
	
	public String toString() {
		return new StringBuffer()
		<#list table.columns as column>
			.append("${column.camelName}=").append(get${column.firstUperCamelName}()).append(",")
		</#list>
			.toString();
	}
	
	
}

	
<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
		/*
	public String get${column.columnNameFirstLower}String() {
		return DateUtils.convertDate2String(FORMAT_${column.constantName}, get${column.columnNameFirstLower}());
	}
	public void set${column.columnNameFirstLower}String(String value) throws ParseException{
		set${column.columnNameFirstLower}(DateUtils.convertString2Date(FORMAT_${column.constantName},value));
	}*/
	
		</#if>	
	public void set${column.firstUperCamelName}(${column.simpleJavaType} ${column.camelName}) {
	    
	     <#if column.simpleJavaType=="String">
		    if(StringUtils.isNotBlank(${column.camelName})){
			 ${column.camelName}=${column.camelName}.trim();
			}
		 </#if>
		this.${column.camelName} = ${column.camelName};
	}
	
	
	public ${column.simpleJavaType} get${column.firstUperCamelName}() {
		return this.${column.camelName};
	}
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
	
	

