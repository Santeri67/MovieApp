document.addEventListener('DOMContentLoaded', function () {
    const movieForm = document.querySelector('#movieForm');
    const table = document.querySelector('.table table-dark table-striped');

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
                const noMoviesText = document.querySelector('.no-movies');
                if (noMoviesText) {
                    noMoviesText.style.display = 'none';
}
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        });
    }
});
function searchMovies() {
    var input = document.getElementById("searchBox");
    var filter = input.value.toUpperCase();
    var table = document.getElementById("moviesTable");
    var tr = table.getElementsByTagName("tr");

    for (var i = 0; i < tr.length; i++) {
        var td = tr[i].getElementsByTagName("td")[0];  // Assuming the movie title is in the first column
        if (td) {
            var txtValue = td.textContent || td.innerText;
            tr[i].style.display = txtValue.toUpperCase().indexOf(filter) > -1 ? "" : "none";
        }
    }
}

