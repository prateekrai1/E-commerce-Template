import axios from "axios";

const API_CLIENT = axios.create({
    baseURL:"http://localhost:9090",
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With',
        'Access-Control-Allow-Credentials': 'true'
    }
})

export const allusers = () => API_CLIENT.get("/user/allUsers")
export const allProducts = () => API_CLIENT.get("/products/allProducts")