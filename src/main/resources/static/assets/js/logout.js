// Function to handle logout
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
            localStorage.removeItem("refreshToken");
            alert("Đăng xuất thành công");
            window.location.href = "/";
        } else {
            alert("Đăng xuất không thành công");
        }
    }).catch(error => {
        console.error("Lỗi khi đăng xuất:", error);
    });
}

// Event listener for DOM content load to set up the visibility of buttons
document.addEventListener("DOMContentLoaded", function () {
    const accessToken = localStorage.getItem("accessToken");
    const registerButton = document.getElementById("registerButton");
    const loginButton = document.getElementById("loginButton");
    const userAccount = document.getElementById("userAccount");

    if (accessToken) {
        registerButton.classList.add("d-none");
        loginButton.classList.add("d-none");
        userAccount.classList.remove("d-none");
    } else {
        registerButton.classList.remove("d-none");
        loginButton.classList.remove("d-none");
        userAccount.classList.add("d-none");
    }
});

