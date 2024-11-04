<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="changePasswordURL" value="/api/user/change-password"/>
<html>
<head>
    <title>Đổi mật khẩu</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Đổi mật khẩu</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <div id="profile">
                        <form:form id="formChangePassword" class="form-horizontal" name="formChangePassword">
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    <%--<spring:message code="label.password.old"/>--%>
                                        Mật khẩu cũ
                                </label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="oldPassword" name="oldPassword"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    <%--<spring:message code="label.password.new"/>--%>
                                        Mật khẩu mới
                                </label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="newPassword" name="newPassword"/>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    <%--<spring:message code="label.password.repeat"/>--%>
                                        Nhập lại mật khẩu mới
                                </label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"/>
                                </div>
                            </div>
                            <!--Btn-->
                            <div class="col-sm-12">
                                <label class="col-sm-3 control-label no-padding-right message-info"></label>
                                <input type="button" class="btn btn-white btn-warning btn-bold" value="Đổi mật khẩu" id="btnChangePassword"/>
                            </div>
                            <!--Btn-->
                            <input type="hidden" value="${model.id}" id="userId"/>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('#btnChangePassword').click(function () {
                $('#formChangePassword').submit();
            });
        });
        $(function() {
            $("form[name='formChangePassword']").validate({
                rules: {
                    oldPassword: "required",
                    newPassword: {
                        required: true,
                        minlength: 6
                    },
                    confirmPassword: "required"
                },
                messages: {
                    oldPassword: "Không bỏ trống",
                    newPassword: {
                        required: "Không bỏ trống",
                        minlength: "Mật khẩu tối thiểu 6 kí tự"
                    },
                    confirmPassword: "Không bỏ trống"
                },
                submitHandler: function(form) {
                    var formData = $('#formChangePassword').serializeArray();
                    var dataArray = {};
                    $.each(formData, function (i, v) {
                        dataArray["" + v.name + ""] = v.value;
                    });
                    changePassword(dataArray, $('#userId').val());
                }
            });
        });

        function changePassword(data, id) {
            $.ajax({
                url: '${changePasswordURL}/'+id,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (res) {
                    if (res == 'update_success') {
                        window.location.href = "<c:url value='/admin/profile-password?message=update_success'/>";
                    } else if (res == 'change_password_fail') {
                        window.location.href = "<c:url value='/admin/profile-password?message=change_password_fail'/>";
                    }
                },
                error: function (res) {
                    console.log(res);
                    window.location.href = "<c:url value='/admin/profile-password?message=error_system'/>";
                }
            });
        }
    </script>
</div>
</body>
</html>
