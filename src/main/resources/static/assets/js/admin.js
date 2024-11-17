document.addEventListener("DOMContentLoaded", function () {
    // Lấy tất cả các nút trong thanh bên
    const userButton = document.querySelector(".nav-link.user");
    const programButton = document.querySelector(".nav-link.program");
    const transactionButton = document.querySelector(".nav-link.transaction");

    // Lấy tất cả các thành phần có class content
    const contents = document.querySelectorAll(".content");

    // Hàm ẩn tất cả các nội dung và hiển thị nội dung cụ thể
    function showContent(contentIndex) {
        contents.forEach((content, index) => {
            if (index === contentIndex) {
                content.classList.remove("d-none");
            } else {
                content.classList.add("d-none");
            }
        });
    }

    // Gán sự kiện cho nút Quản lý Người Dùng
    userButton.addEventListener("click", function () {
        showContent(1); // Hiển thị nội dung đầu tiên (Quản lý Người Dùng)
    });

    // Gán sự kiện cho nút Quản lý Dự án (nếu cần)
    programButton.addEventListener("click", function () {
        showContent(2); // Hiển thị nội dung thứ hai (Quản lý Dự án)
    });

    // Gán sự kiện cho nút Quản lý Giao Dịch (nếu cần)
    transactionButton.addEventListener("click", function () {
        showContent(3); // Hiển thị nội dung thứ ba (Quản lý Giao Dịch)
    });
});
