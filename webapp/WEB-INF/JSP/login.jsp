<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="templates/header.jsp" />
<div class="frontpage-block"> 
	<div id="divlogin">
		<form method="post" action="login">

			<table id="logtable">
				<thead>
					<tr>
						<th colspan="2">Login Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value="" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td>Role</td>
						<td align="left"><input type="radio" name="role" value="1">
							Student<Br> <input type="radio" name="role" value="2">
							Tutor<Br> <input type="radio" name="role" value="3">
							Advanced tutor<Br> <input type="radio" name="role" value="0">
							Administrator</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" class="common-button" value="Login" /></td>

					</tr>
					<tr>
						<td>Join us</td> <td> <a href="signup" class="common-button" style="color: black;">Sign in</a></td>
					</tr>
					<tr>
						<td colspan="2">Forgot password? <a href="recovery">Recover.</a></td>
					</tr>
				</tbody>
			</table>

		</form>
	</div>
	<div id="textbody" class="inline-class">
		<div class="article">
		<div class="article-header">Pushing the footer down
		<div class="article-date">12/06/2015</div></div>
		<p>In case you didn’t see the graphic above, or didn’t understand,
			here it is in a nutshell: When a page contains a large amount of
			content, the footer is pushed down off the viewport, and if you
			scroll down, the page ‘ends’ at the footer. However, if the page has
			small amount of content, the footer can sometimes ‘cling’ to the
			bottom of the content, floating halfway down the page, and leaving a
			blank space underneath. Depending on the design, this can look a
			little amateur, and it’s especially bad on large screens. I’ve seen
			many failed attempts to fix this with absolute positioning, where the
			footer is forcibly positioned at the bottom of the screen – which
			usually ends up in it either overlapping (hiding) the bottom of the
			page content, or staying in the same position when the viewport is
			scrolled (eg. flying up the page when you scroll down) or not being
			pushed down far enough, or floating at the top of the page. I’ve even
			seen people ‘fix’ it with Javascript, finding out the height of the
			page and pushing the footer down after page load. 8-O Bad times.</p>
		</div>
		<div class="article">
		<div class="article-header">To ‘fix’ it with Javascript
		<div class="article-date">9/06/2015</div>
		</div>
		<p>In case you didn’t see the graphic above, or didn’t understand,
			here it is in a nutshell: When a page contains a large amount of
			content, the footer is pushed down off the viewport, and if you
			scroll down, the page ‘ends’ at the footer. However, if the page has
			small amount of content, the footer can sometimes ‘cling’ to the
			bottom of the content, floating halfway down the page, and leaving a
			blank space underneath. Depending on the design, this can look a
			little amateur, and it’s especially bad on large screens. I’ve seen
			many failed attempts to fix this with absolute positioning, where the
			footer is forcibly positioned at the bottom of the screen – which
			usually ends up in it either overlapping (hiding) the bottom of the
			page content, or staying in the same position when the viewport is
			scrolled (eg. flying up the page when you scroll down) or not being
			pushed down far enough, or floating at the top of the page. I’ve even
			seen people ‘fix’ it with Javascript, finding out the height of the
			page and pushing the footer down after page load. 8-O Bad times.</p>
		</div>

	</div>
</div>
<jsp:include page="templates/footer.jsp" />
