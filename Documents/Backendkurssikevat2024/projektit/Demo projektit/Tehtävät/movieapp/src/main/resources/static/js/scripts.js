document.addEventListener('DOMContentLoaded', function () {
    const toggleButton = document.getElementById('toggleMovieForm');
    const formContainer = document.getElementById('movieFormContainer');
    const movieForm = document.querySelector('#movieForm');
    const tableBody = document.querySelector('.table.table-dark.table-striped tbody');

    toggleButton.addEventListener('click', function() {
        formContainer.style.display = formContainer.style.display === 'none' ? 'block' : 'none';
    });

    if (movieForm && tableBody) {
        movieForm.addEventListener('submit', function (e) {
            e.preventDefault();
            const formData = new FormData(this);
            const json = JSON.stringify(Object.fromEntries(formData));

            fetch('/api/movies', {
                method: 'POST',
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
                const newRow = tableBody.insertRow();
                newRow.innerHTML = `
                    <td>${movie.title}</td>
                    <td>${movie.genre}</td>
                    <td>${movie.releaseDate}</td>
                    <td>${movie.director}</td>
                    <td>${movie.duration}</td>
                    <td>${movie.description}</td>
                    <td><button class="delete-button" data-id="${movie.id}">Delete</button></td>
                `;
            })
            .catch(error => console.error('Error:', error));
        });

        // Event delegation for delete buttons
        tableBody.addEventListener('click', function(e) {
            if (e.target.classList.contains('delete-button')) {
                deleteMovieHandler(e);
            }
        });
    }
});

function deleteMovieHandler(event) {
    event.preventDefault();
    const button = event.target;
    const movieId = button.dataset.id;
    console.log(`Attempting to delete movie with ID: ${movieId}`); // Debug log

    fetch(`/api/movies/${movieId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Error deleting movie: ${response.statusText}`);
        }
        const row = button.closest('tr');
        row.remove();
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}