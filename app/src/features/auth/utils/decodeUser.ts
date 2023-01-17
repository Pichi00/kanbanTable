import jwt_decode from "jwt-decode";

export type JwtDecoded = {
  id: number;
  email: string;
  name: string;
  sub: string;
  iat: number;
  exp: number;
};

export type JwtUser = {
  id: number;
  email: string;
  name: string;
  expiringAt: number;
};

export const decodeUser = (token: string): JwtUser => {
  const decoded = jwt_decode(token) satisfies JwtDecoded;

  const { id, sub, name, exp } = decoded;

  return {
    id,
    email: sub,
    name,
    expiringAt: exp,
  };
};
