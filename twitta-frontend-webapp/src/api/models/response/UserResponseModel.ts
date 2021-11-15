import { BaseResponseModel } from '@/api/models/BaseResponseModel';

export interface UserResponseModel extends BaseResponseModel {
  userId: number;
  username: string;
  email: string;
  messages: [];
}
