<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="home" required="false" %>
<%@attribute name="contas" required="false" %>
<%@attribute name="extrato" required="false" %>
<%@attribute name="usuarios" required="false" %>
<%@attribute name="roles" required="false" %>
<%-- <%@attribute name="graficos" required="false" %> --%>
<%@attribute name="cartoes" required="false" %>
<%-- <%@attribute name="calendario" required="false" %> --%>
	<div class="wrapper">

	    <div class="sidebar" data-color=red data-image="/resources/img/sidebar-1.jpg">
			<!--
		        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

		        Tip 2: you can also add an image using data-image tag
		    -->

			<div class="logo">
				<a href="" class="simple-text">
					<fmt:message key="home.header.logo" />
					
				</a>
			</div>

	    	<div class="sidebar-wrapper">
	            <ul class="nav">
	                 <li class="${home}">
	                    <a href="/">
	                        <i class="material-icons">dashboard</i>
	                        <p>
	                        <fmt:message key="home.header.resumo" />
	                        
	                        </p>
	                    </a>
	                </li>
					 <li class="${contas}">
	                    <a href="/conta/mostrar/">
	                        <i class="material-icons">account_balance</i>
	                        <p>
	                        <fmt:message key="home.header.contas" />
	                        </p>
	                    </a>
	                </li>
	                <li class="${extrato}">
	                    <a href="/extrato/mostrar">
	                        <i class="material-icons">receipt</i>
	                        <p>
	                        <fmt:message key="home.header.extrato" />
	                        </p>
	                    </a>
	                </li>
<%-- 	                 <li class="${graficos}"> --%>
<!-- 	                    <a href="#"> -->
<!-- 	                        <i class="material-icons">pie_chart</i> -->
<!-- 	                        <p>Gráficos</p> -->
<!-- 	                    </a> -->
<!-- 	                </li> -->
	                 <li class="${cartoes}">
	                    <a href="/cartao-credito/mostrar/">
	                        <i class="material-icons text-gray">payment</i>
	                        <p>
	                        <fmt:message key="home.header.ccredito" />
	                        </p>
	                    </a>
	                </li>
<%-- 					 <li class="${calendario}"> --%>
<!-- 	                    <a href=""> -->
<!-- 	                        <i class="material-icons">event</i> -->
<!-- 	                        <p>Calendário</p> -->
<!-- 	                    </a> -->
<!-- 	                </li> -->


					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="${roles}">
	                    <a href="/role/listar">
	                    		<i class="glyphicon glyphicon-certificate"></i>
	                        <p>
	                        <fmt:message key="home.header.roles" /> 	
	                        </p>
	                    </a>
	                </li>
					<li class="${usuarios}">
	                    <a href="/usuarios/lista">
	                    		 <i class="glyphicon glyphicon-user"></i>
	                        <p>
	                        <fmt:message key="home.header.usuarios" /> 	
	                        </p>
	                    </a>
	                </li>
					
					
					
					</sec:authorize>



					
				<sec:authorize access="isAnonymous()">
					<li class="logout">
	                    <a href=/login>
	                    		<i class="glyphicon glyphicon-log-in"></i>
	                        <p> 	
	                        <fmt:message key="home.header.login" />
	                        </p>
	                    </a>
	                </li>
	            </sec:authorize>
					 <sec:authorize access="isAuthenticated()">
					 <li class="logout">
	                    <a href="/logout">
	                    		<i class="glyphicon glyphicon-log-out"></i>
	                        <p>
	                        <fmt:message key="home.header.logout" /> 	
	                        </p>
	                    </a>
	                </li>
	                </sec:authorize>
	            </ul>
	    	</div>
	    </div>

	    <div class="main-panel">
			<nav class="navbar navbar-transparent navbar-absolute">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"></a>
					</div>
					<div class="collapse navbar-collapse">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
								<a href="/login" class="dropdown-toggle" data-toggle="dropdown">
	 							   <i class="glyphicon glyphicon-user"></i>
	 							   <p class="hidden-lg hidden-md">
	 							   	<fmt:message key="home.header.usuario" />
	 							   </p>
		 						</a>
		 						<ul class="dropdown-menu">
									<sec:authorize access="isAnonymous()">
										<li><a href="/login">
										<fmt:message key="home.header.login" />
										</a></li>
										<li>
										<a href="${spring:mvcUrl('UC#form').build() }">
											<fmt:message key="home.header.cadastrar" />
										</a>
										</li>
									</sec:authorize>
									<sec:authorize access="hasRole('ADMIN')"> 
										<li>
										<a href="${spring:mvcUrl('UC#formAdmin').build() }">
											<fmt:message key="home.header.cadastrar" />
										</a>
										</li>
									
									</sec:authorize>
									<sec:authorize access="hasRole('ADMIN')"> 
										<li>
										<a href="${spring:mvcUrl('UC#lista').build() }">
											<fmt:message key="home.header.listar" />
										</a>
										</li>
									
									</sec:authorize>
									<sec:authorize access="isAuthenticated()">
									 <sec:authentication property="principal" var="usuario"/>
										<li><a href="${spring:mvcUrl('UC#showUsuario').build() }">
										<fmt:message key="home.header.dados" />
										</a></li>
										<li><a href="/logout">
										 <fmt:message key="home.header.logout" /> 
										</a></li>
									</sec:authorize>
								</ul>
							</li>
						</ul>

					</div>
				</div>
			</nav>