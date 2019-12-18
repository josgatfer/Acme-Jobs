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

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="auditor.job.form.label.reference" path="reference" />
	<acme:form-textbox code="auditor.job.form.label.title" path="title" />
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline" />
	<acme:form-textbox code="auditor.job.form.label.status" path="status" />
	<acme:form-textbox code="auditor.job.form.label.salary" path="salary" />
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo" />

	<acme:form-return code="auditor.job.form.button.return" />
	
	<acme:form-submit code ="auditor.audit-record.form.button.create" test= "${isAuditor == true}" action = "/auditor/audit-record/create?id=${id}" method = "get"/>
	<acme:form-submit code="auditor.job.form.button.descriptor" action="/authenticated/descriptor/show?id=${id}" method="get" />
	<acme:form-submit code="auditor.job.form.button.audits" action="/auditor/audit-record/list?id=${id}" method="get" />
	
</acme:form>
