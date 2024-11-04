<%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 2/28/2024
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"></c:url>

<html>
<head>
    <title>Thêm tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        Sửa hoặc thêm tòa nhà
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <div class="row" style="font-family: Times New Roman, Times, serif;">
                    <form:form modelAttribute="buildingEdit" id="listForm" method="GET">
                        <div class="col-xs-12">
                            <form action="" class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input class="form-control" path="name" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Quận</label>
                                    <div class="col-xs-2">
                                        <form:select class="form-control" path="district">
                                            <form:option value="">---Chọn quận---</form:option>
                                            <form:options items="${districts}"></form:options>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="ward" id="ward" value="">--%>
                                        <form:input class="form-control" path="ward" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đường</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="street" id="street" value="">--%>
                                        <form:input class="form-control" path="street" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Kết cấu</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="structure" id="structure" value="">--%>
                                        <form:input class="form-control" path="structure"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="number" name="numberofbasement" id="numberofbasement" value=""></div>--%>
                                        <form:input class="form-control" path="numberOfBasement"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích sàn</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="number" name="floorArea" id="floorarea" value="">--%>
                                        <form:input class="form-control" path="floorArea"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Hướng</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="direction" id="direction" value="">--%>
                                        <form:input class="form-control" path="direction"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Hạng</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="level" id="level" value="">--%>
                                        <form:input class="form-control" path="level"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="rentarea" id="rentarea" value="">--%>
                                        <form:input class="form-control" path="rentArea"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="number" name="rentprice" id="rentprice" value="">--%>
                                        <form:input class="form-control" path="rentPrice"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Mô tả giá</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="rentpricedescription" id="rentpricedescription" value=""></div>--%>
                                        <form:input class="form-control" path="rentPriceDescription"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="serviceFee" value="">--%>
                                        <form:input class="form-control" path="serviceFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí ô tô</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="carFee" value="">--%>
                                        <form:input class="form-control" path="carFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí mô tô</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="motorbikeFee" value="">--%>
                                        <form:input class="form-control" path="motorbikeFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí ngoài giờ</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="extraFee" value="">--%>
                                        <form:input class="form-control" path="extraFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tiền điện</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="electricFee" value="">--%>
                                        <form:input class="form-control" path="electricFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="deposit" value="">--%>
                                        <form:input class="form-control" path="deposit"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thanh toán</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="payment" value="">--%>
                                        <form:input class="form-control" path="payment"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thời hạn thuê</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="time" value="">--%>
                                        <form:input class="form-control" path="rentTime"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Thời gian trang trí</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="decorationTime" value="">--%>
                                        <form:input class="form-control" path="decorationTime"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Tên quản lý</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="managerName" value="">--%>
                                        <form:input class="form-control" path="managerName"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">SĐT quản lí</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="managerPhone" value="">--%>
                                        <form:input class="form-control" path="managerPhone"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="brokerageFee" value="">--%>
                                        <form:input class="form-control" path="brokerageFee"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Loại tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:checkboxes path="typeCode" items="${typeCodes}"></form:checkboxes>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3">Ghi chú</label>
                                    <div class="col-xs-9">
                                        <%--<input class="form-control" type="text" name="" id="note" value="">--%>
                                        <form:input class="form-control" path="note"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                                    <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                                    <div class="col-sm-9">
                                        <c:if test="${not empty buildingEdit.image}">
                                            <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                            <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                        </c:if>
                                        <c:if test="${empty buildingEdit.image}">
                                            <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">

                                        <c:if test="${not empty buildingEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Cập nhật tòa nhà</button>
                                            <button type="button" class="btn btn-primary" id="btnCancel">Hủy thao tác</button>
                                        </c:if>

                                        <c:if test="${empty buildingEdit.id}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Thêm mới</button>
                                            <button type="button" class="btn btn-primary" id="btnCancel">Hủy thao tác</button>
                                        </c:if>
                                        <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                    </div>
                                </div>

                                <form:hidden path="id" id="buildingId"/>
                            </form>
                        </div>
                    </form:form>

                </div>
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<script>

    var imageBase64 = '';
    var imageName = '';

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $('#btnAddOrUpdateBuilding').click(function () {
        var data = {};
        var typeCode = [];

        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, v) {
            if (v.name != 'typeCode') {
                data["" + v.name + ""] = v.value;
            }

            else {
                typeCode.push(v.value);
            }

            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        data['typeCode'] = typeCode;
        $('#loading_image').show();
        if(typeCode != '')
        {
            addOrUpdateBuilding(data);
        }

        else
        {
            window.location.href="<c:url value = "/admin/building-list?typeCode=require"/>";
        }
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });


    function addOrUpdateBuilding(data)
    {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response)
            {
                window.location.href="<c:url value = '/admin/building-list?message=success'/>";
                alert("Thêm tòa nhà thành công!!!");
            },

            error: function (response) {
                console.log("failed");
                window.location.href="<c:url value = '/admin/building-edit?message=error'/>";
            }
        })
    }


    $('#btnCancel').click(function () {
        window.location.href="/admin/building-list";
    });

    function assignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal();
        $('buildingId').val();
    }

    $('#btnassignmentBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('buildingId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        console.log("ok");
    })
</script>
</body>
</html>
