import { useEffect } from "react";
import { Platform } from "react-native";
import NetInfo from "@react-native-community/netinfo";
import { onlineManager } from "@tanstack/react-query";

export const useOnlineManager = () => {
  useEffect(() => {
    if (Platform.OS === "web") return;

    return NetInfo.addEventListener((state) => {
      onlineManager.setOnline(
        state.isConnected !== null &&
          state.isConnected &&
          Boolean(state.isInternetReachable),
      );
    });
  }, []);
};
