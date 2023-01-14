import axios from "axios";
import * as SecureStore from "expo-secure-store";

const apiClient = axios.create({
  baseURL: "http://192.168.1.229:8080",
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
