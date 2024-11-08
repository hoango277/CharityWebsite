document.addEventListener("DOMContentLoaded", function() {
    // Gán hàm forgotPassword vào sự kiện submit của form
    const form = document.getElementById("forgotPasswordForm");
    if (form) {
        form.addEventListener("submit", forgotPassword);
    }
});

// Hàm xử lý gửi yêu cầu quên mật khẩu
async function forgotPassword(event) {
    event.preventDefault(); // Ngăn chặn hành động mặc định của form

    const email = document.getElementById("email").value;

    if (!email) {
        alert("Please enter your email address.");
        return;
    }
    console.log(email);

    try {
        const response = await fetch("/auth/forgot-password", {
            method: "POST",
            headers: {
                "Content-Type": "text/plain"
            },
            body: email // Gửi email trực tiếp dưới dạng văn bản mà không có dấu ngoặc kép
        });

        if (response.ok) {
            const data = await response.json();
            alert(data.message || "A reset link has been sent to your email.");
            window.location.href = "/login"; // Chuyển hướng về trang đăng nhập
        } else {
            const errorData = await response.json();
            alert(errorData.message || "An error occurred. Please try again.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("An error occurred while trying to send the reset link.");
    }
}
