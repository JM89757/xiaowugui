<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FreeMarker</title>
</head>
<body>
<#include "head.ftl">
${name},Hello! ${message}<br>
<#assign linkman="Mr Wu">
联系人：${linkman}<br>
<#assign info={"mobile":"13888888888","address":"NewYork"}>
电话：${info.mobile} 地址：${info.address}<br>
<#if success=true >
    Is ok!
<#else>
    Is not ok!
</#if>
<br>
<#if failure=true >
    Is ok!
<#else>
    Is not ok!
</#if>
<br>
=============GoodsList=============<br>
<#list goodsList as goods>
    ${goods_index+1} 商品名称：${goods.name} 价格：${goods.price}<br>
</#list>
共${goodsList?size}记录<br>
<#assign text="{'bank':'ICBC','account':'1234567890'}">
<#assign data=text?eval>
开户行：${data.bank} 账户：${data.account}<br>
当前日期：${today?date} <br>
当前时间：${today?time} <br>
当前日期+时间：${today?datetime} <br>
日期格式化： ${today?string("yyyy年MM月dd日HH时mm分ss秒")}<br>
累计金额：${point} 元<br>
累计积分：${point?c}<br>
<#if aaa??>
    aaa存在
<#else>
    aaa不存在
</#if>
<br>
${aaa!'bbb'}<br>
${point+200}<br>
${point-200}<br>
${point*2}<br>
${point/200}<br>
${point%200}<br>
<#if (3>2&&2>1)>
    正确吗？逻辑与
</#if><br>
<#if (1>2||2>1)>
    正确吗？逻辑或
</#if><br>
<#if !(1==3)>
    正确吗？逻辑非
</#if><br>
<#if (point>100)>
    大于
</#if><br>
<#if point gt 100>
    大于
</#if><br>
<#if (point>=1234567890)>
    大于等于
</#if><br>
<#if point gte 1234567890>
    大于等于
</#if><br>
<#if (point<10000000000000)>
    小于
</#if><br>
<#if point lt 10000000000000>
    小于
</#if><br>
<#if (point<=1234567890)>
    小于等于
</#if><br>
<#if point lte 1234567890>
    小于等于
</#if><br>
<#if (1=1)>
    等于吗？
</#if><br>
<#if (1!=1)>
    等于吗？
<#else>
    不等于吗？
</#if><br>

</body>
</html>