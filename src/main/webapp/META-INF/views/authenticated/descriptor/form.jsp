<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="authenticated.descriptor.form.label.description" path="description"/>

	<acme:form-return code="authenticated.descriptor.form.button.return"/>
	<acme:form-submit code="authenticated.descriptor.form.button.duties" action="/authenticated/duty/list?id=${id}" method="get" />
</acme:form>