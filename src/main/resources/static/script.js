const API_BASE = 'http://localhost:8080'


function searchFlights() {
    const from = document.getElementById("from").value;
    const to = document.getElementById("to").value;
    const date = document.getElementById("date").value;

    fetch(`${API_BASE}/flights/search?departureCity=${from}&arrivalCity=${to}&date=${date}`)
        .then(response => response.json())
        .then(data => displayFlights(data))
        .catch(error => console.error("Error:", error));
}

function displayFlights(flights) {
    const results = document.getElementById("results");
    results.innerHTML = "";

    if (flights.length === 0) {
        results.innerHTML = "<p>No flights found</p>";
        return;
    }

    flights.forEach(flight => {
        const div = document.createElement("div");
        div.className = "flight-card";
        div.innerHTML = `
            <h3>Flight ${flight.flightNumber}</h3>
            <p>${flight.departureCity} → ${flight.arrivalCity}</p>
            <p>Departure: ${flight.departureTime}</p>
            <p>Arrival: ${flight.arrivalTime}</p>
            <p>Economy price: €${flight.economyClassPrice}</p>
            <button onclick="bookFlight(${flight.idFlight})">Book</button>
        `;
        results.appendChild(div);
    });
}

function bookFlight(idFlight) {
    alert("Booking flight ID " + idFlight + " (next step)");
}
