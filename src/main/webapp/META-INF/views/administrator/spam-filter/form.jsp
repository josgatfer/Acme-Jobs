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
	<acme:form-textbox code="administrator.spam-filter.form.label.badWords" path="badWords" />
	<acme:form-double code="administrator.spam-filter.form.label.threshold" path="threshold" />
	
	<acme:form-submit
	code = "administrator.spam-filter.form.button.update"
	action = "/administrator/spam-filter/update"/>
	
	<acme:form-return code="administrator.spam-filter.form.button.return" />
</acme:form>