// Hàm định dạng số tiền
function formatCurrency(amount) {
    const number = parseFloat(amount); // Chuyển chuỗi sang số
    if (isNaN(number)) {
        return "0 VNĐ"; // Trả về giá trị mặc định nếu không hợp lệ
    }
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + " VNĐ";
}

// Định dạng lại số tiền trong bảng sau khi trang tải xong
function reformatTransactionAmounts() {
    // Tìm tất cả các ô chứa số tiền theo class
    const amountCells = document.querySelectorAll('.transaction-account-amount');

    amountCells.forEach(cell => {
        const rawAmount = cell.textContent.trim(); // Lấy nội dung thô trong ô
        cell.textContent = formatCurrency(rawAmount); // Định dạng và cập nhật nội dung ô
    });
}

// Khi trang đã tải, thực hiện định dạng số tiền
document.addEventListener("DOMContentLoaded", () => {
    reformatTransactionAmounts();
});
