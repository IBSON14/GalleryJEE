<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Se connecter</title>
<link rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>">

</head>
<body>
	<div class="container" style="margin-bottom: 100px; margin-top: 70px;">
		<div class="row justify-content-center">
			<div class="card col-lg-8">
				<div class="card-header">Se connecter</div>
				<form action="" method="post">
					<div class="card-body">
						<c:if test="${!empty requestScope.formError}">
							<div class="alert alert-danger">
							${requestScope.formError.message}</div>
						</c:if>
						<div class="form-group">
							<label for="">Login:</label> <input type="text"
								class="form-control" name="login" id=""
								placeholder="entrer votre login" required>
						</div>
						<div class="form-group">
							<label for="">Mot de Passe:</label> <input type="password"
								class="form-control" name="password" id=""
								placeholder="Entrer Votre Mot de Passe" required>
						</div>
					</div>
					<div class="card-footer text-muted">

						<input name="" id="" class="btn btn-secondary" type="button"
							value="annuler"> <input name="" id=""
							class="btn btn-primary" type="submit" value="se connecter">
					</div>
				</form>
			</div>

		</div>
	</div>
</body>
</html>