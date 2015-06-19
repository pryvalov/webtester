<%-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <div class="frontpage-block">  -->
<c:if test="${account==null}">
	<div id="divlogin">




		<form:form modelAttribute="loginForm">
			<table id="logtable">
				<thead>
					<tr>
						<th colspan="2">Login Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><label for="email">Email: </label></td>
						<td><form:input path="email" id="email" /></td>
					</tr>
					<tr>
						<td><label for="pass">Password: </label></td>
						<td><form:password path="password" id="pass" /></td>
					</tr>
					<tr>
						<td>Role</td>
						<td><form:select path="role" id="roleSelect" cssStyle="color: #321;">
								<form:option value="">Select role </form:option>
								<c:forEach items="${roleList}" var="roleVal">
									<form:option value="${roleVal}">${roleVal}</form:option>
								</c:forEach>
							
							</form:select></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="common-button" value="Login" /></td>

					</tr>
					<tr>
						<td>Join us</td>
						<td><a href="signup" style="text-decoration: underline;">Sign up</a></td>
					</tr>
					<tr>
						<td colspan="2">Forgot password? <a href="recovery" style="text-decoration: underline;">Recover.</a></td>
					</tr>
				</tbody>
			</table>

		</form:form>
	</div>
</c:if>
<div id="textbody" class="inline-class">
	<div class="article">
		<div class="article-header">
			Pushing the footer down
			<div class="article-date">12/06/2015</div>
		</div>
		<p>In case you didn't see the graphic above, or didn't understand,
			here it is in a nutshell: When a page contains a large amount of
			content, the footer is pushed down off the viewport, and if you
			scroll down, the page 'ends' at the footer. However, if the page has
			small amount of content, the footer can sometimes 'cling' to the
			bottom of the content, floating halfway down the page, and leaving a
			blank space underneath. Depending on the design, this can look a
			little amateur, and it's especially bad on large screens. I've seen
			many failed attempts to fix this with absolute positioning, where the
			footer is forcibly positioned at the bottom of the screen - which
			usually ends up in it either overlapping (hiding) the bottom of the
			page content, or staying in the same position when the viewport is
			scrolled (eg. flying up the page when you scroll down) or not being
			pushed down far enough, or floating at the top of the page. I've even
			seen people 'fix' it with Javascript, finding out the height of the
			page and pushing the footer down after page load. 8-O Bad times.</p>
	</div>
	<div class="article">
		<div class="article-header">
			To 'fix' it with Javascript
			<div class="article-date">9/06/2015</div>
		</div>
		<p>In case you didn't see the graphic above, or didn't understand,
			here it is in a nutshell: When a page contains a large amount of
			content, the footer is pushed down off the viewport, and if you
			scroll down, the page 'ends' at the footer. However, if the page has
			small amount of content, the footer can sometimes 'cling' to the
			bottom of the content, floating halfway down the page, and leaving a
			blank space underneath. Depending on the design, this can look a
			little amateur, and it's especially bad on large screens. I've seen
			many failed attempts to fix this with absolute positioning, where the
			footer is forcibly positioned at the bottom of the screen - which
			usually ends up in it either overlapping (hiding) the bottom of the
			page content, or staying in the same position when the viewport is
			scrolled (eg. flying up the page when you scroll down) or not being
			pushed down far enough, or floating at the top of the page. I've even
			seen people 'fix' it with Javascript, finding out the height of the
			page and pushing the footer down after page load. 8-O Bad times.</p>
	</div>

</div>

<%-- <jsp:include page="templates/footer.jsp" /> --%>
