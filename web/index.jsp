<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Stat page</title>
  <script>
    function checkData() {
      var MIN_NUM = 2;
      var MAX_NUM = 10;
      var err = document.getElementById("errorText");
      var statsNumber = new Number(document.statsNumber.number.value);
      if(isNaN(statsNumber) || !isInteger(statsNumber)) {
        err.innerHTML = "input right number";
        return false;
      }
      if(statsNumber < MIN_NUM || statsNumber > MAX_NUM) {
        err.innerHTML = "input number from " + MIN_NUM + " to " + MAX_NUM;
        return false;
      }
      return true;

      function isInteger(number) {
        return Math.floor(number) == number;
      }
    }

    function sendForm() {
      if(checkData()) {
        document.statsNumber.submit();
      }
    }
  </script>
</head>
<body>
<p id="errorText" style="color:red;"></p>
<p/>
<form name="statsNumber" action="<c:url value='/start'/>" onsubmit="return checkData()">
  stats number (from 2 to 10)
  <input name="number" type="text"/>
  <br/>
  <a href="JavaScript:sendForm()">generate stats</a>
</form>
</body>
</html>
