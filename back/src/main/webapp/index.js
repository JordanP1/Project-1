function redirectPage(isEmployee) {
    if (isEmployee) {
        redirect = "employee.html";
    } else {
        redirect = "manager.html";
    }

    window.location.href = redirect;
}

localStorage.removeItem('login_email');
localStorage.removeItem('login_id');
localStorage.removeItem('is_employee');
// var cred = localStorage.getItem('is_employee');
// if (cred != null) {
//     redirectPage(cred);
// }

function login(jsonData, email, password) {
    for (i = 0; i < jsonData.length; i++) {
        var obj = jsonData[i];

        var e = obj['email'];
        var p = obj['password'];

        if (email === e && password === p) {
            var employee = obj['isEmployee'];
            var id = obj['id'];
            var redirect;

            localStorage.setItem('login_email', email);
            localStorage.setItem('login_id', id);
            localStorage.setItem('is_employee', employee);

            redirectPage(employee);
        }
    }
}

const loginForm = document.getElementById('login-form-id');

loginForm.addEventListener('submit', function (e) {
    e.preventDefault();

    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    if (email && password) {
        fetch('http://localhost:8080/back/api/users')
          .then(res => res.json())
          .then(data => { login(data, email, password) })
          .catch(error => console.log(error))
    }
});