function buildTable(data) {
    var table = document.getElementById('reimbursement-items');
    var tableProcessed = document.getElementById('reimbursement-items-processed');

    for (var i = 0; i < data.length; i++) {
        const tr = document.createElement("tr");

        var obj = data[i];

        for (var key in obj) {
            var value = obj[key];

            const td = document.createElement("td");
            td.textContent = value;
            tr.appendChild(td);
        }

        if (obj['status'] == 'Pending') {
            const loginEmail = localStorage.getItem('login_email');
            const loginId = localStorage.getItem('login_id');

            const acceptBtn = document.createElement("button");
            acceptBtn.textContent = "Accept";
            acceptBtn.setAttribute('data-id', obj['id']);

            acceptBtn.addEventListener('click', function (e) {
                e.preventDefault();
            
                var rid = e.target.getAttribute('data-id');
                const data = { id: rid, submitterId: -2, resolverId: loginId, resolverEmail: loginEmail, status: "Approved" };

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
            });
            tr.appendChild(acceptBtn);
    
            const rejectBtn = document.createElement("button");
            rejectBtn.textContent = "Reject";
            rejectBtn.setAttribute('data-id', obj['id']);

            rejectBtn.addEventListener('click', function (e) {
                e.preventDefault();
            
                var rid = e.target.getAttribute('data-id');
                const data = { id: rid, submitterId: -2, resolverId: loginId, resolverEmail: loginEmail, status: "Rejected" };

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
            });
            tr.appendChild(rejectBtn);
        }

        if (obj['status'] == 'Pending') {
            table.appendChild(tr);
        } else {
            tableProcessed.appendChild(tr);
        }
    }
}

function buildUsers(data) {
    var table = document.getElementById('user-items');

    for (var i = 0; i < data.length; i++) {
        const tr = document.createElement("tr");

        var obj = data[i];

        // const newData = { id: obj['id'], email: obj['email'], firstName: obj['firstName'], lastName: obj['lastName'], isEmployee: obj['isEmployee'] };

        // for (var i2 = 0; i2 < newData.length; i2++) {
        //     var obj2 = data[i2];

        //     for (var key in obj2) {
        //         var value = obj2[key];
    
        //         const td = document.createElement("td");
        //         td.textContent = value;
        //         tr.appendChild(td);
        //     }
        // }

        var id = obj['id'];
        var email = obj['email'];
        var firstName = obj['firstName'];
        var lastName = obj['lastName'];
        var isEmployee = obj['isEmployee'];

        const td = document.createElement("td");
        td.textContent = id;
        tr.appendChild(td);

        const td2 = document.createElement("td");
        td2.textContent = email;
        tr.appendChild(td2);

        const td3 = document.createElement("td");
        td3.textContent = firstName;
        tr.appendChild(td3);

        const td4 = document.createElement("td");
        td4.textContent = lastName;
        tr.appendChild(td4);

        const td5 = document.createElement("td");
        
        var typeStr;
        if (isEmployee) {
            typeStr = "Employee";
        } else {
            typeStr = "Manager";
        }

        td5.textContent = typeStr;
        tr.appendChild(td5);

        table.appendChild(tr);
    }
}

fetch('http://localhost:8080/back/api/reimbursements')
          .then(res => res.json())
          .then(data => { buildTable(data) })
          .catch(error => console.log(error))

fetch('http://localhost:8080/back/api/users')
          .then(res => res.json())
          .then(data => { buildUsers(data) })
          .catch(error => console.log(error))

const logoutBtn = document.getElementById('logout-btn-id');

logoutBtn.addEventListener('click', function (e) {
    e.preventDefault();

    localStorage.removeItem('login_email');
    localStorage.removeItem('login_id');
    localStorage.removeItem('is_employee');

    window.location.href = "/back";
});