<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="customTags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>



<fmt:message key="receita.cadastro.title" var="title" />

<customTags:page title="${title}">


 <jsp:attribute name="extraScripts">
      


   

<script type="text/javascript">
	$(document).ready(function() {
		$('.nav-toggle').click(function(){
			//get collapse content selector
			var collapse_content_selector = $(this).attr('href');					
			
			//make the collapse content to be shown or hide
			var toggle_switch = $(this);
			$(collapse_content_selector).toggle(function(){
				if($(this).css('display')=='none'){
					toggle_switch.html('Show');//change the button label to be 'Show'
				}else{
					toggle_switch.html('Hide');//change the button label to be 'Hide'
				}
			});
		});
		
	});	
</script>
<script type="text/javascript">
$('.selectpicker').selectpicker({
	  style: 'btn-info',
	  size: 4
	});
</script>



<script>
    $(document).ready(function(){
      var date_input=$('input[name="datapagamento"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'dd/mm/yyyy',
        language: "pt-BR",
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
</script>



<!-- Bootstrap Date-Picker Plugin -->
<script src="/resources/js/bootstrap-datepicker.min.js"></script>
<script src="/resources/js/bootstrap-datepicker.pt-BR.min.js"></script>
<link href="/resources/css/bootstrap-datepicker3.css" rel="stylesheet" />




    </jsp:attribute>


    <jsp:body>
		<div class="col-md-8">
	                        <div class="card">
	                            <div class="card-header" data-background-color="blue">
	                                <h4 class="title">
										<fmt:message key="receita.cadastro.h4"/>
									</h4>
									<p class="category">
										<fmt:message key="receita.cadastro.descricao"/>
									</p>
	                            </div>
	                            <div class="card-content">
	                                <form:form action="${s:mvcUrl('RC#gravar').build() }" method="post" modelAttribute="receita" >
 		                                    <div class="row">
	                                        <div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="receita.cadastro.valor"/>
													</label>
													<form:input type="text"  path="valor" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="valor" /></p>
												</div>
	                                        </div>
	                                      
	                                        <div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">
														<fmt:message key="receita.cadastro.nome"/>
													</label>
													<form:input type="text"  path="nome" cssClass="form-control" />
       													<p class="text-danger"> <form:errors path="nome" />
       													</p>
												</div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating ">
													<label class="control-label">
														<fmt:message key="receita.cadastro.descricao"/>
													</label>
													<form:input type="text"  path="descricao" cssClass="form-control" />
       													<p class="text-danger">
       												 <form:errors path="descricao" />
       												 </p>
												</div>
	                                        </div>
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-4">
												<div class="form-group">
													<label class="control-label" for="datapagamento">
														<fmt:message key="receita.cadastro.datapagamento"/>
													</label>
													<form:input type="text" path="datapagamento"
														cssClass="form-control" id="date" name="date"
														placeholder="DD/MM/AAAA" />
       												 	<p class="text-danger">
       												 <form:errors path="datapagamento" />
												</p>
												</div>
	                                        </div>
	                                        
	                                        <div class="col-md-4">
	                                        <div class="form-group">
											<div class=togglebutton>
								<form:select path="subCategoriaReceita.id" class="selectpicker" data-width="100%">
<%-- 												<form:option value="1"><p>Selecione</p></form:option> --%>
												 <c:forEach var="theCategoria" items="${categorias}">
										  <div class="col-md-1">
												
                              				  <form:option value="${theCategoria.id}">
													<c:out value="${theCategoria.nome}" />
												</form:option>
												
										          
										  </div>
												</c:forEach>
                            		</form:select>
												<a href="/subCategoriaReceita/cadastroCategoriaReceita" class="btn btn-info btn-round">
				                    <i	class="glyphicon glyphicon-plus"></i>
				                    <fmt:message key="receita.nova.categoria" /> 
				                      <div class="ripple-container"></div></a>
											</div>
       													<p class="text-danger">
       												 <form:errors path="subCategoriaReceita" />
       												 </p>
										</div>
										</div>
	                                        <div class="col-md-4">
	                                        <div class="form-group">
											<div class=togglebutton>
								<form:select path="conta.id" class="selectpicker" data-width="100%">
												 <c:forEach var="theConta" items="${contas}">
										  <div class="col-md-1">
												
                              				  <form:option value="${theConta.id}">
													<c:out value="${theConta.nome}" />
												</form:option>
												
										          
										  </div>
												</c:forEach>
                            		</form:select>
                            		<a href="/conta/cadastro" class="btn btn-info btn-round">
				                    <i	class="glyphicon glyphicon-plus"></i>
				                    <fmt:message key="receita.nova.conta" /> 
				                      <div class="ripple-container"></div></a>
											</div>
												<p class="text-danger">
       												 <form:errors path="conta" />
       												 </p>
										</div>
										</div>
			                                    </div>

									
									
									<div class="row">
										<div class="col-md-12">
											<div class=togglebutton>
												<label> <form:label path="vaiRepetir" for="vaiRepetir1"
														cssClass="inline">
														<fmt:message key="receita.cadastro.vaiRepetir" />
													</form:label> <form:checkbox path="vaiRepetir" id="myvaiRepetir"
														style="display:none" href="#collapse1" class="nav-toggle" />
												</label>
											</div>
										</div>
				
				
				
									</div>
<c:if test="${receita.vaiRepetir eq true}">
<div id="collapse1" style="">
</c:if>
<c:if test="${receita.vaiRepetir ne true}">
<div id="collapse1" style="display: none">
</c:if>
						<div class="row">
							<div class="col-md-5">
								<div class="form-group label-floating">
									<label class="control-label"> <fmt:message
											key="receita.cadastro.quantidadePeriodo" />
									</label>
									<form:input type="text" path="periodo.quantidade"
										cssClass="form-control" />
									<p class="text-danger">
										<form:errors path="periodo.quantidade" />
									</p>
								</div>
							</div>
							<div class="col-md-7">
							
							
							<form:select path="periodo.periodicidade" class="selectpicker"  >
                                <form:option value="30"><fmt:message key="receita.cadastro.periodo.mensal" /></form:option>
                                <form:option value="1"><fmt:message key="receita.cadastro.periodo.diario" /></form:option>
                                <form:option value="7"><fmt:message key="receita.cadastro.periodo.semanal" /></form:option>
                                <form:option value="365"><fmt:message key="receita.cadastro.periodo.anual" /></form:option>
                            </form:select>
							
							</div>
						</div>
						
							</div>
						</div>
					</div>








<input type="hidden" name="id" value="${receita.id}" />
					<button type="submit" class="btn btn-info pull-right">
												<fmt:message key="receita.cadastro.salvar"/>
										</button>
	                                    <div class="clearfix"></div>
	                               </form:form>
	                            </div>
	                        </div>
	                    </div>

  </jsp:body>
</customTags:page>	

