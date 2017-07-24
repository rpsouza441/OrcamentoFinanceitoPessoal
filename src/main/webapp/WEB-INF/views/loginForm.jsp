<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<fmt:message key="home.title" var="title" />
<customTags:page title="${title}" home="active">

 <jsp:attribute name="extraScripts">


</jsp:attribute>
    <jsp:body>
  <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	    <div class="alert alert-danger ">
		<div class="container-fluid ">
		  <div class="alert-icon">
		    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  </div>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true"><i class="material-icons">clear</i></span>
		  </button>
		  <b>Erro de Login:</b> 
					 <br/><br/>
		        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
		 <c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />
		</div>
	</div>
</c:if>
    <div class="wrapper wrapper-full-page">
        <div class="full-page login-page" filter-color="black" >
            <!--   you can change the color of the filter page using: data-color="blue | purple | green | orange | red | rose " -->
            <div class="content">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-sm-6 col-md-offset-4 col-sm-offset-3">
                       <c:set var="root" value="${pageContext.request.contextPath}"/>
                        
                           <form:form servletRelativeAction="/login">

                                <div class="card card-login card-hidden">
                                    <div class="card-header text-center" data-background-color="red">
                                        <h4 class="card-title">Login</h4>
<!--                                         <div class="social-line"> -->
<!--                                             <a href="#btn" class="btn btn-just-icon btn-simple"> -->
<!--                                                 <i class="fa fa-facebook-square"></i> -->
<!--                                             </a> -->
<!--                                             <a href="#pablo" class="btn btn-just-icon btn-simple"> -->
<!--                                                 <i class="fa fa-twitter"></i> -->
<!--                                             </a> -->
<!--                                             <a href="#eugen" class="btn btn-just-icon btn-simple"> -->
<!--                                                 <i class="fa fa-google-plus"></i> -->
<!--                                             </a> -->
<!--                                         </div> -->
                                    </div>
<!--                                     <p class="category text-center"> -->
<!--                                         Or Be Classical -->
<!--                                     </p> -->
                                    <div class="card-content">
<!--                                         <div class="input-group"> -->
<%--                                             <span class="input-group-addon"> --%>
<!--                                              	<i class="glyphicon glyphicon-user"></i> -->
<%--                                             </span> --%>
<!--                                             <div class="form-group label-floating"> -->
<!--                                                 <label class="control-label">Nome</label> -->
<!--                                                 <input type="text" class="form-control"> -->
<!--                                             </div> -->
<!--                                         </div> -->
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                             <i class="glyphicon glyphicon-envelope"></i>
                                            </span>
                                            <div class="form-group label-floating">
                                                <label class="control-label">Email address</label>
                                                <input type='text' name='username' value='' class="form-control">
                                            </div>
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                 <i class="glyphicon glyphicon-lock"></i>
                                            </span>
                                            <div class="form-group label-floating">
                                                <label class="control-label">Password</label>
                                                <input type='password' name='password' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="footer text-center">
                                        <button type="submit"  class="btn btn-rose  btn-wd btn-lg">
                                       	<fmt:message key="login.ok" />
                                        </button>
                                        <br />
                                        <a href="${spring:mvcUrl('UC#form').build() }"
                                        class="btn btn-rose  btn-wd btn-sm">
											<fmt:message key="home.header.cadastrar" />
										</a> <br /> <br />
                                    </div>
                                </div>

                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	</jsp:body>
</customTags:page>
