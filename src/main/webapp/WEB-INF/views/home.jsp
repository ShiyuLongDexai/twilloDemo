<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Please enter your phone number(xxx)-xxx-xxxx)
</h1>
<form action="/myapp">
  <input type="text" name="digits" placeholder="Phone number" ><br>
  <input type="text" name="sleep" placeholder="sleep time(second)"><br>
  <input type="submit" value="Submit">
</form>
<P>  The time on the server is ${serverTime}. </P>
<br>
<table width="614" border="1">
  <tr>
    <th width="325"scope="col">Call time</th>
    <th width="125" scope="col">To Number</th>
    <th width="186" scope="col">sleep time(s)</th>
    <th width="148" scope="col">input </th>
    <th width="188" scope="col">&nbsp;</th>
  </tr>
  <c:forEach items="${calls}" var="phoneCall">
  <tr>
  <form method="get" action="myapp/callAgain">
    <td>${phoneCall.calltime}</td>
    <td>${phoneCall.phonenumber}</td>
    <td>${phoneCall.sleepTime}</td>
    <td>${phoneCall.input}
    <input name='number' type='hidden' value='${phoneCall.phonenumber}'>
    <input name='input' type='hidden' value='${phoneCall.input}'></td>
    <td><center><button type="submit" class="btn btn-default">Call Again</button></center></td>
    </form>
  </tr>
  </c:forEach>
</table>

</body>
</html>
