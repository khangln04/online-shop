<%-- 
    Document   : returnPayment
    Created on : 27 Feb 2024, 11:24:13
    Author     : DoanManhTai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment Information</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
                background-color: white;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ddd;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h2>Payment Information</h2>
        <table id="table-container">
            <tr>
                <th>Field</th>
                <th>Value</th>
            </tr>
            <tr>
                <td>Amount</td>
                <td>${paymentReturn.vnpAmount} VND</td>
            </tr>
            <tr>
                <td>Bank Code</td>
                <td id="vnpBankCode">${paymentReturn.vnpBankCode}</td>
            </tr>
            <tr>
                <td>VNP Bank Transaction No</td>
                <td id="vnpBankTranNo">${paymentReturn.vnpBankTranNo}</td>
            </tr>
            <tr>
                <td>Card Type</td>
                <td id="vnpCardType">${paymentReturn.vnpCardType}</td>
            </tr>
            <tr>
                <td>Order Info</td>
                <td id="vnpOrderInfo">${paymentReturn.vnpOrderInfo}</td>
            </tr>
            <tr>
                <td>Pay Date</td>
                <td id="vnpPayDate">${paymentReturn.vnpPayDate}</td>
            </tr>
            <tr>
                <td>Status</td>
                <td style="color: red;">${paymentReturn.vnpTransactionStatus == "00" ? "Payment success" : "Payment Failed"}</td>
            </tr>
            <tr>
                <td>VNP Transaction No</td>
                <td id="vnpTransactionNo">${paymentReturn.vnpTransactionNo}</td>
            </tr>
            <tr>
                <td>VNP Transaction Status</td>
                <td id="vnpTransactionStatus">${paymentReturn.vnpTransactionNo}</td>
            </tr>
        </table>
        <button onclick="exportTableToImage()">ExportToImage</button>
        <a href="OrderHistoryServlet">HistoryOder</a>
        <a href="home">Home</a>

        <script>
            // Assume paymentReturn is the object containing payment information
            var paymentReturn = {
                vnpAmount: "10000000",
                vnpBankCode: "NCB",
                vnpBankTranNo: "VNP14306715",
                vnpCardType: "ATM",
                vnpOrderInfo: "Thanh toan don hang:638445898689528670",
                vnpPayDate: "20240227001833",
                vnpResponseCode: "00",
                vnpTmnCode: "YZ7BM6BL",
                vnpTransactionNo: "14306715",
                vnpTransactionStatus: "00",
                vnpTxnRef: "638445898689528670",
                vnpSecureHash: "1c8d0c59530aa49d8c8334b9242cc21c8215a4d3c806fbffbee06605c8290c8bc79983cf17ee23c77b158a06007abdd34022d8e91894951d08cc018350e9a32d"
            };

            // Update HTML elements with payment information
            document.getElementById("vnpAmount").textContent = paymentReturn.vnpAmount;
            document.getElementById("vnpBankCode").textContent = paymentReturn.vnpBankCode;
            document.getElementById("vnpBankTranNo").textContent = paymentReturn.vnpBankTranNo;
            document.getElementById("vnpCardType").textContent = paymentReturn.vnpCardType;
            document.getElementById("vnpOrderInfo").textContent = paymentReturn.vnpOrderInfo;
            document.getElementById("vnpPayDate").textContent = paymentReturn.vnpPayDate;
            document.getElementById("vnpResponseCode").textContent = paymentReturn.vnpResponseCode;
            document.getElementById("vnpTmnCode").textContent = paymentReturn.vnpTmnCode;
            document.getElementById("vnpTransactionNo").textContent = paymentReturn.vnpTransactionNo;
            document.getElementById("vnpTransactionStatus").textContent = paymentReturn.vnpTransactionStatus;
            document.getElementById("vnpTxnRef").textContent = paymentReturn.vnpTxnRef;
            document.getElementById("vnpSecureHash").textContent = paymentReturn.vnpSecureHash;
        </script>
    </body>
</html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
<script>
            function exportTableToImage() {
                console.log("clicked");
                var tableContainer = document.getElementById('table-container');
                html2canvas(tableContainer, {
                    background: undefined, // Set background to undefined for transparency
                    onrendered: function (canvas) {
                        // Convert canvas to base64 image
                        var imgData = canvas.toDataURL('image/png');

                        // Create a link with download attribute
                        var link = document.createElement('a');
                        link.href = imgData;
                        link.download = 'PaymentInformation.png'; // Default file name
                        link.click();
                    }
                });
            }
</script>

