window.onload = function() {
	home();
}

function home() {
    var x = document.getElementById("content");

    let xhr = new XMLHttpRequest();
    
    xhr.open("GET", "../UserInfo");

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = JSON.parse(xhr.responseText);
            x.innerHTML = `<h1>Welcome ${resp.Name}<br>
            Email: ${resp.Email}<br>
            Username: ${resp.Username}<br>
            Birthday: ${resp.Birthdate}<br>
            Address: ${resp.Address}<br>
            Phone Number: ${resp.Number}<br>
            SSN: ${resp.SSN}<br></h1>`;
        }
    }
    xhr.send();
}

function openReimbursementRequest() {
    var x = document.getElementById("content");
    x.innerHTML = 
        `<form id="reimbursementform">
            <h1>Reimbursement Form</h1>

            <label for="description"><b>Description</b></label>
            <textarea name="description" placeholder="Enter Description..." required></textarea>

            <label for="expensecost"><b>Cost</b></label>
            <input type="number" step="0.01" name="expensecost" min="0" required>

            <label for="file"><b>Upload Document (Optional)</b></label>
            <input type="file" class="file" multiple>
        </form>
        `;
}

function openResolvedReimbursements() {
    var x = document.getElementById("content");
    x.innerHTML = `<h1>Resolved</h1>`;
}

function openPendingReimbursements() {
    var x = document.getElementById("content");
    x.innerHTML = `<h1>Pending</h1>`;
}

var dropdown = document.getElementsByClassName("dropdown");
var i;

for (i = 0; i < dropdown.length; i++) {
    dropdown[i].addEventListener("click", function () {
        var dropdowncontent = this.nextElementSibling;
        if (dropdowncontent.style.display === "block") {
            dropdowncontent.style.display = "none";
        } else {
            dropdowncontent.style.display = "block";
        }
    });
}
