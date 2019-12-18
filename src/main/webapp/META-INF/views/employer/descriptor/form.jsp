<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="employer.descriptor.form.label.description" path="description"/>

	<acme:form-return code="employer.descriptor.form.button.return"/>
	<acme:form-submit test="${command == 'show'}" code="employer.descriptor.form.button.update" action="/employer/descriptor/update" />
	<acme:form-submit code="employer.descriptor.form.button.dutiesList" action="/employer/duty/list?id=${id}" method="get" />
	<acme:form-submit code="employer.descriptor.form.button.dutiesCreate" action="/employer/duty/create?id=${id}" method="get" />
</acme:form>