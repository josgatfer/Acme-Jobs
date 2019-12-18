<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="authenticated.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="authenticated.job.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="authenticated.job.form.label.salary" path="salary"/>
	<acme:form-money code="authenticated.job.form.label.moreInfo" path="moreInfo"/>

	<acme:form-return code="authenticated.job.form.button.return"/>
	<acme:form-submit code="authenticated.job.form.button.descriptor" action="/authenticated/descriptor/show?id=${id}" method="get" />
	<acme:form-submit code="authenticated.job.form.button.audits" action="/authenticated/audit-record/list?id=${id}" method="get" />
	
	<acme:form-submit code="authenticated.job.form.button.apply" test="${isWorker == true }" method="get" action="/worker/application/create?id=${id}"/>
</acme:form>