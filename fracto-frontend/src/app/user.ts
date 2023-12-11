export class User {
    id!: number;
    username!: string;
    email!: string;
    password!: string;
    roles: Role[] = []; // Assuming Role is another class representing the role structure
}

export class Role {
  id!: number;
  name!: string;
}