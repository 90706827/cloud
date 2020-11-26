import request from '@/utils/request';

export interface LoginParamsType {
  username: string;
  password: string;
  mobile: string;
  captcha: string;
  grant_type: string;
  scope: string;
}

export async function fakeAccountLogin(params: LoginParamsType) {
  params.scope = 'read'
  params.grant_type = 'password'
  
  return request('/authorization/oauth/token', {
    method: 'POST',
    params
  });
}

export async function getFakeCaptcha(mobile: string) {
  return request(`/api/login/captcha?mobile=${mobile}`);
}
