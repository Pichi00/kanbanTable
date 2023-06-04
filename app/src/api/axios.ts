import axios from "axios";
import * as SecureStore from "expo-secure-store";
import { API_URL, API_PORT } from "@env";

export const BASE_URL = `http://192.168.1.82:8080`;

const apiClient = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

apiClient.interceptors.request.use(async (config) => {
  const token = await SecureStore.getItemAsync("token");
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  } else {
    config.headers["Authorization"] = "";
  }

  return config;
});

export { apiClient };
