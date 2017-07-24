<%@attribute name="title" required="true" %>
<%@attribute name="extraScripts" fragment="true" %>
<!doctype html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="pt"><![endif]-->
<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8" lang="pt"><![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9" lang="pt"><![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="pt">
<!--<![endif]-->

<head>
<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="/resources/img/apple-icon.png" />
	<link rel="icon" type="image/png" href="/resources/img/favicon.png" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>${title } - Orçamento Familiar</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <!-- Bootstrap core CSS     -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" />

    <!--  Material Dashboard CSS    -->
    <link href="/resources/css/material-dashboard.css" rel="stylesheet"/>

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons' rel='stylesheet' type='text/css'>
</head>
<body class="${bodyClass}">

	<%@include file="/WEB-INF/header.jsp" %>
	<div class="content">
				<div class="container-fluid">
					<div class="row">


	<jsp:doBody/>


					</div>
				</div>
			</div>
	<%@include file="/WEB-INF/footer.jsp" %>
	
</body>
</html>
 <jsp:invoke fragment="extraScripts"></jsp:invoke>
