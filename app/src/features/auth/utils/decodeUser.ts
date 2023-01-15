import jwt_decode from "jwt-decode";

export type JwtDecoded = {
  id: string;
  email: string;
  name: string;
  sub: string;
  iat: number;
  exp: number;
};

export type JwtUser = {
  id: string;
  email: string;
  name: string;
};

export const decodeUser = (token: string): JwtUser => {
  const decoded = jwt_decode(token) satisfies JwtDecoded;

  console.log(decoded);

  const { id, sub, name } = decoded;

  return {
    id,
    email: sub,
    name,
  };
};
