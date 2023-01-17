import { View, Text } from "react-native";
import { useQuery } from "@tanstack/react-query";
import { AppAPI } from "../../../api";
import { User, UserTableRole } from "../../../api/types";
import { useTheme } from "../../../theme";

type Props = {
  userTableRole: UserTableRole;
};

export const UserListItem = ({ userTableRole }: Props) => {
  const { id, userId, role } = userTableRole;

  const { theme } = useTheme();
  const userQuery = useQuery(["user", userId], () =>
    AppAPI.app.getUser(userId),
  );

  if (userQuery.isLoading) return <Text>Loading...</Text>;

  return (
    <View
      style={{
        backgroundColor: theme.colors.surfaceLight,
        padding: theme.spacing.$4,
        borderRadius: theme.radii.$3,
        borderWidth: 2,
        borderColor: theme.colors.surface,
      }}
    >
      <Text
        style={{
          fontSize: theme.fontSizes.body,
        }}
      >
        @
        <Text
          style={{
            fontFamily: theme.fontFamily.title,
          }}
        >
          {userQuery.data.name}
        </Text>{" "}
        ({role})
      </Text>
      <Text
        style={{
          fontFamily: theme.fontFamily.title,
          fontSize: theme.fontSizes.bodySmall,
          color: "#31a7dd",
        }}
      >
        {userQuery.data.email}
      </Text>
    </View>
  );
};
