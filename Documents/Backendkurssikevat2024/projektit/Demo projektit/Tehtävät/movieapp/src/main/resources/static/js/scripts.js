document.addEventListener('DOMContentLoaded', function () {
    const toggleButton = document.getElementById('toggleMovieForm');
    const formContainer = document.getElementById('movieFormContainer');
    const movieForm = document.querySelector('#movieForm');
    const tableBody = document.querySelector('.table.table-dark.table-striped tbody');

    toggleButton.addEventListener('click', function() {
        if (formContainer.style.display === 'none' || formContainer.dataset.mode === 'edit') {
            formContainer.style.display = 'block';
            document.getElementById('formTitle').textContent = 'Add Movie'; // Ensure title is set to "Add Movie"
            formContainer.dataset.mode = 'add'; // Set mode to "add"
            movieForm.reset(); // Reset the form fields
        } else {
            formContainer.style.display = 'none';
        }
    });

    movieForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(this);
        const movieId = formData.get('movieId'); // Get the movieId from the form
        const isEditMode = formContainer.dataset.mode === 'edit';
        const endpoint = isEditMode ? `/api/movies/${movieId}` : '/api/movies/addMovie';
        const methodType = isEditMode ? 'PUT' : 'POST';
        const json = JSON.stringify(Object.fromEntries(formData));

        fetch(endpoint, {
            method: methodType,
            headers: {
                'Content-Type': 'application/json',
            },
            body: json
        })
        .then(response => {
            if (!response.ok) throw new Error('Server responded with an error');
            return response.json();
        })
        .then(movie => {
            console.log('Received movie data:', movie);
            // Close the form container after updating or adding
            formContainer.style.display = 'none'; // Hide the form container

            const noMoviesRow = document.querySelector('.no-movies');
            if (noMoviesRow) {
                noMoviesRow.remove();
            }


            if (isEditMode) {
                const row = document.querySelector(`#movie-${movie.id}`);
                if (row) {
                    row.innerHTML = createRowInnerHTML(movie);
                } else {
                    console.error('Row not found for movie ID:', movie.id);
                }
            } else {
                const newRow = tableBody.insertRow();
                newRow.id = `movie-${movie.id}`;
                newRow.innerHTML = createRowInnerHTML(movie);
            }

            formContainer.removeAttribute('data-mode');
            document.getElementById('formTitle').textContent = 'Add Movie';
            movieForm.reset();
        })
        .catch(error => console.error('Error:', error));
    });

    function createRowInnerHTML(movie) {
        return `
            <td>${movie.title || ''}</td>
            <td>${movie.genre || ''}</td>
            <td>${movie.releaseDate || ''}</td>
            <td>${movie.director || ''}</td>
            <td>${movie.duration || ''}</td>
            <td>${movie.description || ''}</td>
            <td>
                <button class="btn btn-warning edit-button" data-id="${movie.id}">Edit</button>
                <button class="btn btn-danger delete-button" data-id="${movie.id}">Delete</button>
            </td>
        `;
    }
    

    tableBody.addEventListener('click', function(e) {
        if (e.target.classList.contains('delete-button')) {
            deleteMovieHandler(e);
        } else if (e.target.classList.contains('edit-button')) {
            editMovieHandler(e);
        }
    });
});

function deleteMovieHandler(event) {
    event.preventDefault();
    const button = event.target;
    const movieId = button.dataset.id;

    fetch(`/api/movies/${movieId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) throw new Error(`Error deleting movie: ${response.statusText}`);
        const row = button.closest('tr');
        row.remove();
    })
    .catch((error) => console.error('Error:', error));
}

function editMovieHandler(event) {
    event.preventDefault();
    const button = event.target;
    const movieId = button.dataset.id;

    fetch(`/api/movies/${movieId}`)
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(movie => {
            const movieForm = document.querySelector('#movieForm');
            const formContainer = document.getElementById('movieFormContainer');
            const formTitle = document.getElementById('formTitle');
            const submitButton = movieForm.querySelector('button[type="submit"]');

            movieForm.querySelector('#movieId').value = movie.id;
            movieForm.querySelector('#title').value = movie.title;
            movieForm.querySelector('#genre').value = movie.genre;
            movieForm.querySelector('#releaseDate').value = movie.releaseDate;
            movieForm.querySelector('#director').value = movie.director;
            movieForm.querySelector('#duration').value = movie.duration;
            movieForm.querySelector('#description').value = movie.description;

            formTitle.textContent = 'Edit Movie';
            submitButton.textContent = 'Update Movie';
            submitButton.classList.remove('btn-success');
            submitButton.classList.add('btn-primary');

            formContainer.style.display = 'block';
            formContainer.dataset.mode = 'edit';
        })
        .catch(error => console.error('Error:', error));
}
function rateMovie(movieId, rating) {
    fetch(`/api/reviews/${movieId}/rate`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ rating: rating })
    })
    .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
    })
    .then(data => {
        console.log('Rating submitted:', data);
        // Update the UI based on the success response
    })
    .catch(error => console.error('Error submitting rating:', error));
}
