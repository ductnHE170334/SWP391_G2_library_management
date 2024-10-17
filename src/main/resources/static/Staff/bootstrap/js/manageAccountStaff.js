let staffIdToDelete;

function openModal(staffId) {
staffIdToDelete = staffId; // Store the ID of the staff to be deleted
document.getElementById("deleteModal").style.display = "block"; // Show the modal
}

function closeModal() {
document.getElementById("deleteModal").style.display = "none"; // Hide the modal
}

document.getElementById("confirmDelete").addEventListener("click", function() {
const form = document.createElement("form");
form.method = "post";
form.action = "/staff/delete/" + staffIdToDelete; // Adjust URL if needed
document.body.appendChild(form);
form.submit(); // Submit the form to delete the staff
});

// Close the modal when the user clicks anywhere outside of it
window.onclick = function(event) {
const modal = document.getElementById("deleteModal");
if (event.target === modal) {
  closeModal();
}
};

function openUpdateStaffModal(staffId, lastName, firstName, email, password, phone, roleId) {
    // Gán giá trị vào các trường trong modal
    document.getElementById('updateStaffId').value = staffId;
    document.getElementById('updateLastName').value = lastName;
    document.getElementById('updateFirstName').value = firstName;
    document.getElementById('updateEmail').value = email;
    document.getElementById('updatePhone').value = phone;
    document.getElementById('updateRoleId').value = roleId;

    // Hiện modal
    document.getElementById('updateStaffModal').style.display = 'block';
}


function closeUpdateStaffModal() {
    document.getElementById('updateStaffModal').style.display = 'none';
}


function openAddStaffModal() {
  document.getElementById("addStaffModal").style.display = "block"; // Hiển thị modal
}

function closeAddStaffModal() {
  document.getElementById("addStaffModal").style.display = "none"; // Đóng modal
}