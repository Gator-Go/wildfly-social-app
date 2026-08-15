def changes = []

public class MyData {

  def theFile = ""

  def extMarker =
"""
"""
  def srcInsert =
"""
"""
}
MyData newData = new MyData()



// ******** Changes to show comment details in delete and view ******** 

newData = new MyData()
newData.theFile = "CommentDelete.jsp"
newData.extMarker =
"""
            <button class='btnBack' id="back" type="button" onclick='goCommentDelete("\${opBack}");'>Back</button>
            &nbsp;&nbsp;&nbsp;
            <button class='btnView' id="show" type="button" onclick='goShow();'>Details</button>
"""
newData.srcInsert =
"""
            <button class='btnBack' id="back" type="button" onclick='goCommentDelete("\${opBack}");'>Back</button>
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "CommentDelete.jsp"
newData.extMarker =
"""
  function goShow()
  {
    var detail = \$('.detail');
    detail.toggle();
  }
"""
newData.srcInsert =
"""
  \$( document ).ready(function() {
     \$(".detail").show();
  });
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentView.jsp"
newData.extMarker =
"""
            <button class='btnBack' id="back" type="button" onclick='goCommentView("\${opBack}");'>Back</button>
            &nbsp;&nbsp;&nbsp;
            <button class='btnView' id="show" type="button" onclick='goShow();'>Details</button>
"""
newData.srcInsert =
"""
            <button class='btnBack' id="back" type="button" onclick='goCommentView("\${opBack}");'>Back</button>
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "CommentView.jsp"
newData.extMarker =
"""
  function goShow()
  {
    var detail = \$('.detail');
    detail.toggle();
  }
"""
newData.srcInsert =
"""
  \$( document ).ready(function() {
     \$(".detail").show();
  });
"""
changes.add(newData)

// ******** Done ********


// ******** Changes to give sort order ******** 

newData = new MyData()
newData.theFile = "MemberService.java"
newData.extMarker =
"""
        return em.createQuery("FROM Member l WHERE l.deleteFlag = FALSE ORDER BY l.lastUpdate DESC").getResultList();
"""
newData.srcInsert =
"""
        return em.createQuery("FROM Member l WHERE l.deleteFlag = FALSE order by l.name").getResultList();
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "MemberService.java"
newData.extMarker =
"""
        return em.createQuery("from Member l WHERE l.deleteFlag = FALSE order by l.id desc", Member.class).setFirstResult(offset).setMaxResults(max).getResultList();
"""
newData.srcInsert =
"""
        return em.createQuery("from Member l WHERE l.deleteFlag = FALSE order by l.name", Member.class).setFirstResult(offset).setMaxResults(max).getResultList();
"""
changes.add(newData)

// ******** Done ********




// ******** Changes to remember member pick ******** 

newData = new MyData()
newData.theFile = "PostingServlet.java"
newData.extMarker =
"""
            id = new Long(req.getParameter("id"));
            req.getSession().setAttribute("PostingMemberId", req.getParameter("id"));
"""
newData.srcInsert =
"""
            id = new Long(req.getParameter("id"));
            req.getSession().setAttribute("PostingMemberId", req.getParameter("id"));
            req.getSession().setAttribute("MemberIdPick", req.getParameter("id"));
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "CommentServlet.java"
newData.extMarker =
"""
            id = new Long(req.getParameter("id"));
            req.getSession().setAttribute("CommentMemberId", req.getParameter("id"));
"""
newData.srcInsert =
"""
            id = new Long(req.getParameter("id"));
            req.getSession().setAttribute("CommentMemberId", req.getParameter("id"));
            req.getSession().setAttribute("MemberIdPick", req.getParameter("id"));
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "PostingServlet.java"
newData.extMarker =
"""
        if (req.getSession().getAttribute("PostingMemberId") != null) {
            String postingMemberId = (String)req.getSession().getAttribute("PostingMemberId");
            Long idPostingMember = new Long(postingMemberId);
            Member member = memberService.getMember(idPostingMember);
            req.setAttribute("postingMemberString", memberString.getString(member));
            req.setAttribute("postingMemberId", postingMemberId);
        } else {
            ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(new Long(-1));
            if(memberOptions.size() == 0) {
                req.setAttribute("msg", "Member records must be entered first.");
                doPostingList(req, resp);
                return;
            }
            req.setAttribute("memberOptions", memberOptions);
        }
"""
newData.srcInsert =
"""
        if (req.getSession().getAttribute("MemberIdPick") != null) {
            String memberId = (String)req.getSession().getAttribute("MemberIdPick");
            Long idMember = new Long(memberId);
            ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(idMember);
            req.setAttribute("memberOptions", memberOptions);
        } else {
            ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(new Long(-1));
            if(memberOptions.size() == 0) {
                req.setAttribute("msg", "Member records must be entered first.");
                doPostingList(req, resp);
                return;
            }
            req.setAttribute("memberOptions", memberOptions);
        }
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "CommentServlet.java"
newData.extMarker =
"""
        if (req.getSession().getAttribute("CommentMemberId") != null) {
            String commentMemberId = (String)req.getSession().getAttribute("CommentMemberId");
            Long idCommentMember = new Long(commentMemberId);
            Member member = memberService.getMember(idCommentMember);
            req.setAttribute("commentMemberString", memberString.getString(member));
            req.setAttribute("commentMemberId", commentMemberId);
        } else {
            ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(new Long(-1));
            if(memberOptions.size() == 0) {
                req.setAttribute("msg", "Member records must be entered first.");
                doCommentList(req, resp);
                return;
            }
            req.setAttribute("memberOptions", memberOptions);
        }
        if (req.getSession().getAttribute("CommentPostingId") != null) {
            String commentPostingId = (String)req.getSession().getAttribute("CommentPostingId");
            Long idCommentPosting = new Long(commentPostingId);
            Posting posting = postingService.getPosting(idCommentPosting);
            req.setAttribute("commentPostingString", postingString.getString(posting));
            req.setAttribute("commentPostingId", commentPostingId);
        } else {
            ArrayList<PostingOption> postingOptions = postingString.loadPostingOptions(new Long(-1));
            if(postingOptions.size() == 0) {
                req.setAttribute("msg", "Posting records must be entered first.");
                doCommentList(req, resp);
                return;
            }
            req.setAttribute("postingOptions", postingOptions);
        }



        req.setAttribute("opBack", "CommentList");
"""
newData.srcInsert =
"""
        if (req.getSession().getAttribute("MemberIdPick") != null) {
            String memberId = (String)req.getSession().getAttribute("MemberIdPick");
            Long idMember = new Long(memberId);
            ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(idMember);
            req.setAttribute("memberOptions", memberOptions);
        } else {
            ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(new Long(-1));
            if(memberOptions.size() == 0) {
                req.setAttribute("msg", "Member records must be entered first.");
                doCommentList(req, resp);
                return;
            }
            req.setAttribute("memberOptions", memberOptions);
        }
        
        String postingId = req.getParameter("PostingId");
        if (postingId != null) {
            req.getSession().setAttribute("PostingId", postingId);
            Long idPosting = new Long(postingId);
            Posting posting = postingService.getPosting(idPosting);
            req.setAttribute("commentPostingString", posting.getPostingName());
            req.setAttribute("commentPostingId", postingId);
            req.setAttribute("id", postingId);
        } else {
          if (req.getSession().getAttribute("CommentPostingId") != null) {
            String commentPostingId = (String)req.getSession().getAttribute("CommentPostingId");
            Long idCommentPosting = new Long(commentPostingId);
            Posting posting = postingService.getPosting(idCommentPosting);
            req.setAttribute("commentPostingString", posting.getPostingName());
            req.setAttribute("commentPostingId", commentPostingId);
          } else {
            ArrayList<PostingOption> postingOptions = postingString.loadPostingOptions(new Long(-1));
            if(postingOptions.size() == 0) {
                req.setAttribute("msg", "Posting records must be entered first.");
                doCommentList(req, resp);
                return;
            }
            req.setAttribute("postingOptions", postingOptions);
          }
        }
        
        req.setAttribute("opBack", "CommentList");

        if (postingId != null)
            req.setAttribute("opBack", "PostingView");
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "PostingDataHelper.java"
newData.extMarker =
"""
        if (req.getParameter("memberId") != null)
            memberId = Long.parseLong(req.getParameter("memberId"));
        else
            memberId = Long.parseLong(req.getParameter("postingMemberId"));
"""
newData.srcInsert =
"""
        if (req.getParameter("memberId") != null) {
            memberId = Long.parseLong(req.getParameter("memberId"));
            req.getSession().setAttribute("MemberIdPick", req.getParameter("memberId"));
        } else {
            memberId = Long.parseLong(req.getParameter("postingMemberId"));
        }
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "CommentDataHelper.java"
newData.extMarker =
"""
        if (req.getParameter("memberId") != null)
            memberId = Long.parseLong(req.getParameter("memberId"));
        else
            memberId = Long.parseLong(req.getParameter("commentMemberId"));
"""
newData.srcInsert =
"""
        if (req.getParameter("memberId") != null) {
            memberId = Long.parseLong(req.getParameter("memberId"));
            req.getSession().setAttribute("MemberIdPick", req.getParameter("memberId"));
        } else {
            memberId = Long.parseLong(req.getParameter("commentMemberId"));
        }
"""
changes.add(newData)

// ******** Done ********



// ******** Changes to Comment java ********

newData = new MyData()
newData.theFile = "CommentServlet.java"
newData.extMarker =
"""
    @Inject
    private PostingString postingString;
"""
newData.srcInsert =
"""
    @Inject
    private PostingString postingString;

    @Inject
    PostingServlet postingServlet;
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentServlet.java"
newData.extMarker =
"""

        doCommentList(req, resp);
"""
newData.srcInsert =
"""
            if (req.getSession().getAttribute("PostingId") != null) {
                postingServlet.doPostingView(req, resp);
                return;
            }

            doCommentList(req, resp);
"""
changes.add(newData)

// ******** Done ********



// ******** Changes to remove posting option list from comments - list will become too large ********


newData = new MyData()
newData.theFile = "CommentServlet.java"
newData.extMarker =
"""
        ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(comment.getMember().getId());
        req.setAttribute("memberOptions", memberOptions);
        ArrayList<PostingOption> postingOptions = postingString.loadPostingOptions(comment.getPosting().getId());
        req.setAttribute("postingOptions", postingOptions);
"""
newData.srcInsert =
"""
        ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(comment.getMember().getId());
        req.setAttribute("memberOptions", memberOptions);
        req.setAttribute("postingId", comment.getPosting().getId().toString());
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentServlet.java"
newData.extMarker =
"""
        ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(new Long(-2));
        req.setAttribute("memberOptions", memberOptions);
        ArrayList<PostingOption> postingOptions = postingString.loadPostingOptions(new Long(-2));
        req.setAttribute("postingOptions", postingOptions);
"""
newData.srcInsert =
"""
        ArrayList<MemberOption> memberOptions = memberString.loadMemberOptions(new Long(-2));
        req.setAttribute("memberOptions", memberOptions);
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentDataHelper.java"
newData.extMarker =
"""
        String postingIdStr = req.getParameter("postingId");
        if (!postingIdStr.equals("-2")) {
            long postingId = Long.parseLong(postingIdStr);
            Posting posting = postingService.getPosting(postingId);
            comment.setPosting(posting);
        }
        else
            comment.setPosting(null);
"""
newData.srcInsert =
"""
        comment.setPosting(null);
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentSearch.jsp"
newData.extMarker =
"""
              <td style="text-align: right;"><label for="posting">Posting:</label></td>
              <td>
                <select name="postingId">
                <c:forEach items="\${postingOptions}" var="item">
                <option value='<c:out value="\${item.id}"/>' <c:out value="\${item.selected}"/>><c:out value="\${item.postingOption}"/></option>
                </c:forEach>
                </select>
              </td>
              <c:if test="\${!empty postingError}">
                <brk/><label style="color: red; width: 100%;text-align: left;">\${postingError}</label>
              </c:if>
              </td>
"""
newData.srcInsert =
"""
            </tr>
"""
changes.add(newData)


// ******** Done ********



// ******** Changes to Comment jsp remove add button ********

newData = new MyData()
newData.theFile = "CommentList.jsp"
newData.extMarker =
"""
<a href='<c:out value="\${addURL}" />'>
  <button class='btnAdd'>Add</button>
</a>

&nbsp;&nbsp;
"""
newData.srcInsert =
"""
&nbsp;&nbsp;
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentParentList.jsp"
newData.extMarker =
"""
&nbsp;&nbsp;

<a href='<c:out value="\${addURL}" />'>
  <button class='btnAdd'>Add</button>
</a>
"""
newData.srcInsert =
"""
&nbsp;&nbsp;
"""
changes.add(newData)

// ******** Done ********



newData = new MyData()
newData.theFile = "MemberList.jsp"
newData.extMarker =
"""
      <a href='<c:out value="\${viewURL}" />'>
        <img src="<%= httpRoot %>/thumbnail/Member/Picture/\${item.cloudId}-\${item.pictureVersion}.jpg" alt="Picture">
      </a>
"""
newData.srcInsert =
"""
      <a href='<c:out value="\${postingMemberURL}" />'>
        <img src="<%= httpRoot %>/thumbnail/Member/Picture/\${item.cloudId}-\${item.pictureVersion}.jpg" alt="Picture">
      </a>
"""
changes.add(newData)


// ******** Done ********




// ******** Changes to format list pages ********


newData = new MyData()
newData.theFile = "MemberList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Picture</th>
  <th valign="top" width="10%">Name</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Picture</th>
  <th valign="top" width="60%">Name</th>
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "MemberSearchList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Picture</th>
  <th valign="top" width="10%">Name</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Picture</th>
  <th valign="top" width="60%">Name</th>
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "MemberParentList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Picture</th>
  <th valign="top" width="10%">Name</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Picture</th>
  <th valign="top" width="60%">Name</th>
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "PostingList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Posting</th>
  <th valign="top" width="10%">Posting Info</th>
  <th valign="top" width="10%">Posting Date</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Posting</th>
  <th valign="top" width="50%">Posting Info</th>
  <th valign="top" width="10%">Posting Date</th>
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "PostingSearchList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Posting</th>
  <th valign="top" width="10%">Posting Info</th>
  <th valign="top" width="10%">Posting Date</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Posting</th>
  <th valign="top" width="50%">Posting Info</th>
  <th valign="top" width="10%">Posting Date</th>
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "PostingParentList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Posting</th>
  <th valign="top" width="10%">Posting Info</th>
  <th valign="top" width="10%">Posting Date</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Posting</th>
  <th valign="top" width="50%">Posting Info</th>
  <th valign="top" width="10%">Posting Date</th>
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Posting Comment</th>
  <th valign="top" width="10%">Comment Date</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Posting</th>
  <th valign="top" width="50%">Posting Comment</th>
  <th valign="top" width="10%">Comment Date</th>
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "CommentSearchList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Posting Comment</th>
  <th valign="top" width="10%">Comment Date</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Posting</th>
  <th valign="top" width="50%">Posting Comment</th>
  <th valign="top" width="10%">Comment Date</th>
"""
changes.add(newData)

newData = new MyData()
newData.theFile = "CommentParentList.jsp"
newData.extMarker =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="10%">Posting Comment</th>
  <th valign="top" width="10%">Comment Date</th>
"""
newData.srcInsert =
"""
  <th valign="top" width="10%">Action</th>
  <th valign="top" width="30%">Posting</th>
  <th valign="top" width="50%">Posting Comment</th>
  <th valign="top" width="10%">Comment Date</th>
"""
changes.add(newData)



// ******** Done ********




// ******** Changes to add posting link to comment lists ********

newData = new MyData()
newData.theFile = "CommentList.jsp"
newData.extMarker =
"""
    <td> <strong style="font-size: 15px;"> \${item.postingComment} </strong> </td>
"""
newData.srcInsert =
"""
    <c:url value="do" var="viewURL">
       <c:param name="op"   value="PostingView" />
       <c:param name="id"   value="\${item.posting.id}" />
    </c:url>
    <td><a href='<c:out value="\${viewURL}" />'>
      <c:if test="\${(item.posting.postingType eq 'pic')}">
        <img src="<%= httpRoot %>/thumbnail/Posting/Posting/\${item.posting.cloudId}PicThumb\${item.posting.postingVersion}.jpg" alt="Posting" style="height:150px;">
      </c:if>
      <c:if test="\${(item.posting.postingType eq 'vid')}">
        <img border="0" src="resources/gfx/vidIcon.jpg" alt="video" style="height:150px;"/><img border="0" src="<%= httpRoot %>/thumbnail/Posting/Posting/\${item.posting.cloudId}VidThumb\${item.posting.postingVersion}.jpg" alt="Posting" style="height:150px;"><img border="0" src="resources/gfx/vidIcon.jpg" alt="video" style="height:150px;"/>
      </c:if>
    </a></td>
    <td> <strong style="font-size: 15px;"> \${item.postingComment} </strong> </td>
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentSearchList.jsp"
newData.extMarker =
"""
    <td> <strong style="font-size: 15px;"> \${item.postingComment} </strong> </td>
"""
newData.srcInsert =
"""
    <c:url value="do" var="viewURL">
       <c:param name="op"   value="PostingView" />
       <c:param name="id"   value="\${item.posting.id}" />
    </c:url>
    <td><a href='<c:out value="\${viewURL}" />'>
      <c:if test="\${(item.posting.postingType eq 'pic')}">
        <img src="<%= httpRoot %>/thumbnail/Posting/Posting/\${item.posting.cloudId}PicThumb\${item.posting.postingVersion}.jpg" alt="Posting" style="height:150px;">
      </c:if>
      <c:if test="\${(item.posting.postingType eq 'vid')}">
        <img border="0" src="resources/gfx/vidIcon.jpg" alt="video" style="height:150px;"/><img border="0" src="<%= httpRoot %>/thumbnail/Posting/Posting/\${item.posting.cloudId}VidThumb\${item.posting.postingVersion}.jpg" alt="Posting" style="height:150px;"><img border="0" src="resources/gfx/vidIcon.jpg" alt="video" style="height:150px;"/>
      </c:if>
    </a></td>
    <td> <strong style="font-size: 15px;"> \${item.postingComment} </strong> </td>
"""
changes.add(newData)


newData = new MyData()
newData.theFile = "CommentParentList.jsp"
newData.extMarker =
"""
    <td> <strong style="font-size: 15px;"> \${item.postingComment} </strong> </td>
"""
newData.srcInsert =
"""
    <td> <img src="<%= httpRoot %>/thumbnail/Posting/Posting/\${item.posting.cloudId}PicThumb\${item.posting.postingVersion}.jpg" alt="Posting"> </td>
    <td> <strong style="font-size: 15px;"> \${item.postingComment} </strong> </td>
"""
changes.add(newData)


// ******** Done ********




// ******** Changes to add member picture and posting info ********


newData = new MyData()
newData.theFile = "PostingView.jsp"
newData.extMarker =
"""
        </table> <%-- end table --%>
"""
newData.srcInsert =
"""
            <tr>
              <td style="text-align: right;"><label for="postingInfoLat">Posting Info:</label></td>
              <td colspan=5 > <strong style="font-size: 17px;">  \${posting.postingInfo} </strong> </td>
            </tr>

        </table> <%-- end table --%>

        <img src="<%= httpRoot %>/thumbnail/Member/Picture/\${posting.member.cloudId}-\${posting.member.pictureVersion}.jpg" alt="Member">
"""
changes.add(newData)


// ******** Done ********
// ******** Done ********


def dir = "../social"

def extFiles ( theDir, changes ) {

   def fileList = new File(theDir).list().toList()

   for ( i in fileList ) {

      def inFile = theDir + "/" + i
      def f1= new File(inFile)

      MyData myData = new MyData();

      if ( f1.isDirectory() ) {
         extFiles ( inFile, changes )
      } else {
//println(i)
        for (c in changes) {
          MyData theData = c
          if ( i.equals(theData.theFile) ) {
            def oldFile = new File(inFile).text
            def newMarker = theData.extMarker.replaceAll( "\\\n", "\\\r\\\n" )
            def newSrc = theData.srcInsert.replaceAll( "\\\n", "\\\r\\\n" )
            def newFile = oldFile.replace(newMarker, newSrc)
            new File(inFile).write(newFile)
            if (newFile.contains(newSrc) == false) { println(theData.theFile + " missing changes") }
          }

        }

      } 
   }
}

extFiles ( dir, changes )



//******** Add files ******** 
//
def PostingCommentList = new File("./PostingCommentList.jsp").text
new File("../social/social-war/src/main/webapp/list/PostingCommentList.jsp").write(PostingCommentList)

def CommentAdd = new File("./CommentAdd.jsp").text
new File("../social/social-war/src/main/webapp/add/CommentAdd.jsp").write(CommentAdd)

def CommentEdit = new File("./CommentEdit.jsp").text
new File("../social/social-war/src/main/webapp/edit/CommentEdit.jsp").write(CommentEdit)

def PostingAdd = new File("./PostingAdd.jsp").text
new File("../social/social-war/src/main/webapp/add/PostingAdd.jsp").write(PostingAdd)

def PostingEdit = new File("./PostingEdit.jsp").text
new File("../social/social-war/src/main/webapp/edit/PostingEdit.jsp").write(PostingEdit)

def src = new File("../social_logo.png").newDataInputStream()
def dst = new File("../social/social-war/src/main/webapp/resources/gfx/logo.png").newDataOutputStream()
dst << src

