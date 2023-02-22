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

    if (bookings.length > 0) {
        return (
            <div>
                {bookings}
            </div>
        )
    } else {
        return <div>No bookings</div>
    }
}

export default MyBookings