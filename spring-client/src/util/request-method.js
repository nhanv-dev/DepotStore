import axios from "axios";
import { getItem } from "./localStorage";

export const baseApp = process.env.REACT_APP_SPRING_URL || "http://localhost:8080/";
export const baseURL = baseApp + "api/";

console.warn(process.env.REACT_APP_SPRING_URL, baseApp, baseURL);

export const publicApp = (url) => {
    if (!url) return url;

    if (url.startsWith("/"))
        return baseApp.slice(0, -1) + url;

    return baseApp + url;
}

export const publicRequest = () => {
    return axios.create({
        baseURL: baseURL,
    });
}

export const protectedRequest = () => {
    const token = getItem("user")?.token;
    return axios.create({
        baseURL: baseURL,
        headers: { Authorization: `Bearer ${token}` },
    });
}
