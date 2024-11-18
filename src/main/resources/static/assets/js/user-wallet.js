function loadUserWallet(userID) {
    fetch(`/wallet/${userID}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
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
            displayUserInfo(data.data);
        })
        .catch(error => {
            console.error("Error fetching user wallet data:", error);
            document.getElementById("transactionContainer").innerHTML = "<div class='col-12'><div class='alert alert-danger'>Failed to load user wallet data.</div></div>";
        });
}

function displayUserInfo(userData) {
    // Lấy và hiển thị tổng số giao dịch
    const totalTransactions = userData.list.length;
    document.getElementById("totalTransaction").innerHTML = `<span class="text-success">${totalTransactions}</span>`;

    // Làm rỗng container trước khi thêm nội dung
    const transactionContainer = document.getElementById("transactionContainer");
    transactionContainer.innerHTML = "";

    // Hiển thị từng giao dịch
    userData.list.forEach(transaction => {
        const transactionAmount = `+${transaction.transactionAmount}`;
        const transactionCard = `
            <div class="col-md-12 mb-3 font-weight-bold">
                <div class="transaction-card shadow-sm" >
                    <p class="transaction-amount"> Số tiền ủng hộ: <span class="text-success" style="font-weight: bold">${transactionAmount}</span></p>
                    <p class="card-text"> Ngày ủng hộ: ${transaction.transactionDate}</p>
                    <p class="card-text"> Tới: ${transaction.transactionType}</p>
                </div>
            </div>
        `;
        transactionContainer.innerHTML += transactionCard;
    });
}


const userID = localStorage.getItem("userId") || 1;
loadUserWallet(userID);

function refreshWallet() {
    loadUserWallet(userID);
}
