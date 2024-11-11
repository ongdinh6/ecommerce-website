export interface Geography {
  lat: string;
  lng: string;
}

export interface Address {
  street: string;
  suite: string;
  city: string;
  zipcode: string;
  geo: Geography;
}

export interface Company {
  name: string;
  catchPhrase: string;
  bs: string;
}

export interface UserDetail {
  id: string;
  name: string;
  username: string;
  email: string;
  address: Address;
  phone: string;
  website: string;
  company: Company;
}

export interface UserLogin {
  email: string;
  password: string;
}