
const API_URL = "http://localhost:8080/api/todos";
let tasks = []

async function addTask(){

    const task = document.getElementById("taskInput");
    const title = task.value.trim()

    const newTask = {
        title: title,
        completed: false
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newTask)
        });

        if(response.ok){
            task.value = "";
            await getTasks()
        } else {
            console.error("failed to add task: ", response.status)
        }
    } catch (error) {
        console.error("error adding task: ", error)
    }

}

async function getTasks(){

    try {
       await fetch(API_URL) .then(response => {
           if (response.ok) {
               return response.json();
           } else {
               throw new Error('API request failed');
           }
       })
           .then(data => {
               console.log(data)

               tasks = []

               data.forEach((item) => {

                   tasks.push(item)
                   render()

               });


           })
           .catch(error => {
               console.error(error);
           });
    } catch (error) {
        console.error("error fetching: ", error)
    }

}

function render() {
    let ul = document.getElementById("taskList")
    ul.innerHTML = ""

    const sortedTasks = tasks.slice().sort((a, b) => {
        return a.completed - b.completed
    })

    sortedTasks.forEach(item => {

        let li = document.createElement('li');
        let checkbox = document.createElement("input");
        let titleSpan = document.createElement("span");
        let removeButton = document.createElement("button");

        checkbox.type = "checkbox";
        checkbox.checked = item.completed;
        checkbox.onclick = () => toggleComplete(item.id);
        if (item.completed) {
            li.classList.add("completed");
        }


        titleSpan.innerText = item.title;
        titleSpan.style.flex = "1";

        removeButton.innerText = "-";
        removeButton.onclick = () => removeTask(item.id);

        li.appendChild(checkbox);
        li.appendChild(titleSpan);
        li.appendChild(removeButton);
        ul.appendChild(li);


    })
}

async function removeTask(id){

    const task = tasks.find(t => t.id === id)

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(task)
        });

        if(response.ok){

            tasks.filter((item) => item.id !== id)

            await getTasks()
        } else {
            console.error("failed to update task")
        }
    } catch(error){
        console.error("failed to fetch updated task: ", error)
    }


}

async function toggleComplete(id){

    const task = tasks.find(t => t.id === id)

    const updatedTask = {
        ...task,
        completed: !task.completed
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedTask)
        });

        if(response.ok){
            await getTasks()
            //render()
        } else {
            console.error("failed to add task: ", response.status)
        }
    } catch (error) {
        console.error("error adding task: ", error)
    }

    //render()

}

