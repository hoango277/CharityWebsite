<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tin tức</title>
</head>
<body>
<!-- MENU  -->
<div class="page-wrapper">
    <%--<header>--%>
        <%--<div class="p-4">--%>
            <%--<div class="row navbar">--%>
                <%--<div class="col-12 col-md-3">--%>
                    <%--<div class="logo">--%>
                        <%--<a href="./ViewHome.html">--%>
                            <%--<img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/logo.png?1676257083798"--%>
                                 <%--alt="">--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-12 col-md-6">--%>
                    <%--<div class="item-menu">--%>
                        <%--<div class="nav nav1">--%>
                            <%--<div class="nav-item p-2">--%>
                                <%--<a class="nav-item-link" href="/trang-chu">--%>
                                    <%--<span>Trang chủ</span>--%>
                                <%--</a>--%>
                            <%--</div>--%>
                            <%--<div class="nav-item p-2">--%>
                                <%--<a href='<c:url value='/gioi-thieu'/>'>--%>
                                    <%--<span>Giới thiệu</span>--%>
                                    <%--&lt;%&ndash;<span style="color: var(--primary-color);">Giới thiệu</span>&ndash;%&gt;--%>
                                <%--</a>--%>
                            <%--</div>--%>
                            <%--<div class="nav-item p-2">--%>
                                <%--&lt;%&ndash;<a class="nav-item-link" href="./Duan.html">&ndash;%&gt;--%>
                                <%--<a href='<c:url value='/san-pham'/>'>--%>
                                    <%--<span>Sản phẩm</span>--%>
                                    <%--&lt;%&ndash;<span style="color: var(--primary-color);">Sản phẩm</span>&ndash;%&gt;--%>
                                <%--</a>--%>
                            <%--</div>--%>
                            <%--<div class="nav-item p-2">--%>
                                <%--&lt;%&ndash;<a class="nav-item-link" href="./TinTuc.html">&ndash;%&gt;--%>
                                <%--<a href='<c:url value='/tin-tuc'/>'>--%>
                                    <%--<span style="color: var(--primary-color);">Tin tức</span>--%>
                                <%--</a>--%>
                            <%--</div>--%>
                            <%--<div class="nav-item p-2">--%>
                                <%--<a href='<c:url value='/lien-he'/>'>--%>
                                    <%--<span>Liên hệ</span>--%>
                                <%--</a>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-12 col-md-3">--%>
                    <%--<button class="btn btn-primary px-4">--%>
                        <%--Liên hệ tư vấn--%>
                    <%--</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</header>--%>
    <!-- INTRO  -->
    <div class="intro text-center">
        <div class="title-page">Tin tức</div>
        <div class="row">
            <div class="col-xs-12 a-left">
                <ul class="desc-intro">
                    <li class="home">
                        <a href="./ViewHome.html"><span style="color:#fff">Trang chủ</span></a>
                        <span class="mx-1" style="color:#fff"> / </span>
                    </li>
                    <li class="intro-item"><span>Tin tức</span></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- CONTENT  -->
    <div class="content-tintuc">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4">
                    <div class="danhmuc mt-5">
                        <h2 class="title-h2">Danh mục tin tức</h2>
                        <div class="item-danhmuc">
                            <p class=""><a href="">Trang chủ</a></p>
                            <p><a href="">Giới thiệu</a></p>
                            <p><a href="">Dự án bất động sản</a></p>
                            <p><a href="">Tin tức</a></p>
                            <p><a href="">Liên hệ</a></p>
                        </div>
                        <h2 class="title-h2">Tin tức xem nhiều</h2>
                        <div class="item-tintuc">
                            <div class="row">
                                <div class="col-12 col-md-5">
                                    <img src="https://bizweb.dktcdn.net/thumb/large/100/328/362/articles/vinpearl-ha-long-bay-resort-0.jpg?v=1534491225390" alt="">
                                </div>
                                <div class="col-12 col-md-7">
                                    <p class="title-tintuc"><a href="">Xuất hiện căn hộ cao cấp làm mê mẩn giới
                                        thượng lưu</a>
                                    </p>
                                </div>
                            </div>
                            <div class="border border-bottom mt-3 "></div>
                            <div class="row mt-2">
                                <div class="col-12 col-md-5">
                                    <img src="https://bizweb.dktcdn.net/thumb/large/100/328/362/articles/a7.jpg?v=1534491036947" alt="">
                                </div>
                                <div class="col-12 col-md-7">
                                    <p class="title-tintuc"><a href="">Tiếp tục rà soát, đánh giá những bất cập liên
                                        quan đến
                                        tích tụ đất đai</a>
                                    </p>
                                </div>
                            </div>
                            <div class="border border-bottom mt-3 "></div>
                            <div class="row mt-2">
                                <div class="col-12 col-md-5">
                                    <img src="https://bizweb.dktcdn.net/thumb/large/100/328/362/articles/36-1-kmey.jpg?v=1534490952150" alt="">
                                </div>
                                <div class="col-12 col-md-7">
                                    <p class="title-tintuc"><a href="">Hà Nội phê duyệt chỉ giới đường đỏ tuyến
                                        đường khu đô
                                        thị Gia Lâm tới ga Phú Thị</a>
                                    </p>
                                </div>
                            </div>
                            <div class="border border-bottom mt-3 "></div>
                            <div class="row mt-2">
                                <div class="col-12 col-md-5">
                                    <img src="https://bizweb.dktcdn.net/thumb/large/100/328/362/articles/a7.jpg?v=1534491036947" alt="">
                                </div>
                                <div class="col-12 col-md-7">
                                    <p class="title-tintuc"><a href="">Chính sách ưu đãi có một không hai khi đầu tư
                                        BĐS nghỉ
                                        dưỡng tại Khánh Hòa</a>
                                    </p>
                                </div>
                            </div>
                            <div class="border border-bottom mt-3 "></div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-8 mt-5">
                    <div class="row">
                        <div class="col-12 col-md-7">
                            <img class="image-tintuc" style="width: 99%; height: 300px;"
                                 src="https://bizweb.dktcdn.net/thumb/large/100/328/362/articles/vinpearl-ha-long-bay-resort-0.jpg?v=1534491225390" alt="">
                        </div>
                        <div class="col-12 col-md-5">
                            <div class="content_blog_full">
                                <h3><a class="title-h3" style="font-size: 20px; font-weight:700;"
                                       title="Xuất hiện căn hộ cao cấp làm mê mẩn giới thượng lưu"
                                       href="/xuat-hien-can-ho-cao-cap-lam-me-man-gioi-thuong-luu">Xuất
                                    hiện căn hộ cao cấp làm mê mẩn giới thượng lưu</a></h3>
                                <span class="time_post f">
                                        <i style="color: #35BF76" class="fa fa-calendar icon-tintuc"></i>&nbsp;Thứ
                                        Fri, 17/08/2018
                                    </span>
                                <span class="time_post">
                                        <i style="color: #35BF76" class="fa fa-comments"
                                           aria-hidden="true"></i>&nbsp;(2)Bình
                                        luận</span>
                                <div class="blog-description">
                                    <p class="text3linsearch-btne">
                                        Venice luôn đứng đầu trong danh sách những thành phố lãng mạn nhất
                                        không
                                        chỉ ở Italy mà trên toàn thế giới. Và nói đến Sky89 người ta sẽ liên
                                        tưởng ngay “siêu phẩm” với thành phố Venice xinh đẹp củ...
                                    </p>
                                    <button class="btn btn-primary px-4">
                                        <a href="./ChiTiet.html" style="color:#fff">Xem chi
                                            tiết</a>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="border border-bottom mt-4"></div>
                    <div class="row mt-4">
                        <div class="col-12 col-md-4">
                            <img style="width: 100%; max-height: 200px;" class="image-tintuc" src="https://bizweb.dktcdn.net/thumb/large/100/328/362/articles/36-1-kmey.jpg?v=1534490952150"
                                 alt="">
                        </div>
                        <div class="col-12 col-md-8">
                            <h3><a class="title-h3" style="font-size: 20px; font-weight:700;"
                                   title=" Tiếp tục rà soát, đánh giá những bất cập liên quan đến tích tụ đất đai"
                                   href="/tiep-tuc-ra-soat-danh-gia-nhung-bat-cap-lien-quan-den-tich-tu-dat-dai">Hà
                                Nội phê duyệt chỉ giới đường đỏ tuyến đường khu đô thị Gia Lâm tới ga Phú
                                Thị</a></h3>
                            <span class="time_post f"><i style="color: #35BF76" class="fa fa-calendar"></i>&nbsp;Thứ
                                    Fri, 17/08/2018
                                </span>
                            <span class="time_post"><i style="color: #35BF76" class="fa fa-comments"
                                                       aria-hidden="true"></i>&nbsp;(0)Bình
                                    luận</span>
                            <div class="blog-description">
                                <p class="text3line">
                                    Đại biểu Quốc hội Phạm Đình Cúc, Đoàn đại biểu Quốc hội tỉnh Bà Rịa - Vũng Tàu
                                    chất vấn Phó Thủ tướng Trịnh Đình Dũng: Việc tập trung ruộng đất cùng với quá
                                    trình cơ cấu lại ngành nông nghiệp để th...
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="border border-bottom mt-4"></div>
                    <div class="row mt-4">
                        <div class="col-12 col-md-4">
                            <img style="width: 100%; max-height: 200px;" class="image-tintuc"
                                 src="https://bizweb.dktcdn.net/thumb/large/100/328/362/articles/36-1-kmey.jpg?v=1534490952150" alt="">
                        </div>
                        <div class="col-12 col-md-8">
                            <h3><a class="title-h3" style="font-size: 20px; font-weight:700;"
                                   title=" Tiếp tục rà soát, đánh giá những bất cập liên quan đến tích tụ đất đai"
                                   href="/tiep-tuc-ra-soat-danh-gia-nhung-bat-cap-lien-quan-den-tich-tu-dat-dai">Chính
                                sách ưu đãi có một không hai khi đầu tư BĐS nghỉ
                                dưỡng tại Khánh Hòa</a></h3>
                            <span class="time_post f"><i style="color: #35BF76" class="fa fa-calendar"></i>&nbsp;Thứ
                                    Fri, 17/08/2018
                                </span>
                            <span class="time_post"><i style="color: #35BF76" class="fa fa-comments"
                                                       aria-hidden="true"></i>&nbsp;(0)Bình
                                    luận</span>
                            <div class="blog-description">
                                <p class="text3line">
                                    Đại biểu Quốc hội Phạm Đình Cúc, Đoàn đại biểu Quốc hội tỉnh Bà Rịa - Vũng Tàu
                                    chất vấn Phó Thủ tướng Trịnh Đình Dũng: Việc tập trung ruộng đất cùng với quá
                                    trình cơ cấu lại ngành nông nghiệp để th...
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="border border-bottom mt-4"></div>
                    <div class="row mt-4">
                        <div class="col-12 col-md-4">
                            <img style="width: 100%; max-height: 200px;" class="image-tintuc" src="https://bizweb.dktcdn.net/thumb/large/100/328/362/articles/a7.jpg?v=1534491036947"
                                 alt="">
                        </div>
                        <div class="col-12 col-md-8">
                            <h3><a class="title-h3" style="font-size: 20px; font-weight:700;"
                                   title=" Tiếp tục rà soát, đánh giá những bất cập liên quan đến tích tụ đất đai"
                                   href="/tiep-tuc-ra-soat-danh-gia-nhung-bat-cap-lien-quan-den-tich-tu-dat-dai">Tiếp
                                tục rà soát, đánh giá những bất cập liên quan đến tích tụ đất đai</a></h3>
                            <span class="time_post f"><i style="color: #35BF76" class="fa fa-calendar"></i>&nbsp;Thứ
                                    Fri, 17/08/2018
                                </span>
                            <span class="time_post"><i style="color: #35BF76" class="fa fa-comments"
                                                       aria-hidden="true"></i>&nbsp;(0)Bình
                                    luận</span>
                            <div class="blog-description">
                                <p class="text3line">
                                    Đại biểu Quốc hội Phạm Đình Cúc, Đoàn đại biểu Quốc hội tỉnh Bà Rịa - Vũng Tàu
                                    chất vấn Phó Thủ tướng Trịnh Đình Dũng: Việc tập trung ruộng đất cùng với quá
                                    trình cơ cấu lại ngành nông nghiệp để th...
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- TIEN TRINH  -->
    <div class="container text-center">
        <div class="shop-pag text-xs-right mt-5">
            <nav class="text-center">
                <ul class="pagination clearfix justify-content-center">
                    <li class="page-item disabled"><a class="page-link" href="#">«</a></li>
                    <li class="active page-item disabled"><a class="page-link" href="javascript:;">1</a></li>
                    <li class="page-item"><a class="page-link" onclick="doSearch(2)" href="javascript:;">2</a>
                    </li>
                    <li class="page-item"><a class="page-link" onclick="doSearch(2)" href="javascript:;">»</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<!-- FOOTER  -->
<footer class="footer">
    <div class="container">
        <div class="top-footer text-center mt-0">
            <div class="logo logo-footer pt-5">
                <a href="./ViewHome.html"><img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/logo_footer.png?1676257083798" alt="logo-footer"></a>
                <p class="desc-logo-footer mt-3">Với hơn 10 năm kinh nghiệm, SkyLand tự hào là sàn
                    mua
                    bán, giao dịch và quảng cáo
                    bất động sản hàng đầu tại Việt Nam</p>
                <div class="item-footer mt-5">
                    <div class="row">
                        <div class="col-12 col-md-4 text-center">
                            <div class="icon-footer">
                                <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_maps.png?1676257083798" alt="">
                            </div>
                            <div class="content-center-footer">
                                <p class="mb-1 mt-3">Trụ sở chính</p>
                                <p class="desc-footer">Số 46 Man Thiện, TP Thủ Đức, TP HCM</p>
                            </div>
                        </div>
                        <div class="col-12 col-md-4 text-center">
                            <div class="icon-footer">
                                <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_phone.png?1676257083798" alt="">
                            </div>
                            <div class="content-center-footer">
                                <p class="mb-1 mt-3">Hotline</p>
                                <p class="desc-footer"><a class="a-text" href="#">098828</a></p>
                            </div>
                        </div>
                        <div class="col-12 col-md-4 text-center">
                            <div class="icon-footer">
                                <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_email.png?1676257083798" alt="">
                            </div>
                            <div class="content-center-footer">
                                <p class="mb-1 mt-3">Email</p>
                                <p class="desc-footer"><a class="a-text" href="#">vsh@gmail.com</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="text-center">
                <div class="border-bottom mb-5 mt-4"></div>
            </div>
        </div>
        <div class="bottom-footer">
            <div class="row">
                <div class="col-12 col-md-3">
                    <h4 class="title-item-bottom-footer">Thông tin công ty</h4>
                    <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                        chủ</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                        sản</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                </div>
                <div class="col-12 col-md-3">
                    <h4 class="title-item-bottom-footer">Chính sách hoạt động</h4>
                    <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                        chủ</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                        sản</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                </div>
                <div class="col-12 col-md-3">
                    <h4 class="title-item-bottom-footer">Hỗ trợ khách hàng</h4>
                    <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                        chủ</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                        sản</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                </div>
                <div class="col-12 col-md-3">
                    <h4 class="title-item-bottom-footer">Kết nối với chúng tôi</h4>
                    <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                        chủ</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                        sản</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                    <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom-footer-2">
        <div class="text-center desc-bottom-footer-2">@ Bản quyền thuộc về Happy Team |
            Cung cấp bởi <a class="a-text group-name" href="#">HappyTeam</a></div>
    </div>
    </div>
</footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
