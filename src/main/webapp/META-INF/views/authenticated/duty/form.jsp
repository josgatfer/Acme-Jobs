<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="authenticated.duty.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.duty.form.label.description" path="description"/>
	<acme:form-textarea code="authenticated.duty.form.label.timePercentage" path="timePercentage"/>

	<acme:form-return code="authenticated.duty.form.button.return"/>
</acme:form>