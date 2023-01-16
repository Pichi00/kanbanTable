export type TableRole = "OWNER" | "ADMIN" | "USER";

export type TableType = {
  id: number;
  name: string;
  taskGroups: TaskGroupType[];
  userTableRolesId: number[];
};

export type TaskGroupType = {
  id: number;
  name: string;
  tasks: TaskType[];
};

export type Tag = {
  id: number;
  name: string;
};

export type TaskType = {
  id: number;
  name: string;
  tags: Tag[];
};
