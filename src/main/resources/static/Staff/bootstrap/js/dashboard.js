// Tên các tháng
const labels = [
  'January', 'February', 'March', 'April', 'May', 'June',
  'July', 'August', 'September', 'October', 'November', 'December'
];

// Dữ liệu số lượt mượn sách theo tháng
const monthlyData = [[${monthlyBorrowRequests}]];

// Kiểm tra dữ liệu đã được lấy đúng chưa
console.log('Monthly Borrow Requests:', monthlyData);

const data = {
  labels: labels,
  datasets: [
    {
      label: 'Request borrow book',
      backgroundColor: 'blue',
      borderColor: 'blue',
      data: monthlyData,
      tension: 0.4,
    }
  ],
};

// Cấu hình biểu đồ
const config = {
  type: 'line',
  data: data,
};

// Khởi tạo biểu đồ
const canvas = document.getElementById('canvas');
const chart = new Chart(canvas, config);
