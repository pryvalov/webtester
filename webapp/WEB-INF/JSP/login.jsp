<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${account==null}">
	<div id="divlogin">

		<form method="POST" action="loginHandler">
			<table>
				<tr>
					<c:if
						test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null }">
						<td colspan="2" class="errors">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</td>
					</c:if>
				</tr>
				<tr>
					<td><label for="j_username">Login</label></td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td><label for="j_password">Password</label></td>
					<td><input type="password" name="j_password" /></td>
				</tr>
				<tr>
					<td><label for="idRole">Role</label></td>
					<td><select name="idRole" style="color: #111;">
							<!-- <option value="-1">Select role</option> -->
							<c:forEach var="role" items="${roles }">
								<option value="${role.roleId }">${role.roleName }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center; padding-top: 20px;">
						<input type="checkbox" value="true"
						name="_spring_security_remember_me">Stay signed
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="common-button" value="Login" />
					</td>
				</tr>
				<tr>
						<td>Join us</td>
						<td><a href="signup" style="text-decoration: underline;">Sign up</a></td>
					</tr>
					<tr>
						<td colspan="2">Forgot password? <a href="recovery" style="text-decoration: underline;">Recover.</a></td>
					</tr>
				<tr>
					<td colspan="2"><a href="fbLogin"> <img alt="fbLogin"
							src="${context}/resources/images/ssfb.png" />
					</a></td>
				</tr>
			</table>
		</form>
	</div>
</c:if>
<div id="textbody" class="inline-class"
	style="${account==null ? 'left: 350px;' : ''}">
	<div class="article">
		<div class="article-header">
			Platform Specific Instructions
			<div class="article-date">28/06/2015</div>
		</div>
		<p>Debian and Ubuntu Linux The standard Linux installation
			instructions provided above can be used without modification to
			successfully install Tomcat on Debian or Ubuntu Linux. However, a
			number of quirks will affect a manual Debian/Ubuntu installation.</p><p> For
			example, Tomcat's STDOUT and STDERR logs will be appended to the main
			syslog, instead of appearing in Tomcat's default log file,
			'catalina.out'. Also, as the Apache distributions of Tomcat include
			no Debian/Ubuntu-specific init script, a custom script must be
			written and installed if Tomcat needs to be run as a service. These
			are not big problems, but they can be a hassle.</p><p> Although it is
			normally a good idea to avoid re-packaged versions of Tomcat from
			repositories, the Debian / Ubuntu package (they share the same
			upstream) is maintained by MuleSoft's own Jason Brittain, author of
			O'Reilly's Definitive Guide To Apache Tomcat. </p><p>Thanks to Jason's work,
			most users should find that a simple 'apt get' command is now the
			easiest and best way to install Tomcat on the Debian and Ubuntu
			platforms. Jason's package includes a custom init script that makes
			Tomcat restarts more reliable, and some custom Tomcat configurations
			that help users to avoid the most common problems with new manual
			installations, such as disabling the SecurityManager by default and
			fixing port binding issues. If you'd like some more information about
			the package, Jason has written two in-depth blogs about his
			improvements, which you can read here and here on the MuleSoft blog.
			</p>
	</div>
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