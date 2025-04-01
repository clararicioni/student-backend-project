var students = [];
var shifts = [];
var courses = [];

//onLoad
loadShift();
loadCourses();
loadStudents();

//Load all students
function loadStudents() {
    $.getJSON("http://localhost:8080/students", (response) => {
        students = response;
        for (let student of students) {
            addNewRow(student);
        }
    })
}

//Load all shift categories
function loadShift() {
    $.ajax({
        url: "http://localhost:8080/shifts",
        type: "GET",
        success: (response) => {
            shifts = response;
            for (var shiftCat of shifts) {
                const turnoId = `pturno${shiftCat.name}`;
                const turnoElement = document.getElementById(turnoId);
                if (turnoElement) {
                    turnoElement.textContent = shiftCat.name;
                }
            }
        }
    });
}

//Load all courses categories
function loadCourses() {
    $.ajax({ //fazendo com que a chamada seja síncrona (na ordem), vai aguardar a execução de todas para executar depois
        url: "http://localhost:8080/courses",
        type: "GET",
        async: false,
        success: (response) => {
            courses = response;
            for (var courseCat of courses) {
                document.getElementById("inputCat").innerHTML += `<option value="${courseCat.id}">${courseCat.name}</option>`;
            }
        }
    })
}

//save a student
function save() {
    var selectedShift = document.querySelector('input[name="exampleRadios"]:checked');
    var newStudent = {
        ra: students.length + 1,
        name: document.getElementById("inputName").value,
        email: document.getElementById("inputEmail").value,
        phone: document.getElementById("inputPhone").value,
        idCourse: document.getElementById("inputCat").value,
        idShift: selectedShift ? parseInt(selectedShift.value) : 1
    };

    $.ajax({
        url: "http://localhost:8080/students",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(newStudent),
        success: (student) => {
            addNewRow(student);
            students.push(student);
            document.getElementById("studentForm").reset();
        }
    });
}
//Add new row
function addNewRow(student) {
    var table = document.getElementById("studentsTable");
    var newRow = table.insertRow();

    //Insert student id
    var raNode = document.createTextNode(student.ra);
    newRow.insertCell().appendChild(raNode);

    //Insert student name
    var nameNode = document.createTextNode(student.name);
    var cell = newRow.insertCell()
    cell.className = 'nome'
    cell.appendChild(nameNode);

    //Insert student email
    var emailNode = document.createTextNode(student.email);
    var cell = newRow.insertCell()
    cell.className = 'd-none d-md-table-cell'
    cell.appendChild(emailNode);

    //Insert student tel
    var telNode = document.createTextNode(student.phone);
    var cell = newRow.insertCell()
    cell.className = 'd-none d-md-table-cell telefone'
    cell.appendChild(telNode);
    //Insert student course
    var courseNode = document.createTextNode(courses[student.idCourse - 1].name);
    var cell = newRow.insertCell()
    cell.className = 'd-none d-md-table-cell'
    cell.appendChild(courseNode);

    //Insert student shift
    var shiftNode = document.createTextNode(shifts[student.idShift - 1].name);
    var cell = newRow.insertCell()
    cell.className = 'd-none d-md-table-cell'
    cell.appendChild(shiftNode);
}
//Telephone mask
$(document).ready(function () {
    $('#inputPhone').mask('(00) 00000-0000');
});