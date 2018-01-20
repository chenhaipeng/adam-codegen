<#assign tableName=model.tableName>
<#assign tableDisplayName=model.tableDisplayName>
<#assign package=vars.package>
<#assign subpackage=vars.subpackage>
package com.thinkme.${package}.${subpackage}.service.impl;

import com.thinkme.${package}.${subpackage}.service.${tableDisplayName}Service;
import com.thinkme.framework.base.service.BaseServiceImpl;
import com.thinkme.${package}.${subpackage}.entity.${tableDisplayName};
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
@Slf4j
@Service
public class ${tableDisplayName}ServiceImpl extends BaseServiceImpl<${tableDisplayName}> implements ${tableDisplayName}Service {


}