import request from '@/utils/request';
export async function fakeAccountLogin(params) {
  sessionStorage.removeItem('access_token');
  return request('/authorization/oauth/token', {
    method: 'POST',
    data: params,
  });
}
export async function getFakeCaptcha(mobile) {
  return request(`/api/login/captcha?mobile=${mobile}`);
}
