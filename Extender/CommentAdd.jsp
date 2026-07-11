<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String httpRoot = System.getProperty("com.pojomax.sync.app.http-root");
%>

<div class='pojo-section'>
<div class='pojo-section-header'>Comment Add Form</div>
<div class='pojo-section-body'>
<div align="left">

    <c:set var="comment" value="${comment}"/>
    <form id="idCommentAdd" name="CommentAdd" action="do" method="POST">

        <p>
            <label style="color: red;width: 100%;text-align: left;">${errorMessage}</label> 
        </p>

        <input type='hidden' id="opCommentAdd" name='op' value="${opAction}">
        <input type='hidden' name='id' value="${id}">
        <input type='hidden' name='deviceId' value="0">
        <input type='hidden' name='commentId' value="0">
        <input type='hidden' name='lastUpdate' value="${comment.lastUpdate}">
        <input type='hidden' name='deleteFlag' value="${comment.deleteFlag}">
        <input type='hidden' name='mode' value="${mode}">
        <input type="hidden" name="commentPostingId" value="${commentPostingId}" />

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
                <brk/><label style="color: red; width: 100%;text-align: left;">${postingCommentError}</label>
              </c:if>
              </td>

            </tr>



        </table>


        <p>
            <button class='btnAdd' id="add" type="button" onclick='goCommentAdd("${opAction}");'>Add</button>
            &nbsp;&nbsp;&nbsp;
            <button class='btnBack' id="back" type="button" onclick='goCommentAdd("${opBack}");'>Back</button>
        </p>

    </form>
</div>

<script>
  function goCommentAdd(op)
  {
    $('#opCommentAdd').val(op);
    $('#idCommentAdd').submit();
  }

  function goCommentUpload(op, type)
  {
    $('#opCommentAdd').val(op);
    $('#postType').val(type);
    $('#idCommentAdd').submit();
  }

  function goDatePicker(y, fmt)
  {
    $(y).datepicker({dateFormat:fmt});
    $(y).focus();
  }
</script>

</div>
</div>
