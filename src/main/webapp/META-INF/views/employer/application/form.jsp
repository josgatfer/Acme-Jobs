<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.application.form.label.reference" path="reference"  />
	<acme:form-textbox code="employer.application.form.label.skills" path="skills" />
	<acme:form-textbox code="employer.application.form.label.qualifications" path="qualifications" />
	<acme:form-moment code="employer.application.form.label.moment" path="moment" />
	<acme:form-textarea code="employer.application.form.label.reference" path="reference" />
	<acme:form-textbox code="employer.application.form.label.motivo" path="motivo" readonly="false" />
	<acme:form-submit test="${command == 'show'}" code="employer.application.form.button.update" action="/employer/application/update-accept" />
	<acme:form-submit test="${command == 'show'}" code="employer.application.form.button.update2" action="/employer/application/update-no-accept" />
	<acme:form-submit test="${command == 'update'}" code="employer.application.form.button.update"
		action="/employer/application/update-accept" />
	<acme:form-return code="employer.application.form.label.button.return" />

</acme:form>


