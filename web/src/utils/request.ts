/**
 * request 网络请求工具
 * 更详细的 api 文档: https://github.com/umijs/umi-request
 */
import { extend } from 'umi-request';
import { notification } from 'antd';
import { history, getDvaApp } from 'umi';

const basicUrl = 'http://service.zgc.com';
const codeMessage = {
  200: '服务器成功返回请求的数据。',
  201: '新建或修改数据成功。',
  202: '一个请求已经进入后台排队（异步任务）。',
  204: '删除数据成功。',
  400: '发出的请求有错误，服务器没有进行新建或修改数据的操作。',
  401: '用户没有权限（令牌、用户名、密码错误）。',
  403: '用户得到授权，但是访问是被禁止的。',
  404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
  406: '请求的格式不可得。',
  410: '请求的资源被永久删除，且不会再得到的。',
  422: '当创建一个对象时，发生一个验证错误。',
  500: '服务器发生错误，请检查服务器。',
  502: '网关错误。',
  503: '服务不可用，服务器暂时过载或维护。',
  504: '网关超时。',
};

/**
 * 异常处理程序
 */
const errorHandler = (error: { response: Response }): Response => {
  const { response } = error;
  console.error('ERROR----------' + error);
  if (response && response.status) {
    const errorText = codeMessage[response.status] || response.statusText;
    const { status, url } = response;

    notification.error({
      message: `请求错误 ${status}: ${url}`,
      description: errorText,
    });
  } else if (!response) {
    notification.error({
      description: '您的网络发生异常，无法连接服务器',
      message: '网络异常',
    });
  }
  return response;
};

/**
 * 配置request请求时的默认参数
 */
const request = extend({
  errorHandler, // 默认错误处理
  credentials: 'include', // 默认请求是否带上cookie
});

// request拦截器, 改变url 或 options.
request.interceptors.request.use((url: any, options: { method: string; body: string | FormData; }) => {

  let token: string | null = sessionStorage.getItem('access_token');

  if ((token === null || token.length === 0) && url !== '/authorization/oauth/token') {
    window.location.href = '/user/login';
    return {};
  }
  const headers = {
    'Content-Type': 'application/json',
    Accept: 'application/json',
    'Access-Control-Allow-Origin': '*',
  };
  console.error('METHOD----' + options.method);
  if (['post', 'put', 'delete'].indexOf(String(options.method)) > -1) {
    if (!(options.body instanceof FormData)) {
      headers['Content-Type'] = 'application/json; charset=utf-8';
      options.body = JSON.stringify(options.body);
    }
  }

  console.error('token:' + token)
  if (token) {
    headers['Authorization'] = `Bearer ${token}`;
    return {
      url: `${basicUrl}${url}`,
      options: { ...options, interceptors: true, headers },
    };
  } else {
    headers['Content-Type'] = 'application/x-www-form-urlencoded'
    headers['Authorization'] = 'Basic dGVzdF9jbGllbnQ6dGVzdF9zZWNyZXQ='
  }
  return {
    url: `${basicUrl}${url}`,
    options: { ...options, interceptors: true, headers },
  };
});

// response拦截器, 处理response
request.interceptors.response.use(async (response: { clone?: any; status?: any; }, options: any) => {
  const { result } = await response.clone().json();
  console.error('RESPONSE-----' +   JSON.stringify(result));
  const { status } = response;
  if (status === 401) {
    notification.error({
      message: '未登录或登录已过期，请重新登录。',
      duration: 3000,
    });
    localStorage.clear();
    setTimeout(() => {
      // history.replace('/login');
      // 跳转登录
      getDvaApp()._store.dispatch({
        type: 'login/logout',
        payload: getDvaApp()._store.getState().global.keycloak,
      });
    }, 3000);
    return false;
  }
  console.error('RESPONSE-----' + JSON.stringify(result));
  return result;
});
export default request;
