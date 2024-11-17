document.addEventListener("DOMContentLoaded", function () {
    // Lấy các nút trong sidebar
    const userButton = document.querySelector(".nav-link.user");
    const contents = document.querySelectorAll(".content");

    // Hàm ẩn tất cả các nội dung
    function hideAllContents() {
        contents.forEach((content) => {
            content.classList.add("d-none");
        });
    }

    // Xử lý sự kiện khi bấm vào nút 'Quản lý Người Dùng'
    userButton.addEventListener("click", function () {
        hideAllContents(); // Ẩn tất cả nội dung
        contents[1].classList.remove("d-none"); // Hiển thị nội dung user
    });
});
