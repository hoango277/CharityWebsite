// Function to validate the login form fields
function validateLoginForm(event) {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (!username || !password) {
        alert("Please enter both username and password.");
        event.preventDefault();
    }
}

// Function to handle token storage and redirection after login
document.addEventListener("DOMContentLoaded", function() {
    const tokenElement = document.getElementById("tokenData");
    if (tokenElement) {
        const accessToken = tokenElement.getAttribute("data-access-token");
        const refreshToken = tokenElement.getAttribute("data-refresh-token");
        const userId = tokenElement.getAttribute("data-userId")
        const roles = tokenElement.getAttribute("data-roles")

        const listRole = roles ? roles.split(",") : [];

        if (accessToken && refreshToken) {
            localStorage.setItem("accessToken", accessToken);
            localStorage.setItem("refreshToken", refreshToken);
            localStorage.setItem("userId",userId);
            console.log("Token saved to localStorage.");
            if (listRole.includes("ADMIN")){
                window.location.href= "/admin"
            } else {
                window.location.href = "/";
            }
        }
    }
});
