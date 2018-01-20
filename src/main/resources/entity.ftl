<#assign tableName=model.tableName>
<#assign tableDisplayName=model.tableDisplayName>
<#assign package=vars.package>
<#assign subpackage=vars.subpackage>
package com.thinkme.${package}.${subpackage}.entity;

import lombok.Data;
import java.io.Serializable;
import org.nutz.dao.entity.annotation.*;
/**
<#if model.tableComment!="" >
 * ${model.tableComment} entity
</#if>
<#if vars.author?exists>
 * @author ${vars.author}
</#if>
 * @version 1.0
 * @date ${date?string("yyyy-MM-dd HH:mm:ss")}
 */
@Table("${tableName}")
@Data
public class ${tableDisplayName} implements Serializable{
<#list model.columnList as col>
<#if col.columnComment!="" >
    // ${col.columnComment}
</#if>
<#if (col.isPK) >
    @Id
</#if>
    @Column("${col.columnName}")
    private ${col.javaType} ${col.columnDisplayName};
</#list>

}