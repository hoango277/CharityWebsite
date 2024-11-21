// Hàm định dạng số tiền
function formatCurrency(amount) {
    const number = parseFloat(amount);
    if (isNaN(number)) {
        return "0 VNĐ";
    }
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + " VNĐ";
}

// Định dạng lại số tiền trong bảng sau khi trang tải xong
function reformatTransactionAmounts() {
    const amountCells = document.querySelectorAll('.transaction-account-amount');

    amountCells.forEach(cell => {
        const rawAmount = cell.textContent.trim();
        cell.textContent = formatCurrency(rawAmount);
    });
}

// Khi trang đã tải, thực hiện định dạng số tiền
document.addEventListener("DOMContentLoaded", () => {
    reformatTransactionAmounts();
});
