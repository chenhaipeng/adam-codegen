<#assign tableName=model.tableName>
<#assign tableDisplayName=model.tableDisplayName>
<#assign package=vars.package>
<#assign subpackage=vars.subpackage>
package com.thinkme.${package}.${subpackage}.service;

import com.thinkme.framework.base.service.BaseService;
import com.thinkme.${package}.${subpackage}.entity.${tableDisplayName};

/**
<#if model.tableComment!="" >
 * ${model.tableComment} service
 *
</#if>
<#if vars.author?exists>
 * @author ${vars.author}
</#if>
 * @version 1.0
 * @date ${date?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${tableDisplayName}Service extends BaseService<${tableDisplayName}>{


}