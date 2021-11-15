import axios from 'axios';
import { UserModel } from '@/api/models/request/UserModel';
import { UserResponseModel } from '@/api/models/response/UserResponseModel';
import { LoginUserModel } from '@/api/models/request/LoginUserModel';
import { LoginResponseModel } from '@/api/models/response/LoginResponseModel';

// eslint-disable-next-line consistent-return
export const register = async (user: UserModel): Promise<UserResponseModel> => {
  try {
    const response = await axios.post('http://localhost:4000/user', {
      username: user.username,
      email: user.email,
      password: user.password,
    });
    const { data } = response.data;
    return data;
  } catch (e) {
    const { data } = e.response.data;
    return data;
  }
};

export const login = async (user: LoginUserModel): Promise<LoginResponseModel> => {
  try {
    const response = await axios.post('http://localhost:4000/user/login', {
      email: user.email,
      password: user.password,
    });
    const { data } = response.data;
    return data;
  } catch (e) {
    const { data } = e.response.data;
    return data;
  }
};

export default {
  register,
  login,
};
