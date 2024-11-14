function loadUserWallet(userID) {
    fetch(`/api/wallet/${userID}`, {
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
    document.getElementById("totalAmount").textContent = userData.totalAmount;
    const transactionContainer = document.getElementById("transactionContainer");
    transactionContainer.innerHTML = "";

    userData.list.forEach(transaction => {
        const transactionCard = `
            <div class="col-md-12 mb-3">
                <div class="transaction-card shadow-sm">
                    <p class="transaction-amount">Transaction Amount: <span class="transaction-amount">${transaction.transactionAmount}</span></p>
                    <p class="card-text">Date: ${transaction.transactionDate}</p>
                    <p class="card-text">Program: ${transaction.transactionType}</p>
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
