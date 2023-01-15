import axios from "axios";
import * as SecureStore from "expo-secure-store";
import { API_URL, API_PORT } from "@env";

const apiClient = axios.create({
  baseURL: `${API_URL}:${API_PORT}`,
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
