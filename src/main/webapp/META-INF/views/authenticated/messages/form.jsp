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

<acme:form readonly="false">

	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="authenticated.messages.form.label.author"
			path="author" readonly="true" />
		<acme:form-moment code="authenticated.messages.form.label.moment"
			path="moment" readonly="true" />
	</jstl:if>
	<acme:form-textbox code="authenticated.messages.form.label.title"
		path="title" />
	<acme:form-textbox code="authenticated.messages.form.label.tags"
		path="tags" />
	<acme:form-textarea code="authenticated.messages.form.label.body"
		path="body" />

	<jstl:if test="${command == 'create' }">
		<acme:form-checkbox code="authenticated.messages.form.label.accept"
			path="accept" />
	</jstl:if>

	<acme:form-submit test="${command=='create'}"
		code="authenticated.messages.form.button.create"
		action="/authenticated/messages/create?id=${idThread}" />
	<acme:form-return code="authenticated.messages.form.button.return" />

</acme:form>
