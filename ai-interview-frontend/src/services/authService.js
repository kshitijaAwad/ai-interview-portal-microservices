import api from "../api/axiosConfig";

export const register = (data) => {
    return api.post("/api/auth/register", data);
};

export const login = (data) => {
    return api.post("/api/auth/login", data);
};