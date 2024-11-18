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

    const totalTransactions = userData.list.length;
    document.getElementById("totalTransaction").innerHTML = `<span class="text-success">${totalTransactions}</span>`;


    const transactionContainer = document.getElementById("transactionContainer");
    transactionContainer.innerHTML = "";


    function formatCurrency(amount) {
        return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + ' VNĐ';
    }


    const fragment = document.createDocumentFragment();

    // Hiển thị từng giao dịch
    userData.list.forEach(transaction => {
        const formattedAmount = `+${formatCurrency(transaction.transactionAmount)}`;
        const transactionCard = document.createElement("div");
        transactionCard.className = "col-md-12 mb-3 font-weight-bold";

        transactionCard.innerHTML = `
            <div class="transaction-card shadow-sm">
                <p class="transaction-amount"> 
                    Số tiền ủng hộ: 
                    <span class="text-success" style="font-weight: bold">${formattedAmount}</span>
                </p>
                <p class="card-text"> Ngày ủng hộ: ${transaction.transactionDate}</p>
                <p class="card-text"> Tới: ${transaction.transactionType}</p>
            </div>
        `;

        fragment.appendChild(transactionCard);
    });

    transactionContainer.appendChild(fragment);
}


const userID = localStorage.getItem("userId") || 1;
loadUserWallet(userID);

function refreshWallet() {
    loadUserWallet(userID);
}
