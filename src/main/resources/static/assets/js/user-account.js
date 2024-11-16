function deleteUser(userId) {
    if (confirm("Bạn có chắc chắn muốn xóa người dùng này?")) {
        fetch(`/admin/account/${userId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                if (data.status === 200) {
                    alert(data.message || 'Xóa thành công!');
                    location.reload();
                } else if (data.status === 400) {
                    alert(data.message || 'Xóa thất bại: Người dùng không hoạt động!');
                } else {
                    alert(data.message || 'Xóa thất bại!');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Có lỗi xảy ra, vui lòng thử lại sau!');
            });
    }
}