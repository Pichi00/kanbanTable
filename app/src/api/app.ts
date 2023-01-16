import { apiClient } from "./axios";
import { TableType, TaskType, TaskGroupType } from "./types";

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

/**
 * Updated table name
 * @param id table id
 * @param name new table name
 * @returns the updated table
 */
export const updateTableName = async (id: number, name: string) => {
  console.log(name);
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

export const createTask = async (taskGroupId: number) => {
  const response = await apiClient.post<TaskType>(
    `/tasks?taskGroup=${taskGroupId}`,
    {
      name: "New Task",
    },
  );

  return response.data;
};
