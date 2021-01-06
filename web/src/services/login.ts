import http from '@/utils/axios';

export interface LoginParamsType {
  username: string;
  password: string;
  grant_type: string;
  scope: string;

};
export type MobileLoginParamsType = {
  mobile: string;
};

enum Api {
  account_login = '/authorization/oauth/token'
}

export async function httpAccountLogin(params: LoginParamsType) {
  localStorage.removeItem('Token');
  params.grant_type = 'password';
  params.scope = 'read';
  return http.request({
    url: Api.account_login,
    method: 'POST',
    headers: {
      'Authorization': 'Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ='
    },
    params,
  }, { joinParamsToUrl: true });
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

