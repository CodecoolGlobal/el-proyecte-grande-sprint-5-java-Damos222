import * as React from 'react';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import Button from './Button';
import { useNavigate } from 'react-router-dom';

export default function AccountMenu() {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const navigate = useNavigate()

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    function logout() {
        handleClose()
        localStorage.removeItem("token")
        navigate("/")
        window.location.reload()
    }

    function goToMyAccommodations() {
        handleClose()
        navigate("/account/accommodations")
    }

    function goToMyBookings() {
        handleClose()
        navigate("/account/bookings")
    }

    function goToAddAccommodation() {
        handleClose()
        navigate("/addAccommodation")
    }

    return (
        <div>
            <Button id="demo-positioned-button"
                aria-controls={open ? 'demo-positioned-menu' : undefined}
                aria-haspopup="true"
                aria-expanded={open ? 'true' : undefined}
                onClick={handleClick}
                type={"light"}
                size={"medium"}
                content={"My Account"}>
            </Button>
            <Menu
                id="demo-positioned-menu"
                aria-labelledby="demo-positioned-button"
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'left',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'left',
                }}
            >
                <MenuItem onClick={goToMyAccommodations}>My Accommodations</MenuItem>
                <MenuItem onClick={goToMyBookings}>My Bookings</MenuItem>
                <MenuItem onClick={goToAddAccommodation}>Add Accommodation</MenuItem>
                <MenuItem onClick={logout}>Logout</MenuItem>
            </Menu>
        </div>
    );
}