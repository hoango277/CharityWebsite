function logout() {
    const accessToken = localStorage.getItem("accessToken");
    if (!accessToken) return;
    fetch("/auth/logout", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "check-token": accessToken
        }
    }).then(response => {
        if (response.ok) {
            localStorage.removeItem("accessToken");
            localStorage.removeItem("refreshToken")
            alert("Đăng xuất thành công");
            window.location.href = "/";
        } else {
            alert("Đăng xuất không thành công");
        }
    }).catch(error => {
        console.error("Lỗi khi đăng xuất:", error);
    });
}