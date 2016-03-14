<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ page import="javax.portlet.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.StringTokenizer" %>
<%@ page import="com.kisti.scienceappstore.model.CommonPackage" %>
<%@ page import="com.kisti.scienceappstore.model.impl.CommonPackageImpl" %>
<%@ page import="com.kisti.scienceappengine.Constants" %>
<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="jspPage" value="/html/scienceappengine/view.jsp" />
</portlet:renderURL>

<form action="pkg_reg_send" method="post">
  Install Method: <input type="text" name="inst_way"><br>
  Package name: <input type="text" name="pkg_name"><br>
  <input type="submit" value="Submit">
</form>

<%
	List<CommonPackage> cmList = (List<CommonPackage>)renderRequest.getAttribute("foundModList");
	//if(clListStr != null){
	//if(cmList != null){
%>
		<table>
			<tr>
				<th>Installed Package Name</th>
				<th>Installed Package Version</th>
			</tr>
		<%
			for(int i=0;i<cmList.size();i++){
				String pkgName = (cmList.get(i)).getPkgName();
				String pkgVer = (cmList.get(i)).getPkgVersion();
		%>
				<tr>
					<td><%= pkgName %></td>
					<td><%= pkgVer %></td>
				</tr>
			<%
			}
			%>
		</table>
	<%
	//}
	%>
<p><a href="<%= viewURL %>">Back</a></p>