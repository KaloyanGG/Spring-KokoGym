async function fillHtml() {

    const response = await fetch('http://localhost:8080/api/groupWorkouts/all');
    const data = await response.json();

    console.log(data);
    const groupWorkoutSection = document.getElementById('groupWorkoutSection');

    data.forEach(workout => {
        const groupWorkoutCardDiv = document.createElement('div');
        groupWorkoutCardDiv.className = 'groupWorkouts-card';
        groupWorkoutCardDiv.innerHTML = `
         <img class="trainer-img"
          src="${workout.trainer.userDTO.imageUrl}"
          alt="Pic not found">
         <div class="groupWorkoutInfo">
             <h1>${workout.name}</h1>
             <h3 style="opacity:0.7">${workout.purpose}</h3>
             <p>${workout.description}</p>
             <h3>Trainer: ${workout.trainer.userDTO.fullName}</h3>
         </div>
        `
        groupWorkoutSection.appendChild(groupWorkoutCardDiv);
    })
}
// fillHtml();
