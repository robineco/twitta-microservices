import { BaseResponseModel } from '@/api/models/BaseResponseModel';

export interface LoginResponseModel extends BaseResponseModel {
  token: string;
}
