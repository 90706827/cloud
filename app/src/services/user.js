import request from '@/utils/request';
export async function query() {
  return request('/api/users');
}
export async function queryCurrent() {
  return request('/organization/currentUser');
}
export async function queryNotices() {
  return request('/organization/notices');
}
