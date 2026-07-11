<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String httpRoot = System.getProperty("com.pojomax.sync.app.http-root");
%>

<div class='pojo-section'>
<div class='pojo-section-header'>Comment Edit Form</div>
<div class='pojo-section-body'>
<div align="left">

    <c:set var="comment" value="${comment}"/>
    <form id="idCommentEdit" name="Comment" action="do" method="POST">

        <p>
            <label style="color: red;width: 100%;text-align: left;">${errorMessage}</label> 
        </p>

        <input type='hidden' id="opCommentEdit" name='op' value="${opAction}">
        <input type='hidden' name='id' value="${comment.id}">
        <input type='hidden' name='deviceId' value="${comment.deviceId}">
        <input type='hidden' name='commentId' value="${comment.commentId}">
        <input type='hidden' name='lastUpdate' value="${comment.lastUpdate}">
        <input type='hidden' name='deleteFlag' value="${comment.deleteFlag}">
        <input type='hidden' name='postingId' value="${postingId}">
        <input type='hidden' name='mode' value="${mode}">

        <table class="pojo_tablestyle">

            <tr>
               <th width="10%"></th>
               <th width="23%"></th>
               <th width="10%"></th>
               <th width="23%"></th>
               <th width="10%"></th>
               <th width="23%"></th>
            </tr>

            <tr>

              <td style="text-align: right;"><label for="member">Member:</label></td>
              <td>
              <c:if test="${!empty commentMemberId}">
                <input type="hidden" id=member name="commentMemberId" value="${commentMemberId}" />
                ${commentMemberString}
              </c:if>
              <c:if test="${!empty memberOptions}">
                <select name="memberId">
                <c:forEach items="${memberOptions}" var="item">
                <option value='<c:out value="${item.id}"/>' <c:out value="${item.selected}"/>><c:out value="${item.memberOption}"/></option>
                </c:forEach>
                </select>
              </c:if>
              </td>

              <td style="text-align: right;"><label for="commentDate">Comment Date:</label></td>
              <td><input type="text" id=commentDate name="commentDate" value="<fmt:formatDate pattern='${formatDateStr}' value='${comment.commentDate}'/>" />
              <img onclick="goDatePicker('#commentDate', 'yy/mm/dd');"border="0" src="resources/gfx/calendar.png" alt='Pick Date' />
              <c:if test="${!empty commentDateError}">
                <brk/><label style="color: red; width: 100%;text-align: left;">${commentDateError}</label>
              </c:if>
              </td>

            </tr>

            <tr>

              <td style="text-align: right;"><label for="postingComment">Posting Comment:</label></td>
              <td colspan=5 ><input type="text" id=postingComment name="postingComment" style="width:100%;" value="${comment.postingComment}" />
              <c:if test="${!empty postingCommentError}">
                <brk/><label style="color: red; width: 130%;text-align: left;">${postingCommentError}</label>
              </c:if>
              </td>

            </tr>

        </table>


        <p>
            <button class='btnEdit' id="edit" type="button" onclick='goCommentEdit("${opAction}");'>Edit</button>
            &nbsp;&nbsp;&nbsp;
            <button class='btnBack' id="back" type="button" onclick='goCommentEdit("${opBack}");'>Back</button>
        </p>

    </form>

</div>

<script>
  function goCommentEdit(op)
  {
    $('#opCommentEdit').val(op);
    $('#idCommentEdit').submit();
  }

  function goCommentUpload(op, type)
  {
    $('#opCommentEdit').val(op);
    $('#postType').val(type);
    $('#idCommentEdit').submit();
  }

  function goDatePicker(y, fmt)
  {
    $(y).datepicker({dateFormat:fmt});
    $(y).focus();
  }
</script>

</div>
</div>
