document.addEventListener('DOMContentLoaded', function () {
    const movieForm = document.querySelector('#movieForm');

    if (movieForm) {
        movieForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const formData = new FormData(movieForm);
            const json = JSON.stringify(Object.fromEntries(formData));

            fetch('/api/movies', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // Add CSRF token if needed here
                },
                body: json
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok.');
                }
                return response.json();
            })
            .then(movie => {
                // Assuming 'movie' is the newly added movie object with all its details
                const table = document.querySelector('.movie-list-table tbody');
                const row = table.insertRow();
                row.innerHTML = `
                    <td>${movie.title}</td>
                    <td>${movie.genre}</td>
                    <td>${movie.releaseDate}</td>
                    <td>${movie.director}</td>
                    <td>${movie.duration}</td>
                    <td>${movie.description}</td>
                `;
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        });
    }
});
