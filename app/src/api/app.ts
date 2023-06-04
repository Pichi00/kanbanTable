import { string } from "zod";
import { BASE_URL, apiClient } from "./axios";
import {
  TableType,
  TaskType,
  TaskGroupType,
  Tag,
  User,
  TableRole,
} from "./types";
import * as FileSystem from "expo-file-system";
import * as Linking from "expo-linking";
import * as SecureStore from "expo-secure-store";
import * as Sharing from "expo-sharing";

export const getTables = async () => {
  const response = await apiClient.get<TableType[]>("/tables");
  return response.data;
};

export const getTable = async (id: number) => {
  const response = await apiClient.get<TableType>(`/tables/${id}`);
  return response.data;
};

/**
 * Creates a new table with default name and no task groups
 */
export const createTable = async () => {
  const response = await apiClient.post<TableType>("/tables", {
    name: "New Table",
  });

  return response.data;
};

export const deleteTable = async (id: number) => {
  const response = await apiClient.delete<TableType>(`/tables/${id}`);

  return response.data;
};

/**
 * Updated table name
 * @param id table id
 * @param name new table name
 * @returns the updated table
 */
export const updateTableName = async (id: number, name: string) => {
  const response = await apiClient.put<TableType>(`/tables/${id}`, {
    name,
  });

  return response.data;
};

/**
 * Creates a new task group in the given table
 * @param tableId id of the table to create the task group in
 * @returns the created task group
 */
export const createTaskGroup = async (tableId: number) => {
  const response = await apiClient.post<TaskGroupType>(
    `/taskgroups?table=${tableId}`,
    {
      name: "New Task Group",
    },
  );

  return response.data;
};

export const getTaskGroup = async (id: number) => {
  const response = await apiClient.get<TaskGroupType>(`/taskgroups/${id}`);

  return response.data;
};

export const updateTaskGroupName = async (id: number, name: string) => {
  const response = await apiClient.put<TaskGroupType>(`/taskgroups/${id}`, {
    name,
  });

  return response.data;
};

export const deleteTaskGroup = async (id: number) => {
  const response = await apiClient.delete<TaskGroupType>(`/taskgroups/${id}`);

  return response.data;
};

export const createTask = async (taskGroupId: number) => {
  const response = await apiClient.post<TaskType>(
    `/tasks?taskGroup=${taskGroupId}`,
    {
      name: "New Task",
    },
  );

  return response.data;
};

export const getTask = async (id: number) => {
  const response = await apiClient.get<TaskType>(`/tasks/${id}`);

  return response.data;
};

export const updateTask = async ({
  id,
  name,
  description,
}: Partial<TaskType>) => {
  const response = await apiClient.put<TaskType>(`/tasks/${id}`, {
    name,
    description,
  });

  return response.data;
};

export const deleteTask = async (id: number) => {
  const response = await apiClient.delete<TaskType>(`/tasks/${id}`);

  return response.data;
};

export const updateTaskTaskGroup = async (id: number, taskGroupId: number) => {
  const response = await apiClient.put<TaskType>(
    `/tasks/${id}/taskgroups/${taskGroupId}`,
  );

  return response.data;
};

export const createTag = async ({
  tableId,
  ...data
}: {
  name: string;
  color: string;
  tableId: number;
}) => {
  const response = await apiClient.post<Tag>(`/tags?table=${tableId}`, data);

  return response.data;
};

export const downloadPdf = async (tableId: number) => {
  try {
    const { uri, mimeType, headers } = await FileSystem.downloadAsync(
      `${BASE_URL}/pdf/${tableId}`,
      FileSystem.documentDirectory + `table_${tableId}.pdf`,
      {
        headers: {
          "Content-Type": "application/pdf",
          Authorization: `Bearer ${await SecureStore.getItemAsync("token")}`,
        },
      },
    );

    await Sharing.shareAsync(uri, {});
  } catch (error) {
    console.error(error);
  }
};

/**
 * Gets all users
 */
export const getUsers = async () => {
  const response = await apiClient.get<User[]>("/users");

  return response.data;
};

/**
 * Gets a user by id
 * @param id user id
 */
export const getUser = async (id: number) => {
  const response = await apiClient.get<User>(`/users/${id}`);

  return response.data;
};

export const addUserToTable = async ({
  tableId,
  userId,
  role = "USER",
}: {
  tableId: number;
  userId: number;
  role: TableRole;
}) => {
  const response = await apiClient.put<TableType>(
    `/tables/${tableId}/users/${userId}/roles/${role}`,
  );

  return response.data;
};

export const addTagsToTask = async ({
  tagId,
  taskId,
}: {
  tagId: number;
  taskId: number;
}) => {
  console.log(tagId, taskId);
  const response = await apiClient.put<TaskType>(
    `/tasks/${taskId}/tags/${tagId}`,
  );

  return response.data;
};
