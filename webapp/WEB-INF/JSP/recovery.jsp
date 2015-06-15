<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    	<jsp:include page="templates/header.jsp" />
    	<div id="recovery">
        <form method="post" action="recovery">
       
            <table>
                <thead>
                    <tr>
                        <th colspan="2">Password recovery</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="" /></td>
                    </tr>    
                    <tr>
                    	<td></td>
                        <td><input type="submit" style="color: black;" value="Send email" /></td>
                        
                    </tr>
                    <tr>
                        <td colspan="2">Remembered password? <a href="login">Login</a></td>
                    </tr>
                </tbody>
            </table>
      
        </form>
        </div>
        <jsp:include page="templates/footer.jsp" />
