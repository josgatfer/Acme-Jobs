<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title" />
	<jstl:if test="${command != 'create' }">
	<acme:form-moment code="authenticated.thread.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textbox code="authenticated.thread.form.label.users" path="users" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command=='show' && usuariosNoAnyadidos.isEmpty()}">
	<acme:message code="authenticated.thread.form.label.messageAllAdded"/>
	</jstl:if>
	<jstl:if test="${command=='show' && !usuariosNoAnyadidos.isEmpty()}">
	<acme:form-select code="authenticated.thread.form.label.usersToAdd" path="usertoadd">
	<jstl:forEach var="user1" items="${usuariosNoAnyadidos}">
	<acme:form-option code="${user1.userAccount.username}" value = "${user1.id}"/>
	</jstl:forEach>
	</acme:form-select>
	<acme:form-submit code="authenticated.thread.form.button.addUser" action="/authenticated/thread/add-user"/>
	</jstl:if>
	<jstl:if test="${command=='show' && !usuariosAnyadidos.isEmpty()}">
	<acme:form-select code="authenticated.thread.form.label.usersToDelete" path="usertodelete">
	<jstl:forEach var="user2" items="${usuariosAnyadidos}">
	<acme:form-option code="${user2.userAccount.username}" value = "${user2.id}"/>
	</jstl:forEach>
	</acme:form-select>
	<acme:form-submit code="authenticated.thread.form.button.deleteUser" action="/authenticated/thread/delete-user"/>
	</jstl:if>
	
	
	<jstl:if test="${command =='create' }">
	<acme:form-submit code="authenticated.thread.form.button.create" action="/authenticated/thread/create"/>
	</jstl:if>
	
	
	<jstl:if test="${command !='create' }">
	<acme:form-submit action="/authenticated/messages/list?id=${id}" method="get" code="authenticated.thread.form.button.list-messages"></acme:form-submit>
	 <acme:form-submit action="/authenticated/messages/create?id=${id}"
		method="get" code="authenticated.thread.form.button.create-messages"></acme:form-submit>
  </jstl:if>
  
	<acme:form-return code="authenticated.thread.form.button.return" />
</acme:form>
