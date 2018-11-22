<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Αυτοκίνητα - Εύρεση</title>
</head>
<body>
	<br />
	<br />
	<br />
	<%
		String reg = "";
		String factory = "";
		String model = "";
		String colour = "";
		String cc = "";
		String regdate = "";
		if (request.getParameter("factory") != null)
			factory = request.getParameter("factory");
		if (request.getParameter("registration") != null)
			reg = request.getParameter("registration");
		if (request.getParameter("model") != null)
			model = request.getParameter("model");
		if (request.getParameter("colour") != null)
			colour = request.getParameter("colour");
		if (request.getParameter("cc") != null)
			cc = request.getParameter("cc");
		if (request.getParameter("regdate") != null)
			regdate = request.getParameter("regdate");
	%>
	<div style="width: 400px; margin: auto">
		<form name="mainform" action="find" method="post">
			<table style="width: 400px" border="1">
				<tr>
					<td colspan="2" style="border: none;"><b>Εύρεση
							αυτοκινήτου</b></td>
				</tr>
				<tr>
					<td colspan="2" style="border: none;" height="10"></td>
				</tr>
				<tr>
					<td width="200">Αριθμός κυκλοφορίας:</td>
					<td width="200" align="center"><input type="text"
						name="registration" value="<%=reg%>" maxlength="8" /></td>
				</tr>
				<tr>
					<td width="200">Εργοστάσιο κατασκευής:</td>
					<td width="200" align="center"><input type="text"
						name="factory" value="<%=factory%>" /></td>
				</tr>
				<tr>
					<td width="200">Μοντέλο:</td>
					<td width="200" align="center"><input type="text" name="model"
						value="<%=model%>" /></td>
				</tr>
				<tr>
					<td width="200">Κυβικά:</td>
					<td width="200" align="center"><input type="text" name="cc"
						value="<%=cc%>" /></td>
				</tr>
				<tr>
					<td width="200">Έτος κατασκευής:</td>
					<td width="200" align="center"><input type="text"
						name="regdate" value="<%=regdate%>" /></td>
				</tr>
				<tr>
					<td colspan="2" height="10" style="border: none;"></td>
				</tr>
				<tr>
					<td width="200" style="border: none;" align="center"><input
						type="reset" value="Καθαρισμός"
						title="Κλικ για καθαρισμό της φόρμας" /></td>
					<td width="200" style="border: none;" align="center"><input
						type="submit" value="Εύρεση" title="Κλικ για αναζήτηση" /></td>
				</tr>
				<tr>
					<td colspan="2" height="15" style="border: none;"></td>
				</tr>
			</table>
		</form>
	</div>
	<br />
	<br />
	<%
		ResultSet cars = (ResultSet) request.getAttribute("cars");
		if (cars != null) {
			if (cars.next() == false) {
		%>
		<div style="width: 400px; text-align: center; margin: auto">
			<p>Δε βρέθηκαν εγγραφές για τα παραπάνω κριτήρια</p>
		</div>
		<% }
			else { %>
	<div style="width: 800px; margin: auto;">
		<table style="width: 800px" border="1">
			<tr>
				<td><b>Αρ. Κυκλοφορίας</b></td>
				<td><b>Εργ. Κατασκευής</b></td>
				<td><b>Μοντέλο</b></td>
				<td><b>Κυβικά</b></td>
				<td><b>Χρώμα</b></td>
				<td><b>Έτος Κατασκευής</b></td>
			</tr>
			<%
				cars.beforeFirst();
				while (cars.next()) {
			%>
				<tr>
					<td><%= cars.getString(1) %></td>
					<td><%= cars.getString(2) %></td>
					<td><%= cars.getString(3) %></td>
					<td><%= cars.getString(4) %></td>
					<td><%= cars.getInt(5) %></td>
					<td><%= cars.getInt(6) %></td>
				</tr>
			<%
				}
			} 
			%>
		</table>
		<%
			}
		%>
		
		<div style="width: 400px; margin: auto; text-align: right">
			<br /> <input type="button" onclick="window.location = 'index.htm'"
				value="Επιστροφή" title="Επιστροφή στην αρχική σελίδα" />
		</div>
	</div>
</body>
</html>