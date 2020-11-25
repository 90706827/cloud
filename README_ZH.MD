# 本地部署配置
## nginx.conf
```shell script
       ## 前端
       server {
           listen       80;
           server_name  www.zgc.com;
   
           location / {
   			proxy_pass http://localhost:8000;
           }
           error_page   500 502 503 504  /50x.html;
           location = /50x.html {
               root   html;
           }         
       }
   	   ## 服务
       server {
           listen       80;
           server_name  service.zgc.com;
   
           location / {
   			proxy_set_header Origin '';
   			add_header Access-Control-Allow-Credentials true;
   			add_header Access-Control-Allow-Headers $http_access_control_request_headers;
   			add_header Access-Control-Allow-Methods POST,GET,OPTIONS,DELETE,PUT,HEAD,PATCH;
   			add_header Access-Control-Allow-Origin $http_origin;
   			add_header Access-Control-Expose-Headers $http_access_control_request_headers;
   
   			if ($request_method = 'OPTIONS') {
   				return 204;
   			}
   			if ($request_method != 'OPTIONS'){
   				proxy_pass http://localhost:8080;
   			}
           }
           error_page   500 502 503 504  /50x.html;
           location = /50x.html {
               root   html;
           }  
       }
   	  ## 文件
       server {
           listen       80;
           server_name  files.zgc.com;
   
   		location ~ .*\.(gif|jpg|jpeg|png|pdf)$ { 
               expires 24h; 
               root D:/temp/;#指定图片存放路径 
               proxy_store on; 
               proxy_store_access user:rw group:rw all:rw; 
               proxy_temp_path     /files/;#图片访问路径 
               proxy_redirect     off; 
               proxy_set_header    Host 127.0.0.1; 
               client_max_body_size  10m; 
               client_body_buffer_size 1280k; 
               proxy_connect_timeout  900; 
               proxy_send_timeout   900; 
               proxy_read_timeout   900; 
               proxy_buffer_size    40k; 
               proxy_buffers      40 320k; 
               proxy_busy_buffers_size 640k; 
               proxy_temp_file_write_size 640k; 
               if ( !-e $request_filename) 
               { 
                   proxy_pass http://127.0.0.1;#默认80端口 
               } 
           } 
           error_page   500 502 503 504  /50x.html;
           location = /50x.html {
               root   html;
           }
   
             
       }
```
## windows HOST
```shell script
127.0.0.1 www.zgc.com
127.0.0.1 service.zgc.com
127.0.0.1 files.zgc.com
```

## windows nginx.bat
```bat
@ECHO OFF
D:\nginx-1.18.0\nginx.exe
ping 127.0.0.1 -n 1>NUL
echo Starting nginx
echo .
echo .
echo .
ping 127.0.0.1 >NUL
EXIT;
```