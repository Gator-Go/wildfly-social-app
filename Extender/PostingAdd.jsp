<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String httpRoot = System.getProperty("com.pojomax.sync.app.http-root");
%>

<div class='pojo-section'>
<div class='pojo-section-header'>Posting Add Form</div>
<div class='pojo-section-body'>
<div align="left">

    <c:set var="posting" value="${posting}"/>
    <form id="idPostingAdd" name="PostingAdd" action="do" method="POST">

        <p>
            <label style="color: red;width: 100%;text-align: left;">${errorMessage}</label> 
        </p>

        <input type='hidden' id="opPostingAdd" name='op' value="${opAction}">
        <input type='hidden' name='id' value="0">
        <input type='hidden' name='deviceId' value="0">
        <input type='hidden' name='postingId' value="0">
        <input type='hidden' name='lastUpdate' value="${posting.lastUpdate}">
        <input type='hidden' name='deleteFlag' value="${posting.deleteFlag}">
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
              <c:if test="${!empty postingMemberId}">
                <input type="hidden" id=member name="postingMemberId" value="${postingMemberId}" />
                ${postingMemberString}
              </c:if>
              <c:if test="${!empty memberOptions}">
                <select name="memberId">
                <c:forEach items="${memberOptions}" var="item">
                <option value='<c:out value="${item.id}"/>' <c:out value="${item.selected}"/>><c:out value="${item.memberOption}"/></option>
                </c:forEach>
                </select>
              </c:if>
              </td>

              <td style="text-align: right;"><label for="postingDate">Posting Date:</label></td>
              <td><input type="text" id=postingDate name="postingDate" value="<fmt:formatDate pattern='${formatDateStr}' value='${posting.postingDate}'/>" />
              <img onclick="goDatePicker('#postingDate', 'yy/mm/dd');"border="0" src="resources/gfx/calendar.png" alt='Pick Date' />
              <c:if test="${!empty postingDateError}">
                <brk/><label style="color: red; width: 100%;text-align: left;">${postingDateError}</label>
              </c:if>
              </td>
            </tr>

            <tr>
              <td style="text-align: right;"><label for="postingInfo">Posting Info:</label></td>
              <td colspan=5 ><input type="text" id=postingInfo name="postingInfo" style="width:100%;" value="${posting.postingInfo}" />
              <c:if test="${!empty postingInfoError}">
                <brk/><label style="color: red; width: 100%;text-align: left;">${postingInfoError}</label>
              </c:if>
              </td>
            </tr>

            <tr>
              <td style="text-align: right;"><label for="PostingVersion">Posting:</label></td>
              <td>
              <input type='hidden' name='postingVersion' value="${posting.postingVersion}">
              <input type='hidden' name='postingTempName' value="${posting.postingTempName}">
              <input type='hidden' id='postType' name='postType' value="${posting.postingType}">
              <button class='btnUpload' id="upload" type="button" onclick='goPostingUpload("PostingPostingUpload","pic");'>Picture</button>
              &nbsp;&nbsp;&nbsp;
              <button class='btnUpload' id="upload" type="button" onclick='goPostingUpload("PostingPostingUpload","vid");'>Video</button>
              <c:if test="${!(empty posting.postingTempName)}">
                  New Posting Uploaded
              </c:if>
              <c:if test="${empty posting.postingTempName}">
                  Posting Not Uploaded
              </c:if>
              <c:if test="${!empty PostingVersionError}">
                <brk/><label style="color: red; width: 130%;text-align: left;">${PostingVersionError}</label>
              </c:if>
              </td>
            </tr>


        </table>

        <c:if test="${(posting.postingType eq 'pic')}">
          <img src="<%= httpRoot %>/temp/${posting.postingTempName}PicThumb.jpg" alt="Posting">
        </c:if>
        <c:if test="${(posting.postingType eq 'vid')}">
          <img src="<%= httpRoot %>/temp/${posting.postingTempName}VidThumb.jpg" alt="Posting">
        </c:if>


        <p>
            <button class='btnAdd' id="add" type="button" onclick='goPostingAdd("${opAction}");'>Add</button>
            &nbsp;&nbsp;&nbsp;
            <button class='btnBack' id="back" type="button" onclick='goPostingAdd("${opBack}");'>Back</button>
        </p>

    </form>
</div>

<script>
  function goPostingAdd(op)
  {
    $('#opPostingAdd').val(op);
    $('#idPostingAdd').submit();
  }

  function goPostingUpload(op, type)
  {
    $('#opPostingAdd').val(op);
    $('#postType').val(type);
    $('#idPostingAdd').submit();
  }

  function goDatePicker(y, fmt)
  {
    $(y).datepicker({dateFormat:fmt});
    $(y).focus();
  }
</script>

</div>
</div>
