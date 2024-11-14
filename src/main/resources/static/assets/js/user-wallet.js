function loadUserWallet(userID) {
    fetch(`/api/wallet/${userID}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            // 'Authorization': `Bearer ${localStorage.getItem("accessToken")}` // Uncomment nếu cần xác thực bằng token
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch user wallet data");
            }
            return response.json();
        })
        .then(data => {
            console.log("User Wallet Data:", data);
            displayUserInfo(data.data); // truyền `data.data` đến hàm để hiển thị thông tin ví
        })
        .catch(error => {
            console.error("Error fetching user wallet data:", error);
            document.getElementById("transactionContainer").innerHTML = "<div class='col-12'><div class='alert alert-danger'>Failed to load user wallet data.</div></div>";
        });
}

// Hàm displayUserInfo để hiển thị dữ liệu vào bảng HTML
function displayUserInfo(userData) {
    // Hiển thị tổng số tiền
    document.getElementById("totalAmount").textContent = userData.totalAmount;

    const transactionContainer = document.getElementById("transactionContainer");

    // Làm trống container trước khi thêm các giao dịch mới
    transactionContainer.innerHTML = "";

    // Duyệt qua danh sách giao dịch và thêm các card vào container
    userData.list.forEach(transaction => {
        const transactionCard = `
            <div class="col-md-6 mb-4">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Transaction Amount: <span class="transaction-amount">${transaction.transactionAmount}</span> </h5>
                        <p class="card-text">Date: ${transaction.transactionDate}</p>
                        <p class="card-text">Type: ${transaction.transactionType}</p>
                    </div>
                </div>
            </div>
        `;
        transactionContainer.innerHTML += transactionCard;
    });
}

// Thay `userID` bằng ID người dùng thực tế hoặc lấy từ localStorage
const userID = localStorage.getItem("userId") || 1; // Hoặc giá trị mặc định
loadUserWallet(userID);

function refreshWallet() {
    loadUserWallet(userID); // Gọi lại hàm để tải dữ liệu ví
}