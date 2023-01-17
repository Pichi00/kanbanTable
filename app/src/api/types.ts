export type TableRole = "OWNER" | "ADMIN" | "USER";
export type Priority = "LOW" | "MEDIUM" | "HIGH";

export type TableType = {
  id: number;
  name: string;
  taskGroups: TaskGroupType[];
  tags: Tag[];
  userTableRoles: UserTableRole[];
};

export type TaskGroupType = {
  id: number;
  name: string;
  tasks: TaskType[];
};

export type Tag = {
  id: number;
  name: string;
  color: string;
};

export type TaskType = {
  id: number;
  name: string;
  description: string;
  tags: Tag[];
  priority: Priority;
  createdDate: string;
  creatorUsername: string;
};

export type UserTableRole = {
  id: number;
  role: TableRole;
  userId: number;
  tableId: number;
};

export type User = {
  id: number;
  name: string;
  email: string;
};
