<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Your Timetable</title>
<link rel="shortcut icon" href="photos/list.png" />
</head>
<body>
	<form action="search" method="post" class="">
		<input type="search" name="req" autofocus
			placeholder="Type here your group number or teacher`s surname"
			class="form-control">
	</form>
	<table class='table table-striped table-sm table-bordered'>
		<thead>
			<tr>
				<th>Group</th>
				<th>Hour</th>
				<th>Lesson</th>
				<th>Hall</th>
				<th>Teacher</th>
			</tr>
		</thead>
		<c:forEach var="i" items="${list}">
			<tr>

				<td><center>${i.groupp_number}</center></td>
				<td><center>${i.evenity}</center>
					<center>${i.day}</center>
					<center>${i.time}</center></td>
				<td><center>${i.type_name}</center>
					<center>${i.subject_name}</center></td>
				<td><center>${i.housing}</center>
					<center>${i.hall_number}</center></td>
				<td><img src="${i.photo}">
					<center>${i.teacher_name}${i.patronymic} ${i.surname}</center>
					<center>${i.degree},</center>
					<center>experience of ${i.experience} years</center></td>
			</tr>
		</c:forEach>
	</table>
	<footer>
	<center>Thanks for using my app</center><center> Created: 19.11.2017 by Jatsko Polina</center>
	</footer>
</body>
</html>