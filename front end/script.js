// script.js
const invoiceTable = document.getElementById("invoiceTable");
const invoiceDetails = document.getElementById("invoiceDetails");

async function fetchInvoices(page, sortBy) {
    const response = await fetch(`/api/invoices?page=${page}&sortBy=${sortBy}`);
    const data = await response.json();
    return data.content;
}

async function fetchInvoiceDetails(id) {
    const response = await fetch(`/api/invoices/${id}/details`);
    const data = await response.json();
    return data;
}

function displayInvoices(invoices) {
    // Clear existing rows
    invoiceTable.querySelector("tbody").innerHTML = "";

    invoices.forEach(invoice => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${invoice.id}</td>
            <td>${invoice.sender}</td>
            <td>${invoice.receiver}</td>
            <td>${invoice.totalAmount}</td>
            <td><a href="#" data-id="${invoice.id}" class="details-link">View Details</a></td>
        `;
        invoiceTable.querySelector("tbody").appendChild(row);
    });

    // Attach event listeners to details links
    const detailsLinks = document.querySelectorAll(".details-link");
    detailsLinks.forEach(link => {
        link.addEventListener("click", event => {
            event.preventDefault();
            const invoiceId = event.target.getAttribute("data-id");
            displayInvoiceDetails(invoiceId);
        });
    });
}

async function displayInvoiceDetails(id) {
    const details = await fetchInvoiceDetails(id);
    let detailsHtml = `<h2>Invoice Details</h2>`;
    detailsHtml += `<table><thead><tr><th>ID</th><th>Sender</th><th>Receiver</th><th>Total Amount</th><th>Paid Amount</th></tr></thead><tbody>`;
    details.forEach(detail => {
        detailsHtml += `
            <tr>
                <td>${detail.id}</td>
                <td>${detail.sender}</td>
                <td>${detail.receiver}</td>
                <td>${detail.totalAmount}</td>
                <td>${detail.paidAmount}</td>
            </tr>
        `;
    });
    detailsHtml += `</tbody></table>`;
    invoiceDetails.innerHTML = detailsHtml;
}

async function initialize() {
    const invoices = await fetchInvoices(0, "id");
    displayInvoices(invoices);
}

initialize();
