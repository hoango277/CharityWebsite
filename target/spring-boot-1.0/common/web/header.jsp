<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaweb.security.utils.SecurityUtils" %>
<nav class="navbar-expand-lg navbar-dark fixed-top">
	<%--<div class="container">--%>
		<%--<a class="navbar-brand" href="#">Nhóm 1</a>--%>
		<%--<button class="navbar-toggler" type="button" data-toggle="collapse"--%>
			<%--data-target="#navbarResponsive" aria-controls="navbarResponsive"--%>
			<%--aria-expanded="false" aria-label="Toggle navigation">--%>
			<%--<span class="navbar-toggler-icon"></span>--%>
		<%--</button>--%>
		<%--<div class="collapse navbar-collapse" id="navbarResponsive">--%>
			<%--<ul class="navbar-nav ml-auto">--%>
				<%--<li class="nav-item active"><a class="nav-link" href="/trang-chu#">Trang chủ--%>
						<%--<span class="sr-only">(current)</span>--%>
				<%--</a></li>--%>
				<%--<security:authorize access = "isAnonymous()">--%>
					<%--<li class><a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a></li>--%>
					<%--<li class="nav-item"><a class="nav-link" href="#">Đăng ký</a></li>="nav-item"--%>
				<%--</security:authorize>--%>
				<%--<security:authorize access = "isAuthenticated()">--%>
					<%--<li class="nav-item"><a class="nav-link" href="#"> Xin chào <%=SecurityUtils.getPrincipal().getUsername()%></a></li>--%>
					<%--<li class="nav-item"><a class="nav-link" href="<c:url value='/logout'/>">Thoát</a></li>--%>
				<%--</security:authorize>--%>
			<%--</ul>--%>
		<%--</div>--%>
	<%--</div>--%>




		<div class="row navbar">
			<div class="col-12 col-md-3">
				<div class="logo">
					<a href="">
						<img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/logo.png?1676257083798"
							 alt="">
					</a>
				</div>
			</div>
			<div class="col-12 col-md-6">
				<div class="item-menu">
					<div class="nav nav1">
						<div class="nav-item p-2">
							<a class="nav-item-link" href="/trang-chu">
								<span style="color: var(--primary-color);">Trang chủ</span>
							</a>
						</div>
						<div class="nav-item p-2">
							<a href='<c:url value='/gioi-thieu'/>'>
								<%--<span>Giới thiệu</span>--%>
								<span style="color: var(--primary-color);">Giới thiệu</span>
							</a>
						</div>
						<div class="nav-item p-2">
							<%--<a class="nav-item-link" href="./Duan.html">--%>
							<a href='<c:url value='/san-pham'/>'>
								<%--<span>Sản phẩm</span>--%>
								<span style="color: var(--primary-color);">Sản phẩm</span>
							</a>
						</div>
						<div class="nav-item p-2">
							<a href='<c:url value='/tin-tuc'/>'>
								<%--<span>Tin tức</span>--%>
								<span style="color: var(--primary-color);">Tin tức</span>
							</a>
						</div>
						<div class="nav-item p-2">
							<a href='<c:url value='/lien-he'/>'>
								<%--<span>Liên hệ</span>--%>
									<span style="color: var(--primary-color);">Liên hệ</span>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<%--<li class="nav-item active"><a class="nav-link" href="/trang-chu#">Trang chủ--%>
						<%--<span class="sr-only">(current)</span>--%>
					<%--</a></li>--%>
					<security:authorize access = "isAnonymous()">
						<li class><a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Đăng ký</a></li>
					</security:authorize>
					<security:authorize access = "isAuthenticated()">
						<li class="nav-item"><a class="nav-link" href="#"> Xin chào <%=SecurityUtils.getPrincipal().getUsername()%></a></li>
						<li class="nav-item"><a class="nav-link" href="<c:url value='/logout'/>">Thoát</a></li>
					</security:authorize>
				</ul>
			</div>
		</div>
</nav>