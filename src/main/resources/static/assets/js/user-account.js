document.addEventListener("DOMContentLoaded", () => {
    // Lấy dữ liệu từ thuộc tính data-user-accounts
    const tableElement = document.querySelector("#userTable");
    const userAccountsData = tableElement.getAttribute("data-user-accounts"); // Sử dụng đúng thuộc tính

    // Kiểm tra nếu dữ liệu rỗng
    if (!userAccountsData) {
        console.error("No data found in data-user-accounts attribute");
        return;
    }

    try {
        // Parse dữ liệu JSON
        const userAccounts = JSON.parse(userAccountsData);

        // Tham chiếu đến phần tử tbody
        const tableBody = document.querySelector("#userTable tbody");

        // Duyệt qua danh sách tài khoản và thêm từng hàng vào bảng
        userAccounts.forEach((user, index) => {
            // Tạo hàng mới
            const row = document.createElement("tr");

            // Tạo từng ô và điền dữ liệu
            const idCell = document.createElement("td");
            idCell.textContent = index + 1; // Số thứ tự, vì JSON không có user_id

            const usernameCell = document.createElement("td");
            usernameCell.textContent = user.userName || "N/A";

            const emailCell = document.createElement("td");
            emailCell.textContent = user.email || "N/A";

            const phoneCell = document.createElement("td");
            phoneCell.textContent = user.phoneNumber || "N/A";

            const statusCell = document.createElement("td");
            statusCell.textContent = user.status === 1 ? "Hoạt động" : "Ngừng hoạt động";

            const roleCell = document.createElement("td");
            roleCell.textContent = user.role || "USER";


            // Thêm các ô vào hàng
            row.appendChild(idCell);
            row.appendChild(usernameCell);
            row.appendChild(emailCell);
            row.appendChild(phoneCell);
            row.appendChild(statusCell);
            row.appendChild(roleCell);

            // Thêm hàng vào tbody
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Error parsing user accounts data:", error);
    }
});
