import http from '@/utils/axios';
import qs from 'qs'

export interface LoginParamsType {
  username: string;
  password: string;
};
export type MobileLoginParamsType = {
  mobile: string;
};

enum Api {
  account_login = '/authorization/oauth/login'
}

export async function httpAccountLogin(params: LoginParamsType) {
  localStorage.removeItem('Token');
  return http.request({
    url: Api.account_login,
    method: 'POST',
    headers: {
      'Authorization': 'Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ='
    },
    params,
  }, { joinParamsToUrl: false });
}

export async function apiGetVerifyCode(params: MobileLoginParamsType) {
  return http.request({
    url: Api.account_login,
    method: 'POST',
    data: params,
  });
}

export async function getFakeCaptcha(mobile: string) {
  return http.request({
    url: Api.account_login,
    method: 'POST',
    data: { mobile: mobile },
  });
}

