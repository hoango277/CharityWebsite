document.addEventListener("DOMContentLoaded", function() {
    let currentPage = 0;
    function loadPage(page) {
        currentPage = page;

        // Gửi yêu cầu AJAX
        fetch(`/projects/${projectID}?page=${page}&size=10`)
            .then(response => response.json())
            .then(data => {
                // Cập nhật bảng với dữ liệu mới
                const tableBody = document.getElementById('donationTableBody');
                tableBody.innerHTML = ''; // Xóa nội dung hiện tại trong bảng
                data.volunteers.forEach(donation => {
                    const row = document.createElement('tr');
                    row.style.backgroundColor = '#fff';
                    row.style.color = '#6c757d';

                    row.innerHTML = `
                        <td style="color: #6c757d; padding: 10px;">
                            ${donation.anonymous ? 'Ẩn danh' : donation.username}
                        </td>
                        <td style="color: #6c757d; padding: 10px;">
                            ${new Intl.NumberFormat('vi-VN').format(donation.moneyDonated)}đ
                        </td>
                        <td style="color: #6c757d; padding: 10px;">
                            ${donation.donateDate}
                        </td>
                    `;
                    tableBody.appendChild(row);
                });

                // Cập nhật phân trang
                const paginationNav = document.getElementById('paginationNav');
                paginationNav.innerHTML = ''; // Xóa phân trang hiện tại
                let totalPages = data.totalPages;

                // Previous Button
                const prevButton = document.createElement('li');
                prevButton.classList.add('page-item');
                if (currentPage === 0) prevButton.classList.add('disabled');
                prevButton.innerHTML = `
                    <a class="page-link" href="#" onclick="loadPage(${Math.max(0, currentPage - 1)})">Trước</a>
                `;
                paginationNav.appendChild(prevButton);

                // Page Number Buttons
                for (let i = 0; i < totalPages; i++) {
                    const pageButton = document.createElement('li');
                    pageButton.classList.add('page-item');
                    if (currentPage === i) pageButton.classList.add('active');
                    pageButton.innerHTML = `
                        <a class="page-link" href="#" onclick="loadPage(${i})">${i + 1}</a>
                    `;
                    paginationNav.appendChild(pageButton);
                }

                // Next Button
                const nextButton = document.createElement('li');
                nextButton.classList.add('page-item');
                if (currentPage === totalPages - 1) nextButton.classList.add('disabled');
                nextButton.innerHTML = `
                    <a class="page-link" href="#" onclick="loadPage(${Math.min(totalPages - 1, currentPage + 1)})">Tiếp</a>
                `;
                paginationNav.appendChild(nextButton);
            });
    }

    // Load page 0 initially
    loadPage(0);
});
