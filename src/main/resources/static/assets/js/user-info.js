// Function to fetch and display user information in a table
function loadUserInfo(userId) {
    fetch(`/api/user/${userId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem("accessToken")}` // Optional if auth is needed
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch user data");
            }
            return response.json();
        })
        .then(data => {
            const userData = data.data; // Accessing the "data" field in the response
            console.log(userData)
            displayUserInfo(userData);
        })
        .catch(error => {
            console.error("Error fetching user information:", error);
            document.getElementById("userInfoTable").innerHTML = "<tr><td colspan='2'>Failed to load user information.</td></tr>";
        });
    console.log(localStorage.getItem("accessToken"))
}

// Function to display user information in the table
function displayUserInfo(user) {
    const userInfoTable = document.getElementById("userInfoTable");
    userInfoTable.innerHTML = `
        <tr><th>Username</th><td>${user.userName}</td></tr>
        <tr><th>Phone Number</th><td>${user.phoneNumber}</td></tr>
        <tr><th>Email</th><td>${user.email}</td></tr>
        <tr><th>Status</th><td>${user.status}</td></tr>
        <tr><th>Total Wallet Amount</th><td>${user.wallet.totalAmount}</td></tr>
        <tr><th>Volunteers</th><td>${user.volunteers.length}</td></tr>
        <tr><th>Transactions</th><td>${user.transactions.length}</td></tr>
    `;
}



// Replace 'user_id' with the actual ID, e.g., from URL or session
const userId = 2; // Replace with actual ID logic
loadUserInfo(userId);
