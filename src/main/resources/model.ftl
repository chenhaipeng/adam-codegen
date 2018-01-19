<#assign package=vars.package>
<#assign class=vars.class>
<#assign tableName=model.tableName>
package com.pp.loan.dto.${package};

import java.util.Date;
import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
/**
* 对象功能:${model.tableComment} Model对象
<#if vars.company?exists>
* 开发公司:${vars.company}
</#if>
<#if vars.developer?exists>
* 开发人员:${vars.developer}
</#if>
* 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
*/
@Table("${tableName}")
public class ${class} implements Serializable{
private static final long serialVersionUID = 1L;
<#list model.columnList as col>
// ${col.columnComment}
    <#if (col.isPK) >
    @Name
    </#if>
@Column("${col.columnName}")
private ${col.javaType} ${col.columnDisplayName};
</#list>

<#list model.columnList as col>
    <#assign colName=col.columnDisplayName>
public void set${colName?cap_first}(${col.javaType} ${colName}){
    this.${colName} = ${colName};
}
/**
* 返回 ${col.columnComent!""}
* @return
*/
public ${col.javaType} get${colName?cap_first}() {
    return this.${colName};
}
</#list>


}