<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.duty.form.label.title" path="title"/>
	<acme:form-textarea code="employer.duty.form.label.description" path="description"/>
	<acme:form-textbox code="employer.duty.form.label.timePercentage" path="timePercentage"/>

	<acme:form-return code="employer.duty.form.button.return"/>
	<acme:form-submit test="${command == 'show'}" code="employer.duty.form.button.update" action="/employer/duty/update" />
	<acme:form-submit test="${command == 'show'}" code="employer.duty.form.button.delete" action="/employer/duty/delete" />
	<acme:form-submit test="${command == 'create'}" code="employer.duty.form.button.create" action="/employer/duty/create?id=${descId}" />
	<acme:form-submit test="${command == 'update'}" code="employer.duty.form.button.update" action="/employer/duty/update" />
	<acme:form-submit test="${command == 'delete'}" code="employer.duty.form.button.delete" action="/employer/duty/delete" />
	
</acme:form>