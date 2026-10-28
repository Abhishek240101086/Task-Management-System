const API_URL = "/tasks";

let editingTaskId = null;

const taskForm = document.getElementById("taskForm");
const statusFilter = document.getElementById("statusFilter");


// ==================== LOAD TASKS ====================

async function loadTasks(status = "") {

    const url = status
        ? `${API_URL}?status=${status}`
        : API_URL;

    const response = await fetch(url);

    const tasks = await response.json();

    const tableBody = document.getElementById("taskTableBody");

    tableBody.innerHTML = "";

    tasks.forEach(task => {

        const row = `
            <tr>
                <td>${task.id}</td>
                <td>${task.title}</td>
                <td>${task.description}</td>
                <td>${task.priority}</td>
                <td>${task.status}</td>
                <td>${task.dueDate}</td>

                <td>
                    <button onclick="editTask(${task.id})">
                        Edit
                    </button>

                    <button onclick="deleteTask(${task.id})">
                        Delete
                    </button>
                </td>
            </tr>
        `;

        tableBody.innerHTML += row;
    });
}


// ==================== EDIT TASK ====================

async function editTask(id) {

    const response = await fetch(`${API_URL}/${id}`);

    const task = await response.json();

    document.getElementById("title").value = task.title;

    document.getElementById("description").value =
        task.description;

    document.getElementById("priority").value =
        task.priority;

    document.getElementById("status").value =
        task.status;

    document.getElementById("dueDate").value =
        task.dueDate;

    editingTaskId = id;

    document.querySelector(
        "#taskForm button[type='submit']"
    ).textContent = "Update Task";
}


// ==================== DELETE TASK ====================

async function deleteTask(id) {

    const response = await fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    });

    if (response.ok) {

        loadTasks(statusFilter.value);

    } else {

        alert("Failed to delete task");
    }
}


// ==================== FORM SUBMIT ====================

taskForm.addEventListener("submit", async function (event) {

    event.preventDefault();

    const task = {

        title: document.getElementById("title").value,

        description:
            document.getElementById("description").value,

        priority:
            document.getElementById("priority").value,

        status:
            document.getElementById("status").value,

        dueDate:
            document.getElementById("dueDate").value
    };


    // ==================== UPDATE TASK ====================

    if (editingTaskId !== null) {

        const response = await fetch(
            `${API_URL}/${editingTaskId}`,
            {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(task)
            }
        );


        if (response.ok) {

            taskForm.reset();

            editingTaskId = null;

            document.querySelector(
                "#taskForm button[type='submit']"
            ).textContent = "Add Task";

            loadTasks(statusFilter.value);

        } else {

            const error = await response.json();

            alert(error.error);
        }

        return;
    }


    // ==================== CREATE TASK ====================

    const response = await fetch(API_URL, {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(task)
    });


    if (response.ok) {

        taskForm.reset();

        loadTasks(statusFilter.value);

    } else {

        const error = await response.json();

        alert(error.error);
    }
});


// ==================== STATUS FILTER ====================

statusFilter.addEventListener("change", function () {

    loadTasks(this.value);
});


// ==================== INITIAL LOAD ====================

loadTasks();