<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-textarea code="employer.job.form.label.reference" path="reference" readonly="true" />
	</jstl:if>

	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" />

	<jstl:if test="${command != 'create'}">
		<acme:form-select code="employer.job.form.label.status" path="status">
			<acme:form-option code="employer.job.form.label.status.draft" value="Draft" />
			<acme:form-option code="employer.job.form.label.status.published" value="Published" />
		</acme:form-select>
	</jstl:if>

	<acme:form-return code="employer.job.form.button.return" />
	<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.update" action="/employer/job/update" />
	<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.delete" action="/employer/job/delete" />
	<acme:form-submit test="${command == 'create'}" code="employer.job.form.button.create" action="/employer/job/create" />
	<acme:form-submit test="${command == 'update'}" code="employer.job.form.button.update" action="/employer/job/update" />
	<acme:form-submit test="${command == 'delete'}" code="employer.job.form.button.delete" action="/employer/job/delete" />

	<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.descriptor" action="/employer/descriptor/show?id=${descId}" method="get" />
	<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.audits" action="/authenticated/audit-record/list?id=${id}" method="get" />
</acme:form>