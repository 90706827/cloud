import http from '@/utils/axios';

export type LoginParamsType = {
  username: string;
  password: string;
};
export type MobileLoginParamsType = {
  mobile: string;
};

enum Api {
  account_login = '/authorization/oauth/token'
}

export async function httpAccountLogin(params: LoginParamsType) {
  localStorage.removeItem('Token');
  return http.request({
    url: Api.account_login,
    method: 'POST',
    data: params,
  });
}

export async function apiGetVerifyCode(params: MobileLoginParamsType) {
  return http.request({
    url: Api.account_login,
    method: 'POST',
    data: params,
  });
}
