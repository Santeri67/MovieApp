document.addEventListener('DOMContentLoaded', function () {

    const movieForm = document.querySelector('form');

    movieForm.addEventListener('submit', function (e) {

        e.preventDefault();

        const title = document.getElementById('title').value;

        const director = document.getElementById('director').value;

        const genre = document.getElementById('genre').value;

        const releaseDate = document.getElementById('releaseDate').value;

        const duration = document.getElementById('duration').value;

        const description = document.getElementById('description').value;



        fetch('/api/movies', {

            method: 'POST',

            headers: {

                'Content-Type': 'application/json',

            },

            body: JSON.stringify({

                title: title,

                director: director,

                genre: genre,

                releaseDate: releaseDate,

                duration: duration,

                description: description

            })

        })

        .then(response => {

            if (response.ok) {

                return response.json();

            }

            throw new Error('Network response was not ok.');

        })

        .then(data => console.log('Success:', data))

        .catch((error) => {

            console.error('Error:', error);

        });

    });

});

  