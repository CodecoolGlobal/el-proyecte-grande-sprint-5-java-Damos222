import React, { useEffect, useState } from 'react'

const MyBookings = () => {
    const [bookings, setBookings] = useState([])
    useEffect(() => {
        fetch('http://localhost:8080/account/bookings', {
            headers:
            {
                Authorization: "Bearer " + localStorage.getItem('token')
            }
        })
            .then(res => res.json())
            .then(data => setBookings(data))
    }, [])

    if (bookings && bookings.length > 0) {
        console.log(bookings)
        return (
            bookings.map((booking) => {
                //this is provisional. Needs styling.
                return (
                    <div key={booking.id}>
                        <p>id: {booking.id}</p>
                        <p>timestamp: {booking.timestamp}</p>
                        <p>startDate: {booking.startDate}</p>
                        <p>endDate: {booking.endDate}</p>
                    </div>
                )
            })
        )
    } else {
        return (
            <div>No bookings</div>
        )
    }
}

export default MyBookings