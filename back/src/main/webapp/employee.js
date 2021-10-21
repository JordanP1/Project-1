function buildTable(data) {
    var table = document.getElementById('reimbursement-items');
    var tableProcessed = document.getElementById('reimbursement-items-processed');

    for (var i = 0; i < data.length; i++) {
        const tr = document.createElement("tr");

        var obj = data[i];

        if (obj['submitterId'] != localStorage.getItem('login_id')) { continue; }

        for (var key in obj) {
            var value = obj[key];

            const td = document.createElement("td");
            td.textContent = value;
            tr.appendChild(td);
        }

        if (obj['status'] == 'Pending') {
            table.appendChild(tr);
        } else {
            tableProcessed.appendChild(tr);
        }
    }
}

fetch('http://localhost:8080/back/api/reimbursements')
          .then(res => res.json())
          .then(data => { buildTable(data) })
          .catch(error => console.log(error))

const logoutBtn = document.getElementById('logout-btn-id');

logoutBtn.addEventListener('click', function (e) {
    e.preventDefault();

    localStorage.removeItem('login_email');
    localStorage.removeItem('login_id');
    localStorage.removeItem('is_employee');

    window.location.href = "/back";
});

const submitForm = document.getElementById('reimbursement-form-id');

submitForm.addEventListener('submit', function (e) {
    e.preventDefault();

    var amt = document.getElementById('amount').value;
    var subId = localStorage.getItem('login_id');
    var subEmail = localStorage.getItem('login_email');
    var stat = "Pending";

    if (amt > 0) {
        const data = { submitterId: subId, submitterEmail: subEmail, amount: amt, status: stat };

        fetch('http://localhost:8080/back/api/reimbursements', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
        })
            .then(response => {
                response.json()
            })
            .then(data => {
                console.log('Success:', data);
                window.location.reload();
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
});