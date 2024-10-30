let categoryIdToDelete;

function openDeleteModal(categoryId) {
categoryIdToDelete = categoryId;
document.getElementById("deleteModal").style.display = "block";
}

function closeDeleteModal() {
document.getElementById("deleteModal").style.display = "none"; // Hide the modal
}

document.getElementById("confirmDelete").addEventListener("click", function() {
const form = document.createElement("form");
form.method = "post";
form.action = "/admin/dashboard/category/delete/" + categoryIdToDelete; // Use the categoryIdToDelete
document.body.appendChild(form);
form.submit(); // Submit the form to delete the category
});

// Close the modal when the user clicks anywhere outside of it
window.onclick = function(event) {
const modal = document.getElementById("deleteModal");
if (event.target === modal) {
  closeModal();
}
};


function openUpdateuserModal(userId, lastName, firstName, email, password, phone, roleId) {
    // Gán giá trị vào các trường trong modal
    document.getElementById('updateUserId').value = userId;
    document.getElementById('updateLastName').value = lastName;
    document.getElementById('updateFirstName').value = firstName;
    document.getElementById('updateEmail').value = email;
    document.getElementById('updatePhone').value = phone;
    document.getElementById('updateRoleId').value = roleId;

    // Hiện modal
    document.getElementById('updateUserModal').style.display = 'block';
}


function closeUpdateUserModal() {
    document.getElementById('updateUserModal').style.display = 'none';
}


function openAddUserModal() {
  document.getElementById("addUserModal").style.display = "block"; // Hiển thị modal
}

function closeAddUserModal() {
  document.getElementById("addUserModal").style.display = "none"; // Đóng modal
}