import http from '@/utils/axios';


enum Api {
  query_users = '/api/users',
  query_current = '/api/currentUser',
  query_notices = '/api/notices',
};
export async function query(): Promise<any> {
  return http.request({
    url: Api.query_users,
    method: 'GET',
  });
}

export async function queryCurrent(): Promise<any> {
  return http.request({
    url: Api.query_current,
    method: 'GET',
  });
}

export async function queryNotices(): Promise<any> {
  return http.request({
    url: Api.query_notices,
    method: 'GET',
  });
}
