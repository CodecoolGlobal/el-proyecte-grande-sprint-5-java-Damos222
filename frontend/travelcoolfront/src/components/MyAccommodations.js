import React, { useEffect, useState } from 'react'
import AccommodationOverview from './AccommodationOverview'

const MyAccommodations = ({ setShowLoginModal }) => {
    const [accommodations, setAccommodations] = useState([])
    const requestController = new AbortController()

    const fetchAccommodations = () => {
        return fetch("http://localhost:8080/account/accommodations", {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("token")
            }
        })
            .then((response) => {
                if (!response.ok) {
                    localStorage.removeItem("token")
                    setShowLoginModal(true)
                    requestController.abort()
                } else {
                    return response.json()
                }
            })
            .then(data => {
                console.log(data)
                setAccommodations(data);
            });
    }

    useEffect(() => {
        fetchAccommodations();
    }, [])

    if (accommodations && accommodations.length > 0) {
        console.log(accommodations)
        return (
            <>
                {accommodations.map((accommodation) => {
                    return (
                        <AccommodationOverview accommodation={accommodation}></AccommodationOverview>
                    )
                })}
            </>
        )
    } else {
        console.log(accommodations)
        return (
            <h2>No accommodations are linked to your account.</h2>
        )
    }
}

export default MyAccommodations