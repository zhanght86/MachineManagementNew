<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<table  class="table table-hover" id="transferrecordcontent_table">
<tr>
<th style="with-space:nowrap;display:none;">记录编号</th>
<th style="with-space:nowrap;display:none;">设备编号</th>
<th style="width:75px;">前所有者</th>
<th>前所有者邮箱</th>
<th style="width:75px;">现所有者</th>
<th>现所有者邮箱</th>
<th style="display:none;">前记录编号牌</th>
<th>部门</th>
<th>调转原因</th>
<th>更新日期</th>
<th style="color:red;width:63px;">维护</th>
</tr>
 	<c:forEach items="${transferRecordList}" var="tr" varStatus="var">
 	  <tr id="transferrecord_tr_${tr.id}">
 	  <td  style="display:none;"><c:out value="${tr.id}"></c:out></td>
      <td  style="display:none;"><c:out value="${tr.machineInfoID}"></c:out></td>
      <td><input class="form-control " placeholder="前所有者" aria-describedby="basic-addon1" type="text" value="${tr.preOwner}" style="width:55px;padding:1px 1px;"/></td>
      <td><input class="form-control " placeholder="前所有者邮箱" aria-describedby="basic-addon1" type="text"  value="${tr.preOwnerEmail}"  style="padding:1px 1px;"/></td>
      <td><input class="form-control " placeholder="现所有" aria-describedby="basic-addon1" type="text"  value="${tr.curOwner}"  style="width:55px;padding:1px 1px;"/></td>
      <td><input class="form-control " placeholder="现所者邮箱" aria-describedby="basic-addon1" type="text" value="${tr.curOwnerEmail}"  style="padding:1px 1px;"/></td>
      <td  style="display:none;"><c:out value="${tr.preid}"></c:out></td>
      <td><input class="form-control " placeholder="部门" aria-describedby="basic-addon1" type="text" value="${tr.department}" style="padding:1px 1px;"/></td>
      <td><input class="form-control " placeholder="原因" aria-describedby="basic-addon1" type="text" value="${tr.reason}" style="padding:1px 1px;"/></td>
      <td><input class="form-control " placeholder="记录更新时间" aria-describedby="basic-addon1" type="text"  readonly="true" value="${tr.happenTime}" style="padding:1px 1px;"/></td>
      <td>
         <div class="btn-group btn-group-xs" >
          <button class="btn btn-default" style="display:inline-block;" onclick="transferrecord_savechange(${tr.id});" title="保存"><span class="glyphicon glyphicon-floppy-saved" aria-hidden="true" ></span></button>
          <button class="btn btn-default"  style="display:inline-block;" onClick="transferrecord_delete(${tr.id});" title="删除"><span class="glyphicon glyphicon-remove" aria-hidden="true" ></span></button>
          </div>
      </td>
 	  </tr>
 	</c:forEach>

</table>

