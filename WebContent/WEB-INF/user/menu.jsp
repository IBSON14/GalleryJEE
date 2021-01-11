<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gallery</title>
<link rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>">
<link rel="stylesheet" href="<c:url value='/css/fontawesome.css'/>">
<link rel="stylesheet" href="<c:url value='/css/fontawesome.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/all.css'/>">
<script src="https://kit.fontawesome.com/7f5918d569.js"
	crossorigin="anonymous"></script>

<script type="text/javascript"
	src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/bootstrap.min.js'/>"></script>
</head>
<body>
	<nav class="navbar fixed-top navbar-expand-sm navbar-dark bg-primary">
		<b> <a class="navbar-brand" href="<c:url value='/user'/>">G<i
				class="fa fa-picture-o" aria-hidden="true"></i>llery
		</a></b>
		<button class="navbar-toggler d-lg-none" type="button"
			data-toggle="collapse" data-target="#collapsibleNavId"
			aria-controls="collapsibleNavId" aria-expanded="false"
			aria-label="Toggle navigation" style="color: white;"></button>
		<div class="collapse navbar-collapse" id="collapsibleNavId">
			<ul class="navbar-nav ml-lg-auto">
				<c:if test="${sessionScope.sessionUtilisateur.role == 'admin'}">
					<li class="nav-item active"><a class="nav-link mx-2"
						href="<c:url value='/admin/users'/>" style="color: white;"><i
							class="fa fa-users" aria-hidden="true"></i> Utilisateurs</a></li>
					<li class="nav-item active"><a class="nav-link mx-2"
						href="<c:url value='/admin/albums'/>" style="color: white;"><i
							class="fa fa-folder" aria-hidden="true"></i> Albums</a></li>
				</c:if>
				<li class="nav-item active"><a class="nav-link mx-2"
					href="<c:url value='/user/albums'/>" style="color: white;"><i
						class="fa fa-folder-open" aria-hidden="true"></i> Mes Albums</a></li>
				<li class="nav-item"><a class="nav-link "
					href="<c:url value='/user/account?login=${sessionScope.sessionUtilisateur.login}'/>"
					style="color: white;"><i class="fa fa-user-circle"
						aria-hidden="true"></i> <c:if
							test="${!empty sessionScope.sessionUtilisateur}">${sessionScope.sessionUtilisateur.prenom}</c:if></a></li>
			</ul>
		</div>
	</nav>