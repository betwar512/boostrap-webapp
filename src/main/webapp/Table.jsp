<%@page import="utils.CustomItem"%>
<%@page import="utils.GroupTotal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
       xmlns:c="http://java.sun.com/jsp/jstl/core"
       >
        <ui:composition template="/templates/default/main.xhtml">
      <ui:component>
 <% ArrayList<CustomItem> items =(ArrayList<CustomItem>)session.getAttribute("items");
 
%> 
 <% ArrayList<GroupTotal> cards =(ArrayList<GroupTotal>)session.getAttribute("cards");
%>

 
<div class="row">
<p>this is a table jps</p>

								<table class="table table-default">
										<tr>
									 <th>merchantId</th>
									<th>terminalId</th>
									<th>date</th>
									<th>cardNumber</th>
									<th>cardType</th>
									<th>status</th>
									<th>purchaseAmount</th>
									<th>Total</th>

										</tr>


<c:forEach items="${items}" var="item">
    <tr>
        <td> <c:out value="${item.merchantId}"/></td>
   	    <td> <c:out value="${item.terminalId}"/></td>
   	  	<td><c:out value="${item.dateTime}"/></td>
   	    <td> <c:out value="${item.cardNumber}"/></td>
   	     <td><c:out value="${item.cardType}"/></td>
   	    <td><c:out value="${item.status}"/></td>
   	    <td> <c:out value="${item.purchaseAmount}"/></td>
   	    <td> $<c:out value="${item.totalAmount}"/></td>
    </tr>
</c:forEach>

</table>
</div>
<div class="row">
<h3 class="col-md-6 col-md-offset-6">Total amount for this log :$<c:out value="${total}"></c:out></h3>
</div>
<div class="row">

<c:forEach items ="${cards}" var="gp">
    
   	
 <p> for card type  <c:out value="${gp.key}"/> total is :<c:out value="${gp.total}"/></p>
   	   
</c:forEach>



</div>
<!-- Latest compiled and minified JavaScript -->
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
  </ui:component>

  </ui:composition> 
</html>

