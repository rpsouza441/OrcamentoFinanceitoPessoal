<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:message key="home.title" var="title" />
<customTags:page title="${title}" home="active">

 <jsp:attribute name="extraScripts">

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
    
    <script>
    

    $(document).ready(function () {

        // Build the chart
        Highcharts.chart('container', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '<fmt:message key="home.pie.contas" />'
            },
            tooltip: {
//                 pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                name: 'Contas',
                colorByPoint: true,
                data: [{
               <c:forEach var="conta" items="${contas}" varStatus="loop"> 
                 	name: '${conta.nome}',
                     y: ${conta.saldo}
                 	<c:if test="${!loop.last}">
                }, {
                	</c:if>
                
              </c:forEach> 
               
                	
                }]
            }]
        });
    });
    </script>
    <script>
    Highcharts.chart('container2', {
        chart: {
            type: 'column'
        },
        title: {
            text: '<fmt:message key="home.chart.valorSeteDias" />'
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
//             	 'Jan',
//                  'Feb',
//                  'Mar',
//                  'Apr',
//                  'May',
                 
//                  'Jul',
                
                 
//                  'Dec'
            	
            	
           <c:forEach var="dias" items="${diasSemana}" varStatus="loop"> 
              	 '<fmt:message key="home.diaSemana.${dias}" />'
		            <c:if test="${!loop.last}">
		             , 
		            </c:if>
             
           </c:forEach> 
           
           
           ],
           crosshair: true
       },
       yAxis: {
           min: 0,
           title: {
               text: '<fmt:message key="home.chart.cifrao" />'
           }
       },
       tooltip: {
           headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
           pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
               '<td style="padding:0"><b><fmt:message key="home.chart.cifrao" /> {point.y:.1f} </b></td></tr>',
           footerFormat: '</table>',
           shared: true,
           useHTML: true
       },
       plotOptions: {
           column: {
               pointPadding: 0.2,
               borderWidth: 0
           }
       },
       series: [{
            name: '<fmt:message key="home.receita" />',
            data: [

            	<c:forEach var="saldo" items="${saldosReceita}" varStatus="loop"> 
              	${saldo}
	           <c:if test="${!loop.last}">
	           			, 
	           </c:if>
          </c:forEach> 
          
            	
         ]
            }, {
            name: '<fmt:message key="home.despesa" />',
            data: [
            	
           <c:forEach var="saldo" items="${saldosDespesa}" varStatus="loop"> 
               	${saldo}
 	           <c:if test="${!loop.last}">
 	           			, 
 	           </c:if>
           </c:forEach> 
           	
      ]
            }]
    });
    </script>

</jsp:attribute>
    <jsp:body>
	<div class="row">
		<div class="col-lg-4 col-md-6 col-sm-6">
			<div class="card card-stats">
				<div class="card-header" data-background-color="orange">
				 <i	class="glyphicon glyphicon-calendar"></i> 
				</div>
				<div class="card-content">
<%-- 					<p class="category"><fmt:message key="home.resumo" /></p> --%>
					<h3 class="title">
						<fmt:message key="home.resumo" />
					</h3>
				</div>
				
					<div class="card-content">
							
							<div class="card-content table-responsive">
							
							
							
	                                <table class="table">
									    <tbody>
									        <tr>
									        </tr>
									        <tr>
									          <th>Total de Receita</th>
									            <td>${totalReceita }</td>
									        </tr>
									        <tr>
									          <th>Total de Receita</th>
									            <td>${totalDespesa }</td>
									        </tr>
									    </tbody>
									</table>
	                            </div>
							
							<a href="/despesa/cadastro" class="btn btn btn-danger">
		                    <i	class="glyphicon glyphicon-minus"></i>
		                    <fmt:message key="home.novaDespesa" /> 
							</a>
							<a href="/despesa/cadastroCredito" class="btn btn btn-danger">
		                    <i	class="glyphicon glyphicon-minus"></i>
		                    <fmt:message key="home.novaDespesaCC" /> 
							</a>
							<a href="/receita/cadastro" class="btn btn-success">
		                    <i	class="glyphicon glyphicon-plus"></i>
		                    <fmt:message key="home.novaReceita" /> 
							</a>
							
							
	                            
	                            

						</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-6 col-sm-6">
			<div class="card card-stats">
				<div class="card-header" data-background-color="green">
					<i	class="glyphicon glyphicon-piggy-bank"></i> 
					
					
				</div>
				<div class="card-content">
<%-- 					<p class="category"><fmt:message key="home.contas" /></p> --%>
					<h3 class="title"><fmt:message key="home.contas" /></h3>
				</div>
						<div class="card-content">
							<div id="container" ></div>
						</div>
				<div class="card-footer">
					<div class="stats">


					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-6 col-sm-6">
			<div class="card card-stats">
				<div class="card-header" data-background-color="red">
					<i	class="glyphicon glyphicon-check"></i> 
				</div>
				<div class="card-content">
<!-- 					<p class="category"></p> -->
					<h3 class="title"><fmt:message key="home.sete" /></h3>
				</div>
						<div class="card-content">
							<div id="container2" ></div>
						</div>
			</div>
		</div>

	</div>
	</jsp:body>
</customTags:page>
