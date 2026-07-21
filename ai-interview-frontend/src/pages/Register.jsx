import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../services/authService";
import { Link } from "react-router-dom";

function Register() {

    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleRegister = async () => {

        try {

            await register({
                name,
                email,
                password,
                role: "USER"
            });

            alert("Registration Successful");

            navigate("/");

       } catch (error) {

            console.error(error);

            if (error.response?.status === 409) {
                alert("Email already exists");
            } else if (error.response?.data?.message) {
                alert(error.response.data.message);
            } else {
                alert("Registration Failed");
            }
        }
    };

    return (
        <div className="container mt-5">

            <h2>Register</h2>

            <input
                className="form-control mb-3"
                placeholder="Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
            />

            <input
                className="form-control mb-3"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                type="password"
                className="form-control mb-3"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <button
                className="btn btn-success"
                onClick={handleRegister}
            >
                Register
            </button>

            <p className="mt-3">
                Already have an account?{" "}
                <Link to="/">Login</Link>
            </p>

        </div>
    );
}

export default Register;